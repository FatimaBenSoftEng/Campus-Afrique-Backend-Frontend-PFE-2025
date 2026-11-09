package com.campus.CampusAfrique.repository;


import com.campus.CampusAfrique.model.Etudiant;
import com.campus.CampusAfrique.model.Preinscription;
import com.campus.CampusAfrique.model.StatutPreinscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PreinscriptionRepository extends JpaRepository<Preinscription, Long> {
    List<Preinscription> findByEtudiantId(Long etudiantId);
    List<Preinscription> findByFormationId(Long formationId);
    List<Preinscription> findByEtablissementId(Long etablissementId);
    List<Preinscription> findByStatut(String statut);
    List<Preinscription> findByOrdrePreference(int ordrePreference);
    int countByEtudiantId(Long id);
    int countByEtudiantIdAndStatut(Long id, StatutPreinscription statut);
}
