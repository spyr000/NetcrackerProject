package person;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

public class PersonTest extends TestCase {
    Person person;
    @Before
    public void setUp()
    {
        person = new Person(1,"Testerov Tester Testerovich",
                new Date(2001,03,23),Gender.Male,new int[]{2016,234567}
        );
    }
    @Test
    public void testGetAge() {
//        System.out.print(person.getAge());
        Assert.assertEquals(20,person.getAge());
    }
}