package com.campus.CampusAfrique.repository;

import com.campus.CampusAfrique.model.Dossier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DossierRepository extends JpaRepository<Dossier, Long> {
    List<Dossier> findByEtudiantId(Long etudiantId);
    List<Dossier> findByStatut(String statut);
    List<Dossier> findByTypeDossier(String typeDossier);
    //List<Dossier> findByType(String type);
}
