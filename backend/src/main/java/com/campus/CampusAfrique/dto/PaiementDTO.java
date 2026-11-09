package com.campus.CampusAfrique.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaiementDTO {
    private Long id;
    private LocalDate datePaiement;
    private Double montant;
    private String justificatif;
}
