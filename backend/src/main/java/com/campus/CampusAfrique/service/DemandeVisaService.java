package com.campus.CampusAfrique.service;

import com.campus.CampusAfrique.dto.DemandeVisaFormDTO;
import com.campus.CampusAfrique.model.*;
import com.campus.CampusAfrique.repository.*;
import com.campus.CampusAfrique.security.JwtService;
import com.campus.CampusAfrique.security.user.User;
import com.campus.CampusAfrique.security.user.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@Service
public class DemandeVisaService {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    @Autowired
    private EtudiantRepository etudiantRepository;

    @Autowired
    private DemandeVisaRepository demandeVisaRepository;

    @Autowired
    private DossierRepository dossierRepository;

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private AgentAmbassadeRepository agentAmbassadeRepository;

    public void traiterDemande(DemandeVisaFormDTO dto, String token) {
        // Authentification : récupérer l'étudiant connecté
        String email = jwtService.extractUsername(token.replace("Bearer ", ""));
        User user = userService.findByEmail(email).orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));
        Etudiant etudiant = etudiantRepository.findByUser(user).orElseThrow(() -> new RuntimeException("Étudiant introuvable"));

        // Récupérer l’ambassade si sélectionnée
        AgentAmbassade ambassade = null;
        if (dto.getAmbassadeId() != null) {
            ambassade = agentAmbassadeRepository.findById(dto.getAmbassadeId())
                    .orElseThrow(() -> new RuntimeException("Ambassade non trouvée"));
        }

        // Création de la demande de visa
        DemandeVisa demande = new DemandeVisa();
        demande.setEtudiant(etudiant);
        demande.setPaysDestination(dto.getPaysDestination());
        demande.setVille(dto.getVille());
        demande.setEtablissement(dto.getEtablissement());
        demande.setDateDepart(dto.getDateDepart());
        demande.setDuree(dto.getDuree());
        demande.setAdresse(dto.getAdresse());
        demande.setNomGarant(dto.getNomGarant());
        demande.setLien(dto.getLien());
        demande.setAdresseGarant(dto.getAdresseGarant());
        demande.setTelephoneGarant(dto.getTelephoneGarant());
        demande.setProfessionGarant(dto.getProfessionGarant());
        demande.setRevenu(dto.getRevenu());
        demande.setCommentaires(dto.getCommentaires());
        demande.setDateDemande(LocalDate.now());
        demande.setStatut(StatutDemande.COMPLEMENT);

        if (ambassade != null) {
            demande.setAmbassade(ambassade);
        }

        // Sauvegarde de la demande
        demandeVisaRepository.save(demande);

        // Création du dossier lié à cette demande
        Dossier dossier = new Dossier();
        dossier.setEtudiant(etudiant);
        dossier.setTypeDossier(TypeDossier.VISA);
        dossier.setDateCreation(LocalDate.now());
        dossier.setDateMiseAJour(LocalDate.now());
        dossier.setStatut("EN_ATTENTE");
        dossier.setDemandeVisa(demande);
        dossierRepository.save(dossier);

        // Enregistrement des documents
        if (dto.getFichiers() != null) {
            for (MultipartFile fichier : dto.getFichiers()) {
                if (!fichier.isEmpty()) {
                    Document doc = new Document();
                    doc.setNom(fichier.getOriginalFilename());
                    doc.setCheminFichier("uploads/" + fichier.getOriginalFilename());
                    doc.setDateAjout(LocalDate.now());
                    doc.setType(fichier.getContentType());
                    doc.setStatut("EN_ATTENTE");
                    doc.setDossier(dossier);
                    documentRepository.save(doc);
                }
            }
        }

        System.out.println("✅ Demande de visa enregistrée pour : " + etudiant.getNom());
    }

    public List<DemandeVisa> getDemandesParEtudiant(String token) {
        String email = jwtService.extractUsername(token.replace("Bearer ", ""));
        User user = userService.findByEmail(email).orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));
        Etudiant etudiant = etudiantRepository.findByUser(user).orElseThrow(() -> new RuntimeException("Étudiant introuvable"));
        return demandeVisaRepository.findByEtudiant(etudiant);
    }
}
