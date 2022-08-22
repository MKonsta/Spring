package com.kos.jdbcVsSpringData.repository;

import com.kos.jdbcVsSpringData.entity.PersonSpringData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonSpringDataRepository extends JpaRepository<PersonSpringData, Integer> {
}
