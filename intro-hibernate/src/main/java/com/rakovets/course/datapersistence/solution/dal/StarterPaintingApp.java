package com.rakovets.course.datapersistence.solution.dal;

import com.rakovets.course.datapersistence.solution.dal.dao.PaintingDao;
import com.rakovets.course.datapersistence.solution.dal.entity.Painting;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class StarterPaintingApp {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        PaintingDao paintingDao = new PaintingDao();
        paintingDao.createPainting(new Painting("Mikhail Bulgakov", "Master and Margarita"), session);
        paintingDao.createPainting(new Painting("Nikolai Gogol", "Dead Souls"), session);
        paintingDao.createPainting(new Painting("Mikhail Bulgakov", "Heart of a Dog"), session);
        System.out.println(paintingDao.readPainting(1, session));
        paintingDao.updatePainting(new Painting(1,"Fyodor Dostoevsky",
                "Crime and Punishment"), session);
        paintingDao.deletePainting(new Painting(1,"Mikhail Bulgakov",
                "Master and Margarita"), session);
        transaction.commit();
        session.close();
    }
}
