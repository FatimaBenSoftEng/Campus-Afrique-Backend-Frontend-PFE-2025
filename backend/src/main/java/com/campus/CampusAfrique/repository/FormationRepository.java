package com.campus.CampusAfrique.repository;

import com.campus.CampusAfrique.model.Formation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FormationRepository extends JpaRepository<Formation, Long> {
    List<Formation> findByEtablissementId(Long etablissementId);
    List<Formation> findByNomContaining(String nom);
    List<Formation> findByDuree(int duree);
    //List<Formation> findByFraisAnnuelLessThanEqual(float fraisMax);
   // List<Formation> findByTypeFormation(String typeFormation);
    //List<Formation> findByNiveau(String niveau);
    //List<Formation> findByDomaine(String domaine);
    //List<Formation> findByPrixLessThan(float prix);
}
