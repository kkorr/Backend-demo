package com.amr.project.service.abstracts;


import com.amr.project.model.entity.Item;

import java.util.List;

public interface ItemService extends ReadWriteService<Item, Long> {
    @Override
    void persist(Item item);

    @Override
    void update(Item item);

    @Override
    void delete(Item item);

    @Override
    List<Item> getAll();

    @Override
    Item getByKey(Long id);

    @Override
    Item getByName(String name);
}
