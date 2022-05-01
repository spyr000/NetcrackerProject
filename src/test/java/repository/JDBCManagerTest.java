package repository;

import contracts.Contract;
import contracts.DigitalTelevisionContract;
import contracts.MobileContract;
import contracts.WiredInternetContract;
import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import person.Gender;
import person.Person;

import java.time.LocalDate;
import java.util.Calendar;

public class JDBCManagerTest {
    private Contract wiredInternetContract;
    private Contract digitalTelevisionContract;
    private Contract mobileContract;
    private ContractRepository contractRepository;

    /**
     * This function initializes fields:
     * {@link JDBCManagerTest#wiredInternetContract},
     * {@link JDBCManagerTest#digitalTelevisionContract},
     * {@link JDBCManagerTest#mobileContract}
     *
     * and adds them to {@link JDBCManagerTest#contractRepository}
     */
    @Before
    public void setUp() {
        contractRepository = new ContractRepository();

        mobileContract = new MobileContract(1,
                LocalDate.of(2021, 2, 24),
                LocalDate.of(2022, 3, 18),
                "3",
                new Person(2,
                        "Testerov Tester Testerovich",
                        LocalDate.of(2001, Calendar.APRIL, 28),
                        Gender.Male,
                        new int[]{2016, 137567}),
                12,
                34,
                56);

        digitalTelevisionContract = new DigitalTelevisionContract(
                2,
                LocalDate.of(2021, 1, 24),
                LocalDate.of(2022, 4, 18),
                "4",
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
                "5",
                new Person(4,
                        "Testerova Tester Testerovna",
                        LocalDate.of(2003, 5, 25),
                        Gender.Female,
                        new int[]{2016, 134667}),
                23.7
        );

        contractRepository.add(mobileContract);
        contractRepository.add(digitalTelevisionContract);
        contractRepository.add(wiredInternetContract);
    }

    private static void printRepository(@NotNull ContractRepository contractRepository) {
        for (int i = 0; i < contractRepository.getLength(); i++) {
            System.out.print(contractRepository.getContractByIndex(i).toString() + "\n");
        }
        System.out.print("\n");
    }

    @Test
    public void testDB() {
        printRepository(contractRepository);
        JDBCManager jdbcManager = JDBCManager.newEmptyDB(
                JDBCManager.JDBC_DRIVER,
                JDBCManager.USER,
                JDBCManager.PASS,
                JDBCManager.DB_URL
        );
        jdbcManager.insertContracts(contractRepository);

        ContractRepository newContractRepository = new ContractRepository();
        jdbcManager.getContractsFromDB(newContractRepository);
        printRepository(newContractRepository);

        Assert.assertEquals(
                contractRepository.getContractByID(1).getOwner(),
                newContractRepository.getContractByID(1).getOwner()
        );
    }
}