package com.campus.CampusAfrique.controller;

import com.campus.CampusAfrique.dto.DocumentDTO;
import com.campus.CampusAfrique.service.DocumentConsultationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/documentsConsultation")
public class DocumentConsultationController {

    @Autowired
    private DocumentConsultationService documentConsultationService;

    @GetMapping("/mes-documents")
    public ResponseEntity<List<DocumentDTO>> getMesDocuments(@RequestHeader("Authorization") String token) {
        List<DocumentDTO> documents = documentConsultationService.getDocumentsByEtudiant(token);
        return ResponseEntity.ok(documents);
    }
}
