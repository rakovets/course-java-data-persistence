package by.rakovets.course.datapersistence.example.transactions.dao;

import by.rakovets.course.datapersistence.example.transactions.entity.Employee;
import by.rakovets.course.datapersistence.example.transactions.entity.Payment;
import by.rakovets.course.datapersistence.example.transactions.util.EmployeeTestDataImporter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;


public class EmployeeDaoTest {

    @BeforeAll
    static void init() {
        EmployeeTestDataImporter.getInstance().importTestData();
    }

    @Test
    public void testFindAll() {
        List<Employee> results = EmployeeDao.getInstance().findAll();
        List<String> fullNames
                = results.stream().map(Employee::fullName).collect(toList());
        assertThat(results, hasSize(5));
        assertThat(fullNames, containsInAnyOrder("Bill Gates", "Steve Jobs", "Sergey Brin", "Tim Cook", "Diane Greene"));
    }

    @Test
    public void testFindAllByFirstName() {
        List<Employee> results = EmployeeDao.getInstance().findAllByFirstName("Bill");
        assertThat(results, hasSize(1));
        assertThat(results.get(0).getLastName(), equalTo("Gates"));
    }

    @Test
    public void testFindFirstThreeEmployeesOrderedByBirthday() {
        List<Employee> results = EmployeeDao.getInstance().findLimitedEmployeesOrderedByBirthday(3);
        assertThat(results, hasSize(3));
        List<String> fullNames
                = results.stream().map(Employee::fullName).collect(toList());
        assertThat(fullNames, contains("Diane Greene", "Steve Jobs", "Bill Gates"));
    }

    @Test
    public void testAllFindByOrganizationName() {
        List<Employee> results = EmployeeDao.getInstance().findAllByOrganizationName("Google");
        assertThat(results, hasSize(2));
        List<String> fullNames
                = results.stream().map(Employee::fullName).collect(toList());
        assertThat(fullNames, containsInAnyOrder("Sergey Brin", "Diane Greene"));
    }

    @Test
    public void testFindAllPaymentsByOrganizationName() {
        List<Payment> applePayments = EmployeeDao.getInstance().findAllPaymentsByOrganizationName("Apple");
        assertThat(applePayments, hasSize(5));
        List<Integer> amounts = applePayments.stream().map(p -> p.getAmount().intValue()).collect(toList());
        assertThat(amounts, contains(250, 500, 600, 300, 400));
    }

    @Test
    public void testFindAveragePaymentAmountByFirstAndLastNames() {
        Double averagePaymentAmount = EmployeeDao
                .getInstance()
                .findAveragePaymentAmountByFirstAndLastNames("Bill", "Gates");
        assertThat(averagePaymentAmount, equalTo(300.0d));
    }

    @Test
    public void testFindOrganizationNamesWithAvgEmployeePaymentsOrderedByOrgName() {
        List<Object[]> results = EmployeeDao
                .getInstance()
                .findOrganizationNamesWithAvgEmployeePaymentsOrderedByOrgName();
        assertThat(results, hasSize(3));
        List<String> orgNames = results.stream().map(a -> (String) a[0]).collect(toList());
        assertThat(orgNames, contains("Apple", "Google", "Microsoft"));

        List<Double> orgAvgPayments = results.stream().map(a -> (Double) a[1]).collect(toList());
        assertThat(orgAvgPayments, contains(410.0d, 400.0d, 300.0d));
    }

    @Test
    @Disabled
    public void testCanYouDoIt() {
        List<Object[]> results = EmployeeDao.getInstance().canYouDoIt();
        assertThat(results, hasSize(2));

        List<String> names = results.stream().map(r -> ((Employee) r[0]).fullName()).collect(toList());
        assertThat(names, contains("Sergey Brin", "Steve Jobs"));

        List<Double> averagePayments = results.stream().map(r -> (Double) r[1]).collect(toList());
        assertThat(averagePayments, contains(500.0d, 450.0d));
    }
}
