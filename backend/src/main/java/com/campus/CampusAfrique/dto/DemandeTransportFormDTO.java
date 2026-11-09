package com.campus.CampusAfrique.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@Data
public class DemandeTransportFormDTO {
    private String ville;
    private String adresse;
    private String frequence;
    private String typeTransport;
    private Boolean besoinAbonnement;
    private String commentaires;
    private List<MultipartFile> fichiers;
}
