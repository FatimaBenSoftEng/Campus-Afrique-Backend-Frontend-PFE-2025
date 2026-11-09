package com.campus.CampusAfrique.dto;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class DemandeBourseFormDTO {
    private String adresse;
    private String numeroEtudiant;
    private String email;
    private String telephone;

    private String diplome;
    private String anneeDiplome;
    private String filiere;
    private String anneeUniversitaire;
    private String etablissementCible;
    private String typeBourse;
    private Float moyenne;
    private Long organismeBourseId;

    private List<MultipartFile> fichiers;
}
