package com.campus.CampusAfrique.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InscriptionDTO {
    private Long id;
    private LocalDate dateInscription;
    private String statut;
    private Long etudiantId;
    private Long formationId;
    private boolean paiementEffectue;
    private String justificatifPaiement;

    // ✅ Constructeur utilisé dans ton contrôleur
    public InscriptionDTO(Long id, LocalDate dateInscription, String statut, Long etudiantId, Long formationId) {
        this.id = id;
        this.dateInscription = dateInscription;
        this.statut = statut;
        this.etudiantId = etudiantId;
        this.formationId = formationId;
    }
}
