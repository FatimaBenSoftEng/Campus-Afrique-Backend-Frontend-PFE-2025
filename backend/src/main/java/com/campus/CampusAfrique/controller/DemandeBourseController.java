package com.campus.CampusAfrique.controller;

import com.campus.CampusAfrique.dto.DemandeBourseDTO;
import com.campus.CampusAfrique.dto.DemandeBourseFormDTO;
import com.campus.CampusAfrique.model.DemandeBourse;
import com.campus.CampusAfrique.model.Etudiant;
import com.campus.CampusAfrique.model.OrganismeBourse;
import com.campus.CampusAfrique.model.StatutDemande;
import com.campus.CampusAfrique.security.JwtService;
import com.campus.CampusAfrique.service.DemandeBourseService;
import com.campus.CampusAfrique.service.EtudiantService;
import com.campus.CampusAfrique.service.OrganismeBourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/demandes-bourse")
public class DemandeBourseController {

    @Autowired
    private DemandeBourseService demandeBourseService;

    @Autowired
    private EtudiantService etudiantService;

    @Autowired
    private OrganismeBourseService organismeBourseService;

    @Autowired
    private JwtService jwtService;

    @GetMapping("/all")
    public ResponseEntity<List<DemandeBourseDTO>> getAllDemandesBourse() {
        return ResponseEntity.ok(
                demandeBourseService.findAll()
                        .stream()
                        .map(this::toDto)
                        .collect(Collectors.toList())
        );
    }

    @PostMapping("/add")
    public ResponseEntity<DemandeBourseDTO> createDemandeBourse(@RequestBody DemandeBourseDTO dto) {
        Optional<Etudiant> etudiantOpt = etudiantService.findById(dto.getEtudiantId());
        Optional<OrganismeBourse> organismeOpt = organismeBourseService.findById(dto.getOrganismeBourseId());

        if (etudiantOpt.isEmpty() || organismeOpt.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        DemandeBourse demande = new DemandeBourse();
        demande.setDateDemande(LocalDate.now());

        // ✅ Conversion String → Enum
        demande.setStatut(StatutDemande.valueOf(dto.getStatut()));

        demande.setEtudiant(etudiantOpt.get());
        demande.setOrganismeBourse(organismeOpt.get());
        demande.setTypeBourse(dto.getTypeBourse());
        demande.setAnneeUniversitaire(dto.getAnneeUniversitaire());
        demande.setEtablissementCible(dto.getEtablissementCible());

        return ResponseEntity.ok(toDto(demandeBourseService.save(demande)));
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<DemandeBourseDTO> getDemandeBourseById(@PathVariable Long id) {
        return demandeBourseService.findById(id)
                .map(this::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/find/etudiant/{etudiantId}")
    public ResponseEntity<List<DemandeBourseDTO>> getDemandesBourseByEtudiant(@PathVariable Long etudiantId) {
        return ResponseEntity.ok(
                demandeBourseService.findByEtudiantId(etudiantId)
                        .stream()
                        .map(this::toDto)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/find/organisme/{organismeId}")
    public ResponseEntity<List<DemandeBourseDTO>> getDemandesBourseByOrganisme(@PathVariable Long organismeId) {
        return ResponseEntity.ok(
                demandeBourseService.findByOrganismeBourseId(organismeId)
                        .stream()
                        .map(this::toDto)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/find/statut/{statut}")
    public ResponseEntity<List<DemandeBourseDTO>> getDemandesBourseByStatut(@PathVariable String statut) {
        return ResponseEntity.ok(
                demandeBourseService.findByStatut(statut)
                        .stream()
                        .map(this::toDto)
                        .collect(Collectors.toList())
        );
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<DemandeBourseDTO> updateDemandeBourse(@PathVariable Long id, @RequestBody DemandeBourseDTO dto) {
        Optional<DemandeBourse> existing = demandeBourseService.findById(id);
        if (existing.isEmpty()) return ResponseEntity.notFound().build();

        DemandeBourse demande = existing.get();

        // ✅ Conversion String → Enum
        demande.setStatut(StatutDemande.valueOf(dto.getStatut()));

        demande.setTypeBourse(dto.getTypeBourse());
        demande.setAnneeUniversitaire(dto.getAnneeUniversitaire());
        demande.setEtablissementCible(dto.getEtablissementCible());

        return ResponseEntity.ok(toDto(demandeBourseService.save(demande)));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteDemandeBourse(@PathVariable Long id) {
        demandeBourseService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/formulaire", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Map<String, String>> traiterFormulaire(
            @ModelAttribute DemandeBourseFormDTO formDTO,
            @RequestHeader("Authorization") String authorizationHeader) {
        try {
            String token = authorizationHeader.replace("Bearer ", "");
            System.out.println("🔍 Appel backend avec fichiers : " + (formDTO.getFichiers() != null ? formDTO.getFichiers().size() : "null"));

            demandeBourseService.traiteFormulaire(formDTO, token);

            Map<String, String> response = new HashMap<>();
            response.put("message", "Demande de bourse enregistrée avec succès");

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            Map<String, String> error = new HashMap<>();
            error.put("error", "Erreur lors de la soumission : " + e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @GetMapping("/mes")
    public ResponseEntity<List<DemandeBourseDTO>> getMesDemandes(@RequestHeader("Authorization") String token) {
        String email = jwtService.extractUsername(token.replace("Bearer ", ""));
        Etudiant etudiant = etudiantService.findByEmail(email);
        List<DemandeBourse> demandes = demandeBourseService.findByEtudiantId(etudiant.getId());

        List<DemandeBourseDTO> dtos = demandes.stream().map(demande -> {
            DemandeBourseDTO dto = toDto(demande);
            if (demande.getOrganismeBourse() != null) {
                dto.setOrganismeNom(demande.getOrganismeBourse().getNom());
            } else {
                dto.setOrganismeNom("—");
            }
            return dto;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
    }

    private DemandeBourseDTO toDto(DemandeBourse demande) {
        return new DemandeBourseDTO(
                demande.getId(),
                demande.getDateDemande(),
                demande.getStatut().name(), // ✅ .name() pour retourner une chaîne
                demande.getEtudiant().getId(),
                demande.getOrganismeBourse() != null ? demande.getOrganismeBourse().getId() : null,
                demande.getTypeBourse(),
                demande.getAnneeUniversitaire(),
                demande.getEtablissementCible()
        );
    }
}
