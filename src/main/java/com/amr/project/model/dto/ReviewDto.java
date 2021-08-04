package com.amr.project.model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReviewDto {
    Long id;
    String dignity;
    String flaw;
    String text;
    Date date;
    int likes;
    int dislikes;
    int rating;
    Long userId;
    String userFirstName;
    String userLastName;
    String user_email;
    Long itemId;
    String itemName;
    Long shopId;
    String shopName;
    boolean isModerated;
    boolean isModerateAccept;
    String moderatedRejectReason;
}
