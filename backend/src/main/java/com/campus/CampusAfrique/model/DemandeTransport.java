package com.campus.CampusAfrique.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DemandeTransport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ville;
    private String adresse;
    private String frequence;
    private String typeTransport;
    private Boolean besoinAbonnement;
    private String commentaires;
    private LocalDate dateDemande;

    @Enumerated(EnumType.STRING)
    private StatutDemande  statut;

    @ManyToOne
    private Etudiant etudiant;

    @OneToOne(mappedBy = "demandeTransport")
    private Dossier dossier;
}
