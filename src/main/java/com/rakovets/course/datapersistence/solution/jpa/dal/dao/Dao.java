package com.rakovets.course.datapersistence.solution.jpa.dal.dao;

import com.rakovets.course.datapersistence.solution.jpa.dal.entity.MessageEntity;

public abstract class Dao<T> {
    public abstract void saveMessageEntity(T t);

    public abstract MessageEntity findMessageEntityById(int id);
}
