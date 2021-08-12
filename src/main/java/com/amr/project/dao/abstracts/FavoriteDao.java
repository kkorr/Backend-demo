package com.amr.project.dao.abstracts;

import com.amr.project.model.entity.CartItem;
import com.amr.project.model.entity.Favorite;
import com.amr.project.model.entity.User;

import java.util.Optional;

public interface FavoriteDao {

    Optional<Favorite> findByUser(User user);

}
