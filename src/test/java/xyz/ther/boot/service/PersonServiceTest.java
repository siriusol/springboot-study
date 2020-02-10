package xyz.ther.boot.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xyz.ther.boot.pojo.Person;
import xyz.ther.boot.pojo.PersonService;

import javax.validation.*;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonServiceTest {
    @Autowired
    private PersonService service;
    @Autowired
    private Validator validator;

    @Test
    public void should_throw_exception_when_person_is_not_valid() {
        Person person = new Person();
        person.setSex("Man");
        person.setClassId("82938390");
        person.setEmail("SnailClimb@qq.com");
        person.setName("SnailClimb");
        person.setRegion("China-HongKong");
        person.setPhoneNumber("13791726418");
        service.validatePerson(person);
    }

    @Test
    public void check_person_manually() {
        Person person = new Person();
        person.setSex("Man22");
        person.setClassId("82938390");
        person.setEmail("SnailClimb");
        person.setPhoneNumber("56+454");
        Set<ConstraintViolation<Person>> violations = validator.validate(person);
        for (ConstraintViolation<Person> constraintViolation : violations) {
            System.out.println(constraintViolation.getMessage());
        }
    }

}