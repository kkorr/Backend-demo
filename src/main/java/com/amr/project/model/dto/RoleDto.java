package com.amr.project.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import springfox.documentation.annotations.ApiIgnore;

@NoArgsConstructor
@Getter
@Setter
@ApiIgnore
public class RoleDto {
    private Long id;
    private String name;
}
