package com.rakovets.course.datapersistence.solution.jpa.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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
