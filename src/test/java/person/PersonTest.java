package person;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

/**
 * @author almtn
 */
public class PersonTest {
    Person person;

    /**
     * This function initializes field {@link PersonTest#person}
     */
    @Before
    public void setUp()
    {
        person = new Person(1,"Testerov Tester Testerovich",
                LocalDate.of(2001,3,23),Gender.Male,new int[]{2016,234567}
        );
    }

    /**
     * This function tests getting person's age
     */
    @Test
    public void testGetAge() {
        Assert.assertEquals(20,person.getAge());
    }
}