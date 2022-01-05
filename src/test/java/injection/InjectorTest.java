package injection;

import exceptions.DependencyInjectionException;
import org.junit.Assert;
import org.junit.Test;
import repository.ContractRepository;

public class InjectorTest {

    @Test
    public void getFieldsToInject() throws NoSuchFieldException {
        Assert.assertEquals(ContractRepository.class.getDeclaredField("sorterPlugins"), Injector.getFieldsToInject(new ContractRepository()).get(0));
    }

    @Test
    public void inject() throws DependencyInjectionException {
        var testRepo = new ContractRepository();
        Assert.assertNull(testRepo.getSorterByName("sorting.sorters.BubbleSorter"));
        Assert.assertNull(testRepo.getSorterByName("sorting.sorters.MergeSorter"));
        testRepo = Injector.inject(new ContractRepository());
        Assert.assertNotNull(testRepo.getSorterByName("sorting.sorters.BubbleSorter"));
        Assert.assertNotNull(testRepo.getSorterByName("sorting.sorters.BubbleSorter"));

    }
}