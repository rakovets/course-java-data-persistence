package com.rakovets.course.datapersistence.solution.hibernate.dal.dao;

import com.rakovets.course.datapersistence.solution.hibernate.dal.entity.Painting;
import org.hibernate.Session;

public class PaintingDao extends Dao<Painting> {
    @Override
    public void createPainting(Painting painting, Session session) {
        session.save(painting);
    }

    @Override
    public Painting readPainting(int id, Session session) {
        return session.find(Painting.class, id);
    }

    @Override
    public void updatePainting(Painting painting, Session session) {
        session.update(painting);
    }

    @Override
    public void deletePainting(Painting painting, Session session) {
        session.delete(painting);
    }
}
