package by.rakovets.course.datapersistence.example.transactions.util;

import by.rakovets.course.datapersistence.example.transactions.entity.Employee;
import by.rakovets.course.datapersistence.example.transactions.entity.Gender;
import by.rakovets.course.datapersistence.example.transactions.entity.Organization;
import by.rakovets.course.datapersistence.example.transactions.entity.Payment;
import jakarta.persistence.EntityManager;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;


public final class EmployeeTestDataImporter {
    private static EmployeeTestDataImporter INSTANCE;

    private EmployeeTestDataImporter() {
    }

    public static EmployeeTestDataImporter getInstance() {
        if (INSTANCE == null) {
            synchronized (EmployeeTestDataImporter.class) {
                if (INSTANCE == null) {
                    INSTANCE = new EmployeeTestDataImporter();
                }
            }
        }
        return INSTANCE;
    }

    public void importTestData() {
        EntityManager em = EntityManagerUtil.getEntityManager();
        em.getTransaction().begin();

        Organization microsoft = saveOrganization(em, "Microsoft");
        Organization apple = saveOrganization(em, "Apple");
        Organization google = saveOrganization(em, "Google");

        Employee billGates = saveEmployee(em, "Bill", "Gates",
                LocalDate.of(1955, Month.OCTOBER, 28), Gender.MALE, microsoft);
        Employee steveJobs = saveEmployee(em, "Steve", "Jobs",
                LocalDate.of(1955, Month.FEBRUARY, 24), Gender.MALE, apple);
        Employee sergeyBrin = saveEmployee(em, "Sergey", "Brin",
                LocalDate.of(1973, Month.AUGUST, 21), Gender.MALE, google);
        Employee timCook = saveEmployee(em, "Tim", "Cook",
                LocalDate.of(1960, Month.NOVEMBER, 1), Gender.MALE, apple);
        Employee dianeGreene = saveEmployee(em, "Diane", "Greene",
                LocalDate.of(1955, Month.JANUARY, 1), Gender.FEMALE, google);

        savePayment(em, billGates, BigDecimal.valueOf(100));
        savePayment(em, billGates, BigDecimal.valueOf(300));
        savePayment(em, billGates, BigDecimal.valueOf(500));

        savePayment(em, steveJobs, BigDecimal.valueOf(250));
        savePayment(em, steveJobs, BigDecimal.valueOf(600));
        savePayment(em, steveJobs, BigDecimal.valueOf(500));

        savePayment(em, timCook, BigDecimal.valueOf(400));
        savePayment(em, timCook, BigDecimal.valueOf(300));

        savePayment(em, sergeyBrin, BigDecimal.valueOf(500));
        savePayment(em, sergeyBrin, BigDecimal.valueOf(500));
        savePayment(em, sergeyBrin, BigDecimal.valueOf(500));

        savePayment(em, dianeGreene, BigDecimal.valueOf(300));
        savePayment(em, dianeGreene, BigDecimal.valueOf(300));
        savePayment(em, dianeGreene, BigDecimal.valueOf(300));

        em.getTransaction().commit();
        em.close();
    }

    private Organization saveOrganization(EntityManager em, String name) {
        Organization organization = new Organization(name);
        em.persist(organization);
        return organization;
    }

    private Employee saveEmployee(EntityManager em, String firstName, String lastName,
                                  LocalDate birthday, Gender gender, Organization organization) {
        Employee employee = new Employee(firstName, lastName, birthday, gender, organization);
        em.persist(employee);
        return employee;
    }

    private void savePayment(EntityManager em, Employee employee, BigDecimal amount) {
        em.persist(new Payment(amount, employee));
    }
}
