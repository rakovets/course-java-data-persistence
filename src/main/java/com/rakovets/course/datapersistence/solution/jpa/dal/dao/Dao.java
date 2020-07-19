package com.rakovets.course.datapersistence.solution.jpa.dal.dao;

public abstract class Dao<T> {
    public abstract void save(T t);
}
