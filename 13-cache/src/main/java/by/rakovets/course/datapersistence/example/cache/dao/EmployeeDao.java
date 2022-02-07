package by.rakovets.course.datapersistence.example.cache.dao;

import by.rakovets.course.datapersistence.example.cache.entity.Employee;
import by.rakovets.course.datapersistence.example.cache.util.EntityManagerUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.RollbackException;


public final class EmployeeDao {
    private static EmployeeDao INSTANCE;

    private EmployeeDao() {
    }

    public static EmployeeDao getInstance() {
        if (INSTANCE == null) {
            synchronized (EmployeeDao.class) {
                if (INSTANCE == null) {
                    INSTANCE = new EmployeeDao();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * Возвращает сотрудника по id
     */
    public Employee findById(Long id) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        Employee result = null;
        try {
            em.getTransaction().begin();
            result = em.find(Employee.class, id);
            em.getTransaction().commit();
        } catch (RollbackException e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }

        return result;
    }
}
