package com.campus.CampusAfrique.controller;

import com.campus.CampusAfrique.dto.PaiementDTO;
import com.campus.CampusAfrique.model.Etudiant;
import com.campus.CampusAfrique.model.Paiement;
import com.campus.CampusAfrique.service.EtudiantService;
import com.campus.CampusAfrique.service.PaiementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/paiements")
public class PaiementController {

    @Autowired
    private PaiementService paiementService;

    @Autowired
    private EtudiantService etudiantService;

    // 🔁 Convertir Entity -> DTO
    private PaiementDTO toDto(Paiement paiement) {
        PaiementDTO dto = new PaiementDTO();
        dto.setId(paiement.getId());
        dto.setMontant((double) paiement.getMontant());
        dto.setDatePaiement(paiement.getDatePaiement());
        dto.setJustificatif(paiement.getStatut()); // ⚠ Remplacer si tu veux un champ réel "justificatif"
        return dto;
    }

    // 🔁 Convertir DTO -> Entity
    private Paiement toEntity(PaiementDTO dto, Etudiant etudiant) {
        Paiement paiement = new Paiement();
        paiement.setId(dto.getId());
        paiement.setMontant(dto.getMontant().floatValue());
        paiement.setDatePaiement(dto.getDatePaiement() != null ? dto.getDatePaiement() : LocalDate.now());
        paiement.setStatut(dto.getJustificatif()); // ⚠ Remplacer si tu as un champ séparé
        paiement.setEtudiant(etudiant);
        return paiement;
    }

    @PostMapping("/add")
    public ResponseEntity<PaiementDTO> createPaiement(@RequestBody PaiementDTO dto) {
        Optional<Etudiant> etudiantOpt = etudiantService.findById(dto.getId()); // ⚠ Adapter si tu as un champ `etudiantId` dans le DTO
        if (etudiantOpt.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        Paiement saved = paiementService.save(toEntity(dto, etudiantOpt.get()));
        return ResponseEntity.ok(toDto(saved));
    }

    @GetMapping("/all")
    public ResponseEntity<List<PaiementDTO>> getAllPaiements() {
        List<PaiementDTO> paiements = paiementService.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(paiements);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<PaiementDTO> getPaiementById(@PathVariable Long id) {
        return paiementService.findById(id)
                .map(p -> ResponseEntity.ok(toDto(p)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/find/etudiant/{etudiantId}")
    public ResponseEntity<List<PaiementDTO>> getPaiementsByEtudiant(@PathVariable Long etudiantId) {
        List<PaiementDTO> paiements = paiementService.findByEtudiantId(etudiantId)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(paiements);
    }

    @GetMapping("/find/statut/{statut}")
    public ResponseEntity<List<PaiementDTO>> getPaiementsByStatut(@PathVariable String statut) {
        List<PaiementDTO> paiements = paiementService.findByStatut(statut)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(paiements);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PaiementDTO> updatePaiement(@PathVariable Long id, @RequestBody PaiementDTO dto) {
        Optional<Etudiant> etudiantOpt = etudiantService.findById(dto.getId()); // ⚠ à adapter selon champ réel
        if (etudiantOpt.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        try {
            Paiement updated = paiementService.update(id, toEntity(dto, etudiantOpt.get()));
            return ResponseEntity.ok(toDto(updated));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePaiement(@PathVariable Long id) {
        paiementService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
