package com.campus.CampusAfrique.repository;

import com.campus.CampusAfrique.model.AgentAmbassade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface AgentAmbassadeRepository extends JpaRepository<AgentAmbassade, Long> {
    Optional<AgentAmbassade> findByEmail(String email);
    List<AgentAmbassade> findByPaysResponsable(String paysResponsable);
    boolean existsByEmail(String email);
    //List<AgentAmbassade> findByPays(String pays);
    //List<AgentAmbassade> findByPoste(String poste);
    //List<AgentAmbassade> findByStatut(String statut);
}