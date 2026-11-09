package com.campus.CampusAfrique.repository;

import com.campus.CampusAfrique.model.DemandeHebergement;
import com.campus.CampusAfrique.model.Etudiant;
import com.campus.CampusAfrique.model.StatutDemande;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DemandeHebergementRepository extends JpaRepository<DemandeHebergement, Long> {
    List<DemandeHebergement> findByEtudiantId(Long etudiantId);
    int countByEtudiantId(Long etudiantId);
    int countByEtudiantIdAndStatut(Long etudiantId, StatutDemande statut);

}
