package com.campus.CampusAfrique.model;

import lombok.*;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String type;
    private String cheminFichier;
    private LocalDate dateAjout;
    private String statut;

    @ManyToOne
    private Dossier dossier;

}