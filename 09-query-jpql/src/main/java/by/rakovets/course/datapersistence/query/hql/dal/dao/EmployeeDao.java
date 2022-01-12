package by.rakovets.course.datapersistence.query.hql.dal.dao;

import by.rakovets.course.datapersistence.query.hql.dal.entity.Employee;
import by.rakovets.course.datapersistence.query.hql.dal.entity.Payment;
import org.hibernate.Session;

import java.util.List;


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
     * Возвращает всех сотрудников
     */
    public List<Employee> findAll(Session session) {
        return session
                .createQuery("select e from Employee e", Employee.class)
                .getResultList();
    }

    /**
     * Возвращает всех сотрудников с указанным именем
     */
    public List<Employee> findAllByFirstName(Session session, String firstName) {
        return session
                .createQuery("select e from Employee e where e.firstName=:firstName", Employee.class)
                .setParameter("firstName", firstName)
                .getResultList();
    }

    /**
     * Возвращает первые {limit} сотрудников, упорядоченных по дате рождения (в порядке возрастания)
     */
    public List<Employee> findLimitedEmployeesOrderedByBirthday(Session session, int limit) {
        return session
                .createQuery("select e from Employee e order by e.birthday", Employee.class)
                .setMaxResults(limit)
                .getResultList();
    }

    /**
     * Возвращает всех сотрудников организации с указанным названием
     */
    public List<Employee> findAllByOrganizationName(Session session, String organizationName) {
        return session
                .createQuery("select e from Employee e where e.organization.name=:orgName", Employee.class)
                .setParameter("orgName", organizationName)
                .getResultList();
//        return session
//                .createQuery("select e from Employee e join e.organization o " +
//                        "where o.name=:orgName", Employee.class)
//                .setParameter("orgName", organizationName)
//                .getResultList();
    }

    /**
     * Возвращает все выплаты, полученные сотрудниками организации с указанными именем,
     * упорядоченные по имени сотрудника, а затем по размеру выплаты
     */
    public List<Payment> findAllPaymentsByOrganizationName(Session session, String organizationName) {
        return session
                .createQuery("select p from Payment p where p.receiver.organization.name=:orgName " +
                        "order by p.receiver.firstName, p.amount", Payment.class)
                .setParameter("orgName", organizationName)
                .getResultList();
//        return session
//                .createQuery("select p from Payment p join p.receiver r join r.organization o " +
//                        "where o.name=:orgName order by r.firstName, p.amount", Payment.class)
//                .setParameter("orgName", organizationName)
//                .getResultList();
    }

    /**
     * Возвращает среднюю зарплату сотрудника с указанными именем и фамилией
     */
    public Double findAveragePaymentAmountByFirstAndLastNames(Session session, String firstName, String lastName) {
        return session
                .createQuery("select avg(p.amount) from Payment p where p.receiver.firstName=:firstName " +
                        "and p.receiver.lastName=:lastName", Double.class)
                .setParameter("firstName", firstName)
                .setParameter("lastName", lastName)
                .getSingleResult();
    }

    /**
     * Возвращает для каждой организации: название, среднюю зарплату всех её сотрудников. Организации упорядочены по названию.
     */
    public List<Object[]> findOrganizationNamesWithAvgEmployeePaymentsOrderedByOrgName(Session session) {
        return session
                .createQuery("select o.name, avg(p.amount) from Payment p " +
                        "join p.receiver r join r.organization o group by o.name order by o.name", Object[].class)
                .getResultList();
    }

    /**
     * Возвращает список: сотрудник (объект Employee), средний размер выплат, но только для тех сотрудников, чей средний размер выплат
     * больше среднего размера выплат всех сотрудников
     * Упорядочить по имени сотрудника
     */
    public List<Object[]> canYouDoIt(Session session) {
        return session.createQuery("select e, avg(p.amount) from Payment p join p.receiver e " +
                "group by e.firstName having avg(p.amount) > (select avg(p.amount) from Payment p) " +
                "order by e.firstName", Object[].class)
                .getResultList();
    }
}
