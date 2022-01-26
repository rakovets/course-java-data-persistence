package by.rakovets.course.datapersistence.example.mapping.inheritance.example2;

import by.rakovets.course.datapersistence.example.mapping.inheritance.example2.dal.entity.ArmorType;
import by.rakovets.course.datapersistence.example.mapping.inheritance.example2.dal.entity.ArmoredEnemyEntity;
import by.rakovets.course.datapersistence.example.mapping.inheritance.example2.dal.entity.EnemyEntity;
import by.rakovets.course.datapersistence.example.mapping.inheritance.example2.dal.entity.InvisibleEnemyEntity;
import by.rakovets.course.datapersistence.example.mapping.inheritance.example2.dal.entity.RangedEnemyEntity;
import by.rakovets.course.datapersistence.example.mapping.inheritance.example2.dal.entity.WeaponType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;


public class EnemiesTest {
    private static SessionFactory SESSION_FACTORY;

    @BeforeAll
    public static void init() {
        SESSION_FACTORY = new Configuration().configure().buildSessionFactory();
    }

    @Test
    public void testSaveEnemies() {
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = session.beginTransaction();

        ArmoredEnemyEntity armoredEnemy = new ArmoredEnemyEntity();
        armoredEnemy.setArmorType(ArmorType.STEEL);
        armoredEnemy.setArmorDurability(100);
        armoredEnemy.setName("Bozo");
        session.save(armoredEnemy);

        RangedEnemyEntity rangedEnemy = new RangedEnemyEntity();
        rangedEnemy.setRangeOfFire(30);
        rangedEnemy.setWeaponType(WeaponType.BOW);
        rangedEnemy.setName("Archer");
        session.save(rangedEnemy);

        InvisibleEnemyEntity invisibleEnemy = new InvisibleEnemyEntity();
        invisibleEnemy.setInvisibilityDuration(10);
        invisibleEnemy.setName("Ghost");
        session.save(invisibleEnemy);

        transaction.commit();
        session.close();
    }

    @Test
    public void testGetAllEnemies() {
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = session.beginTransaction();

        List<EnemyEntity> enemies = session
                .createQuery("from EnemyEntity", EnemyEntity.class)
                .getResultList();
        enemies.forEach(System.out::println);

        transaction.commit();
        session.close();
    }

    @AfterAll
    public static void finish() {
        SESSION_FACTORY.close();
    }
}
