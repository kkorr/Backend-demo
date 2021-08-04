package com.amr.project.model.dto;

import com.amr.project.model.entity.*;
import com.amr.project.model.enums.Gender;
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

    private Address address;

    private Collection<Role> roles;

    private Gender gender;

    private Calendar birthday;

    private Image images;

    private Collection<Coupon> coupons;

//    private Collection<Item> cart;

    private Collection<Order> orders;

    private Collection<Review> reviews;

    private Collection<Shop> shops;
}
