package by.rakovets.course.datapersistence.example.jpa.example1.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class DynamicEntityManagerFactoryUtil {
    private static EntityManagerFactory entityManagerFactory;

    static {
        entityManagerFactory = Persistence.createEntityManagerFactory("Hibernate");
    }

    public static void init(String persistenceUnit) {
        entityManagerFactory = Persistence.createEntityManagerFactory(persistenceUnit);
    }

    public static EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    public static void close() {
        entityManagerFactory.close();
    }
}
