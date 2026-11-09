package com.campus.CampusAfrique.model;

import lombok.*;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String destinataire;
    private String message;
    private LocalDate dateEnvoi;
    private boolean lue;

    @ManyToOne
    private Etudiant etudiant;
}