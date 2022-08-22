package com.kos.jdbcVsSpringData.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "persons_jdbc")
public class PersonJdbc {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "inserted_by")
    private String insertedBy = "JDBC";

    @Column(name = "age")
    private int age;

    @Column(name = "creation_date")
    private Timestamp creationDate;
}
