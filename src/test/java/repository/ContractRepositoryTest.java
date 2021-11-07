package repository;

import contracts.Contract;
import contracts.DigitalTelevisionContract;
import contracts.MobileContract;
import contracts.WiredInternetContract;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import person.Gender;
import person.Person;
import java.time.LocalDate;
import java.util.Calendar;

/**
 * @author almtn
 */
public class ContractRepositoryTest {
    private Contract wiredInternetContract;
    private Contract digitalTelevisionContract;
    private Contract mobileContract;
    private ContractRepository contractRepository;

    /**
     * This function initializes fields:
     * {@link ContractRepositoryTest#wiredInternetContract},
     * {@link ContractRepositoryTest#digitalTelevisionContract},
     * {@link ContractRepositoryTest#mobileContract}
     *
     * and adds them to {@link ContractRepositoryTest#contractRepository}
     */
    @Before
    public void setUp() {
        contractRepository = new ContractRepository();

        mobileContract = new MobileContract(1,
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
                2,
                LocalDate.of(2021, 1, 24),
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
    }

    /**
     * This funcion tests getting contacts by ID
     */
    @Test
    public void testContractGetByID() {
        Assert.assertEquals("Проверка на соответствие контракта на мобильную связь" +
                " до и после добавления его в репозиторий",
                contractRepository.getContractByID(mobileContract.getId()), mobileContract);
        Assert.assertEquals("Проверка на соответствие контракта на цифровое телевидение" +
                " до и после добавления его в репозиторий",
                contractRepository.getContractByID(digitalTelevisionContract.getId()), digitalTelevisionContract);
        Assert.assertEquals("Проверка на соответствие контракта на проводной интернет" +
                " до и после добавления его в репозиторий",
                contractRepository.getContractByID(wiredInternetContract.getId()), wiredInternetContract);
    }

    /**
     * This function tests deleting contacts by ID
     */
    @Test
    public void testDeleteByID() {
        ContractRepository contractRepository = new ContractRepository();

        Contract mobileContract = new MobileContract(1,
                LocalDate.of(2021, Calendar.FEBRUARY, 24),
                LocalDate.of(2022, Calendar.MARCH, 18),
                3,
                new Person(2,
                        "Testerov Tester Testerovich",
                        LocalDate.of(2001, Calendar.APRIL, 28),
                        Gender.Male,
                        new int[]{2016, 134567}),
                12,
                34,
                56);

        contractRepository.add(mobileContract);
        Assert.assertNotNull("Проверка на существования контракта" +
                " с ID соответствующим контракту mobileContract",
                contractRepository.getContractByID(mobileContract.getId()));

        contractRepository.deleteByID(mobileContract.getId());
        Assert.assertNull("Проверка на отсутствие контракта с ID" +
                " соответствующим удалённому контракту mobileContract",
                contractRepository.getContractByID(mobileContract.getId()));

        int before = contractRepository.getLength();
        contractRepository.deleteByID(-92239424);
        Assert.assertEquals("Проверка на сохранение количества контрактов " +
                "в нашем репозитории после попытки удаления контракта с несуществующим " +
                "в нашем репозитории ID", before, contractRepository.getLength());
    }

    /**
     * This function test getting length of repository
     */
    @Test
    public void testGetLength() {
        Assert.assertEquals("Проверка на правильность вывода длины нашего репозитория",
                3, contractRepository.getLength());
    }
}