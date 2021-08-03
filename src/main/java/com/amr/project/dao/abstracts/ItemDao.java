package com.amr.project.dao.abstracts;

import com.amr.project.model.entity.Item;

import java.util.List;

public interface ItemDao extends ReadWriteDAO<Item, Long>  {
    List<Item> getItemsByShopId(Long shopId);
}
