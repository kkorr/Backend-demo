package com.amr.project.model.entity;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "address")
@NoArgsConstructor
@Data
@Hidden
public class Address {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "city_index")
    private String cityIndex;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Country country;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private City city;

    @Column
    private String street;

    @Column
    private String house;

}
