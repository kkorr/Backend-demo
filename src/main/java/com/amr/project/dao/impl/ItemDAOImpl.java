package com.amr.project.dao.impl;

import com.amr.project.dao.abstracts.ItemDAO;
import com.amr.project.model.entity.Item;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class ItemDAOImpl implements ItemDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Item> allItem() {
        return null;
    }

    @Override
    public Item itemById(int id) {
        TypedQuery<Item> q = entityManager.createQuery("select item from Item item where item.id = :id", Item.class);
        q.setParameter("id", id);
        Item result = q.getResultList().stream().filter(item -> item.getId() == id).findAny().orElse(null);
        return result;
    }

    @Override
    public void save(Item item) {
        entityManager.persist(item);
    }

    @Override
    public void update(Item item) {
        entityManager.merge(item);
    }

    @Override
    public void delete(Item item) {
        entityManager.remove(item);
    }
}
