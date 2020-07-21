package com.rakovets.course.datapersistence.solution.hibernate.dal.dao;

import com.rakovets.course.datapersistence.solution.hibernate.dal.entity.Painting;
import org.hibernate.Session;

public abstract class Dao<T> {
    public abstract void createPainting(T object, Session session);

    public abstract T readPainting(int objectId, Session session);

    public abstract void updatePainting(T object, Session session);

    public abstract void deletePainting(T object, Session session);
}
