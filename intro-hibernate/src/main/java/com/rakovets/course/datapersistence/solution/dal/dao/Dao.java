package com.rakovets.course.datapersistence.solution.dal.dao;

import com.rakovets.course.datapersistence.solution.dal.entity.Painting;

public abstract class Dao<T> {
    public abstract void createPainting(T t);

    public abstract Painting readPainting(int id);

    public abstract void updatePainting(T t);

    public abstract void deletePainting(int id);
}
