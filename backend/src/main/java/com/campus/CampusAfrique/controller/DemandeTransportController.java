package com.campus.CampusAfrique.controller;

import com.campus.CampusAfrique.dto.DemandeTransportDTO;
import com.campus.CampusAfrique.dto.DemandeTransportFormDTO;
import com.campus.CampusAfrique.model.DemandeTransport;
import com.campus.CampusAfrique.service.DemandeTransportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/transports")
public class DemandeTransportController {

    @Autowired
    private DemandeTransportService service;

    @PostMapping("/formulaire")
    public ResponseEntity<?> envoyerDemande(
            @ModelAttribute DemandeTransportFormDTO dto,
            @RequestHeader("Authorization") String authHeader
    ) {
        try {
            String token = authHeader.replace("Bearer ", "");
            service.traiterDemande(dto, token);
            return ResponseEntity.ok(Map.of("message", "Demande de transport enregistrée avec succès"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/mes")
    public ResponseEntity<List<DemandeTransportDTO>> getMesDemandes(@RequestHeader("Authorization") String token) {
        String cleanToken = token.replace("Bearer ", "");
        return ResponseEntity.ok(service.getDemandesByToken(cleanToken));
    }

}

