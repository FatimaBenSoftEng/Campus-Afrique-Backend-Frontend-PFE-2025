package com.campus.CampusAfrique.controller;

import com.campus.CampusAfrique.dto.ResponsableEtablissementDTO;
import com.campus.CampusAfrique.model.Etablissement;
import com.campus.CampusAfrique.model.ResponsableEtablissement;
import com.campus.CampusAfrique.service.EtablissementService;
import com.campus.CampusAfrique.service.ResponsableEtablissementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/responsables-etablissement")
public class ResponsableEtablissementController {

    @Autowired
    private ResponsableEtablissementService responsableService;

    @Autowired
    private EtablissementService etablissementService;

    // Ajouter
    @PostMapping("/add")
    public ResponseEntity<ResponsableEtablissementDTO> create(@RequestBody ResponsableEtablissementDTO dto) {
        Optional<Etablissement> etabOpt = etablissementService.findById(dto.getEtablissementId());
        if (etabOpt.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        ResponsableEtablissement res = new ResponsableEtablissement();
        res.setNom(dto.getNom());
        res.setEmail(dto.getEmail());
        res.setMotDePasse(dto.getMotDePasse());
        res.setEtablissement(etabOpt.get());

        ResponsableEtablissement saved = responsableService.save(res);

        ResponsableEtablissementDTO result = new ResponsableEtablissementDTO(
                saved.getId(),
                saved.getNom(),
                saved.getEmail(),
                saved.getMotDePasse(),
                saved.getEtablissement().getId()
        );

        return ResponseEntity.ok(result);
    }

    // Lire tout
    @GetMapping("/all")
    public List<ResponsableEtablissementDTO> getAll() {
        return responsableService.findAll().stream().map(res -> new ResponsableEtablissementDTO(
                res.getId(),
                res.getNom(),
                res.getEmail(),
                res.getMotDePasse(),
                res.getEtablissement() != null ? res.getEtablissement().getId() : null
        )).collect(Collectors.toList());
    }

    // Lire par ID
    @GetMapping("/find/{id}")
    public ResponseEntity<ResponsableEtablissementDTO> getById(@PathVariable Long id) {
        return responsableService.findById(id)
                .map(res -> ResponseEntity.ok(new ResponsableEtablissementDTO(
                        res.getId(),
                        res.getNom(),
                        res.getEmail(),
                        res.getMotDePasse(),
                        res.getEtablissement() != null ? res.getEtablissement().getId() : null
                )))
                .orElse(ResponseEntity.notFound().build());
    }

    // Lire par email
    @GetMapping("/find/email/{email}")
    public ResponseEntity<ResponsableEtablissementDTO> getByEmail(@PathVariable String email) {
        return responsableService.findByEmail(email)
                .map(res -> ResponseEntity.ok(new ResponsableEtablissementDTO(
                        res.getId(),
                        res.getNom(),
                        res.getEmail(),
                        res.getMotDePasse(),
                        res.getEtablissement() != null ? res.getEtablissement().getId() : null
                )))
                .orElse(ResponseEntity.notFound().build());
    }

    // Liste par établissement
    @GetMapping("/find/etablissement/{etablissementId}")
    public ResponseEntity<List<ResponsableEtablissementDTO>> getByEtablissement(@PathVariable Long etablissementId) {
        return ResponseEntity.ok(
                responsableService.findByEtablissementId(etablissementId).stream()
                        .map(res -> new ResponsableEtablissementDTO(
                                res.getId(),
                                res.getNom(),
                                res.getEmail(),
                                res.getMotDePasse(),
                                res.getEtablissement() != null ? res.getEtablissement().getId() : null
                        )).collect(Collectors.toList())
        );
    }

    // Modifier
    @PutMapping("/update/{id}")
    public ResponseEntity<ResponsableEtablissementDTO> update(@PathVariable Long id, @RequestBody ResponsableEtablissementDTO dto) {
        Optional<ResponsableEtablissement> existingOpt = responsableService.findById(id);
        if (existingOpt.isEmpty()) return ResponseEntity.notFound().build();

        Optional<Etablissement> etabOpt = etablissementService.findById(dto.getEtablissementId());
        if (etabOpt.isEmpty()) return ResponseEntity.badRequest().build();

        ResponsableEtablissement res = existingOpt.get();
        res.setNom(dto.getNom());
        res.setEmail(dto.getEmail());
        res.setMotDePasse(dto.getMotDePasse());
        res.setEtablissement(etabOpt.get());

        ResponsableEtablissement updated = responsableService.save(res);

        ResponsableEtablissementDTO result = new ResponsableEtablissementDTO(
                updated.getId(),
                updated.getNom(),
                updated.getEmail(),
                updated.getMotDePasse(),
                updated.getEtablissement() != null ? updated.getEtablissement().getId() : null
        );

        return ResponseEntity.ok(result);
    }

    // Supprimer
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        responsableService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
