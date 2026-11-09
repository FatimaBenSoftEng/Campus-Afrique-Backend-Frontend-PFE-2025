package com.campus.CampusAfrique.controller;

import com.campus.CampusAfrique.dto.FormationDTO;
import com.campus.CampusAfrique.model.Etablissement;
import com.campus.CampusAfrique.model.Formation;
import com.campus.CampusAfrique.model.NiveauScolaire;
import com.campus.CampusAfrique.repository.NiveauScolaireRepository;
import com.campus.CampusAfrique.service.EtablissementService;
import com.campus.CampusAfrique.dto.EtablissementDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/etablissements")
public class EtablissementController {

    @Autowired
    private EtablissementService etablissementService;

    @Autowired
    private NiveauScolaireRepository niveauScolaireRepository;

    public EtablissementController(EtablissementService etablissementService) {
        this.etablissementService = etablissementService;
    }

    @PostMapping("/add")
    public ResponseEntity<Etablissement> createEtablissement(@RequestBody Etablissement etablissement) {
        return ResponseEntity.ok(etablissementService.save(etablissement));
    }

    @GetMapping("/all")
    public ResponseEntity<List<EtablissementDTO>> getAllEtablissements() {
        List<EtablissementDTO> dtoList = etablissementService.findAll().stream()
                .map(e -> new EtablissementDTO(
                        e.getId(),
                        e.getNom(),
                        e.getAdresse(),
                        e.getDescription(),
                        e.getEmail(),
                        e.getSiteWeb(),
                        e.isBourseDisponible(),
                        e.getVille(),
                        e.getPays(),
                        e.getNomAbr(),
                        e.getType(),
                        e.getImageUrl(),
                        e.isFavori(),
                        e.getTelephone(),
                        e.getDateCreation(),
                        e.getDateDebutCandidature(),
                        e.getDateFinCandidature(),
                        e.getTypeDiplome(),
                        e.getReconnaissanceEtat()
                ))
                .toList();
        return ResponseEntity.ok(dtoList);
    }

    @GetMapping("/search")
    public ResponseEntity<List<EtablissementDTO>> searchEtablissements(@RequestParam("keyword") String keyword) {
        List<EtablissementDTO> result = etablissementService.searchByKeyword(keyword).stream()
                .map(e -> new EtablissementDTO(
                        e.getId(),
                        e.getNom(),
                        e.getAdresse(),
                        e.getDescription(),
                        e.getEmail(),
                        e.getSiteWeb(),
                        e.isBourseDisponible(),
                        e.getVille(),
                        e.getPays(),
                        e.getNomAbr(),
                        e.getType(),
                        e.getImageUrl(),
                        e.isFavori(),
                        e.getTelephone(),
                        e.getDateCreation(),
                        e.getDateDebutCandidature(),
                        e.getDateFinCandidature(),
                        e.getTypeDiplome(),
                        e.getReconnaissanceEtat()
                ))
                .toList();

        return ResponseEntity.ok(result);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<EtablissementDTO> getEtablissementById(@PathVariable Long id) {
        return etablissementService.findById(id)
                .map(e -> {
                    List<FormationDTO> formationDTOs = e.getFormations().stream()
                            .map(FormationDTO::new)
                            .toList();

                    double minFrais = formationDTOs.stream()
                            .mapToDouble(FormationDTO::getFraisAnnuel)
                            .min().orElse(0);

                    double maxFrais = formationDTOs.stream()
                            .mapToDouble(FormationDTO::getFraisAnnuel)
                            .max().orElse(0);

                    List<String> parcours = niveauScolaireRepository.findAll().stream()
                            .map(NiveauScolaire::getNom)
                            .toList();

                    EtablissementDTO dto = new EtablissementDTO(
                            e.getId(), e.getNom(), e.getAdresse(), e.getDescription(),
                            e.getEmail(), e.getSiteWeb(), e.isBourseDisponible(),
                            e.getVille(), e.getPays(), e.getNomAbr(), e.getType(),
                            e.getImageUrl(), e.isFavori(), e.getTelephone(),
                            e.getDateCreation(), e.getDateDebutCandidature(), e.getDateFinCandidature(),
                            e.getTypeDiplome(), e.getReconnaissanceEtat()
                    );

                    dto.setFormations(formationDTOs);
                    dto.setParcours(parcours);
                    dto.setFraisScolariteMin(minFrais);
                    dto.setFraisScolariteMax(maxFrais);

                    return dto;
                })
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @GetMapping("/find/pays/{pays}")
    public ResponseEntity<List<Etablissement>> getEtablissementsByPays(@PathVariable String pays) {
        return ResponseEntity.ok(etablissementService.findByPays(pays));
    }

    @GetMapping("/find/ville/{ville}")
    public ResponseEntity<List<Etablissement>> getEtablissementsByVille(@PathVariable String ville) {
        return ResponseEntity.ok(etablissementService.findByVille(ville));
    }

    @GetMapping("/find/type/{type}")
    public ResponseEntity<List<Etablissement>> getEtablissementsByType(@PathVariable String type) {
        return ResponseEntity.ok(etablissementService.findByType(type));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Etablissement> updateEtablissement(@PathVariable Long id, @RequestBody Etablissement etablissement) {
        try {
            return ResponseEntity.ok(etablissementService.update(id, etablissement));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteEtablissement(@PathVariable Long id) {
        etablissementService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
