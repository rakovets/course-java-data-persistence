package by.rakovets.course.datapersistence.mapping.association.demo;

import by.rakovets.course.datapersistence.mapping.association.demo.entity.Employee;
import by.rakovets.course.datapersistence.mapping.association.demo.entity.Organization;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class DemoApp {
    private static final SessionFactory SESSION_FACTORY = new Configuration().configure().buildSessionFactory();

    public static void main(String[] args) {
        Session session = SESSION_FACTORY.openSession();

        Organization organization = session.find(Organization.class, 1L);
        if (organization == null) {
            organization = new Organization();
            organization.setName("rakovets-lab");
        }

        Employee employee = new Employee();
        employee.setOrganization(organization);
        employee.setName("Dmitry");
        session.save(employee);

        Employee removeEmployee = session.find(Employee.class, 2L);
        if (removeEmployee != null) {
            session.delete(removeEmployee);
        }

        session.close();
        SESSION_FACTORY.close();
    }
}
