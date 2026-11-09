package com.campus.CampusAfrique.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import jakarta.persistence.*;
import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Formation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String description;
    private int duree;
    private double fraisAnnuel;


    @ManyToOne
    @JsonIgnore  // ✅ coupe la récursivité
    private Etablissement etablissement;


    @OneToMany(mappedBy = "formation")
    private List<Preinscription> preinscriptions;

    @OneToMany(mappedBy = "formation")
    private List<Inscription> inscriptions;



}