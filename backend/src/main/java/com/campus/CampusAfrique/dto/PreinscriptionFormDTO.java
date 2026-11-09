package com.campus.CampusAfrique.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@Data
public class PreinscriptionFormDTO {

    // Infos personnelles
    private String nom;
    private String prenom;
    private String genre;
    private String nationalite;
    private String paysResidence;
    private String adresse;
    private String email;
    private String telephone;
    private LocalDate dateNaissance;
    private String typePiece;
    private String numeroPiece;
    private String matricule;

    // Infos académiques
    private String dernierDiplome;
    private String specialite;
    private Integer anneeObtention;
    private String paysObtention;
    private Float moyenne;

    // Choix du formation
    private Long etablissementId;
    private Long formationId;
    private String niveau;

    // Fichiers
    private List<MultipartFile> fichiers;
}
