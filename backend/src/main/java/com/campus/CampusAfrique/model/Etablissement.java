package com.campus.CampusAfrique.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Etablissement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String adresse;
    private String description;
    private String email;
    private String siteWeb;
    private boolean bourseDisponible;
    private String ville;
    private String pays;
    private String nomAbr;
    private String type;
    private String imageUrl;
    private boolean isFavori;
    private String telephone;
    private LocalDate dateCreation;
    private LocalDate dateDebutCandidature;
    private LocalDate dateFinCandidature;
    private String typeDiplome;
    private String reconnaissanceEtat;

    @OneToMany(mappedBy = "etablissement")
    private List<Candidature> candidatures;

    @OneToMany(mappedBy = "etablissement")
    private List<Formation> formations;

    @OneToMany(mappedBy = "etablissement")
    private List<Inscription> inscriptions;

    @OneToMany(mappedBy = "etablissement")
    private List<Preinscription> preinscriptions;

    @OneToMany(mappedBy = "etablissement")
    private List<GaleriePhoto> galeriePhotos;

    @OneToOne(mappedBy = "etablissement")
    private ResponsableEtablissement responsable;

}