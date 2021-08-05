package com.amr.project.dao.impl;

import com.amr.project.dao.abstracts.MainPageItemsDAO;
import com.amr.project.model.entity.Item;
import org.springframework.stereotype.Repository;

@Repository
public class MainPageItemsDaoImpl extends ReadWriteDAOImpl<Item,Long> implements MainPageItemsDAO {
}
