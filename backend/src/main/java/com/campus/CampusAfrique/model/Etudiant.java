package com.campus.CampusAfrique.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.*;
import jakarta.persistence.OneToOne;
import jakarta.persistence.JoinColumn;
import com.campus.CampusAfrique.security.user.User;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Etudiant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private String typeIdentite;   // CIN ou Passeport
    private String numeroIdentite; // PA6060 par exemple
    private String matricule;

    @ManyToOne
    private NiveauScolaire niveauScolaire;

    @OneToMany(mappedBy = "etudiant")
    private List<Dossier> dossiers;

    @OneToMany(mappedBy = "etudiant")
    private List<Notification> notifications;

    @OneToMany(mappedBy = "etudiant")
    private List<Inscription> inscriptions;

    @OneToMany(mappedBy = "etudiant")
    private List<Preinscription> preinscriptions;

    @OneToMany(mappedBy = "etudiant")
    private List<DemandeBourse> demandesBourse;

    @OneToMany(mappedBy = "etudiant")
    private List<Candidature> candidatures;

    @OneToMany(mappedBy = "etudiant")
    private List<Paiement> paiements;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;


}
