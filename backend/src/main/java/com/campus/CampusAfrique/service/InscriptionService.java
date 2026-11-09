package com.campus.CampusAfrique.service;

import com.campus.CampusAfrique.model.Inscription;
import com.campus.CampusAfrique.repository.InscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Date;

@Service
public class InscriptionService {

    @Autowired
    private InscriptionRepository inscriptionRepository;

    public Inscription save(Inscription inscription) {
        return inscriptionRepository.save(inscription);
    }

    public List<Inscription> findAll() {
        return inscriptionRepository.findAll();
    }

    public Optional<Inscription> findById(Long id) {
        return inscriptionRepository.findById(id);
    }

    public List<Inscription> findByEtudiantId(Long etudiantId) {
        return inscriptionRepository.findByEtudiantId(etudiantId);
    }

    public List<Inscription> findByEtablissementId(Long etablissementId) {
        return inscriptionRepository.findByEtablissementId(etablissementId);
    }

    public List<Inscription> findByStatut(String statut) {
        return inscriptionRepository.findByStatut(statut);
    }

/*    public List<Inscription> findByDateInscriptionBetween(Date dateDebut, Date dateFin) {
        return inscriptionRepository.findByDateInscriptionBetween(dateDebut, dateFin);
    }

    public List<Inscription> findByMontantFraisGreaterThan(float montant) {
        return inscriptionRepository.findByMontantFraisGreaterThan(montant);
    }*/

    public List<Inscription> findByFormationId(Long formationId) {
        return inscriptionRepository.findByFormationId(formationId);
    }

    public void deleteById(Long id) {
        inscriptionRepository.deleteById(id);
    }

    public Inscription update(Long id, Inscription inscription) {
        if (inscriptionRepository.existsById(id)) {
            inscription.setId(id);
            return inscriptionRepository.save(inscription);
        }
        throw new RuntimeException("Inscription non trouvée avec l'id: " + id);
    }
}
