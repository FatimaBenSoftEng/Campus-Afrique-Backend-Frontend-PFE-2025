package com.campus.CampusAfrique.service;

import com.campus.CampusAfrique.model.AgentMinistere;
import com.campus.CampusAfrique.repository.AgentMinistereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AgentMinistereService {

    @Autowired
    private AgentMinistereRepository agentMinistereRepository;

    public AgentMinistere save(AgentMinistere agent) {
        return agentMinistereRepository.save(agent);
    }

    public List<AgentMinistere> findAll() {
        return agentMinistereRepository.findAll();
    }

    public Optional<AgentMinistere> findById(Long id) {
        return agentMinistereRepository.findById(id);
    }

    public Optional<AgentMinistere> findByEmail(String email) {
        return agentMinistereRepository.findByEmail(email);
    }

    public List<AgentMinistere> findByNom(String ministere) {
        return agentMinistereRepository.findByNom(ministere);
    }

    /*public List<AgentMinistere> findByPoste(String poste) {
        return agentMinistereRepository.findByPoste(poste);
    }

    public List<AgentMinistere> findByStatut(String statut) {
        return agentMinistereRepository.findByStatut(statut);
    }*/

    public void deleteById(Long id) {
        agentMinistereRepository.deleteById(id);
    }

    public boolean existsByEmail(String email) {
        return agentMinistereRepository.existsByEmail(email);
    }

    public AgentMinistere update(Long id, AgentMinistere agent) {
        if (agentMinistereRepository.existsById(id)) {
            agent.setId(id);
            return agentMinistereRepository.save(agent);
        }
        throw new RuntimeException("Agent du ministère non trouvé avec l'id: " + id);
    }
}
