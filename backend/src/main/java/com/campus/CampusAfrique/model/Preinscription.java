package com.campus.CampusAfrique.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Preinscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String choixFormation;

    @Enumerated(EnumType.STRING)
    private StatutPreinscription statut;


    private int ordrePreference;
    private LocalDate datePreInscription;
    private String numeroDossier;


    @ManyToOne
    private Etudiant etudiant;

    @ManyToOne
    private Formation formation;

    @ManyToOne
    private Etablissement etablissement;

    @OneToMany(mappedBy = "preinscription")
    private List<Dossier> dossiers;


}