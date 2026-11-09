package com.campus.CampusAfrique.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DemandeVisaDTO {
    private Long id;
    private String ambassadeNom;
    private String paysDestination;
    private String ville;
    private String etablissement;
    private LocalDate dateDepart;
    private String adresse;
    private String nomGarant;
    private String lien;
    private LocalDate dateDemande;
    private String statut;


}
