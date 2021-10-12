package com.amr.project.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ImageDto {
    Long id;
    String url;
    byte[] picture;
}
