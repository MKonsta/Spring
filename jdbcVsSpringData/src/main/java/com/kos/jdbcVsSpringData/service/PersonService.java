package com.kos.jdbcVsSpringData.service;

import com.kos.jdbcVsSpringData.entity.PersonJdbc;
import com.kos.jdbcVsSpringData.entity.PersonSpringData;
import com.kos.jdbcVsSpringData.repository.PersonSpringDataRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

@Service
public class PersonService {

    @Autowired
    PersonSpringDataRepository personRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Getter
    @Value("${spring.jpa.properties.hibernate.jdbc.batch_size}")
    private int batchSize;

    @Value("${prop.total.count}")
    private int totalCount;


    public void startSpringDataBatch() {

        List<PersonSpringData> people = generateSpringData(totalCount);

        long start = System.currentTimeMillis();

        personRepository.saveAll(people);

        System.out.println("Spring data: " + getDuration(System.currentTimeMillis() - start));

    }

    public void startJdbcBatch() {

        List<PersonJdbc> people = generateJdbc(totalCount);

        long start = System.currentTimeMillis();

        for (int i = 0; i < people.size(); i = i + batchSize) {

            List<PersonJdbc> batchList = people.subList(i, i + batchSize);

            batchInsert(batchList);
        }

        System.out.println("JDBC: " + getDuration(System.currentTimeMillis() - start));
    }

    private List<PersonSpringData> generateSpringData(int count) {

        List<PersonSpringData> result = new ArrayList<>();

        Random random = new Random();

        for (int i = 0; i < count; i++) {

            PersonSpringData person = PersonSpringData.builder()
                    .name(UUID.randomUUID().toString())
                    .age(random.nextInt(99) + 1)
                    .creationDate(new Timestamp(System.currentTimeMillis()))
                    .build();

            result.add(person);
        }

        return result;
    }

    private List<PersonJdbc> generateJdbc(int count) {

        List<PersonJdbc> result = new ArrayList<>();

        Random random = new Random();

        for (int i = 0; i < count; i++) {

            PersonJdbc person = PersonJdbc.builder()
                    .name(UUID.randomUUID().toString())
                    .age(random.nextInt(99) + 1)
                    .creationDate(new Timestamp(System.currentTimeMillis()))
                    .build();

            result.add(person);
        }

        return result;
    }

    private void batchInsert(List<PersonJdbc> people) {

        jdbcTemplate.batchUpdate("INSERT INTO persons_jdbc (name, inserted_by, age, creation_date) values(?, ?, ?, ?)", new BatchPreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1, people.get(i).getName());
                ps.setString(2,  people.get(i).getInsertedBy());
                ps.setInt(3,  people.get(i).getAge());
                ps.setTimestamp(4,  people.get(i).getCreationDate());
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
