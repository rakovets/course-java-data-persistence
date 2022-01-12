package by.rakovets.course.datapersistence.mapping.association.hero;

import by.rakovets.course.datapersistence.mapping.association.hero.entity.Inventory;
import by.rakovets.course.datapersistence.mapping.association.hero.entity.Item;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


public class InventoryItemsTest {
    private static SessionFactory SESSION_FACTORY;

    @BeforeAll
    public static void init() {
        SESSION_FACTORY = new Configuration().configure().buildSessionFactory();
    }

    @Test
    public void testSaveInventoryAndItem() {
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = session.beginTransaction();

        Inventory inventory = new Inventory();
        inventory.setMaxSize(30);
        session.save(inventory);

        Item item = new Item();
        item.setName("Healing potion");
        item.setInventory(inventory);
        session.save(item);

        transaction.commit();
        session.close();
    }

    @Test
    public void getInventoryById() {
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = session.beginTransaction();

        Inventory inventory = session.load(Inventory.class, 2L);

        transaction.commit();
        session.close();
    }

    @AfterAll
    public static void finish() {
        SESSION_FACTORY.close();
    }
}
