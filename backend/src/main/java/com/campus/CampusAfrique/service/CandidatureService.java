package com.campus.CampusAfrique.service;

import com.campus.CampusAfrique.model.Candidature;
import com.campus.CampusAfrique.repository.CandidatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Date;

@Service
public class CandidatureService {

    @Autowired
    private CandidatureRepository candidatureRepository;

    public Candidature save(Candidature candidature) {
        return candidatureRepository.save(candidature);
    }

    public List<Candidature> findAll() {
        return candidatureRepository.findAll();
    }

    public Optional<Candidature> findById(Long id) {
        return candidatureRepository.findById(id);
    }

    public List<Candidature> findByEtudiantId(Long etudiantId) {
        return candidatureRepository.findByEtudiantId(etudiantId);
    }

   /* public List<Candidature> findByFormationId(Long formationId) {
        return candidatureRepository.findByFormationId(formationId);
    }*/

    public List<Candidature> findByStatut(String statut) {
        return candidatureRepository.findByStatut(statut);
    }

    public List<Candidature> findByEtablissementId(Long etablissementId) {
        return candidatureRepository.findByEtablissementId(etablissementId);
    }

   /* public List<Candidature> findByDateCandidatureBetween(Date dateDebut, Date dateFin) {
        return candidatureRepository.findByDateCandidatureBetween(dateDebut, dateFin);
    }*/

    public void deleteById(Long id) {
        candidatureRepository.deleteById(id);
    }

    public Candidature update(Long id, Candidature candidature) {
        if (candidatureRepository.existsById(id)) {
            candidature.setId(id);
            return candidatureRepository.save(candidature);
        }
        throw new RuntimeException("Candidature non trouvée avec l'id: " + id);
    }


}
