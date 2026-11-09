package com.campus.CampusAfrique.controller;

import com.campus.CampusAfrique.model.AgentAmbassade;
import com.campus.CampusAfrique.service.AgentAmbassadeService;
import com.campus.CampusAfrique.service.EtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agents-ambassade")
public class AgentAmbassadeController {

    @Autowired
    private AgentAmbassadeService agentAmbassadeService;

    public AgentAmbassadeController(AgentAmbassadeService agentAmbassadeService) {
        this.agentAmbassadeService = agentAmbassadeService;
    }

    @PostMapping("/add")
    public ResponseEntity<AgentAmbassade> createAgent(@RequestBody AgentAmbassade agent) {
        return ResponseEntity.ok(agentAmbassadeService.save(agent));
    }

    @GetMapping("/all")
    public ResponseEntity<List<AgentAmbassade>> getAllAgents() {
        return ResponseEntity.ok(agentAmbassadeService.findAll());
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<AgentAmbassade> getAgentById(@PathVariable Long id) {
        return agentAmbassadeService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/find/email/{email}")
    public ResponseEntity<AgentAmbassade> getAgentByEmail(@PathVariable String email) {
        return agentAmbassadeService.findByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/find/pays/{pays}")
    public ResponseEntity<List<AgentAmbassade>> getAgentsByPays(@PathVariable String pays) {
        return ResponseEntity.ok(agentAmbassadeService.findByPaysResponsable(pays));
    }

    /*@GetMapping("/poste/{poste}")
    public ResponseEntity<List<AgentAmbassade>> getAgentsByPoste(@PathVariable String poste) {
        return ResponseEntity.ok(agentAmbassadeService.findByPoste(poste));
    }

    @GetMapping("/statut/{statut}")
    public ResponseEntity<List<AgentAmbassade>> getAgentsByStatut(@PathVariable String statut) {
        return ResponseEntity.ok(agentAmbassadeService.findByStatut(statut));
    }*/

    @PutMapping("/update/{id}")
    public ResponseEntity<AgentAmbassade> updateAgent(@PathVariable Long id, @RequestBody AgentAmbassade agent) {
        try {
            return ResponseEntity.ok(agentAmbassadeService.update(id, agent));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAgent(@PathVariable Long id) {
        agentAmbassadeService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
