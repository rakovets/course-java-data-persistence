package by.rakovets.course.datapersistence.example.hibernate.dal.dao;

import by.rakovets.course.datapersistence.example.hibernate.dal.entity.EmployeeEntity;

public class EmployeeDaoImpl extends BaseDaoImpl<EmployeeEntity> {
    public EmployeeDaoImpl() {
        super(EmployeeEntity.class);
    }
}
