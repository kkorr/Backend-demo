package com.amr.project.service.abstracts;

import com.amr.project.model.dto.ItemDto;
import com.amr.project.model.entity.Item;

import java.util.List;

/**
 * @author denisqaa on 28.07.2021.
 * @project platform
 */
public interface ItemService {
    Item findItemById(Long id);

    Item findItemByName(String name);

    void save(Item item);

    void delete(Long id);

    void update(Item item);

    List<Item> findAll();
}
