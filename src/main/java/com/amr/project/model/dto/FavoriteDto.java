package com.amr.project.model.dto;

import com.amr.project.model.entity.Favorite;
import com.amr.project.model.entity.Item;
import com.amr.project.model.entity.Shop;
import com.amr.project.model.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Getter
@Setter
public class FavoriteDto {

    public FavoriteDto (Favorite fav) {
        this.items = fav.getItems();
        this.shops = fav.getShops();
    }

    private Collection<Shop> shops;

    private Collection<Item> items;
}