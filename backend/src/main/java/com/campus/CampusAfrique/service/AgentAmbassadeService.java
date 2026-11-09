package com.campus.CampusAfrique.service;

import com.campus.CampusAfrique.model.AgentAmbassade;
import com.campus.CampusAfrique.repository.AgentAmbassadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AgentAmbassadeService {

    @Autowired
    private AgentAmbassadeRepository agentAmbassadeRepository;

    public AgentAmbassade save(AgentAmbassade agent) {
        return agentAmbassadeRepository.save(agent);
    }

    public List<AgentAmbassade> findAll() {
        return agentAmbassadeRepository.findAll();
    }

    public Optional<AgentAmbassade> findById(Long id) {
        return agentAmbassadeRepository.findById(id);
    }

    public Optional<AgentAmbassade> findByEmail(String email) {
        return agentAmbassadeRepository.findByEmail(email);
    }

    public List<AgentAmbassade> findByPaysResponsable(String pays) {
        return agentAmbassadeRepository.findByPaysResponsable(pays);
    }

   /* public List<AgentAmbassade> findByPoste(String poste) {
        return agentAmbassadeRepository.findByPoste(poste);
    }

    public List<AgentAmbassade> findByStatut(String statut) {
        return agentAmbassadeRepository.findByStatut(statut);
    }*/

    public void deleteById(Long id) {
        agentAmbassadeRepository.deleteById(id);
    }

    public boolean existsByEmail(String email) {
        return agentAmbassadeRepository.existsByEmail(email);
    }

    public AgentAmbassade update(Long id, AgentAmbassade agent) {
        if (agentAmbassadeRepository.existsById(id)) {
            agent.setId(id);
            return agentAmbassadeRepository.save(agent);
        }
        throw new RuntimeException("Agent d'ambassade non trouvé avec l'id: " + id);
    }
}
