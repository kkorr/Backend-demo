package com.amr.project.dao.impl;


import com.amr.project.dao.abstracts.ItemDao;
import com.amr.project.model.entity.Item;
import com.amr.project.model.entity.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ItemDaoImpl extends ReadWriteDAOImpl<Item, Long> implements ItemDao {
}
