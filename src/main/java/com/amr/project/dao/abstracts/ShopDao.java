package com.amr.project.dao.abstracts;

import com.amr.project.model.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author denisqaa on 28.07.2021.
 * @project platform
 */
public interface ShopDao extends  ReadWriteDAO<Shop, Long>{
    Shop findShopByName(String name);
    Shop findShopById(Long id);

}
