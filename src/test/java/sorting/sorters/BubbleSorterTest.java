package sorting.sorters;

import contracts.Contract;
import contracts.DigitalTelevisionContract;
import contracts.MobileContract;
import contracts.WiredInternetContract;
import junit.framework.TestCase;
import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import person.Gender;
import person.Person;
import repository.ContractRepository;
import sorting.comparators.*;

import java.time.LocalDate;
import java.util.Calendar;

public class BubbleSorterTest {
    private Contract wiredInternetContract;
    private Contract digitalTelevisionContract;
    private Contract mobileContract;
    private ContractRepository contractRepository;

    private BubbleSorter bubbleSorter;
    private MergeSorter mergeSorter;
    private FinishDateComparator finishDateComparator;
    private IDComparator idComparator;
    private NumberComparator numberComparator;
    private OwnerComparator ownerComparator;
    private StartDateComparator startDateComparator;
    private TypeComparator typeComparator;

    @Before
    public void setUp() {
        contractRepository = new ContractRepository();

        mobileContract = new MobileContract(2,
                LocalDate.of(2021, 2, 24),
                LocalDate.of(2022, 3, 18),
                3,
                new Person(2,
                        "Testerov Tester Testerovich",
                        LocalDate.of(2001, Calendar.APRIL, 28),
                        Gender.Male,
                        new int[]{2016, 134567}),
                12,
                34,
                56);

        digitalTelevisionContract = new DigitalTelevisionContract(
                1,
                LocalDate.of(2020, 6, 13),
                LocalDate.of(2022, 4, 18),
                4,
                new Person(3,
                        "Testerov Tester Testerovich",
                        LocalDate.of(2002, Calendar.MAY, 29),
                        Gender.Male,
                        new int[]{2016, 134567}),
                new String[]{"NTV", "TNT", "STS", "MatchTV"}
        );

        wiredInternetContract = new WiredInternetContract(
                3,
                LocalDate.of(2021, 1, 24),
                LocalDate.of(2022, 2, 18),
                5,
                new Person(4,
                        "Testerova Tester Testerovna",
                        LocalDate.of(2003, 5, 25),
                        Gender.Female,
                        new int[]{2016, 134567}),
                23.7
        );
        contractRepository.add(mobileContract);
        contractRepository.add(digitalTelevisionContract);
        contractRepository.add(wiredInternetContract);

        bubbleSorter = new BubbleSorter();
        mergeSorter = new MergeSorter();
        typeComparator = new TypeComparator();
        finishDateComparator = new FinishDateComparator(true);
        idComparator = new IDComparator();
        numberComparator = new NumberComparator();
        ownerComparator = new OwnerComparator();
        startDateComparator = new StartDateComparator();
    }
    private static void printRepository(@NotNull ContractRepository contractRepository)
    {
        for (int i = 0;i < contractRepository.getLength();i++)
        {
            System.out.print(contractRepository.getContractByIndex(i).toString() + "\n");
        }
        System.out.print("\n");
    }

    @Test
    public void testBubbleSortingByStDate()
    {
        printRepository(contractRepository);
        ContractRepository sortedContractRepository = bubbleSorter.sort(contractRepository,startDateComparator);
        printRepository(sortedContractRepository);

        Contract[] expextedContracts = new Contract[]{contractRepository.getContractByID(1),
                contractRepository.getContractByID(3),contractRepository.getContractByID(2)};
        Contract[] actualContracts = new Contract[]{sortedContractRepository.getContractByIndex(0),
                sortedContractRepository.getContractByIndex(1),sortedContractRepository.getContractByIndex(2)};

        Assert.assertArrayEquals("Проверка сортировки слиянием по дате начала контактов",
                expextedContracts,actualContracts);
    }

    @Test
    public void testBubbleSortingByFnDateDesc()
    {
        printRepository(contractRepository);
        ContractRepository sortedContractRepository = bubbleSorter.sort(contractRepository,finishDateComparator);
        printRepository(sortedContractRepository);
        Contract[] expextedContracts = new Contract[]{contractRepository.getContractByID(1),
                contractRepository.getContractByID(2),contractRepository.getContractByID(3)};
        Contract[] actualContracts = new Contract[]{sortedContractRepository.getContractByIndex(0),
                sortedContractRepository.getContractByIndex(1),sortedContractRepository.getContractByIndex(2)};

        Assert.assertArrayEquals("Проверка сортировки пузырьком по убыванию по датам окончания контактов",
                expextedContracts,actualContracts);
    }


}