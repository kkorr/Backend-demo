package com.amr.project.dao.impl;

import com.amr.project.dao.abstracts.ShowcaseShopDao;
import com.amr.project.model.entity.Shop;
import org.springframework.stereotype.Repository;

@Repository
public class ShowcaseShopDaoImpl extends ReadWriteDAOImpl<Shop, Long> implements ShowcaseShopDao {
}
