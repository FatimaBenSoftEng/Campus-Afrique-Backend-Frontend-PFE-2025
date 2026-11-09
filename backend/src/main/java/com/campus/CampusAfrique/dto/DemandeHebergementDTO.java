package com.campus.CampusAfrique.dto;

import com.campus.CampusAfrique.model.StatutDemande;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DemandeHebergementDTO {
    private Long id;
    private String typeHebergement;
    private String ville;
    private String commentaire;
    private LocalDate dateDemande;
    private LocalDate dateArrivee;
    private String commentaires;
    private StatutDemande statut;


}

