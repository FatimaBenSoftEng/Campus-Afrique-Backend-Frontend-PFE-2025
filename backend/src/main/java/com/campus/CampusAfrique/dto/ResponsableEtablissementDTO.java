package com.campus.CampusAfrique.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponsableEtablissementDTO {
    private Long id;
    private String nom;
    private String email;
    private String motDePasse;
    private Long etablissementId; // correspond à l’établissement lié
}
