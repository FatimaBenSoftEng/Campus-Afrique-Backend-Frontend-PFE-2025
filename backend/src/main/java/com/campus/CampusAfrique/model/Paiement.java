package com.campus.CampusAfrique.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Paiement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private float montant;
    private LocalDate datePaiement;
    private String statut;

    @ManyToOne
    private Etudiant etudiant;

}
