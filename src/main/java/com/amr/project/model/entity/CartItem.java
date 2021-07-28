package com.amr.project.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by Leanz on 26.05.2021
 **/

@Entity
@Table(name = "cart_items")
@Data
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private Shop shop;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private int quantity;

    @Transient
    public BigDecimal getSubTotal() {
        return this.item.getPrice().subtract(BigDecimal.valueOf(this.item.getDiscount())
                .multiply(this.item.getPrice())
                .divide(BigDecimal.valueOf(100)))
                .multiply(new BigDecimal(quantity)).setScale(2, RoundingMode.CEILING);
    }


}
