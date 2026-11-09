package com.campus.CampusAfrique.dto;

import com.campus.CampusAfrique.security.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import com.campus.CampusAfrique.model.NiveauScolaire;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EtudiantDTO {
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String motDePasse;
    private String paysOrigine;
    private LocalDate dateNaissance;
    private LocalDate anneeObtention;
    private String nationalite;
    private String photoProfil;
    private String statut;
    private String telephone;
    private String adresse;
    private String ville;
    private String genre;
    private String dernierDiplome;
    private String specialite;
    private Float moyenne;
    private NiveauScolaire niveauScolaire;
    private User user;
    private String typeIdentite;
    private String numeroIdentite;
    private String matricule;

}
