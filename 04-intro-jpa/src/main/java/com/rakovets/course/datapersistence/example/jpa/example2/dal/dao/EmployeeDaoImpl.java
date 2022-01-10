package com.rakovets.course.datapersistence.example.jpa.example2.dal.dao;

import com.rakovets.course.datapersistence.example.jpa.example2.dal.entity.EmployeeEntity;

public class EmployeeDaoImpl extends BaseDaoImpl<EmployeeEntity> {
    public EmployeeDaoImpl() {
        super(EmployeeEntity.class);
    }
}
