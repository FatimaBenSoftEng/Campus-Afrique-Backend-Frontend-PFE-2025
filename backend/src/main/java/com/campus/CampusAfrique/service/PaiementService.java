package com.campus.CampusAfrique.service;

import com.campus.CampusAfrique.model.Paiement;
import com.campus.CampusAfrique.repository.PaiementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Date;

@Service
public class PaiementService {

    @Autowired
    private PaiementRepository paiementRepository;

    public Paiement save(Paiement paiement) {
        return paiementRepository.save(paiement);
    }

    public List<Paiement> findAll() {
        return paiementRepository.findAll();
    }

    public Optional<Paiement> findById(Long id) {
        return paiementRepository.findById(id);
    }

//    public List<Paiement> findByInscriptionId(Long inscriptionId) {
//        return paiementRepository.findByInscriptionId(inscriptionId);
//    }

   /* public List<Paiement> findByModePaiement(String modePaiement) {
        return paiementRepository.findByModePaiement(modePaiement);
    }*/

    /*public List<Paiement> findByType(String type) {
        return paiementRepository.findByType(type);
    }

    public List<Paiement> findByFormationId(Long formationId) {
        return paiementRepository.findByFormationId(formationId);
    }

    public List<Paiement> findByMontantBetween(Double montantMin, Double montantMax) {
        return paiementRepository.findByMontantBetween(montantMin, montantMax);
    }*/

    public List<Paiement> findByEtudiantId(Long etudiantId) {
        return paiementRepository.findByEtudiantId(etudiantId);
    }

    public List<Paiement> findByStatut(String statut) {
        return paiementRepository.findByStatut(statut);
    }

  /*  public List<Paiement> findByDatePaiementBetween(Date dateDebut, Date dateFin) {
        return paiementRepository.findByDatePaiementBetween(dateDebut, dateFin);
    }

    public List<Paiement> findByMontantGreaterThan(float montant) {
        return paiementRepository.findByMontantGreaterThan(montant);
    }*/

    public void deleteById(Long id) {
        paiementRepository.deleteById(id);
    }

    public Paiement update(Long id, Paiement paiement) {
        if (paiementRepository.existsById(id)) {
            paiement.setId(id);
            return paiementRepository.save(paiement);
        }
        throw new RuntimeException("Paiement non trouvé avec l'id: " + id);
    }
}
