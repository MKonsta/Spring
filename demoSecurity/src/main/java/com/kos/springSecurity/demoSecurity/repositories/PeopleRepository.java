package com.kos.springSecurity.demoSecurity.repositories;

import com.kos.springSecurity.demoSecurity.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Konstantin Matushenko
 */
@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {

    Optional<Person> findByUsername(String username);
}
