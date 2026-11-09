package com.campus.CampusAfrique.controller;

import com.campus.CampusAfrique.dto.FormationDTO;
import com.campus.CampusAfrique.model.Formation;
import com.campus.CampusAfrique.service.FormationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/formations")
public class FormationController {

    @Autowired
    private FormationService formationService;

    @PostMapping("/add")
    public ResponseEntity<FormationDTO> createFormation(@RequestBody FormationDTO dto) {
        Formation formation = new Formation();
        formation.setNom(dto.getNom());
        formation.setDescription(dto.getDescription());
        formation.setDuree(dto.getDuree());
        formation.setFraisAnnuel(dto.getFraisAnnuel());

        return ResponseEntity.ok(toDto(formationService.save(formation)));
    }

    @GetMapping("/all")
    public ResponseEntity<List<FormationDTO>> getAllFormations() {
        return ResponseEntity.ok(
                formationService.findAll()
                        .stream()
                        .map(this::toDto)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<FormationDTO> getFormationById(@PathVariable Long id) {
        return formationService.findById(id)
                .map(this::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<FormationDTO> updateFormation(@PathVariable Long id, @RequestBody FormationDTO dto) {
        Optional<Formation> existing = formationService.findById(id);
        if (existing.isEmpty()) return ResponseEntity.notFound().build();

        Formation formation = existing.get();
        formation.setNom(dto.getNom());
        formation.setDescription(dto.getDescription());
        formation.setDuree(dto.getDuree());
        formation.setFraisAnnuel(dto.getFraisAnnuel());

        return ResponseEntity.ok(toDto(formationService.save(formation)));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteFormation(@PathVariable Long id) {
        formationService.deleteById(id);
        return ResponseEntity.ok().build();
    }

 /*   @GetMapping("/etablissement/{etablissementId}")
    public ResponseEntity<List<FormationDTO>> getFormationsByEtablissement(@PathVariable Long etablissementId) {
        List<Formation> formations = formationService.findByEtablissementId(etablissementId);
        List<FormationDTO> dtos = formations.stream()
                .map(this::toDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
    }
*/
    @GetMapping("/etablissement/{etablissementId}")
    public ResponseEntity<List<FormationDTO>> getFormationsByEtablissement(@PathVariable Long etablissementId) {
        List<Formation> formations = formationService.findByEtablissementId(etablissementId);
        List<FormationDTO> dtos = formations.stream()
                .map(this::toDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
    }

    private FormationDTO toDto(Formation f) {
        FormationDTO dto = new FormationDTO();
        dto.setId(f.getId());
        dto.setNom(f.getNom());
        dto.setDescription(f.getDescription());
        dto.setDuree(f.getDuree());
        dto.setFraisAnnuel(f.getFraisAnnuel());
        dto.setEtablissementId(f.getEtablissement().getId());
        return dto;
    }
}
