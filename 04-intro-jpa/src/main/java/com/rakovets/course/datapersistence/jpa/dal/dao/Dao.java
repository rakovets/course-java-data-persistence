package com.rakovets.course.datapersistence.jpa.dal.dao;

public abstract class Dao<T> {
    public abstract void save(T t);
}
