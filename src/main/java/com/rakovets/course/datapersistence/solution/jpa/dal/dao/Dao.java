package com.rakovets.course.datapersistence.solution.jpa.dal.dao;

import com.rakovets.course.datapersistence.solution.jpa.dal.entity.MessageEntity;

import java.util.List;

public abstract class Dao<T> {
    public abstract void save(T t);

    public abstract MessageEntity findMessageEntityById(int id);
}
