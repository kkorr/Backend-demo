package com.amr.project.dao.impl;

import com.amr.project.dao.abstracts.CountryDao;
import com.amr.project.dao.abstracts.FavoriteDao;
import com.amr.project.model.entity.CartItem;
import com.amr.project.model.entity.Country;
import com.amr.project.model.entity.Favorite;
import com.amr.project.model.entity.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class FavoriteDaoImpl extends ReadWriteDAOImpl<Favorite, Long> implements FavoriteDao {

    @Override
    public Optional<Favorite> findByUser(User user) {
        return entityManager.createQuery("select f from Favorite f where f.user.username = :username", Favorite.class)
                .setParameter("username", user.getUsername())
                .getResultStream().findAny();
    }
}
