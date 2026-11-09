package com.campus.CampusAfrique.service;

import com.campus.CampusAfrique.dto.DemandeTransportDTO;
import com.campus.CampusAfrique.dto.DemandeTransportFormDTO;
import com.campus.CampusAfrique.model.*;
import com.campus.CampusAfrique.repository.DemandeTransportRepository;
import com.campus.CampusAfrique.repository.DocumentRepository;
import com.campus.CampusAfrique.repository.DossierRepository;
import com.campus.CampusAfrique.repository.EtudiantRepository;
import com.campus.CampusAfrique.security.JwtService;
import com.campus.CampusAfrique.security.user.User;
import com.campus.CampusAfrique.security.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DemandeTransportService {

    @Autowired
    private JwtService jwtService;
    @Autowired private UserService userService;
    @Autowired private EtudiantRepository etudiantRepository;
    @Autowired private DemandeTransportRepository demandeTransportRepository;
    @Autowired private DossierRepository dossierRepository;
    @Autowired private DocumentRepository documentRepository;

    @Autowired private DemandeTransportRepository transportRepository;
    public void traiterDemande(DemandeTransportFormDTO dto, String token) {
        String email = jwtService.extractUsername(token);
        User user = userService.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        Etudiant etudiant = etudiantRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Étudiant non trouvé"));

        // 1. Enregistrer la demande
        DemandeTransport demande = new DemandeTransport();
        demande.setEtudiant(etudiant);
        demande.setVille(dto.getVille());
        demande.setAdresse(dto.getAdresse());
        demande.setFrequence(dto.getFrequence());
        demande.setStatut(StatutDemande.VALIDEE);
        demande.setTypeTransport(dto.getTypeTransport());
        demande.setBesoinAbonnement(dto.getBesoinAbonnement());
        demande.setCommentaires(dto.getCommentaires());
        demande.setDateDemande(LocalDate.now());

        demandeTransportRepository.save(demande);

        // 2. Créer le dossier lié
        Dossier dossier = new Dossier();
        dossier.setEtudiant(etudiant);
        dossier.setDateCreation(LocalDate.now());
        dossier.setDateMiseAJour(LocalDate.now());
        dossier.setStatut("EN_ATTENTE");
        dossier.setTypeDossier(TypeDossier.TRANSPORT);
        dossier.setDemandeTransport(demande);
        dossierRepository.save(dossier);

        // 3. Enregistrer les fichiers
        if (dto.getFichiers() != null) {
            for (MultipartFile fichier : dto.getFichiers()) {
                if (!fichier.isEmpty()) {
                    Document doc = new Document();
                    doc.setNom(fichier.getOriginalFilename());
                    doc.setType(fichier.getContentType());
                    doc.setCheminFichier("uploads/" + fichier.getOriginalFilename());
                    doc.setDateAjout(LocalDate.now());
                    doc.setStatut("EN_ATTENTE");
                    doc.setDossier(dossier);
                    documentRepository.save(doc);
                }
            }
        }
    }

    public List<DemandeTransport> getDemandesParEtudiant(String token) {
        String email = jwtService.extractUsername(token);
        User user = userService.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        Etudiant etudiant = etudiantRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Étudiant non trouvé"));

        return demandeTransportRepository.findByEtudiant(etudiant);
    }

    public List<DemandeTransportDTO> getDemandesByToken(String token) {
        String email = jwtService.extractUsername(token);
        User user = userService.findByEmail(email).orElseThrow();
        Etudiant etudiant = etudiantRepository.findByUser(user).orElseThrow();

        return transportRepository.findByEtudiantId(etudiant.getId())
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private DemandeTransportDTO toDto(DemandeTransport d) {
        return new DemandeTransportDTO(
                d.getId(),
                d.getVille(),
                d.getAdresse(),
                d.getFrequence(),
                d.getTypeTransport(),
                d.getBesoinAbonnement(),
                d.getCommentaires(),
                d.getDateDemande(),
                d.getStatut()
        );
    }

}

