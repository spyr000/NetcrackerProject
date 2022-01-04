package injection;

@Configuration(packages = {"sorting.sorters"})
public class Injector {
    private Object injectedObject;

    public Injector(Object injectedObject) {
        this.injectedObject = injectedObject;
    }

    public void depInjection() {

    }

    private void searchForClasses()
    {

    }
    public Object getInjectedObject() {
        return injectedObject;
    }

    public void setInjectedObject(Object injectedObject) {
        this.injectedObject = injectedObject;
    }
}
