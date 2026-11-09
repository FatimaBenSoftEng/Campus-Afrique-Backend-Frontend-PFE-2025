package com.campus.CampusAfrique.repository;

import com.campus.CampusAfrique.model.Paiement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Date;

@Repository
public interface PaiementRepository extends JpaRepository<Paiement, Long> {
    List<Paiement> findByEtudiantId(Long etudiantId);
    //List<Paiement> findByInscriptionId(Long inscriptionId);
    List<Paiement> findByStatut(String statut);
    //List<Paiement> findByDatePaiementBetween(Date dateDebut, Date dateFin);
    //List<Paiement> findByMontantGreaterThan(float montant);
    //List<Paiement> findByModePaiement(String modePaiement);
    //List<Paiement> findByFormationId(Long formationId);
    //List<Paiement> findByType(String type);
    //List<Paiement> findByMontantBetween(Double montantMin, Double montantMax);
}
