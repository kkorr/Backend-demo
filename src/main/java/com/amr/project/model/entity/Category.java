package com.amr.project.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.Api;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "category")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Data
@Api(hidden = true)
@Builder
public class Category {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

}
