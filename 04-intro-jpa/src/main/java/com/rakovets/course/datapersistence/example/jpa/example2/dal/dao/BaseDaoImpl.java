package com.rakovets.course.datapersistence.example.jpa.example2.dal.dao;

import com.rakovets.course.datapersistence.example.jpa.example1.util.DynamicEntityManagerFactoryUtil;
import com.rakovets.course.datapersistence.example.jpa.example2.dal.entity.BaseEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class BaseDaoImpl<T extends BaseEntity> implements BaseDao<T> {
    private final Class<T> clazz;

    public BaseDaoImpl(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public void save(T entity) {
        EntityManager em = DynamicEntityManagerFactoryUtil.getEntityManager();
        em.getTransaction().begin();

        em.persist(entity);

        em.getTransaction().commit();
    }

    @Override
    public T findOne(Long id) {
        EntityManager em = DynamicEntityManagerFactoryUtil.getEntityManager();
        em.getTransaction().begin();


        T entity = em.find(clazz, id);

        em.getTransaction().commit();
        return entity;
    }

    @Override
    public List<T> findAll() {
        EntityManager em = DynamicEntityManagerFactoryUtil.getEntityManager();
        em.getTransaction().begin();


        TypedQuery<T> query = em.createQuery(
                String.format("select entity from %s entity", clazz.getSimpleName()), clazz);

        em.getTransaction().commit();
        return query.getResultList();
    }

    @Override
    public void update(T entity) {
        EntityManager em = DynamicEntityManagerFactoryUtil.getEntityManager();
        em.getTransaction().begin();

        em.merge(entity);

        em.getTransaction().commit();
    }

    @Override
    public void delete(T entity) {
        EntityManager em = DynamicEntityManagerFactoryUtil.getEntityManager();
        em.getTransaction().begin();

        T merged = em.merge(entity);
        em.remove(merged);

        em.getTransaction().commit();
    }

}
