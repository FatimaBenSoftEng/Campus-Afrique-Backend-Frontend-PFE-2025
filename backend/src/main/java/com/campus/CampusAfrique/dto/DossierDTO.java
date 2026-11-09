package com.campus.CampusAfrique.dto;

import com.campus.CampusAfrique.model.TypeDossier;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DossierDTO {
    private Long id;
    private LocalDate dateCreation;
    private String statut;
    private LocalDate dateMiseAJour;
    private Long etudiantId;
    private Long preinscriptionId;
    private TypeDossier typeDossier; // avec enum: PREINSCRIPTION, BOURSE, etc.

}
