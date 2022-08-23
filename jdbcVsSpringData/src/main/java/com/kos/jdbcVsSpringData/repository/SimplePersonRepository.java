package com.kos.jdbcVsSpringData.repository;

import com.kos.jdbcVsSpringData.entity.SimplePerson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SimplePersonRepository extends JpaRepository<SimplePerson, UUID>, BatchRepository<SimplePerson> {
}
