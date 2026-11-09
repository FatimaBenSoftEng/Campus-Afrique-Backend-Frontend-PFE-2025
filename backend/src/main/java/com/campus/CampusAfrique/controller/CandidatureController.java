package com.campus.CampusAfrique.controller;

import com.campus.CampusAfrique.dto.CandidatureDTO;
import com.campus.CampusAfrique.model.Candidature;
import com.campus.CampusAfrique.model.Etablissement;
import com.campus.CampusAfrique.model.Etudiant;
import com.campus.CampusAfrique.service.CandidatureService;
import com.campus.CampusAfrique.service.EtablissementService;
import com.campus.CampusAfrique.service.EtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/candidatures")
public class CandidatureController {

    @Autowired
    private CandidatureService candidatureService;

    @Autowired
    private EtudiantService etudiantService;

    @Autowired
    private EtablissementService etablissementService;

    @PostMapping("/add")
    public ResponseEntity<CandidatureDTO> createCandidature(@RequestBody CandidatureDTO dto) {
        Optional<Etudiant> etudiantOpt = etudiantService.findById(dto.getEtudiantId());
        Optional<Etablissement> etablissementOpt = etablissementService.findById(dto.getEtablissementId());

        if (etudiantOpt.isEmpty() || etablissementOpt.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        Candidature candidature = new Candidature();
        candidature.setDateSoumission(LocalDate.now());
        candidature.setStatut(dto.getStatut());
        candidature.setMessageMotivation(dto.getMessageMotivation());
        candidature.setEtudiant(etudiantOpt.get());
        candidature.setEtablissement(etablissementOpt.get());

        Candidature saved = candidatureService.save(candidature);
        return ResponseEntity.ok(toDto(saved));
    }

    @GetMapping("/all")
    public ResponseEntity<List<CandidatureDTO>> getAllCandidatures() {
        return ResponseEntity.ok(
                candidatureService.findAll()
                        .stream()
                        .map(this::toDto)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<CandidatureDTO> getCandidatureById(@PathVariable Long id) {
        return candidatureService.findById(id)
                .map(this::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/find/etudiant/{etudiantId}")
    public ResponseEntity<List<CandidatureDTO>> getCandidaturesByEtudiant(@PathVariable Long etudiantId) {
        return ResponseEntity.ok(
                candidatureService.findByEtudiantId(etudiantId)
                        .stream()
                        .map(this::toDto)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/find/statut/{statut}")
    public ResponseEntity<List<CandidatureDTO>> getCandidaturesByStatut(@PathVariable String statut) {
        return ResponseEntity.ok(
                candidatureService.findByStatut(statut)
                        .stream()
                        .map(this::toDto)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/find/etablissement/{etablissementId}")
    public ResponseEntity<List<CandidatureDTO>> getCandidaturesByEtablissement(@PathVariable Long etablissementId) {
        return ResponseEntity.ok(
                candidatureService.findByEtablissementId(etablissementId)
                        .stream()
                        .map(this::toDto)
                        .collect(Collectors.toList())
        );
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CandidatureDTO> updateCandidature(@PathVariable Long id, @RequestBody CandidatureDTO dto) {
        Optional<Candidature> existingOpt = candidatureService.findById(id);
        if (existingOpt.isEmpty()) return ResponseEntity.notFound().build();

        Candidature candidature = existingOpt.get();
        candidature.setStatut(dto.getStatut());
        candidature.setMessageMotivation(dto.getMessageMotivation());

        return ResponseEntity.ok(toDto(candidatureService.save(candidature)));
    }

    // === Méthode utilitaire de conversion ===
    private CandidatureDTO toDto(Candidature c) {
        return new CandidatureDTO(
                c.getId(),
                c.getDateSoumission(),
                c.getStatut(),
                c.getMessageMotivation(),
                c.getEtudiant().getId(),
                c.getEtablissement().getId()
        );
    }
}
