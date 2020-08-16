package com.rakovets.course.datapersistence.jpa.dal.dao;

import com.rakovets.course.datapersistence.jpa.dal.entity.EmployeeEntity;
import com.rakovets.course.datapersistence.jpa.util.DynamicEntityManagerFactoryUtil;

import javax.persistence.EntityManager;

public class EmployeeDao extends Dao<EmployeeEntity> {
    public void save(EmployeeEntity employeeEntity) {
        EntityManager entityManager = DynamicEntityManagerFactoryUtil.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(employeeEntity);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
