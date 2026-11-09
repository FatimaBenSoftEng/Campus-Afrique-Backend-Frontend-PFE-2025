package com.campus.CampusAfrique.controller;

import com.campus.CampusAfrique.dto.DemandeHebergementDTO;
import com.campus.CampusAfrique.dto.DemandeHebergementFormDTO;
import com.campus.CampusAfrique.model.DemandeHebergement;
import com.campus.CampusAfrique.service.DemandeHebergementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/hebergements")
public class DemandeHebergementController {

    @Autowired
    private DemandeHebergementService service;

    @PostMapping("/formulaire")
    public ResponseEntity<?> envoyerDemande(
            @ModelAttribute DemandeHebergementFormDTO dto,
            @RequestHeader("Authorization") String authHeader
    ) {
        try {
            String token = authHeader.replace("Bearer ", "");
            service.traiterDemande(dto, token);
            return ResponseEntity.ok(Map.of("message", "Demande enregistrée avec succès"));

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erreur : " + e.getMessage());
        }
    }


    @GetMapping("/mes")
    public ResponseEntity<List<DemandeHebergementDTO>> getMesDemandes(@RequestHeader("Authorization") String token) {
        String cleanToken = token.replace("Bearer ", "");
        return ResponseEntity.ok(service.getDemandesByToken(cleanToken));
    }

}  