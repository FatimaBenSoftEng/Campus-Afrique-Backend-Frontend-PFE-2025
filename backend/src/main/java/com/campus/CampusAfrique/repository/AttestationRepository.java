package com.campus.CampusAfrique.repository;

import com.campus.CampusAfrique.model.Attestation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Date;

@Repository
public interface AttestationRepository extends JpaRepository<Attestation, Long> {
    List<Attestation> findByType(String type);
    List<Attestation> findByEmisPar(String emisPar);
    //List<Attestation> findByDateEmissionBetween(Date dateDebut, Date dateFin);
    //List<Attestation> findByTypeAndDateEmissionBetween(String type, Date dateDebut, Date dateFin);
    //List<Attestation> findByEtudiantId(Long etudiantId);
    //List<Attestation> findByStatut(String statut);
}
