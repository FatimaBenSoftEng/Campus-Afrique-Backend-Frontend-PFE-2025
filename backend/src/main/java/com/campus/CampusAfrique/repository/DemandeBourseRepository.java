package com.campus.CampusAfrique.repository;

import com.campus.CampusAfrique.model.DemandeBourse;
import com.campus.CampusAfrique.model.Etudiant;
import com.campus.CampusAfrique.model.StatutDemande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Date;

@Repository
public interface DemandeBourseRepository extends JpaRepository<DemandeBourse, Long> {
    List<DemandeBourse> findByEtudiantId(Long etudiantId);
    List<DemandeBourse> findByOrganismeBourseId(Long organismeBourseId);
    List<DemandeBourse> findByStatut(String statut);
    int countByEtudiantId(Long etudiantId);
    int countByEtudiantIdAndStatut(Long etudiantId, StatutDemande statut);
}