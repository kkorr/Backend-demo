package com.amr.project.dao.impl;


import com.amr.project.dao.abstracts.ItemDao;
import com.amr.project.model.entity.Item;
import org.springframework.stereotype.Repository;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class ItemDaoImpl extends ReadWriteDAOImpl<Item, Long> implements ItemDao {
    @Override
    public Item findItemById(Long id) {
        return entityManager.createQuery("SELECT i from Item i where i.id = :id", Item.class)
                .setParameter("id", id).getResultList()
                .stream()
                .findFirst().orElse(new Item());
    }

    @Override
    public Item findItemByName(String name) {
        return entityManager.createQuery("SELECT i from Item i where i.name = :name", Item.class)
                .setParameter("name", name).getResultList()
                .stream()
                .findFirst().orElse(new Item());
    }

    @Override
    public List<Item> getItemsByShopId(Long id) {

        String HQL = "SELECT i FROM Item i LEFT JOIN FETCH i.shop s WHERE " +
                "s.id =:id ";

        TypedQuery<Item> query = entityManager.createQuery(HQL, Item.class);
        query.setParameter("id", id);


        return query.getResultList();

    }
}

