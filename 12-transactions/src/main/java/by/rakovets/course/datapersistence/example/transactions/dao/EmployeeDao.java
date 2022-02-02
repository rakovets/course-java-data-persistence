package by.rakovets.course.datapersistence.example.transactions.dao;

import by.rakovets.course.datapersistence.example.transactions.entity.Employee;
import by.rakovets.course.datapersistence.example.transactions.entity.Organization;
import by.rakovets.course.datapersistence.example.transactions.entity.Payment;
import by.rakovets.course.datapersistence.example.transactions.util.EntityManagerUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.RollbackException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;

import java.time.LocalDate;
import java.util.ArrayList;
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
    public List<Employee> findAll() {
        EntityManager em = EntityManagerUtil.getEntityManager();
        List<Employee> result = new ArrayList<>();
        try {
            em.getTransaction().begin();

            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Employee> query = cb.createQuery(Employee.class);
            query.select(query.from(Employee.class));
            result = em.createQuery(query).getResultList();

            em.getTransaction().commit();
        } catch (RollbackException e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }

        return result;
    }

    /**
     * Возвращает всех сотрудников с указанным именем
     */
    public List<Employee> findAllByFirstName(String firstName) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        em.getTransaction().begin();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Employee> criteria = cb.createQuery(Employee.class);
        Root<Employee> employee = criteria.from(Employee.class);
        Path<String> employeeFirstName = employee.get("firstName");
        criteria.select(employee)
                .where(cb.equal(employeeFirstName, firstName));

        em.getTransaction().commit();
        em.close();
        return em.createQuery(criteria).getResultList();
    }

    /**
     * Возвращает первые {limit} сотрудников, упорядоченных по дате рождения (в порядке возрастания)
     */
    public List<Employee> findLimitedEmployeesOrderedByBirthday(int limit) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        em.getTransaction().begin();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Employee> criteria = cb.createQuery(Employee.class);
        Root<Employee> employee = criteria.from(Employee.class);
        Path<LocalDate> employeeBirthday = employee.get("birthday");

        criteria.select(employee)
                .orderBy(cb.asc(employeeBirthday));

        em.getTransaction().commit();
        em.close();

        return em.createQuery(criteria)
                .setMaxResults(limit)
                .getResultList();
    }

    /**
     * Возвращает всех сотрудников организации с указанным названием
     */
    public List<Employee> findAllByOrganizationName(String organizationName) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        em.getTransaction().begin();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Employee> criteria = cb.createQuery(Employee.class);
        Root<Employee> employee = criteria.from(Employee.class);
        Join<Employee, Organization> organization = employee.join("organization");

        Path<String> orgName = organization.get("name");
        criteria.select(employee).where(cb.equal(orgName, organizationName));

        em.getTransaction().commit();
        em.close();
        return em.createQuery(criteria).getResultList();
    }

    /**
     * Возвращает все выплаты, полученные сотрудниками организации с указанными именем,
     * упорядоченные по имени сотрудника, а затем по размеру выплаты
     */
    public List<Payment> findAllPaymentsByOrganizationName(String organizationName) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        em.getTransaction().begin();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Payment> criteria = cb.createQuery(Payment.class);
        Root<Payment> payment = criteria.from(Payment.class);
        Join<Payment, Employee> receiver = payment.join("receiver");
        Join<Employee, Organization> organization = receiver.join("organization");

        Path<String> orgName = organization.get("name");
        criteria.select(payment)
                .where(cb.equal(orgName, organizationName))
                .orderBy(
                        cb.asc(receiver.get("firstName")),
                        cb.asc(payment.get("amount"))
                );

        em.getTransaction().commit();
        em.close();
        return em.createQuery(criteria).getResultList();
    }

    /**
     * Возвращает среднюю зарплату сотрудника с указанными именем и фамилией
     */
    public Double findAveragePaymentAmountByFirstAndLastNames(String firstName, String lastName) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        em.getTransaction().begin();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Double> criteria = cb.createQuery(Double.class);
        Root<Payment> payment = criteria.from(Payment.class);
        Join<Payment, Employee> receiver = payment.join("receiver");

        criteria.select(cb.avg(payment.get("amount")))
                .where(cb.and(
                        cb.equal(receiver.get("firstName"), firstName),
                        cb.equal(receiver.get("lastName"), lastName)
                ));

        List<Double> results = em.createQuery(criteria).getResultList();

        em.getTransaction().commit();
        em.close();
        return results.size() > 0 ? results.get(0) : 0d;
    }

    /**
     * Возвращает для каждой организации: название, среднюю зарплату всех её сотрудников.
     * Организации упорядочены по названию.
     */
    public List<Object[]> findOrganizationNamesWithAvgEmployeePaymentsOrderedByOrgName() {
        EntityManager em = EntityManagerUtil.getEntityManager();
        em.getTransaction().begin();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteria = cb.createQuery(Object[].class);
        Root<Payment> payment = criteria.from(Payment.class);
        Join<Payment, Employee> receiver = payment.join("receiver");
        Join<Employee, Organization> organization = receiver.join("organization");

        Path<String> orgName = organization.get("name");
        Expression<Double> avgPayment = cb.avg(payment.get("amount"));
        criteria.multiselect(orgName, avgPayment)
                .groupBy(orgName)
                .orderBy(cb.asc(orgName));

        em.getTransaction().commit();
        em.close();
        return em.createQuery(criteria).getResultList();
    }

    /**
     * Возвращает список: сотрудник (объект Employee), средний размер выплат, но только для тех сотрудников,
     * чей средний размер выплат
     * больше среднего размера выплат всех сотрудников
     * Упорядочить по имени сотрудника
     */
    public List<Object[]> canYouDoIt() {
        EntityManager em = EntityManagerUtil.getEntityManager();
        em.getTransaction().begin();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteria = cb.createQuery(Object[].class);
        Root<Payment> payment = criteria.from(Payment.class);
        Join<Payment, Employee> receiver = payment.join("receiver");

        Subquery<Double> subquery = criteria.subquery(Double.class);
        Root<Payment> subqueryRoot = subquery.from(Payment.class);
        subquery.select(cb.avg(subqueryRoot.get("amount")));

        Expression<Double> avgPayment = cb.avg(payment.get("amount"));
        Path<String> employeeName = receiver.get("firstName");
        criteria.multiselect(receiver, avgPayment)
                .orderBy(cb.asc(employeeName))
                .groupBy(employeeName)
                .having(cb.gt(avgPayment, subquery));

        em.getTransaction().commit();
        em.close();
        return em.createQuery(criteria).getResultList();
    }
}
