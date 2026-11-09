package com.campus.CampusAfrique.controller;

import com.campus.CampusAfrique.dto.DemandeVisaDTO;
import com.campus.CampusAfrique.dto.DemandeVisaFormDTO;
import com.campus.CampusAfrique.model.DemandeVisa;
import com.campus.CampusAfrique.service.DemandeVisaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/visas")
public class DemandeVisaController {

    @Autowired
    private DemandeVisaService service;
    @Autowired
    private DemandeVisaService demandeVisaService;

    @PostMapping("/formulaire")
    public ResponseEntity<?> envoyerDemande(
            @ModelAttribute DemandeVisaFormDTO dto,
            @RequestHeader("Authorization") String authHeader
    ) {
        try {
            String token = authHeader.replace("Bearer ", "");
            service.traiterDemande(dto, token);
            return ResponseEntity.ok(Map.of("message", "Demande de visa enregistrée avec succès"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }


    @GetMapping("/mes-demandes")
    public ResponseEntity<List<DemandeVisaDTO>> getMesDemandes(@RequestHeader("Authorization") String token) {
        List<DemandeVisa> demandes = demandeVisaService.getDemandesParEtudiant(token);

        List<DemandeVisaDTO> dtos = demandes.stream().map(demande -> {
            String ambassadeNom = (demande.getAmbassade() != null) ? demande.getAmbassade().getNom() : "-";
            return new DemandeVisaDTO(
                    demande.getId(),
                    ambassadeNom,
                    demande.getPaysDestination(),
                    demande.getVille(),
                    demande.getEtablissement(),
                    demande.getDateDepart(),
                    demande.getAdresse(),
                    demande.getNomGarant(),
                    demande.getLien(),
                    demande.getDateDemande(),
                    demande.getStatut().toString()
            );
        }).toList();

        return ResponseEntity.ok(dtos);
    }


}

