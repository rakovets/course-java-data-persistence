package by.rakovets.course.datapersistence.mapping.inheritance;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Application {
    public static void main(String[] args) {
        SessionFactory SESSION_FACTORY = new Configuration().configure().buildSessionFactory();
    }
}
