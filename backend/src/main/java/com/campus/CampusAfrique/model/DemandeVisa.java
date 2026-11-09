package com.campus.CampusAfrique.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DemandeVisa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String paysDestination;
    private String ville;
    private String etablissement;
    private LocalDate dateDepart;
    private String duree;
    private String adresse;

    private String nomGarant;
    private String lien;
    private String adresseGarant;
    private String telephoneGarant;
    private String professionGarant;
    private Double revenu;

    private String commentaires;
    private LocalDate dateDemande;

    @Enumerated(EnumType.STRING)
    private StatutDemande  statut;


    @ManyToOne
    private Etudiant etudiant;

    @OneToOne(mappedBy = "demandeVisa")
    private Dossier dossier;

    @ManyToOne
    private AgentAmbassade ambassade;

}
