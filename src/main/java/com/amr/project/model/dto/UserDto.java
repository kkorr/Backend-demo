package com.amr.project.model.dto;

import com.amr.project.model.entity.Address;
import com.amr.project.model.entity.Coupon;
import com.amr.project.model.entity.Image;
import com.amr.project.model.entity.Order;
import com.amr.project.model.entity.Review;
import com.amr.project.model.entity.Role;
import com.amr.project.model.entity.Shop;
import com.amr.project.model.enums.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Calendar;
import java.util.Collection;

/**
 * Created by Leanz on 18.05.2021
 **/

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {

    private Long id;

    private String email;

    private String username;

    private String password;

    private boolean activate;

    private String activationCode;

    private String phone;

    private String firstName;

    private String lastName;

    private int age;

    private AddressDto address;

    private Collection<Role> roles;

    private Gender gender;

    @JsonFormat(pattern="yyyy.MM.dd")
    private Calendar birthday;

    private Image images;

    private Collection<Coupon> coupons;

//    private Collection<Item> cart;

    private Collection<Order> orders;

    private Collection<Review> reviews;

    private Collection<Shop> shops;
}
