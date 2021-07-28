package com.amr.project.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "discount")
@Data
@NoArgsConstructor
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "min_order")
    private int minOrder;

    @Column(name = "percentage")
    private int percentage;

    @Column(name = "fixed_discount")
    private int fixedDiscount;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Shop shop;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

}
