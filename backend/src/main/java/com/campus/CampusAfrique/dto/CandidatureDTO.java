package com.campus.CampusAfrique.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidatureDTO {
    private Long id;
    private LocalDate dateSoumission;
    private String statut;
    private String messageMotivation;
    private Long etudiantId;
    private Long etablissementId;
}
