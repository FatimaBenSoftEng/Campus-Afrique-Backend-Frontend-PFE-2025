package com.campus.CampusAfrique.service;

import com.campus.CampusAfrique.dto.DemandeHebergementDTO;
import com.campus.CampusAfrique.dto.DemandeHebergementFormDTO;
import com.campus.CampusAfrique.model.*;
import com.campus.CampusAfrique.repository.DemandeHebergementRepository;
import com.campus.CampusAfrique.repository.DocumentRepository;
import com.campus.CampusAfrique.repository.DossierRepository;
import com.campus.CampusAfrique.repository.EtudiantRepository;
import com.campus.CampusAfrique.security.JwtService;
import com.campus.CampusAfrique.security.user.User;
import com.campus.CampusAfrique.security.user.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DemandeHebergementService {

    @Autowired
    private DemandeHebergementRepository hebergementRepository;

    @Autowired
    private DossierRepository dossierRepository;

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    @Autowired
    private EtudiantService etudiantService;

    @Autowired
    private EtudiantRepository etudiantRepository;

    @Autowired
    private DemandeHebergementRepository demandeHebergementRepository;

    @Autowired private DemandeHebergementRepository repository;


    public void traiterDemande(DemandeHebergementFormDTO dto, String token) {
        // 1. Extraire l'utilisateur depuis le token
        String email = jwtService.extractUsername(token);
        User user = userService.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        // 2. Récupérer l'étudiant lié
        Etudiant etudiant = etudiantRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Étudiant non trouvé pour cet utilisateur"));

        // 3. Créer une demande d’hébergement
        DemandeHebergement demande = new DemandeHebergement();
        demande.setEtudiant(etudiant);
        demande.setDateDemande(LocalDate.now());
        demande.setDateArrivee(dto.getDateArrivee());
        demande.setStatut(StatutDemande.EN_ATTENTE);// Attention au format
        demande.setTypeHebergement(dto.getTypeHebergement());
        demande.setVille(dto.getVille());
        demande.setCommentaires(dto.getCommentaires());

        demandeHebergementRepository.save(demande);

        // 4. Créer un dossier lié à la demande
        Dossier dossier = new Dossier();
        dossier.setEtudiant(etudiant);
        dossier.setDateCreation(LocalDate.now());
        dossier.setStatut("EN_ATTENTE");
        dossier.setTypeDossier(TypeDossier.HEBERGEMENT);
        dossier.setDateMiseAJour(LocalDate.now());

        // 🔗 Lier la demande d’hébergement au dossier
        dossier.setDemandeHebergement(demande);

        dossierRepository.save(dossier);

        // 5. Enregistrer les fichiers uploadés dans la table Document
        if (dto.getFichiers() != null && !dto.getFichiers().isEmpty()) {
            for (MultipartFile fichier : dto.getFichiers()) {
                if (!fichier.isEmpty()) {
                    String fileName = fichier.getOriginalFilename();
                    String chemin = "uploads/" + fileName;

                    Document doc = new Document();
                    doc.setNom(fileName);
                    doc.setCheminFichier(chemin);
                    doc.setDateAjout(LocalDate.now());
                    doc.setStatut("EN_ATTENTE");
                    doc.setType(fichier.getContentType());
                    doc.setDossier(dossier);

                    documentRepository.save(doc);
                }
            }
        }
    }


    public List<DemandeHebergementDTO> getDemandesByToken(String token) {
        String email = jwtService.extractUsername(token);
        User user = userService.findByEmail(email).orElseThrow();
        Etudiant etudiant = etudiantRepository.findByUser(user).orElseThrow();

        return repository.findByEtudiantId(etudiant.getId())
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private DemandeHebergementDTO toDto(DemandeHebergement d) {
        return new DemandeHebergementDTO(
                d.getId(),
                d.getTypeHebergement(),
                d.getVille(),
                d.getCommentaires(),
                d.getDateDemande(),
                d.getDateArrivee(),
                d.getCommentaires(),
                d.getStatut()
        );

    }






}
