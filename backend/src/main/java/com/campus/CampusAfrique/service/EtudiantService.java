package com.campus.CampusAfrique.service;

import com.campus.CampusAfrique.dto.EtudiantDTO;
import com.campus.CampusAfrique.model.Etudiant;
import com.campus.CampusAfrique.repository.EtudiantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.campus.CampusAfrique.security.user.UserService;
import com.campus.CampusAfrique.service.NiveauScolaireService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class EtudiantService {

    private final EtudiantRepository etudiantRepository;
    @Autowired
    private UserService userService;

    @Autowired
    private NiveauScolaireService niveauScolaireService;

    @Autowired
    public EtudiantService(EtudiantRepository etudiantRepository) {
        this.etudiantRepository = etudiantRepository;
    }

    // Sauvegarder un étudiant
    public Etudiant saveEtudiant(Etudiant etudiant) {
        return etudiantRepository.save(etudiant);
    }

    // Trouver tous les étudiants
    public List<Etudiant> findAllEtudiants() {
        return etudiantRepository.findAll();
    }

    // Trouver un étudiant par ID
    public Etudiant findEtudiantById(Long id) {
        return etudiantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Étudiant non trouvé avec l'ID : " + id));
    }

    public List<Etudiant> findByPaysOrigine(String pays) {
        return etudiantRepository.findByPaysOrigine(pays);
    }

    public List<Etudiant> findByNiveauScolaireId(Long id) {
        return etudiantRepository.findByNiveauScolaireId(id);
    }

    public List<Etudiant> findByStatut(String statut) {
        return etudiantRepository.findByStatut(statut);
    }

    // Mettre à jour un étudiant
    public Etudiant updateEtudiant(Long id, Etudiant etudiantDetails) {
        Etudiant etudiant = etudiantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Étudiant non trouvé avec l'ID : " + id));

        etudiant.setNom(etudiantDetails.getNom());
        etudiant.setPrenom(etudiantDetails.getPrenom());
        etudiant.setEmail(etudiantDetails.getEmail());
        etudiant.setMotDePasse(etudiantDetails.getMotDePasse());
        etudiant.setDateNaissance(etudiantDetails.getDateNaissance());
        etudiant.setTelephone(etudiantDetails.getTelephone());
        etudiant.setNationalite(etudiantDetails.getNationalite());
        etudiant.setAdresse(etudiantDetails.getAdresse());
        etudiant.setPaysOrigine(etudiantDetails.getPaysOrigine());

        return etudiantRepository.save(etudiant);
    }

    // Supprimer un étudiant
    public void deleteEtudiant(Long id) {
        etudiantRepository.deleteById(id);
    }

    // Méthodes personnalisées (exemples)
    public Etudiant findByEmail(String email) {
        return etudiantRepository.findByEmail(email);
    }

    public boolean existsByEmail(String email) {
        return etudiantRepository.existsByEmail(email);
    }

    public List<Etudiant> findByVille(String ville) {
        return etudiantRepository.findByVille(ville);
    }

    public Optional<Etudiant> findById(Long id) {
        return etudiantRepository.findById(id);
    }

    // Récupérer un étudiant à partir de l'email de l'utilisateur lié (via user.email)
    public Etudiant getEtudiantByEmail(String email) {
        return etudiantRepository.findByUserEmail(email)
                .orElseThrow(() -> new RuntimeException("Étudiant non trouvé pour l'email utilisateur : " + email));
    }

    public Etudiant updateEtudiantFromDTO(Long id, EtudiantDTO dto) {
        Etudiant etudiant = findEtudiantById(id); // méthode déjà existante

        etudiant.setNom(dto.getNom());
        etudiant.setPrenom(dto.getPrenom());
        etudiant.setEmail(dto.getEmail());
        etudiant.setMotDePasse(dto.getMotDePasse());
        etudiant.setPaysOrigine(dto.getPaysOrigine());
        etudiant.setDateNaissance(dto.getDateNaissance());
        etudiant.setAnneeObtention(dto.getAnneeObtention());
        etudiant.setNationalite(dto.getNationalite());
        etudiant.setPhotoProfil(dto.getPhotoProfil());
        etudiant.setStatut(dto.getStatut());
        etudiant.setTelephone(dto.getTelephone());
        etudiant.setAdresse(dto.getAdresse());
        etudiant.setVille(dto.getVille());
        etudiant.setGenre(dto.getGenre());
        etudiant.setDernierDiplome(dto.getDernierDiplome());
        etudiant.setSpecialite(dto.getSpecialite());
        etudiant.setMoyenne(dto.getMoyenne());

        // Relations : niveau scolaire
        if (dto.getNiveauScolaire() != null && dto.getNiveauScolaire().getId() != null) {
            niveauScolaireService.findById(dto.getNiveauScolaire().getId())
                    .ifPresent(etudiant::setNiveauScolaire);
        }

        // Relation : utilisateur (User)
        if (dto.getUser() != null && dto.getUser().getId() != null) {
            userService.findById(dto.getUser().getId())
                    .ifPresent(etudiant::setUser);
        }

        return etudiantRepository.save(etudiant);
    }

}