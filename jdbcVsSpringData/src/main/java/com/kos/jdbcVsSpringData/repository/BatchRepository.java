package com.kos.jdbcVsSpringData.repository;


import java.util.List;

public interface BatchRepository<T> {

    void batchInsert(List<T> list);
}
