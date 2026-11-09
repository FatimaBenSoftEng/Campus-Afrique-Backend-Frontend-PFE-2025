package com.campus.CampusAfrique.dto;

import com.campus.CampusAfrique.model.Formation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EtablissementDTO {
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

    private List<FormationDTO> formations;

    private List<String> parcours;
    private Double fraisScolariteMin;
    private Double fraisScolariteMax;
    public EtablissementDTO(
            Long id,
            String nom,
            String adresse,
            String description,
            String email,
            String siteWeb,
            boolean bourseDisponible,
            String ville,
            String pays,
            String nomAbr,
            String type,
            String imageUrl,
            boolean isFavori,
            String telephone,
            LocalDate dateCreation,
            LocalDate dateDebutCandidature,
            LocalDate dateFinCandidature,
            String typeDiplome,
            String reconnaissanceEtat
    ) {
        this.id = id;
        this.nom = nom;
        this.adresse = adresse;
        this.description = description;
        this.email = email;
        this.siteWeb = siteWeb;
        this.bourseDisponible = bourseDisponible;
        this.ville = ville;
        this.pays = pays;
        this.nomAbr = nomAbr;
        this.type = type;
        this.imageUrl = imageUrl;
        this.isFavori = isFavori;
        this.telephone = telephone;
        this.dateCreation = dateCreation;
        this.dateDebutCandidature = dateDebutCandidature;
        this.dateFinCandidature = dateFinCandidature;
        this.typeDiplome = typeDiplome;
        this.reconnaissanceEtat = reconnaissanceEtat;
    }


}
