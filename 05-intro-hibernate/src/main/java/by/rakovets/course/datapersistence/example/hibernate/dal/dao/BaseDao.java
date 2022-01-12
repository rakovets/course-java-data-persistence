package by.rakovets.course.datapersistence.example.hibernate.dal.dao;

import by.rakovets.course.datapersistence.example.hibernate.dal.entity.BaseEntity;

import java.util.List;

public interface BaseDao<T extends BaseEntity> {
    void save(T entity);

    T findOne(Long id);

    List<T> findAll();

    void update(T entity);

    void delete(T entity);
}
