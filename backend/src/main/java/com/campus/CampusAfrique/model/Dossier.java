package com.campus.CampusAfrique.model;

import lombok.*;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dossier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate  dateCreation;
    private String statut;
    private LocalDate dateMiseAJour;
    @Enumerated(EnumType.STRING)
    private TypeDossier typeDossier;

    @ManyToOne
    private Etudiant etudiant;

    @OneToMany(mappedBy = "dossier")
    private List<Document> documents;

    @ManyToOne
    private Preinscription preinscription;

    @OneToOne
    @JoinColumn(name = "demande_bourse_id")
    private DemandeBourse demandeBourse;

    @OneToOne
    @JoinColumn(name = "demande_hebergement_id")
    private DemandeHebergement demandeHebergement;

    @OneToOne
    @JoinColumn(name = "demande_transport_id")
    private DemandeTransport demandeTransport;

    @OneToOne
    @JoinColumn(name = "demande_visa_id")
    private DemandeVisa demandeVisa;



}
