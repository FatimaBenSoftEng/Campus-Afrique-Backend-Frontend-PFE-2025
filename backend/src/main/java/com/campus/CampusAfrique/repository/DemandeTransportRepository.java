package com.campus.CampusAfrique.repository;

import com.campus.CampusAfrique.model.DemandeTransport;
import com.campus.CampusAfrique.model.Etudiant;
import com.campus.CampusAfrique.model.StatutDemande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DemandeTransportRepository extends JpaRepository<DemandeTransport, Long> {
    List<DemandeTransport> findByEtudiant(Etudiant etudiant);
    List<DemandeTransport> findByEtudiantId(Long id);
    int countByEtudiantId(Long etudiantId);
    int countByEtudiantIdAndStatut(Long etudiantId, StatutDemande statut);
}

