package com.campus.CampusAfrique.repository;

import com.campus.CampusAfrique.model.Candidature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Date;

@Repository
public interface CandidatureRepository extends JpaRepository<Candidature, Long> {
    List<Candidature> findByEtudiantId(Long etudiantId);
    List<Candidature> findByStatut(String statut);
    List<Candidature> findByEtablissementId(Long etablissementId);
    //List<Candidature> findByDateSoumissionBetween(Date dateDebut, Date dateFin);
    //List<Candidature> findByFormationId(Long formationId);
    //List<Candidature> findByDateCandidatureBetween(Date dateDebut, Date dateFin);
}