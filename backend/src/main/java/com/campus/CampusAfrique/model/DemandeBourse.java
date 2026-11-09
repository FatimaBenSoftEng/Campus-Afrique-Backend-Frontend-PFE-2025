package com.campus.CampusAfrique.model;

import lombok.*;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DemandeBourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dateDemande;

    @Enumerated(EnumType.STRING)
    private StatutDemande statut;

    private String anneeUniversitaire; // (ex : 2025/2026)
    private String typeBourse; // excellence, sociale, etc.
    private String etablissementCible; // (si indépendant de l’organisme)

    @ManyToOne
    private Etudiant etudiant;

    @ManyToOne
    private OrganismeBourse organismeBourse;

    @OneToOne(mappedBy = "demandeBourse", cascade = CascadeType.ALL)
    private Dossier dossier;


}