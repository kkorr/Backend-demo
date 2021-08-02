package com.amr.project.dao.impl;


import com.amr.project.dao.abstracts.ItemDao;
import com.amr.project.model.entity.Item;
import org.springframework.stereotype.Repository;

@Repository
public class ItemDaoImpl extends ReadWriteDAOImpl<Item, Long> implements ItemDao {
}
