package com.campus.CampusAfrique.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DemandeHebergement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ville;
    private LocalDate dateArrivee;
    private String typeHebergement;
    private String commentaires;
    private LocalDate dateDemande;

    @Enumerated(EnumType.STRING)
    private StatutDemande  statut;

    @ManyToOne
    private Etudiant etudiant;

    @OneToOne(mappedBy = "demandeHebergement")
    private Dossier dossier;

}

