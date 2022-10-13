package com.kos.springSecurity.demoSecurity.services;

import com.kos.springSecurity.demoSecurity.models.Person;
import com.kos.springSecurity.demoSecurity.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author Konstantin Matushenko
 */
@Service
public class PeopleService {

    private PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public Person loadPersonByUsername(String username) throws UsernameNotFoundException {

        Optional<Person> person = peopleRepository.findByUsername(username);

        return person.orElse(null);
    }

    @Transactional
    public Person save(Person person) {
        return peopleRepository.save(person);
    }
}
