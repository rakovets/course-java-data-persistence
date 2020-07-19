package com.rakovets.course.datapersistence.solution.dal.dao;

import com.rakovets.course.datapersistence.solution.dal.entity.Painting;
import org.hibernate.Session;

public abstract class Dao<T> {
    public abstract void createPainting(T t, Session session);

    public abstract Painting readPainting(int id, Session session);

    public abstract void updatePainting(T t, Session session);

    public abstract void deletePainting(T t, Session session);
}
