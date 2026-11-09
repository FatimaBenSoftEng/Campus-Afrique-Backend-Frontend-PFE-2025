package com.campus.CampusAfrique.controller;

import com.campus.CampusAfrique.dto.DossierDTO;
import com.campus.CampusAfrique.model.Dossier;
import com.campus.CampusAfrique.model.Etudiant;
import com.campus.CampusAfrique.service.DossierService;
import com.campus.CampusAfrique.service.EtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/dossiers")
public class DossierController {

    @Autowired
    private DossierService dossierService;

    @Autowired
    private EtudiantService etudiantService;

    @PostMapping("/add")
    public ResponseEntity<DossierDTO> createDossier(@RequestBody DossierDTO dto) {
        Optional<Etudiant> etudiantOpt = etudiantService.findById(dto.getEtudiantId());
        if (etudiantOpt.isEmpty()) return ResponseEntity.badRequest().build();

        Dossier dossier = new Dossier();
        dossier.setDateCreation(LocalDate.now());
        dossier.setDateMiseAJour(LocalDate.now());
        dossier.setStatut(dto.getStatut());
        dossier.setTypeDossier(dto.getTypeDossier());
        dossier.setEtudiant(etudiantOpt.get());


        return ResponseEntity.ok(toDto(dossierService.save(dossier)));
    }

    @GetMapping("/all")
    public ResponseEntity<List<DossierDTO>> getAllDossiers() {
        return ResponseEntity.ok(
                dossierService.findAll()
                        .stream()
                        .map(this::toDto)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<DossierDTO> getDossierById(@PathVariable Long id) {
        return dossierService.findById(id)
                .map(this::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/find/etudiant/{etudiantId}")
    public ResponseEntity<List<DossierDTO>> getDossiersByEtudiant(@PathVariable Long etudiantId) {
        return ResponseEntity.ok(
                dossierService.findByEtudiantId(etudiantId)
                        .stream()
                        .map(this::toDto)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/find/type/{type}")
    public ResponseEntity<List<DossierDTO>> getDossiersByType(@PathVariable String type) {
        return ResponseEntity.ok(
                dossierService.findByType(type)
                        .stream()
                        .map(this::toDto)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/find/statut/{statut}")
    public ResponseEntity<List<DossierDTO>> getDossiersByStatut(@PathVariable String statut) {
        return ResponseEntity.ok(
                dossierService.findByStatut(statut)
                        .stream()
                        .map(this::toDto)
                        .collect(Collectors.toList())
        );
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<DossierDTO> updateDossier(@PathVariable Long id, @RequestBody DossierDTO dto) {
        Optional<Dossier> existingOpt = dossierService.findById(id);
        if (existingOpt.isEmpty()) return ResponseEntity.notFound().build();

        Dossier dossier = existingOpt.get();
        dossier.setStatut(dto.getStatut());
        dossier.setTypeDossier(dto.getTypeDossier());
        dossier.setDateMiseAJour(LocalDate.now());

        return ResponseEntity.ok(toDto(dossierService.save(dossier)));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteDossier(@PathVariable Long id) {
        dossierService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    private DossierDTO toDto(Dossier dossier) {
        DossierDTO dto = new DossierDTO();
        dto.setId(dossier.getId());
        dto.setDateCreation(dossier.getDateCreation());
        dto.setStatut(dossier.getStatut());
        dto.setDateMiseAJour(dossier.getDateMiseAJour());
        dto.setTypeDossier(dossier.getTypeDossier());
        dto.setEtudiantId(dossier.getEtudiant().getId());
        dto.setPreinscriptionId(dossier.getPreinscription().getId());
        return dto;
    }
}
