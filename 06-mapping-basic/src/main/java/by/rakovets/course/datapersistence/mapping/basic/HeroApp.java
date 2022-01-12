package by.rakovets.course.datapersistence.mapping.basic;

import by.rakovets.course.datapersistence.mapping.basic.dal.entity.EquipmentSet;
import by.rakovets.course.datapersistence.mapping.basic.dal.entity.Gender;
import by.rakovets.course.datapersistence.mapping.basic.dal.entity.HeroEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.time.LocalDateTime;

public class HeroApp {
    private static final SessionFactory SESSION_FACTORY
            = new Configuration().configure().buildSessionFactory();

    public static void main(String[] args) {
        try (Session session = SESSION_FACTORY.openSession()) {
            Transaction transaction = session.beginTransaction();

            HeroEntity heroEntity = new HeroEntity();
            heroEntity.setName("Legolas");
            heroEntity.setGender(Gender.MALE);
            heroEntity.setCreationTime(LocalDateTime.now());
            heroEntity.setFirstEquipmentSet(new EquipmentSet("Sword", "Short bow"));
            heroEntity.setSecondEquipmentSet(new EquipmentSet("Dagger", "Long bow"));
            session.saveOrUpdate(heroEntity);

            transaction.commit();

            transaction = session.beginTransaction();

            heroEntity = session.get(HeroEntity.class, 1L);
            System.out.println(heroEntity);

            transaction.commit();

        }
        SESSION_FACTORY.close();
    }
}
