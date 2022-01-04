package validators;

import contracts.MobileContract;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import person.Gender;
import person.Person;

import java.time.LocalDate;
import java.util.Calendar;

import static org.junit.Assert.*;

public class MobileContractValidatorTest {
    private MobileContract okMobileContract;
    private MobileContract errorMobileContract;
    private MobileContract redriskMobileContract;

    @Before
    public void setUp() {
        okMobileContract = new MobileContract(
                2,
                LocalDate.of(2021, 1, 24),
                LocalDate.of(2022, 4, 18),
                "+79102325546",
                new Person(3,
                        "Testerov Tester Testerovich",
                        LocalDate.of(2002, Calendar.MAY, 29),
                        Gender.Male,
                        new int[]{2016, 134567}),
                11,
                16,
                32
        );
        errorMobileContract = new MobileContract(
                2,
                LocalDate.of(2021, 1, 24),
                LocalDate.of(2022, 4, 18),
                "89102325546",
                new Person(3,
                        "Testerov Tester Testerovich",
                        LocalDate.of(2002, Calendar.MAY, 29),
                        Gender.Male,
                        new int[]{2016, 134567}),
                -11,
                16,
                32
        );
        redriskMobileContract = new MobileContract(
                2,
                LocalDate.of(2021, 1, 24),
                LocalDate.of(2022, 4, 18),
                "89102325546",
                new Person(3,
                        null,
                        LocalDate.of(2002, Calendar.MAY, 29),
                        Gender.Male,
                        new int[]{2016, 134567}),
                11,
                16,
                32
        );
    }
    @Test
    public void testContractGetByID() {
        MobileContractValidator okValidator = new MobileContractValidator();
        MobileContractValidator errorValidator = new MobileContractValidator();
        MobileContractValidator redriskValidator = new MobileContractValidator();
        okValidator.validateContract(okMobileContract);
        errorValidator.validateContract(errorMobileContract);
        redriskValidator.validateContract(redriskMobileContract);

        ValidationStatus okStatus = okValidator.getStatus();
        ValidationStatus errorStatus = errorValidator.getStatus();
        ValidationStatus redrisk = redriskValidator.getStatus();

        Assert.assertEquals(ValidationStatus.OK,okStatus);
        Assert.assertEquals(ValidationStatus.Error,errorStatus);
        Assert.assertEquals(redrisk,ValidationStatus.RedRisk);
    }

}