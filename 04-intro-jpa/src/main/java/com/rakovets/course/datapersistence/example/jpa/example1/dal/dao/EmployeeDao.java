package com.rakovets.course.datapersistence.example.jpa.example1.dal.dao;

import com.rakovets.course.datapersistence.example.jpa.example1.dal.entity.EmployeeEntity;

public interface EmployeeDao {
    /**
     * Сохранение сущности в хранилище.
     *
     * @param employeeEntity сохраняемая сущность
     */
    void save(EmployeeEntity employeeEntity);
}
