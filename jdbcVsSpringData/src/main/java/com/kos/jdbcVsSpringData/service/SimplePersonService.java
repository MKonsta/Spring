package com.kos.jdbcVsSpringData.service;

import com.kos.jdbcVsSpringData.entity.SimplePerson;
import com.kos.jdbcVsSpringData.repository.PersonSpringDataRepository;
import com.kos.jdbcVsSpringData.repository.SimplePersonRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class SimplePersonService {

    @Autowired
    PersonSpringDataRepository personRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    SimplePersonRepository simplePersonRepository;

    @Getter
    @Value("${spring.jpa.properties.hibernate.jdbc.batch_size}")
    private int batchSize;

    @Value("${prop.total.count}")
    private int totalCount;


    public void startSpringDataBatch() {

        List<SimplePerson> people = generatePeople(totalCount, "Spring Data");

        long start = System.currentTimeMillis();

        simplePersonRepository.batchInsert(people);

        System.out.println("(Id - UUID) Spring data: " + getDuration(System.currentTimeMillis() - start));

    }

    public void startJdbcBatch() {

        List<SimplePerson> people = generatePeople(totalCount, "JDBC");

        long start = System.currentTimeMillis();

        for (int i = 0; i < people.size(); i = i + batchSize) {

            List<SimplePerson> batchList = people.subList(i, i + batchSize);

            batchInsert(batchList);
        }

        System.out.println("(Id - UUID) JDBC: " + getDuration(System.currentTimeMillis() - start));
    }


    private List<SimplePerson> generatePeople(int count, String insertType) {

        List<SimplePerson> result = new ArrayList<>();

        Random random = new Random();

        for (int i = 0; i < count; i++) {

            SimplePerson person = SimplePerson.builder()
                    .id(UUID.randomUUID())
                    .insertedBy(insertType)
                    .name(UUID.randomUUID().toString())
                    .age(random.nextInt(99) + 1)
                    .creationDate(new Timestamp(System.currentTimeMillis()))
                    .build();

            result.add(person);
        }

        return result;
    }

    private void batchInsert(List<SimplePerson> people) {

        jdbcTemplate.batchUpdate("INSERT INTO simple_persons (id, name, inserted_by, age, creation_date) values(?, ?, ?, ?, ?)", new BatchPreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setObject(1, people.get(i).getId());
                ps.setString(2, people.get(i).getName());
                ps.setString(3,  people.get(i).getInsertedBy());
                ps.setInt(4,  people.get(i).getAge());
                ps.setTimestamp(5,  people.get(i).getCreationDate());
            }

            @Override
            public int getBatchSize() {
                return people.size();
            }
        });
    }

    private String getDuration(long durationInMillis) {

        long millis = durationInMillis % 1000;
        long second = (durationInMillis / 1000) % 60;
        long minute = (durationInMillis / (1000 * 60)) % 60;
        long hour = (durationInMillis / (1000 * 60 * 60)) % 24;

        return String.format("%02d:%02d:%02d.%d", hour, minute, second, millis);
    }
}
