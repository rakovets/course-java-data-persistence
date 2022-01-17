package by.rakovets.course.datapersistence.example.hibernate;

import by.rakovets.course.datapersistence.example.hibernate.dal.entity.EmployeeEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Example1 {
    private static final Logger log = LogManager.getLogger(Example1.class);

    public void show() {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();

        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setName("Dmitry Rakovets");

        Transaction transaction = session.getTransaction();
        transaction.begin();
        session.createNativeQuery("INSERT INTO employee (name) VALUES (:name)", EmployeeEntity.class)
                .setParameter("name", employeeEntity.getName())
                .executeUpdate();
        transaction.commit();
        session.saveOrUpdate(employeeEntity);

        EmployeeEntity retrievedPerson = null;
        List<EmployeeEntity> findEmployeeEntity = session.createNativeQuery("SELECT * FROM employee WHERE employee_id = 2", EmployeeEntity.class)
                .getResultList();
        if (!findEmployeeEntity.isEmpty()) {
            retrievedPerson = findEmployeeEntity.get(0);
        }

        session.find(EmployeeEntity.class, 2L);
        log.debug(retrievedPerson);

        List<EmployeeEntity> resultList = session.createNativeQuery("SELECT * FROM employee", EmployeeEntity.class)
                .getResultList();

        List<EmployeeEntity> resultList1 = session.createQuery("from EmployeeEntity", EmployeeEntity.class)
                .getResultList();

        resultList.forEach(log::debug);

        session.close();
        sessionFactory.close();
    }
}
