// SERVICE : PreinscriptionService.java

package com.campus.CampusAfrique.service;

import com.campus.CampusAfrique.dto.PreinscriptionFormDTO;
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
import java.util.Optional;
import java.util.UUID;

@Service
public class PreinscriptionService {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    @Autowired
    private EtudiantRepository etudiantRepository;

    @Autowired
    private PreinscriptionRepository preinscriptionRepository;

    @Autowired
    private FormationRepository formationRepository;

    @Autowired
    private EtablissementRepository etablissementRepository;

    @Autowired
    private DossierRepository dossierRepository;

    @Autowired
    private DocumentRepository documentRepository;


    public Preinscription save(Preinscription preinscription) {
        return preinscriptionRepository.save(preinscription);
    }

    public List<Preinscription> findAll() {
        return preinscriptionRepository.findAll();
    }

    public Optional<Preinscription> findById(Long id) {
        return preinscriptionRepository.findById(id);
    }

    public List<Preinscription> findByEtudiantId(Long etudiantId) {
        return preinscriptionRepository.findByEtudiantId(etudiantId);
    }

    public List<Preinscription> findByFormationId(Long formationId) {
        return preinscriptionRepository.findByFormationId(formationId);
    }

    public List<Preinscription> findByStatut(String statut) {
        return preinscriptionRepository.findByStatut(statut);
    }

    public List<Preinscription> findByOrdrePreference(int ordrePreference) {
        return preinscriptionRepository.findByOrdrePreference(ordrePreference);
    }

    public void deleteById(Long id) {
        preinscriptionRepository.deleteById(id);
    }

    public Preinscription update(Long id, Preinscription preinscription) {
        if (preinscriptionRepository.existsById(id)) {
            preinscription.setId(id);
            return preinscriptionRepository.save(preinscription);
        }
        throw new RuntimeException("Préinscription non trouvée avec l'id: " + id);
    }

    public void traiteFormulaire(PreinscriptionFormDTO formDTO, String token) {
        // === 1. Récupérer l'utilisateur connecté à partir du token JWT ===
        String email = jwtService.extractUsername(token);  // email contenu dans le token
        User user = userService.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        // === 2. Récupérer l'étudiant lié à cet utilisateur ===
        Etudiant etudiant = etudiantRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Étudiant non trouvé pour cet utilisateur"));

        // === 3. Compléter les champs manquants (remplissage automatique partiel) ===
        if (!"undefined".equals(formDTO.getTypePiece()))
            etudiant.setTypeIdentite(formDTO.getTypePiece());

        if (!"undefined".equals(formDTO.getNumeroPiece()))
            etudiant.setNumeroIdentite(formDTO.getNumeroPiece());

        if (!"undefined".equals(formDTO.getMatricule()))
            etudiant.setMatricule(formDTO.getMatricule());

        if (!"undefined".equals(formDTO.getPaysResidence()))
            etudiant.setPaysOrigine(formDTO.getPaysResidence());


        etudiantRepository.save(etudiant); // mise à jour en base

        // === 4. Récupérer la formation et l'établissement ===
        Formation formation = formationRepository.findById(formDTO.getFormationId())
                .orElseThrow(() -> new RuntimeException("Formation non trouvée"));
        Etablissement etablissement = formation.getEtablissement();

        // === 5. Créer la préinscription ===
        Preinscription pre = new Preinscription();
        pre.setEtudiant(etudiant);
        pre.setFormation(formation);
        pre.setEtablissement(etablissement);
        pre.setStatut(StatutPreinscription.VALIDEE);
        pre.setChoixFormation(formation.getNom());
        pre.setOrdrePreference(1);
        pre.setNumeroDossier(UUID.randomUUID().toString());
        pre.setDatePreInscription(LocalDate.now());

        preinscriptionRepository.save(pre);

        // === 6. Créer le dossier de préinscription ===
        Dossier dossier = new Dossier();
        dossier.setEtudiant(etudiant);
        dossier.setPreinscription(pre);
        dossier.setDateCreation(LocalDate.now());
        dossier.setStatut("EN_ATTENTE");
        dossier.setTypeDossier(TypeDossier.PREINSCRIPTION);

        dossierRepository.save(dossier);

        // === 7. Enregistrer les documents uploadés ===
        if (formDTO.getFichiers() != null) {
            for (MultipartFile fichier : formDTO.getFichiers()) {
                String fileName = fichier.getOriginalFilename();
                String chemin = "uploads/" + fileName;

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

    public List<Preinscription> findByToken(String token) {
        String email = jwtService.extractUsername(token);
        User user = userService.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        Etudiant etudiant = etudiantRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Étudiant non trouvé"));
        return preinscriptionRepository.findByEtudiantId(etudiant.getId());
    }


}
