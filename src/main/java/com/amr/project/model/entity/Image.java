package com.amr.project.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import springfox.documentation.annotations.ApiIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "image")
@Data
@NoArgsConstructor
@ApiIgnore
public class Image {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String url;

    @Lob
    @Column(length = Integer.MAX_VALUE)
    private byte[] picture;

    @Column(name = "is_main")
    private Boolean isMain = false;

}
