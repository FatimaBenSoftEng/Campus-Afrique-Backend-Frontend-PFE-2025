package com.campus.CampusAfrique.model;

import lombok.*;
import jakarta.persistence.*;
import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NiveauScolaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String description;

    @OneToMany(mappedBy = "niveauScolaire")
    private List<Etudiant> etudiants;
}