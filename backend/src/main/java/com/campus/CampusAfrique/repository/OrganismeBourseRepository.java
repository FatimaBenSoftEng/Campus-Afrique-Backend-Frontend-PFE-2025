package com.campus.CampusAfrique.repository;

import com.campus.CampusAfrique.model.OrganismeBourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrganismeBourseRepository extends JpaRepository<OrganismeBourse, Long> {
    Optional<OrganismeBourse> findByNom(String nom);
    Optional<OrganismeBourse> findByEmail(String email);
    boolean existsByEmail(String email);
    List<OrganismeBourse> findByPays(String pays);
    List<OrganismeBourse> findByType(String type);
    //List<OrganismeBourse> findByStatut(String statut);
}
