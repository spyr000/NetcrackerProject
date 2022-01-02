package validators;

import contracts.DigitalTelevisionContract;
import contracts.WiredInternetContract;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import person.Gender;
import person.Person;

import java.time.LocalDate;
import java.util.Calendar;

public class WIContractValidatorTest {
    private WiredInternetContract okWiredInternetContract;
    private WiredInternetContract errorWiredInternetContract;
    private WiredInternetContract redriskWiredInternetContract;

    @Before
    public void setUp() {
        okWiredInternetContract = new WiredInternetContract(
                2,
                LocalDate.of(2021, 1, 24),
                LocalDate.of(2022, 4, 18),
                "89102325546",
                new Person(3,
                        "Testerov Tester Testerovich",
                        LocalDate.of(2002, Calendar.MAY, 29),
                        Gender.Male,
                        new int[]{2016, 134567}),
                14.4
        );
        errorWiredInternetContract = new WiredInternetContract(
                2,
                LocalDate.of(2021, 1, 24),
                LocalDate.of(2022, 4, 18),
                "89102325546",
                new Person(3,
                        "Testerov Tester Testerovich",
                        LocalDate.of(2002, Calendar.MAY, 29),
                        Gender.Male,
                        new int[]{2016, 134567}),
                -14.4
        );
        redriskWiredInternetContract = new WiredInternetContract(
                2,
                LocalDate.of(2021, 1, 24),
                LocalDate.of(2022, 4, 18),
                "4fkdas",
                new Person(3,
                        "Testerov Tester Testerovich",
                        LocalDate.of(2002, Calendar.MAY, 29),
                        Gender.Male,
                        new int[]{2016, 134567}),
                -14.4
        );
    }
    @Test
    public void testContractGetByID() {
        WIContractValidator okValidator = new WIContractValidator();
        WIContractValidator errorValidator = new WIContractValidator();
        WIContractValidator redriskValidator = new WIContractValidator();


        okValidator.validateContract(okWiredInternetContract);
        errorValidator.validateContract(errorWiredInternetContract);
        redriskValidator.validateContract(redriskWiredInternetContract);

        ValidationStatus okStatus = okValidator.getStatus();
        ValidationStatus errorStatus = errorValidator.getStatus();
        ValidationStatus redrisk = redriskValidator.getStatus();

        Assert.assertEquals(ValidationStatus.OK,okStatus);
        Assert.assertEquals(ValidationStatus.Error,errorStatus);
        Assert.assertEquals(redrisk,ValidationStatus.RedRisk);
    }
}