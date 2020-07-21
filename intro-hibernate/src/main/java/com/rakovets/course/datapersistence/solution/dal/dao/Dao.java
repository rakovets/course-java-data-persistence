package com.rakovets.course.datapersistence.solution.dal.dao;

import com.rakovets.course.datapersistence.solution.dal.entity.Painting;
import org.hibernate.Session;

public abstract class Dao<T> {
    public abstract void create(T t, Session session);

    public abstract Painting read(int id, Session session);

    public abstract void update(T t, Session session);

    public abstract void delete(T t, Session session);
}
