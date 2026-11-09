package com.campus.CampusAfrique.repository;

import com.campus.CampusAfrique.model.DemandeVisa;
import com.campus.CampusAfrique.model.Etudiant;
import com.campus.CampusAfrique.model.StatutDemande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DemandeVisaRepository extends JpaRepository<DemandeVisa, Long> {
    List<DemandeVisa> findByEtudiant(Etudiant etudiant);
    int countByEtudiantId(Long etudiantId);
    int countByEtudiantIdAndStatut(Long etudiantId, StatutDemande statut);
}

