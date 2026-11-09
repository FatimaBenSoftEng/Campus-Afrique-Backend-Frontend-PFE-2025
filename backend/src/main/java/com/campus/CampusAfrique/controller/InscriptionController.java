package com.campus.CampusAfrique.controller;

import com.campus.CampusAfrique.dto.InscriptionDTO;
import com.campus.CampusAfrique.model.Etudiant;
import com.campus.CampusAfrique.model.Formation;
import com.campus.CampusAfrique.model.Inscription;
import com.campus.CampusAfrique.service.EtudiantService;
import com.campus.CampusAfrique.service.FormationService;
import com.campus.CampusAfrique.service.InscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/inscriptions")
public class InscriptionController {

    @Autowired
    private InscriptionService inscriptionService;

    @Autowired
    private EtudiantService etudiantService;

    @Autowired
    private FormationService formationService;

    @PostMapping("/add")
    public ResponseEntity<InscriptionDTO> createInscription(@RequestBody InscriptionDTO dto) {
        Optional<Etudiant> etudiantOpt = etudiantService.findById(dto.getEtudiantId());
        Optional<Formation> formationOpt = formationService.findById(dto.getFormationId());

        if (etudiantOpt.isEmpty() || formationOpt.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        Inscription inscription = new Inscription();
        inscription.setDateInscription(dto.getDateInscription());
        inscription.setStatut(dto.getStatut());
        inscription.setEtudiant(etudiantOpt.get());
        inscription.setFormation(formationOpt.get());

        Inscription saved = inscriptionService.save(inscription);
        return ResponseEntity.ok(toDto(saved));
    }

    @GetMapping("/all")
    public ResponseEntity<List<InscriptionDTO>> getAllInscriptions() {
        List<InscriptionDTO> dtos = inscriptionService.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<InscriptionDTO> getInscriptionById(@PathVariable Long id) {
        return inscriptionService.findById(id)
                .map(this::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<InscriptionDTO> updateInscription(@PathVariable Long id, @RequestBody InscriptionDTO dto) {
        Optional<Inscription> existing = inscriptionService.findById(id);
        Optional<Etudiant> etudiantOpt = etudiantService.findById(dto.getEtudiantId());
        Optional<Formation> formationOpt = formationService.findById(dto.getFormationId());

        if (existing.isEmpty() || etudiantOpt.isEmpty() || formationOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Inscription inscription = existing.get();
        inscription.setDateInscription(dto.getDateInscription());
        inscription.setStatut(dto.getStatut());
        inscription.setEtudiant(etudiantOpt.get());
        inscription.setFormation(formationOpt.get());

        Inscription updated = inscriptionService.save(inscription);
        return ResponseEntity.ok(toDto(updated));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteInscription(@PathVariable Long id) {
        inscriptionService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    private InscriptionDTO toDto(Inscription inscription) {
        return new InscriptionDTO(
                inscription.getId(),
                inscription.getDateInscription(),
                inscription.getStatut(),
                inscription.getEtudiant() != null ? inscription.getEtudiant().getId() : null,
                inscription.getFormation() != null ? inscription.getFormation().getId() : null
        );
    }
}
