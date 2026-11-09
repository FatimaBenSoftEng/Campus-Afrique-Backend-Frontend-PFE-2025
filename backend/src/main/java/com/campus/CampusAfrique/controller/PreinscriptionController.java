// CONTROLLER 6 : PreinscriptionController.java

package com.campus.CampusAfrique.controller;

import com.campus.CampusAfrique.dto.PreinscriptionDTO;
import com.campus.CampusAfrique.dto.PreinscriptionFormDTO;
import com.campus.CampusAfrique.model.Etudiant;
import com.campus.CampusAfrique.model.Etablissement;
import com.campus.CampusAfrique.model.Formation;
import com.campus.CampusAfrique.model.Preinscription;
import com.campus.CampusAfrique.service.EtudiantService;
import com.campus.CampusAfrique.service.EtablissementService;
import com.campus.CampusAfrique.service.FormationService;
import com.campus.CampusAfrique.service.PreinscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.HashMap;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/preinscriptions")
public class PreinscriptionController {

    @Autowired
    private PreinscriptionService preinscriptionService;

    @Autowired
    private EtudiantService etudiantService;

    @Autowired
    private FormationService formationService;

    @Autowired
    private EtablissementService etablissementService;

    @PostMapping("/add")
    public ResponseEntity<PreinscriptionDTO> create(@RequestBody PreinscriptionDTO dto) {
        Optional<Etudiant> etudiantOpt = etudiantService.findById(dto.getEtudiantId());
        Optional<Formation> formationOpt = formationService.findById(dto.getFormationId());
        Optional<Etablissement> etabOpt = etablissementService.findById(dto.getEtablissementId());

        if (etudiantOpt.isEmpty() || formationOpt.isEmpty() || etabOpt.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        Preinscription preinscription = new Preinscription();
        preinscription.setStatut(dto.getStatut()); // dto.getStatut() retourne enum maintenant ✅

        preinscription.setChoixFormation(dto.getChoixFormation());
        preinscription.setOrdrePreference(dto.getOrdrePreference());
        preinscription.setEtudiant(etudiantOpt.get());
        preinscription.setFormation(formationOpt.get());
        preinscription.setEtablissement(etabOpt.get());
        preinscription.setNumeroDossier(UUID.randomUUID().toString());
        Preinscription saved = preinscriptionService.save(preinscription);
        return ResponseEntity.ok(toDto(saved));
    }

    @GetMapping("/all")
    public ResponseEntity<List<PreinscriptionDTO>> getAll() {
        List<PreinscriptionDTO> dtos = preinscriptionService.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<PreinscriptionDTO> getById(@PathVariable Long id) {
        return preinscriptionService.findById(id)
                .map(this::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/find/etudiant/{etudiantId}")
    public ResponseEntity<List<PreinscriptionDTO>> getByEtudiant(@PathVariable Long etudiantId) {
        List<PreinscriptionDTO> dtos = preinscriptionService.findByEtudiantId(etudiantId)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/find/formation/{formationId}")
    public ResponseEntity<List<PreinscriptionDTO>> getByFormation(@PathVariable Long formationId) {
        List<PreinscriptionDTO> dtos = preinscriptionService.findByFormationId(formationId)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/find/statut/{statut}")
    public ResponseEntity<List<PreinscriptionDTO>> getByStatut(@PathVariable String statut) {
        List<PreinscriptionDTO> dtos = preinscriptionService.findByStatut(statut)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PreinscriptionDTO> update(@PathVariable Long id, @RequestBody PreinscriptionDTO dto) {
        Optional<Etudiant> etudiantOpt = etudiantService.findById(dto.getEtudiantId());
        Optional<Formation> formationOpt = formationService.findById(dto.getFormationId());
        Optional<Etablissement> etabOpt = etablissementService.findById(dto.getEtablissementId());

        if (etudiantOpt.isEmpty() || formationOpt.isEmpty() || etabOpt.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        Optional<Preinscription> existing = preinscriptionService.findById(id);
        if (existing.isEmpty()) return ResponseEntity.notFound().build();

        Preinscription preinscription = existing.get();
        preinscription.setStatut(dto.getStatut());
        preinscription.setChoixFormation(dto.getChoixFormation());
        preinscription.setOrdrePreference(dto.getOrdrePreference());
        preinscription.setEtudiant(etudiantOpt.get());
        preinscription.setFormation(formationOpt.get());
        preinscription.setEtablissement(etabOpt.get());

        return ResponseEntity.ok(toDto(preinscriptionService.save(preinscription)));
    }


    @PostMapping(value = "/formulaire", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Map<String, String>> traiteFormulaire(
            @ModelAttribute PreinscriptionFormDTO formDTO,
            @RequestHeader("Authorization") String authorizationHeader) {

        // Extraire le token depuis le header "Authorization: Bearer <token>"
        String token = authorizationHeader.replace("Bearer ", "");

        // Appel du service avec formDTO et token
        preinscriptionService.traiteFormulaire(formDTO, token);

        // Réponse
        Map<String, String> response = new HashMap<>();
        response.put("message", "Préinscription enregistrée avec succès");

        return ResponseEntity.ok(response);
    }

    @GetMapping("/mes-demandes")
    public ResponseEntity<List<PreinscriptionDTO>> getMesPreinscriptions(@RequestHeader("Authorization") String token) {
        String jwt = token.replace("Bearer ", "");
        List<PreinscriptionDTO> dtos = preinscriptionService.findByToken(jwt).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        preinscriptionService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    private PreinscriptionDTO toDto(Preinscription entity) {
        PreinscriptionDTO dto = new PreinscriptionDTO();
        dto.setId(entity.getId());
        dto.setChoixFormation(entity.getChoixFormation());
        dto.setStatut(entity.getStatut());

        dto.setEtudiantId(entity.getEtudiant().getId());
        dto.setFormationId(entity.getFormation().getId());
        dto.setEtablissementId(entity.getEtablissement().getId());

        dto.setEtablissementNom(entity.getEtablissement().getNom()); // important
        dto.setDatePreInscription(entity.getDatePreInscription());  // important

        return dto;
    }

}
