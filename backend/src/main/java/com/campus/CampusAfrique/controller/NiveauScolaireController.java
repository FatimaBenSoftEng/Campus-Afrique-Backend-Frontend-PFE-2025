package com.campus.CampusAfrique.controller;

import com.campus.CampusAfrique.dto.NiveauScolaireDTO;
import com.campus.CampusAfrique.model.NiveauScolaire;
import com.campus.CampusAfrique.service.NiveauScolaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/niveaux-scolaires")
public class NiveauScolaireController {

    @Autowired
    private NiveauScolaireService niveauScolaireService;

    // 🔁 Utilitaire pour convertir vers DTO
    private NiveauScolaireDTO toDto(NiveauScolaire niveau) {
        return new NiveauScolaireDTO(niveau.getId(), niveau.getNom());
    }

    // 🔁 Utilitaire pour convertir depuis DTO
    private NiveauScolaire toEntity(NiveauScolaireDTO dto) {
        NiveauScolaire niveau = new NiveauScolaire();
        niveau.setId(dto.getId());
        niveau.setNom(dto.getLibelle());
        return niveau;
    }

    @PostMapping("/add")
    public ResponseEntity<NiveauScolaireDTO> createNiveau(@RequestBody NiveauScolaireDTO dto) {
        NiveauScolaire saved = niveauScolaireService.save(toEntity(dto));
        return ResponseEntity.ok(toDto(saved));
    }

    @GetMapping("/all")
    public ResponseEntity<List<NiveauScolaireDTO>> getAllNiveaux() {
        List<NiveauScolaireDTO> niveaux = niveauScolaireService.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(niveaux);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<NiveauScolaireDTO> getNiveauById(@PathVariable Long id) {
        Optional<NiveauScolaire> niveauOpt = niveauScolaireService.findById(id);
        return niveauOpt.map(niveau -> ResponseEntity.ok(toDto(niveau)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/find/nom/{nom}")
    public ResponseEntity<NiveauScolaireDTO> getNiveauByNom(@PathVariable String nom) {
        Optional<NiveauScolaire> niveauOpt = niveauScolaireService.findByNom(nom);
        return niveauOpt.map(niveau -> ResponseEntity.ok(toDto(niveau)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<NiveauScolaireDTO> updateNiveau(@PathVariable Long id, @RequestBody NiveauScolaireDTO dto) {
        try {
            NiveauScolaire updated = niveauScolaireService.update(id, toEntity(dto));
            return ResponseEntity.ok(toDto(updated));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteNiveau(@PathVariable Long id) {
        niveauScolaireService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
