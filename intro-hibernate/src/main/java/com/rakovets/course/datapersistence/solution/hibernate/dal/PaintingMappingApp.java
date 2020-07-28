package com.rakovets.course.datapersistence.solution.hibernate.dal;

import com.rakovets.course.datapersistence.solution.hibernate.dal.dao.PaintingDao;
import com.rakovets.course.datapersistence.solution.hibernate.dal.entity.Painting;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class PaintingMappingApp {
    static Logger logger = LogManager.getLogger(PaintingMappingApp.class);

    public static void main(String[] args) {
        Painting painting = new Painting();
        painting.setName("Wave");
        painting.setNameAuthor("No name");


        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        logger.debug("Open session");

        Transaction transaction = session.beginTransaction();
        logger.info("Transaction start");

        PaintingDao paintingDao = new PaintingDao();
        paintingDao.createPainting(painting, session);
        logger.debug("Create painting in DB");
        Painting paintingFromDb = paintingDao.readPainting(1, session);
        logger.debug("Read painting from DB");
        transaction.commit();
        logger.info("Transaction commit");

        session.close();
        logger.info("Close session");
        sessionFactory.close();
        logger.info("Close session factory");
        System.out.println(paintingFromDb);
    }
}
