package com.amr.project.model.entity;

import io.swagger.annotations.Api;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "сategory")
@NoArgsConstructor
@Data
@Api(hidden = true)
public class Category {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;
}
