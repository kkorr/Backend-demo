package com.amr.project.dao.abstracts;

import com.amr.project.model.entity.Shop;

import java.util.List;

public interface MainPageShopsDao extends ReadWriteDAO<Shop, Long> {
    List<Shop> findPopularShops();
}
