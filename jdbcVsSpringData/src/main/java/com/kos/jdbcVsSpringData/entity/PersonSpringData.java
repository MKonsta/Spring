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
@Table(name = "persons_spring_data")
public class PersonSpringData {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "persons_spring_data_id_seq")
    @SequenceGenerator(name = "persons_spring_data_id_seq", allocationSize = 100000)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "inserted_by")
    private String insertedBy = "Spring data";

    @Column(name = "age")
    private int age;

    @Column(name = "creation_date")
    private Timestamp creationDate;
}
