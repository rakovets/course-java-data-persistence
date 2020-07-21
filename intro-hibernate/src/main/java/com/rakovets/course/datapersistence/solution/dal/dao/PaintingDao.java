package com.rakovets.course.datapersistence.solution.dal.dao;

import com.rakovets.course.datapersistence.solution.dal.entity.Painting;
import org.hibernate.Session;

public class PaintingDao extends Dao<Painting>{
    @Override
    public void create(Painting painting, Session session) {
        session.saveOrUpdate(painting);
    }

    @Override
    public Painting read(int id, Session session) {
        return session.find(Painting.class, id);
    }

    @Override
    public void update(Painting painting, Session session) {
        session.update(painting);
    }

    @Override
    public void delete(Painting painting, Session session) {
        session.delete(painting);
    }
}
