package com.campus.CampusAfrique.repository;

import com.campus.CampusAfrique.model.NiveauScolaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NiveauScolaireRepository extends JpaRepository<NiveauScolaire, Long> {
    Optional<NiveauScolaire> findByNom(String nom);
    //List<NiveauScolaire> findByNiveau(String niveau);
   // List<NiveauScolaire> findByCycle(String cycle);
   // List<NiveauScolaire> findByStatut(String statut);
}