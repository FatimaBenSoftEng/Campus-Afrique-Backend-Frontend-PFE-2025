// CONTROLLER 5 : DocumentController.java

package com.campus.CampusAfrique.controller;

import com.campus.CampusAfrique.dto.DocumentDTO;
import com.campus.CampusAfrique.model.Document;
import com.campus.CampusAfrique.model.Dossier;
import com.campus.CampusAfrique.service.DocumentService;
import com.campus.CampusAfrique.service.DossierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/documents")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @Autowired
    private DossierService dossierService;

    @PostMapping("/add")
    public ResponseEntity<DocumentDTO> createDocument(@RequestBody DocumentDTO dto) {
        Optional<Dossier> dossierOpt = dossierService.findById(dto.getDossierId());
        if (dossierOpt.isEmpty()) return ResponseEntity.badRequest().build();

        Document doc = new Document();
        doc.setNom(dto.getNom());
        doc.setType(dto.getType());
        doc.setCheminFichier(dto.getCheminFichier());
        doc.setDateAjout(LocalDate.now());
        doc.setStatut(dto.getStatut());
        doc.setDossier(dossierOpt.get());

        return ResponseEntity.ok(toDto(documentService.save(doc)));
    }

    @GetMapping("/all")
    public ResponseEntity<List<DocumentDTO>> getAllDocuments() {
        return ResponseEntity.ok(
                documentService.findAll()
                        .stream()
                        .map(this::toDto)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<DocumentDTO> getDocumentById(@PathVariable Long id) {
        return documentService.findById(id)
                .map(this::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/find/dossier/{dossierId}")
    public ResponseEntity<List<DocumentDTO>> getDocumentsByDossier(@PathVariable Long dossierId) {
        return ResponseEntity.ok(
                documentService.findByDossierId(dossierId)
                        .stream()
                        .map(this::toDto)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/find/type/{type}")
    public ResponseEntity<List<DocumentDTO>> getDocumentsByType(@PathVariable String type) {
        return ResponseEntity.ok(
                documentService.findByType(type)
                        .stream()
                        .map(this::toDto)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/find/statut/{statut}")
    public ResponseEntity<List<DocumentDTO>> getDocumentsByStatut(@PathVariable String statut) {
        return ResponseEntity.ok(
                documentService.findByStatut(statut)
                        .stream()
                        .map(this::toDto)
                        .collect(Collectors.toList())
        );
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<DocumentDTO> updateDocument(@PathVariable Long id, @RequestBody DocumentDTO dto) {
        Optional<Document> existingOpt = documentService.findById(id);
        if (existingOpt.isEmpty()) return ResponseEntity.notFound().build();

        Document doc = existingOpt.get();
        doc.setNom(dto.getNom());
        doc.setType(dto.getType());
        doc.setCheminFichier(dto.getCheminFichier());
        doc.setStatut(dto.getStatut());

        return ResponseEntity.ok(toDto(documentService.save(doc)));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteDocument(@PathVariable Long id) {
        documentService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    private DocumentDTO toDto(Document d) {
        return new DocumentDTO(
                d.getId(),
                d.getNom(),
                d.getType(),
                d.getCheminFichier(),
                d.getDateAjout(),
                d.getStatut(),
                d.getDossier().getId()
        );
    }
}
