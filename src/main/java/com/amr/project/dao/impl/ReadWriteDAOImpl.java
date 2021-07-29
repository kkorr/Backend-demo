package com.amr.project.dao.impl;

import com.amr.project.dao.abstracts.ReadWriteDAO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.ParameterizedType;
import java.util.List;

@Repository
public abstract class ReadWriteDAOImpl<E,K> implements ReadWriteDAO<E,K> {

    @PersistenceContext
    private EntityManager entityManager;

    private Class<E> clazz;

    public ReadWriteDAOImpl() {
        this.clazz = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
    }


    @Override
    @Transactional
    public void persist(E e) {
        entityManager.persist(e);
    }

    @Override
    @Transactional
    public void update(E e) {
        entityManager.merge(e);
    }

    @Override
    @Transactional
    public void delete(E e) {
        entityManager.remove(e);
    }

    @Override
    @Transactional
    public void deleteByKeyCascadeEnable(K id) {
        entityManager.remove(entityManager.find(clazz, id));
    }

    @Override
    @Transactional
    public void deleteByKeyCascadeIgnore(K id) {
        Query query = entityManager.createQuery(
                "DELETE FROM " + clazz.getName() + " e WHERE e.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public boolean existsById(K id) {
        long count = (long) entityManager.createQuery("SELECT COUNT(e) FROM " + clazz.getName() + " e WHERE e.id =: id").setParameter("id", id).getSingleResult();
        return count > 0;
    }

    @Override
    public E getByKey(K id) {
        return entityManager.find(clazz, id);
    }

    @Override
    public List<E> getAll() {
        return entityManager.createQuery("from " + clazz.getName()).getResultList();
    }
}
