package com.amr.project.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import springfox.documentation.annotations.ApiIgnore;

import javax.persistence.*;

@Entity
@Table(name = "role")
@Data
@NoArgsConstructor
@ApiIgnore
public class Role implements GrantedAuthority {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role_name", unique = true)
    private String name;

    @Override
    public String getAuthority() {
        return name;
    }
}
