package com.amr.project.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import springfox.documentation.annotations.ApiIgnore;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;

@Entity
@Table(name = "item")
@Data
@NoArgsConstructor
@ApiIgnore
public class Item {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private BigDecimal price;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "item_category",
            joinColumns = {@JoinColumn(name = "item_id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id")})
    private Collection<Category> categories;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "item_image",
            joinColumns = {@JoinColumn(name = "item_id")},
            inverseJoinColumns = {@JoinColumn(name = "image_id")})
    private Collection<Image> images;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "item_review",
            joinColumns = {@JoinColumn(name = "item_id")},
            inverseJoinColumns = {@JoinColumn(name = "review_id")})
    private Collection<Review> reviews;
    @Column
    private int count;
    @Column
    private double rating;
    @Column
    private String description;
    @Column
    private int discount;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Shop shop;
    @Column(name = "is_moderated")
    private boolean isModerated = false;
    @Column(name = "is_moderate_accept")
    private boolean isModerateAccept = false;
    @Column(name = "moderated_reject_reason")
    private String moderatedRejectReason;
    @Column(name = "is_pretendent_to_be_deleted")
    private boolean isPretendentToBeDeleted = false;

    public Item(String name, BigDecimal price, int count, double rating, String description) {
        this.name = name;
        this.price = price;
        this.count = count;
        this.rating = rating;
        this.description = description;
    }
}
