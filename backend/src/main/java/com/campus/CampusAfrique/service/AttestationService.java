package com.campus.CampusAfrique.service;

import com.campus.CampusAfrique.model.Attestation;
import com.campus.CampusAfrique.repository.AttestationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Date;

@Service
public class AttestationService {

    @Autowired
    private AttestationRepository attestationRepository;

    public Attestation save(Attestation attestation) {
        return attestationRepository.save(attestation);
    }

    public List<Attestation> findAll() {
        return attestationRepository.findAll();
    }

    public Optional<Attestation> findById(Long id) {
        return attestationRepository.findById(id);
    }

   /* public List<Attestation> findByEtudiantId(Long etudiantId) {
        return attestationRepository.findByEtudiantId(etudiantId);
    }*/

    public List<Attestation> findByType(String type) {
        return attestationRepository.findByType(type);
    }

    /*public List<Attestation> findByStatut(String statut) {
        return attestationRepository.findByStatut(statut);
    }*/

   /* public List<Attestation> findByDateEmissionBetween(Date dateDebut, Date dateFin) {
        return attestationRepository.findByDateEmissionBetween(dateDebut, dateFin);
    }*/

    public void deleteById(Long id) {
        attestationRepository.deleteById(id);
    }

    public Attestation update(Long id, Attestation attestation) {
        if (attestationRepository.existsById(id)) {
            attestation.setId(id);
            return attestationRepository.save(attestation);
        }
        throw new RuntimeException("Attestation non trouvée avec l'id: " + id);
    }
}