package com.campus.CampusAfrique.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@Data
public class DemandeHebergementFormDTO {
    private String ville;
    private LocalDate dateArrivee;
    private String typeHebergement;
    private String commentaires;
    private List<MultipartFile> fichiers;
}
