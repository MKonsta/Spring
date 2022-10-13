package com.kos.springSecurity.demoSecurity.util;


import com.kos.springSecurity.demoSecurity.models.Person;
import com.kos.springSecurity.demoSecurity.services.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {

    private final PeopleService peopleService;

    @Autowired
    public PersonValidator(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        Person person = (Person) target;

        Person result = peopleService.loadPersonByUsername(person.getUsername());

        if (result == null) {
            return;
        }

        errors.rejectValue("username", "", "Человек с таким именем уже существует");
    }
}
