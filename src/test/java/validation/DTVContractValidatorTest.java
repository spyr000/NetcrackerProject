package validation;

import contracts.DigitalTelevisionContract;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import person.Gender;
import person.Person;
import validation.validators.DTVContractValidator;

import java.time.LocalDate;
import java.util.Calendar;

public class DTVContractValidatorTest {
    private DigitalTelevisionContract okDigitalTelevisionContract;
    private DigitalTelevisionContract errorDigitalTelevisionContract;
    private DigitalTelevisionContract redriskDigitalTelevisionContract;

    @Before
    public void setUp() {
        okDigitalTelevisionContract = new DigitalTelevisionContract(
                2,
                LocalDate.of(2021, 1, 24),
                LocalDate.of(2022, 4, 18),
                "89102325546",
                new Person(3,
                        "Testerov Tester Testerovich",
                        LocalDate.of(2002, Calendar.MAY, 29),
                        Gender.Male,
                        new int[]{2016, 134567}),
                new String[]{"NTV", "TNT", "STS", "MatchTV"}
        );
        errorDigitalTelevisionContract = new DigitalTelevisionContract(
                2,
                LocalDate.of(2021, 1, 24),
                LocalDate.of(2022, 4, 18),
                "89102325546",
                new Person(3,
                        "Testerov Tester Testerovich",
                        LocalDate.of(2002, Calendar.MAY, 29),
                        Gender.Male,
                        new int[]{2016, 134567}),
                new String[]{"NTV", null, "STS", "MatchTV"}
        );
        redriskDigitalTelevisionContract = new DigitalTelevisionContract(
                2,
                LocalDate.of(2021, 1, 24),
                LocalDate.of(2022, 4, 18),
                "4fkdas",
                new Person(3,
                        "Testerov Tester Testerovich",
                        LocalDate.of(2002, Calendar.MAY, 29),
                        Gender.Male,
                        new int[]{2016, 134567}),
                new String[]{"NTV", "TNT", "STS", "MatchTV"}
        );
    }
    @Test
    public void testContractGetByID() {
        DTVContractValidator okValidator = new DTVContractValidator();
        DTVContractValidator errorValidator = new DTVContractValidator();
        DTVContractValidator redriskValidator = new DTVContractValidator();
        okValidator.validateContract(okDigitalTelevisionContract);
        errorValidator.validateContract(errorDigitalTelevisionContract);
        redriskValidator.validateContract(redriskDigitalTelevisionContract);

        ValidationStatus okStatus = okValidator.getStatus();
        ValidationStatus errorStatus = errorValidator.getStatus();
        ValidationStatus redrisk = redriskValidator.getStatus();

        Assert.assertEquals(ValidationStatus.OK,okStatus);
        Assert.assertEquals(ValidationStatus.Error,errorStatus);
        Assert.assertEquals(redrisk,ValidationStatus.RedRisk);
    }
}