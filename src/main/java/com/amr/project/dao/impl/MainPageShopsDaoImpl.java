package com.amr.project.dao.impl;

import com.amr.project.dao.abstracts.MainPageShopsDao;
import com.amr.project.model.entity.Shop;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MainPageShopsDaoImpl extends ReadWriteDAOImpl<Shop, Long> implements MainPageShopsDao {

    /**
     * Метод используеся для поиска 5 популярным магазинов
     * @return
     */
    @Override
    public List<Shop> findPopularShops() {
        return entityManager.createQuery("SELECT s FROM Shop s WHERE s.isModerated = true AND s.isModerateAccept = true ORDER BY s.count DESC")
                .setMaxResults(5).getResultList();
    }
}
