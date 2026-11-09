package com.campus.CampusAfrique.controller;

import com.campus.CampusAfrique.dto.OrganismeBourseDTO;
import com.campus.CampusAfrique.model.OrganismeBourse;
import com.campus.CampusAfrique.service.OrganismeBourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/organismes-bourse")
public class OrganismeBourseController {

    @Autowired
    private OrganismeBourseService organismeBourseService;

    // 🔁 Convertisseur Entité -> DTO
    private OrganismeBourseDTO toDTO(OrganismeBourse organisme) {
        return new OrganismeBourseDTO(
                organisme.getId(),
                organisme.getNom(),
                organisme.getType(),
                organisme.getPays(),
                organisme.getEmail() // ⚠ Si tu veux un champ "siteWeb", ajoute-le dans l'entité !
        );
    }

    // 🔁 Convertisseur DTO -> Entité
    private OrganismeBourse toEntity(OrganismeBourseDTO dto) {
        OrganismeBourse organisme = new OrganismeBourse();
        organisme.setId(dto.getId());
        organisme.setNom(dto.getNom());
        organisme.setType(dto.getType());
        organisme.setPays(dto.getPays());
        organisme.setEmail(dto.getSiteWeb()); // ⚠ "email" est utilisé ici pour siteWeb, à ajuster si besoin
        return organisme;
    }

    @PostMapping("/add")
    public ResponseEntity<OrganismeBourseDTO> createOrganisme(@RequestBody OrganismeBourseDTO dto) {
        OrganismeBourse saved = organismeBourseService.save(toEntity(dto));
        return ResponseEntity.ok(toDTO(saved));
    }

    @GetMapping("/all")
    public ResponseEntity<List<OrganismeBourseDTO>> getAllOrganismes() {
        List<OrganismeBourseDTO> dtos = organismeBourseService.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<OrganismeBourseDTO> getById(@PathVariable Long id) {
        Optional<OrganismeBourse> organismeOpt = organismeBourseService.findById(id);
        return organismeOpt.map(organisme -> ResponseEntity.ok(toDTO(organisme)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/find/email/{email}")
    public ResponseEntity<OrganismeBourseDTO> getByEmail(@PathVariable String email) {
        Optional<OrganismeBourse> organismeOpt = organismeBourseService.findByEmail(email);
        return organismeOpt.map(organisme -> ResponseEntity.ok(toDTO(organisme)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/find/pays/{pays}")
    public ResponseEntity<List<OrganismeBourseDTO>> getByPays(@PathVariable String pays) {
        List<OrganismeBourseDTO> dtos = organismeBourseService.findByPays(pays)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/find/type/{type}")
    public ResponseEntity<List<OrganismeBourseDTO>> getByType(@PathVariable String type) {
        List<OrganismeBourseDTO> dtos = organismeBourseService.findByType(type)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/find/nomOrganisme/{nom}")
    public ResponseEntity<OrganismeBourseDTO> getByNom(@PathVariable String nom) {
        Optional<OrganismeBourse> organismeOpt = organismeBourseService.findByNom(nom);
        return organismeOpt.map(organisme -> ResponseEntity.ok(toDTO(organisme)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<OrganismeBourseDTO> updateOrganisme(@PathVariable Long id, @RequestBody OrganismeBourseDTO dto) {
        try {
            OrganismeBourse updated = organismeBourseService.update(id, toEntity(dto));
            return ResponseEntity.ok(toDTO(updated));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteOrganisme(@PathVariable Long id) {
        organismeBourseService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
