package com.kos.jdbcVsSpringData.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "simple_persons")
public class SimplePerson {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "inserted_by")
    private String insertedBy;

    @Column(name = "age")
    private int age;

    @Column(name = "creation_date")
    private Timestamp creationDate;
}
