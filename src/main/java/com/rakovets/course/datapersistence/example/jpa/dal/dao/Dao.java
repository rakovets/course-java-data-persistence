package com.rakovets.course.datapersistence.example.jpa.dal.dao;

public abstract class Dao<T> {
    public abstract void save(T t);
}
