package by.rakovets.course.datapersistence.example.jpa.example1.dal.dao;

import by.rakovets.course.datapersistence.example.jpa.example1.dal.entity.EmployeeEntity;
import by.rakovets.course.datapersistence.example.jpa.example1.util.DynamicEntityManagerFactoryUtil;
import jakarta.persistence.EntityManager;

public class EmployeeDaoJpa implements EmployeeDao {
    public void save(EmployeeEntity employeeEntity) {
        EntityManager entityManager = DynamicEntityManagerFactoryUtil.getEntityManager();
        entityManager.getTransaction().begin();

        entityManager.persist(employeeEntity);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
