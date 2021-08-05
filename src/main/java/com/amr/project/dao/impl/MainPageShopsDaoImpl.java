package com.amr.project.dao.impl;

import com.amr.project.dao.abstracts.MainPageShopsDao;
import com.amr.project.model.entity.Shop;
import org.springframework.stereotype.Repository;

@Repository
public class MainPageShopsDaoImpl extends ReadWriteDAOImpl<Shop,Long> implements MainPageShopsDao {
}
