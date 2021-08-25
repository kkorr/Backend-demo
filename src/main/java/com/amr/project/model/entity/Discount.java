package com.amr.project.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;


@Entity
@Table(name = "discount")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@EqualsAndHashCode
@ToString
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
