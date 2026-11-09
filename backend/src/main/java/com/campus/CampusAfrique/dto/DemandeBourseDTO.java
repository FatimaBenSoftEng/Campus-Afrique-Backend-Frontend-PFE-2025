package com.campus.CampusAfrique.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DemandeBourseDTO {
    private Long id;
    private LocalDate dateDemande;
    private String statut;
    private Long etudiantId;
    private Long organismeBourseId;
    // Champs ajoutés :
    private String typeBourse;
    private String anneeUniversitaire;
    private String etablissementCible;
    // Ajouté pour affichage
    private String organismeNom;

    public DemandeBourseDTO(Long id, LocalDate dateDemande, String statut,
                            Long etudiantId, Long organismeBourseId,
                            String typeBourse, String anneeUniversitaire, String etablissementCible) {
        this.id = id;
        this.dateDemande = dateDemande;
        this.statut = statut;
        this.etudiantId = etudiantId;
        this.organismeBourseId = organismeBourseId;
        this.typeBourse = typeBourse;
        this.anneeUniversitaire = anneeUniversitaire;
        this.etablissementCible = etablissementCible;
    }

}
