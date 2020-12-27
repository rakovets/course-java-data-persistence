package com.rakovets.course.datapersistence.mapping.inheritance.enemy;

import com.rakovets.course.datapersistence.mapping.inheritance.enemy.dal.entity.ArmorType;
import com.rakovets.course.datapersistence.mapping.inheritance.enemy.dal.entity.ArmoredEnemyEntity;
import com.rakovets.course.datapersistence.mapping.inheritance.enemy.dal.entity.EnemyEntity;
import com.rakovets.course.datapersistence.mapping.inheritance.enemy.dal.entity.InvisibleEnemyEntity;
import com.rakovets.course.datapersistence.mapping.inheritance.enemy.dal.entity.RangedEnemyEntity;
import com.rakovets.course.datapersistence.mapping.inheritance.enemy.dal.entity.WeaponType;
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
