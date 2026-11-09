package com.campus.CampusAfrique.repository;

import com.campus.CampusAfrique.model.Inscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Date;

@Repository
public interface InscriptionRepository extends JpaRepository<Inscription, Long> {
    List<Inscription> findByEtudiantId(Long etudiantId);
    List<Inscription> findByEtablissementId(Long etablissementId);
    List<Inscription> findByStatut(String statut);
    List<Inscription> findByFormationId(Long formationId);

}

