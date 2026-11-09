package com.campus.CampusAfrique.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GaleriePhotoDTO {
    private Long id;
    private String titre;
    private String description;
    private String imageUrl;
}
