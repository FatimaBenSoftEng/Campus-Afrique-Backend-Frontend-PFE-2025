package com.campus.CampusAfrique.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@Data
public class DemandeVisaFormDTO {
    private String paysDestination;
    private String ville;
    private String etablissement;
    private LocalDate dateDepart;
    private String duree;
    private String adresse;

    private String nomGarant;
    private String lien;
    private String adresseGarant;
    private String telephoneGarant;
    private String professionGarant;
    private Double revenu;

    private String commentaires;
    private Long ambassadeId;

    private List<MultipartFile> fichiers;
}
