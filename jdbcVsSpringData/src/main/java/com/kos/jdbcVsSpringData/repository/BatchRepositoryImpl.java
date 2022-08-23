package com.kos.jdbcVsSpringData.repository;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Iterator;
import java.util.List;

@Repository
public class BatchRepositoryImpl<T> implements BatchRepository<T> {


    @Getter
    @Value("${spring.jpa.properties.hibernate.jdbc.batch_size}")
    private Integer batchSize;

    @Autowired
    @Qualifier("entityManagerFactory")
    private EntityManager entityManager;

    @Transactional
    @Override
    public void batchInsert(List<T> list) {

        Iterator<T> iterator = list.iterator();

        int index = 0;

        while (iterator.hasNext()) {

            entityManager.persist(iterator.next());

            index++;

            if (index % getBatchSize() == 0) {

                entityManager.flush();
                entityManager.clear();
            }
        }

        if (index % getBatchSize() != 0) {

            entityManager.flush();
            entityManager.clear();
        }
    }
}