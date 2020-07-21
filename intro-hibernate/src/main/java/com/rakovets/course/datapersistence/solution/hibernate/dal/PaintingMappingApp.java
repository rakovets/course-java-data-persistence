package com.rakovets.course.datapersistence.solution.hibernate.dal;

import com.rakovets.course.datapersistence.solution.hibernate.dal.dao.PaintingDao;
import com.rakovets.course.datapersistence.solution.hibernate.dal.entity.Painting;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class PaintingMappingApp {
    public static void main(String[] args) {
        Painting painting = new Painting();
        painting.setName("Wave");
        painting.setNameAuthor("No name");


        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        PaintingDao paintingDao = new PaintingDao();
        paintingDao.createPainting(painting, session);
        Painting paintingFromDb = paintingDao.readPainting(1, session);
        transaction.commit();

        session.close();
        sessionFactory.close();
        System.out.println(paintingFromDb);
    }
}
