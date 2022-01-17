package by.rakovets.course.datapersistence.example.mapping.basic;

import by.rakovets.course.datapersistence.example.mapping.basic.dal.entity.EquipmentSet;
import by.rakovets.course.datapersistence.example.mapping.basic.dal.entity.Gender;
import by.rakovets.course.datapersistence.example.mapping.basic.dal.entity.HeroEntity;
import jakarta.persistence.TypedQuery;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.TimeZone;

public class Example1 {
    private static final Logger LOGGER = LogManager.getLogger(Example1.class);
    private static final SessionFactory SESSION_FACTORY
            = new Configuration().configure().buildSessionFactory();

    public void show() {
        TimeZone.setDefault(TimeZone.getTimeZone(ZoneOffset.UTC));
        try (Session session = SESSION_FACTORY.openSession()) {
            Transaction transaction = session.beginTransaction();

            HeroEntity heroEntity = new HeroEntity();
            heroEntity.setName("Legolas");
            heroEntity.setGender(Gender.MALE);
            heroEntity.setBirthday(LocalDate.of(345, 3, 19));
            heroEntity.setAttackDuration("P15M");
            heroEntity.setCreationTime(ZonedDateTime.now(ZoneOffset.UTC));
            heroEntity.setFirstEquipmentSet(new EquipmentSet("Sword", "Short bow"));
            heroEntity.setSecondEquipmentSet(new EquipmentSet("Dagger", "Long bow"));
            session.saveOrUpdate(heroEntity);

            transaction.commit();

            transaction = session.beginTransaction();

            TypedQuery<HeroEntity> query = session.createQuery("SELECT he FROM HeroEntity he", HeroEntity.class);

            transaction.commit();
            query.getResultStream()
                    .forEach(LOGGER::debug);
        }
        SESSION_FACTORY.close();
    }
}
