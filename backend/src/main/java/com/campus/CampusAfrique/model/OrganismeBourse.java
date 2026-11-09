package com.campus.CampusAfrique.model;

import lombok.*;
import jakarta.persistence.*;
import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrganismeBourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String email;
    private String pays;
    private String type;

    @OneToMany(mappedBy = "organismeBourse")
    private List<DemandeBourse> demandesBourse;
}