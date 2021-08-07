package com.amr.project.model.dto;

import lombok.*;
import springfox.documentation.annotations.ApiIgnore;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ApiIgnore
public class CategoryDto {
    private Long id;
    private String name;
}
