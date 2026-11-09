package com.campus.CampusAfrique.controller;

import com.campus.CampusAfrique.model.Attestation;
import com.campus.CampusAfrique.service.AttestationService;
import com.campus.CampusAfrique.service.EtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/attestations")
public class AttestationController {

    @Autowired
    private AttestationService attestationService;

    public AttestationController(AttestationService attestationService) {
        this.attestationService = attestationService;
    }

    @PostMapping("/add")
    public ResponseEntity<Attestation> createAttestation(@RequestBody Attestation attestation) {
        return ResponseEntity.ok(attestationService.save(attestation));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Attestation>> getAllAttestations() {
        return ResponseEntity.ok(attestationService.findAll());
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Attestation> getAttestationById(@PathVariable Long id) {
        return attestationService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /*@GetMapping("/etudiant/{etudiantId}")
    public ResponseEntity<List<Attestation>> getAttestationsByEtudiant(@PathVariable Long etudiantId) {
        return ResponseEntity.ok(attestationService.findByEtudiantId(etudiantId));
    }*/

    @GetMapping("/find/type/{type}")
    public ResponseEntity<List<Attestation>> getAttestationsByType(@PathVariable String type) {
        return ResponseEntity.ok(attestationService.findByType(type));
    }

   /* @GetMapping("/statut/{statut}")
    public ResponseEntity<List<Attestation>> getAttestationsByStatut(@PathVariable String statut) {
        return ResponseEntity.ok(attestationService.findByStatut(statut));
    }

    @GetMapping("/periode")
    public ResponseEntity<List<Attestation>> getAttestationsByPeriode(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dateDebut,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dateFin) {
        return ResponseEntity.ok(attestationService.findByDateEmissionBetween(dateDebut, dateFin));
    }*/

    @PutMapping("/update/{id}")
    public ResponseEntity<Attestation> updateAttestation(@PathVariable Long id, @RequestBody Attestation attestation) {
        try {
            return ResponseEntity.ok(attestationService.update(id, attestation));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAttestation(@PathVariable Long id) {
        attestationService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
