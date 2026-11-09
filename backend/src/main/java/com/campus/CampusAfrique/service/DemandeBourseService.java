package com.campus.CampusAfrique.service;


import com.campus.CampusAfrique.dto.DemandeBourseFormDTO;
import com.campus.CampusAfrique.model.*;
import com.campus.CampusAfrique.repository.DemandeBourseRepository;
import com.campus.CampusAfrique.repository.DocumentRepository;
import com.campus.CampusAfrique.repository.DossierRepository;
import com.campus.CampusAfrique.repository.EtudiantRepository;
import com.campus.CampusAfrique.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Date;

@Service
public class DemandeBourseService {

    @Autowired private DemandeBourseRepository demandeBourseRepository;
    @Autowired private EtudiantRepository etudiantRepository;
    @Autowired private DocumentRepository documentRepository;
    @Autowired private DossierRepository dossierRepository;
    @Autowired private JwtService jwtService;
    @Autowired
    private OrganismeBourseService organismeBourseService;


    @Value("${upload.directory}")
    private String uploadDir;

    public DemandeBourse save(DemandeBourse demande) {
        return demandeBourseRepository.save(demande);
    }

    public List<DemandeBourse> findAll() {
        return demandeBourseRepository.findAll();
    }

    public Optional<DemandeBourse> findById(Long id) {
        return demandeBourseRepository.findById(id);
    }

    public List<DemandeBourse> findByEtudiantId(Long etudiantId) {
        return demandeBourseRepository.findByEtudiantId(etudiantId);
    }

    public List<DemandeBourse> findByOrganismeBourseId(Long organismeId) {
        return demandeBourseRepository.findByOrganismeBourseId(organismeId);
    }

    public List<DemandeBourse> findByStatut(String statut) {
        return demandeBourseRepository.findByStatut(statut);
    }

    public void traiteFormulaire(DemandeBourseFormDTO formDTO, String token) {
        // === 1. Récupérer l'étudiant connecté ===
        String email = jwtService.extractUsername(token);
        Etudiant etudiant = etudiantRepository.findByEmail(email);
        if (etudiant == null) throw new RuntimeException("Étudiant introuvable avec ce token");

        // === 2. Créer la demande de bourse ===
        DemandeBourse demande = new DemandeBourse();
        demande.setDateDemande(LocalDate.now());
        demande.setStatut(StatutDemande.EN_ATTENTE);
        demande.setEtudiant(etudiant);
        demande.setTypeBourse(formDTO.getTypeBourse());
        demande.setAnneeUniversitaire(formDTO.getAnneeUniversitaire());
        demande.setEtablissementCible(formDTO.getEtablissementCible());

        if (formDTO.getOrganismeBourseId() != null) {
            OrganismeBourse organisme = organismeBourseService
                    .findById(formDTO.getOrganismeBourseId())
                    .orElseThrow(() -> new RuntimeException("Organisme introuvable"));
            demande.setOrganismeBourse(organisme);
        }

        demandeBourseRepository.save(demande);

        // === 3. Créer le dossier lié à cette demande de bourse ===
        Dossier dossier = new Dossier();
        dossier.setEtudiant(etudiant);
        dossier.setDemandeBourse(demande);
        dossier.setDateCreation(LocalDate.now());
        dossier.setDateMiseAJour(LocalDate.now());
        dossier.setStatut("EN_ATTENTE");
        dossier.setTypeDossier(TypeDossier.BOURSE);

        dossierRepository.save(dossier);

        // === 4. Enregistrer les documents dans la base ===
        if (formDTO.getFichiers() != null) {
            for (MultipartFile fichier : formDTO.getFichiers()) {
                String fileName = fichier.getOriginalFilename();
                if (fileName == null || fileName.trim().isEmpty()) continue;

                String chemin = "uploads/" + fileName; // tu n'enregistres pas physiquement, donc ok

                Document document = new Document();
                document.setNom(fileName);
                document.setType(fichier.getContentType());
                document.setCheminFichier(chemin);
                document.setDateAjout(LocalDate.now());
                document.setStatut("EN_ATTENTE");
                document.setDossier(dossier);

                documentRepository.save(document);
            }
        }
    }



    public void deleteById(Long id) {
        demandeBourseRepository.deleteById(id);
    }

    public DemandeBourse update(Long id, DemandeBourse demande) {
        if (demandeBourseRepository.existsById(id)) {
            demande.setId(id);
            return demandeBourseRepository.save(demande);
        }
        throw new RuntimeException("Demande de bourse non trouvée avec l'id: " + id);
    }
}
