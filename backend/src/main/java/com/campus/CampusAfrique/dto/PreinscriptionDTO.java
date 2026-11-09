package com.campus.CampusAfrique.dto;

import com.campus.CampusAfrique.model.StatutPreinscription;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PreinscriptionDTO {
    private Long id;
    //private LocalDate dateSoumission;
    private StatutPreinscription statut;
    private String numeroEtudiant;
    private Long etudiantId;
    private Long formationId;
    private Long etablissementId;
    private String choixFormation;
    private int ordrePreference;
    private LocalDate datePreInscription;
    // ✅ À ajouter pour corriger ton erreur :
    private String etablissementNom;


}
