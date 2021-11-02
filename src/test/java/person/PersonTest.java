package person;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Date;

public class PersonTest {
    Person person;
    @Before
    public void setUp()
    {
        person = new Person(1,"Testerov Tester Testerovich",
                LocalDate.of(2001,3,23),Gender.Male,new int[]{2016,234567}
        );
    }

    @Test
    public void testGetAge() {
        Assert.assertEquals(20,person.getAge());
    }
}