package com.kos.springSecurity.demoSecurity.services;

import com.kos.springSecurity.demoSecurity.models.Person;
import com.kos.springSecurity.demoSecurity.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author Konstantin Matushenko
 */
@Service
public class PeopleService {

    private PeopleRepository peopleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository, PasswordEncoder passwordEncoder) {
        this.peopleRepository = peopleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Person loadPersonByUsername(String username) throws UsernameNotFoundException {

        Optional<Person> person = peopleRepository.findByUsername(username);

        return person.orElse(null);
    }

    @Transactional
    public Person save(Person person) {
        String encodedPassword = passwordEncoder.encode(person.getPassword());
        person.setPassword(encodedPassword);
        person.setRole("ROLE_USER");
        return peopleRepository.save(person);
    }
}
