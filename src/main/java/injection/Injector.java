package injection;

import exceptions.DependencyInjectionException;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.*;

/**
 * @author almtn
 */
@Configuration(packages = {"sorting.sorters","validation.validators"})
public class Injector {
    private static final String rootPath = "src/main/java/";

    /**
     * Gets all classes from packages available in {@link Configuration} annotation
     * @return classes from packages
     */
    private static List<Class<?>> getClassesFromPackages() {
        //получаем пакеты из аннотации Configuration
        String[] pkgsForSearch = Injector.class.getAnnotation(Configuration.class).packages();
        //создаем список директорий
        List<File> directories = new ArrayList<File>();
        //заполняем список директорий файлами директорий
        for (String str : pkgsForSearch) {
            directories.add(new File(rootPath + str.replace('.', '/')));
        }
        //создаем список адресов файлов лежащих в директориях
        List<String> files = new ArrayList<String>();
        //проходим по каждой директории
        for (File dir : directories) {
            //получаем список файлов в директории а если их нет то переходим к следующей
            File[] filesInDir = dir.listFiles();

            if (filesInDir == null)
                continue;
            //проходимся по файлам в директории
            for (File file : filesInDir) {
                //получаем адрес файла
                String filePath = file.getPath();
                //если адрес файла является адресом .java файла то добавляем его в список
                if (filePath.length() > 5 && filePath.substring(filePath.length() - 5).equals(".java")) {
                    //отрезаем у адреса файла адрес корневой части и расширение файла
                    files.add(filePath.substring(rootPath.length(), filePath.length() - 5).replace('\\', '.'));
                }
            }
        }
        //создаем список классов
        List<Class<?>> classes = new ArrayList<Class<?>>();
        //заполняем
        for (String path : files) {
            try {
                classes.add(Class.forName(path));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return classes;
    }

    /**
     * Gets all fields with {@link AutoInjectable} annotation and available for dependency injection from object
     * @param object object for fields retrieving
     * @return matching fields from object
     */
    public static List<Field> getFieldsToInject(Object object) {
        //считываем все поля инжектируемого объекта
        var injectedObjectfields = object.getClass().getDeclaredFields();
        //создаем список полей в которые мы хотим произвести инъекцию
        List<Field> fieldsToInject = new ArrayList<Field>();
        //перебираем все поля
        for (Field field : injectedObjectfields) {
            //считываем все аннотации поля
            Annotation[] fieldAnnotation = field.getAnnotations();

            //проверяем есть ли у поля аннотации
            if (fieldAnnotation.length > 0)
                //перебираем все аннотации поля
                for (Annotation annotation : fieldAnnotation)
                    //если аннотация приводима к AutoInjectable о добавляем поле с ней в список
                    if (AutoInjectable.class.isAssignableFrom(annotation.getClass()))
                        fieldsToInject.add(field);
        }
        return fieldsToInject;
    }

    /**
     * Gets all classes from packages available in {@link Configuration} annotation and injects their objects to
     * object fields
     * @param object object for injection
     * @param <T> object type
     * @return object with injected dependencies
     * @throws DependencyInjectionException if there was no
     * or there was more than one class for injecting their object into the field
     * or if you have non-unique classes in {@link Configuration} packages
     */
    public static <T> T inject(T object) throws DependencyInjectionException {
        //получаем классы для инъекции 
        var classes = getClassesFromPackages();
        if ((new HashSet<Class<?>>(classes)).size() != classes.size())
            throw new DependencyInjectionException("Non-unique classes in @Configuration packages");
        //получаем поля для инъекции
        var fields = getFieldsToInject(object);
        //создаем множество полей-коллекций
        Set<Field> collectionFields = new HashSet<>();
        for (Field field : fields) {
            //если класс является коллекцией то переносим его в список полей-коллекций
            if (Collection.class.isAssignableFrom(field.getType())) {
                collectionFields.add(field);
            }
        }
        //проходим по всем полям
        for (Field field : fields) {
            //делаем поле доступным
            field.setAccessible(true);

            //если поле не является коллекцией
            if (!collectionFields.contains(field)) {
                //классы подходящие для инъекции своих объектов в данное поле
                List<Class<?>> suitableClasses = new ArrayList<Class<?>>();
                Class<?> fieldClass = field.getType();
                for (Class<?> clazz : classes) {
                    //если находим класс для инъекции схожий с классом поля
                    if (fieldClass.isAssignableFrom(clazz)) {
                        //добавляем в suitableClasses
                        suitableClasses.add(clazz);
                    }
                }
                if (suitableClasses.size() < 1)
                    throw new DependencyInjectionException("Class for injecting his object to the field '" + field.toString() + "' was not found");
                else if (suitableClasses.size() > 1)
                    throw new DependencyInjectionException(suitableClasses.toString() + " classes are equally suitable for injection their objects to the field '" + field.toString() + "'");
                else
                    try {
                        field.set(object, suitableClasses.get(0).getDeclaredConstructor().newInstance());
                    } catch (IllegalAccessException | NoSuchMethodException | InstantiationException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
            }
            //если поле есть в множестве полей-коллекций
            else {
                Class<?> fieldGenericClass = null;
                if (ParameterizedType.class.isAssignableFrom(field.getGenericType().getClass()))
                    //получаем дженерик поля-коллекции
                    fieldGenericClass = (Class<?>) ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0];
                else
                    fieldGenericClass = Object.class;
                //получаем класс коллекции поля
                Class<?> fieldCollectionType = null;
                try {
                    fieldCollectionType = field.get(object).getClass();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                //создаем пустой объект этого класса коллекции
                Collection<Object> temp = null;
                try {
                    assert fieldCollectionType != null;
                    temp = (Collection<Object>) fieldCollectionType.getDeclaredConstructor().newInstance();
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                    e.printStackTrace();
                }
                assert temp != null;
                //перебираем классы для инъекции
                for (Class<?> clazz : classes) {
                    //если находим класс для инъекции схожий с дженериком поля-коллекции
                    if (fieldGenericClass.isAssignableFrom(clazz)) {
                        try {
                            temp.add(clazz.getDeclaredConstructor().newInstance());
                        } catch (IllegalAccessException | NoSuchMethodException | InstantiationException | InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                }
                if (temp.size() < 1)
                    throw new DependencyInjectionException("Class for injecting his object to the field '" + field.toString() + "' was not found");
                try {
                    field.set(object, temp);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return object;
    }
}
