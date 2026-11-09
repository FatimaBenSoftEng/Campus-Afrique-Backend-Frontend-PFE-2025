package com.campus.CampusAfrique.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Candidature {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dateSoumission;
    private String statut;
    private String messageMotivation;

    @ManyToOne
    private Etudiant etudiant;

    @ManyToOne
    private Etablissement etablissement;

}