package com.amr.project.service.abstracts;

import com.amr.project.model.entity.Item;

/**
 * @author denisqaa on 28.07.2021.
 * @project platform
 */
public interface ItemService extends ReadWriteService<Item, Long> {
    Item findItemById(Long id);

    Item findItemByName(String name);

}
