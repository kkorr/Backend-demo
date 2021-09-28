package com.amr.project.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "country")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ApiIgnore
@Builder
public class Country {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "country_city",
            joinColumns = {@JoinColumn(name = "country_id")},
            inverseJoinColumns = {@JoinColumn(name = "city_id")})
    private Collection<City> cities;

    public Country (String name) {
        this.name = name;
    }

}
