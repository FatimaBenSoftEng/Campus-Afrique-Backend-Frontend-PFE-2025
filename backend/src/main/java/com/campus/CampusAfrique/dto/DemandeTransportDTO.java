package com.campus.CampusAfrique.dto;

import com.campus.CampusAfrique.model.StatutDemande;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DemandeTransportDTO {
    private Long id;
    private String ville;
    private String adresse;
    private String frequence;
    private String typeTransport;
    private Boolean besoinAbonnement;
    private String commentaires;
    private LocalDate dateDemande;
    private StatutDemande statut;
}
