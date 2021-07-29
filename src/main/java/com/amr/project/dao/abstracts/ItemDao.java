package com.amr.project.dao.abstracts;

import com.amr.project.model.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author denisqaa on 28.07.2021.
 * @project platform
 */
public interface ItemDao extends JpaRepository<Item, Long> {
    Item findItemById(Long id);
    Item findItemByName(String name);
}
