package xyz.ther.boot.pojo;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Service
@Validated
public class PersonService {

    public void validatePerson(@Valid Person person){
        // do something
    }
}