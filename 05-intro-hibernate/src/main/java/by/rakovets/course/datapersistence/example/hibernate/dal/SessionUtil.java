package by.rakovets.course.datapersistence.example.hibernate.dal;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class SessionUtil {
    private static final Object LOCK = new Object();
    private static SessionFactory INSTANCE = null;

    private static SessionFactory getInstance() {
        if (INSTANCE == null) {
            synchronized (LOCK) {
                if (INSTANCE == null) {
                    INSTANCE = new Configuration().configure().buildSessionFactory();
                }
            }
        }
        return INSTANCE;
    }

    public static Session openSession() {
        return getInstance().openSession();
    }
}
