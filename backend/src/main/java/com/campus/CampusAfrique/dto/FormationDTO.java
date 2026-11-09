// src/main/java/com/campus/CampusAfrique/dto/FormationDTO.java
package com.campus.CampusAfrique.dto;

import com.campus.CampusAfrique.model.Formation;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FormationDTO {
    private Long id;
    private String nom;
    private String description;
    private int duree;
    private double fraisAnnuel;
    private Long etablissementId;
    // ✅ Constructeur à partir d’un objet Formation
    public FormationDTO(Formation formation) {
        this.id = formation.getId();
        this.nom = formation.getNom();
        this.description = formation.getDescription();
        this.duree = formation.getDuree();
        this.fraisAnnuel = formation.getFraisAnnuel();
        this.etablissementId = formation.getEtablissement() != null ? formation.getEtablissement().getId() : null;
    }

}
