package com.rakovets.course.datapersistence.solution.dal;

import com.rakovets.course.datapersistence.solution.dal.dao.PaintingDao;
import com.rakovets.course.datapersistence.solution.dal.entity.Painting;

import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class StarterPaintingApp {
    static Logger logger = LogManager.getLogger(StarterPaintingApp.class);

    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        logger.info("begin transaction");
        PaintingDao paintingDao = new PaintingDao();
        paintingDao.create(new Painting("Mikhail Bulgakov", "Master and Margarita"), session);
        paintingDao.create(new Painting("Nikolai Gogol", "Dead Souls"), session);
        paintingDao.create(new Painting("Mikhail Bulgakov", "Heart of a Dog"), session);
        Painting read = paintingDao.read(1, session);
        System.out.println(read);
        read.setAuthorName("Fyodor Dostoevsky");
        read.setName("Crime and Punishment");
        paintingDao.update(read, session);
        paintingDao.delete(read, session);
        transaction.commit();
        session.close();
    }
}
