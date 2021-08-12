package com.amr.project.service.abstracts;

import com.amr.project.model.entity.Favorite;
import com.amr.project.model.entity.User;

import java.util.Optional;

public interface FavoriteService extends ReadWriteService<Favorite, Long> {

    Optional<Favorite> findByUser(User user);
}
