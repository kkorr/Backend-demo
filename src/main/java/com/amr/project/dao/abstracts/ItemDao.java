package com.amr.project.dao.abstracts;

import com.amr.project.model.entity.Item;

/**
 * @author denisqaa on 28.07.2021.
 * @project platform
 */


public interface ItemDao extends ReadWriteDAO<Item, Long> {
    Item findItemById(Long id);

    Item findItemByName(String name);
}
