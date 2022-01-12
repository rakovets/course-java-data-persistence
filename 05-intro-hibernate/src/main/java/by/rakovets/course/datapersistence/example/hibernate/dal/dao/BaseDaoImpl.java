package by.rakovets.course.datapersistence.example.hibernate.dal.dao;

import by.rakovets.course.datapersistence.example.hibernate.dal.SessionUtil;
import by.rakovets.course.datapersistence.example.hibernate.dal.entity.BaseEntity;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;

import java.util.List;

public class BaseDaoImpl<T extends BaseEntity> implements BaseDao<T> {
    private final Class<T> clazz;

    public BaseDaoImpl(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public void save(T entity) {
        Session session = SessionUtil.openSession();
        session.getTransaction().begin();

        session.save(entity);
        session.flush();

        session.getTransaction().commit();
        session.close();
    }

    @Override
    public T findOne(Long id) {
        Session session = SessionUtil.openSession();
        session.getTransaction().begin();

        T entity = session.find(clazz, id);

        session.getTransaction().commit();
        return entity;
    }

    @Override
    public List<T> findAll() {
        Session session = SessionUtil.openSession();
        session.getTransaction().begin();

        TypedQuery<T> query = session.createQuery(
                String.format("select entity from %s entity", clazz.getSimpleName()), clazz);

        session.getTransaction().commit();
        return query.getResultList();
    }

    @Override
    public void update(T entity) {
        Session session = SessionUtil.openSession();
        session.getTransaction().begin();

        session.merge(entity);

        session.getTransaction().commit();
    }

    @Override
    public void delete(T entity) {
        Session session = SessionUtil.openSession();
        session.getTransaction().begin();

//        T merged = session.merge(entity);
//        session.remove(merged);
        session.delete(entity);

        session.getTransaction().commit();
    }
}
