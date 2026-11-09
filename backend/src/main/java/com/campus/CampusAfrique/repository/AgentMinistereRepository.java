package com.campus.CampusAfrique.repository;


import com.campus.CampusAfrique.model.AgentMinistere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AgentMinistereRepository extends JpaRepository<AgentMinistere, Long> {
    Optional<AgentMinistere> findByEmail(String email);
    boolean existsByEmail(String email);
    List<AgentMinistere> findByNom(String nom);
    //List<AgentMinistere> findByPoste(String poste);
    //List<AgentMinistere> findByStatut(String statut);
}
