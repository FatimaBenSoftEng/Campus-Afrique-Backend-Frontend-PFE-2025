package com.campus.CampusAfrique.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocumentDTO {
    private Long id;
    private String nom;
    private String type;
    private String cheminFichier;
    private LocalDate dateAjout;
    private String statut;
    private Long dossierId;
}
