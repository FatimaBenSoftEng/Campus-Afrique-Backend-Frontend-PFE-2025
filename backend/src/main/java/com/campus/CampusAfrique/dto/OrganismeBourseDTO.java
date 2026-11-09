package com.campus.CampusAfrique.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrganismeBourseDTO {
    private Long id;
    private String nom;
    private String type;
    private String pays;
    private String siteWeb;
}
