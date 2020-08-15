package com.rakovets.course.datapersistence.query.criteria.dal.dao;

import com.rakovets.course.datapersistence.query.criteria.dal.entity.Employee;
import com.rakovets.course.datapersistence.query.criteria.dal.entity.Organization;
import com.rakovets.course.datapersistence.query.criteria.dal.entity.Payment;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import java.time.LocalDate;
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
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Employee> criteria = cb.createQuery(Employee.class);
        criteria.select(criteria.from(Employee.class));
        return session.createQuery(criteria).getResultList();
    }

    /**
     * Возвращает всех сотрудников с указанным именем
     */
    public List<Employee> findAllByFirstName(Session session, String firstName) {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Employee> criteria = cb.createQuery(Employee.class);
        Root<Employee> employee = criteria.from(Employee.class);
        Path<String> employeeFirstName = employee.get(Employee_.firstName);
        criteria.select(employee)
                .where(cb.equal(employeeFirstName, firstName));
        return session.createQuery(criteria).getResultList();
    }

    /**
     * Возвращает первые {limit} сотрудников, упорядоченных по дате рождения (в порядке возрастания)
     */
    public List<Employee> findLimitedEmployeesOrderedByBirthday(Session session, int limit) {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Employee> criteria = cb.createQuery(Employee.class);
        Root<Employee> employee = criteria.from(Employee.class);
        Path<LocalDate> employeeBirthday = employee.get(Employee_.birthday);

        criteria.select(employee)
                .orderBy(cb.asc(employeeBirthday));

        return session.createQuery(criteria)
                .setMaxResults(limit)
                .getResultList();
    }

    /**
     * Возвращает всех сотрудников организации с указанным названием
     */
    public List<Employee> findAllByOrganizationName(Session session, String organizationName) {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Employee> criteria = cb.createQuery(Employee.class);
        Root<Employee> employee = criteria.from(Employee.class);
        Join<Employee, Organization> organization = employee.join(Employee_.organization);

        Path<String> orgName = organization.get(Organization_.name);
        criteria.select(employee).where(cb.equal(orgName, organizationName));

        return session.createQuery(criteria).getResultList();
    }

    /**
     * Возвращает все выплаты, полученные сотрудниками организации с указанными именем,
     * упорядоченные по имени сотрудника, а затем по размеру выплаты
     */
    public List<Payment> findAllPaymentsByOrganizationName(Session session, String organizationName) {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Payment> criteria = cb.createQuery(Payment.class);
        Root<Payment> payment = criteria.from(Payment.class);
        Join<Payment, Employee> receiver = payment.join(Payment_.receiver);
        Join<Employee, Organization> organization = receiver.join(Employee_.organization);

        Path<String> orgName = organization.get(Organization_.name);
        criteria.select(payment)
                .where(cb.equal(orgName, organizationName))
                .orderBy(
                        cb.asc(receiver.get(Employee_.firstName)),
                        cb.asc(payment.get(Payment_.amount))
                );

        return session.createQuery(criteria).getResultList();
    }

    /**
     * Возвращает среднюю зарплату сотрудника с указанными именем и фамилией
     */
    public Double findAveragePaymentAmountByFirstAndLastNames(Session session, String firstName, String lastName) {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Double> criteria = cb.createQuery(Double.class);
        Root<Payment> payment = criteria.from(Payment.class);
        Join<Payment, Employee> receiver = payment.join(Payment_.receiver);

        criteria.select(cb.avg(payment.get(Payment_.amount)))
                .where(cb.and(
                        cb.equal(receiver.get(Employee_.firstName), firstName),
                        cb.equal(receiver.get(Employee_.lastName), lastName)
                ));

        List<Double> results = session.createQuery(criteria).getResultList();
        return results.size() > 0 ? results.get(0) : 0d;
    }

    /**
     * Возвращает для каждой организации: название, среднюю зарплату всех её сотрудников.
     * Организации упорядочены по названию.
     */
    public List<Object[]> findOrganizationNamesWithAvgEmployeePaymentsOrderedByOrgName(Session session) {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteria = cb.createQuery(Object[].class);
        Root<Payment> payment = criteria.from(Payment.class);
        Join<Payment, Employee> receiver = payment.join(Payment_.receiver);
        Join<Employee, Organization> organization = receiver.join(Employee_.organization);

        Path<String> orgName = organization.get(Organization_.name);
        Expression<Double> avgPayment = cb.avg(payment.get(Payment_.amount));
        criteria.multiselect(orgName, avgPayment)
                .groupBy(orgName)
                .orderBy(cb.asc(orgName));

        return session.createQuery(criteria).getResultList();
    }

    /**
     * Возвращает список: сотрудник (объект Employee), средний размер выплат, но только для тех сотрудников,
     * чей средний размер выплат
     * больше среднего размера выплат всех сотрудников
     * Упорядочить по имени сотрудника
     */
    public List<Object[]> canYouDoIt(Session session) {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteria = cb.createQuery(Object[].class);
        Root<Payment> payment = criteria.from(Payment.class);
        Join<Payment, Employee> receiver = payment.join(Payment_.receiver);

        Subquery<Double> subquery = criteria.subquery(Double.class);
        Root<Payment> subqueryRoot = subquery.from(Payment.class);
        subquery.select(cb.avg(subqueryRoot.get(Payment_.amount)));

        Expression<Double> avgPayment = cb.avg(payment.get(Payment_.amount));
        Path<String> employeeName = receiver.get(Employee_.firstName);
        criteria.multiselect(receiver, avgPayment)
                .orderBy(cb.asc(employeeName))
                .groupBy(employeeName)
                .having(cb.gt(avgPayment, subquery));

        return session.createQuery(criteria).getResultList();
    }
}
