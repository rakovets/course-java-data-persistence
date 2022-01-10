package com.rakovets.course.datapersistence.example.jpa.example2.dal.dao;

import com.rakovets.course.datapersistence.example.jpa.example2.dal.entity.BaseEntity;

import java.util.List;

public interface BaseDao<T extends BaseEntity> {
    void save(T entity);

    T findOne(Long id);

    List<T> findAll();

    void update(T entity);

    void delete(T entity);
}
