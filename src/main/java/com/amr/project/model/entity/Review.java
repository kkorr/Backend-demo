package com.amr.project.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import springfox.documentation.annotations.ApiIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "review")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@ApiIgnore
@Builder
@AllArgsConstructor
@ToString(exclude = "user")
public class Review {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String dignity; //плюсы
    String flaw; //минусы
    String text;
    Date date;
    int rating;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private User user;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Shop shop;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "item_review", joinColumns = @JoinColumn(name = "review_id"), inverseJoinColumns = @JoinColumn(name = "item_id"))
    private Item item;

    @Column
    private boolean isModerated = false;

    @Column
    private boolean isModerateAccept = false;

    @Column
    private String moderatedRejectReason;

    public void setRating(int rating) {
        if (rating < 0) {
            this.rating = 0;
        } else if (rating > 5) {
            this.rating = 5;
        } else {
            this.rating = rating;
        }
    }
}
