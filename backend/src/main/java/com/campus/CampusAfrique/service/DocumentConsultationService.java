package com.campus.CampusAfrique.service;

import com.campus.CampusAfrique.dto.DocumentDTO;
import com.campus.CampusAfrique.model.Document;
import com.campus.CampusAfrique.model.Etudiant;
import com.campus.CampusAfrique.repository.DocumentRepository;
import com.campus.CampusAfrique.repository.EtudiantRepository;
import com.campus.CampusAfrique.security.JwtService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocumentConsultationService {

    @Autowired private DocumentRepository documentRepository;
    @Autowired private JwtService jwtService;
    @Autowired private EtudiantRepository etudiantRepository;

    public List<DocumentDTO> getDocumentsByEtudiant(String token) {
        String email = jwtService.extractUsername(token.replace("Bearer ", ""));

        Etudiant etudiant = etudiantRepository.findByUserEmail(email)
                .orElseThrow(() -> new RuntimeException("Étudiant non trouvé pour l'email : " + email));

        return etudiant.getDossiers().stream()
                .flatMap(dossier -> dossier.getDocuments().stream())
                .map(this::mapToDTO)
                .toList();
    }

    private DocumentDTO mapToDTO(Document doc) {
        return new DocumentDTO(
                doc.getId(),
                doc.getNom(),
                doc.getType(),
                doc.getCheminFichier(),
                doc.getDateAjout(),
                doc.getStatut(),
                doc.getDossier() != null ? doc.getDossier().getId() : null
        );
    }
}
