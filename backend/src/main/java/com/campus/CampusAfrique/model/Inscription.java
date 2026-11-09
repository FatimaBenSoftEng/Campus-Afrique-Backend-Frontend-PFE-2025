package com.campus.CampusAfrique.model;

import lombok.*;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Inscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dateInscription;
    private float montantFrais;
    private String statut;

    @ManyToOne
    private Etudiant etudiant;

    @ManyToOne
    private Etablissement etablissement;

    @ManyToOne
    private Formation formation;

}
