package com.campus.CampusAfrique.controller;

import com.campus.CampusAfrique.model.AgentMinistere;
import com.campus.CampusAfrique.service.AgentMinistereService;
import com.campus.CampusAfrique.service.EtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agents-ministere")
public class AgentMinistereController {

    @Autowired
    private AgentMinistereService agentMinistereService;

    public AgentMinistereController(AgentMinistereService agentMinistereService) {
        this.agentMinistereService = agentMinistereService;
    }

    @PostMapping("/add")
    public ResponseEntity<AgentMinistere> createAgent(@RequestBody AgentMinistere agent) {
        return ResponseEntity.ok(agentMinistereService.save(agent));
    }

    @GetMapping("/all")
    public ResponseEntity<List<AgentMinistere>> getAllAgents() {
        return ResponseEntity.ok(agentMinistereService.findAll());
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<AgentMinistere> getAgentById(@PathVariable Long id) {
        return agentMinistereService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/find/email/{email}")
    public ResponseEntity<AgentMinistere> getAgentByEmail(@PathVariable String email) {
        return agentMinistereService.findByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/find/ministere/{ministere}")
    public ResponseEntity<List<AgentMinistere>> getAgentsByMinistere(@PathVariable String ministere) {
        return ResponseEntity.ok(agentMinistereService.findByNom(ministere));
    }

   /* @GetMapping("/poste/{poste}")
    public ResponseEntity<List<AgentMinistere>> getAgentsByPoste(@PathVariable String poste) {
        return ResponseEntity.ok(agentMinistereService.findByPoste(poste));
    }

    @GetMapping("/statut/{statut}")
    public ResponseEntity<List<AgentMinistere>> getAgentsByStatut(@PathVariable String statut) {
        return ResponseEntity.ok(agentMinistereService.findByStatut(statut));
    }*/

    @PutMapping("/update/{id}")
    public ResponseEntity<AgentMinistere> updateAgent(@PathVariable Long id, @RequestBody AgentMinistere agent) {
        try {
            return ResponseEntity.ok(agentMinistereService.update(id, agent));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAgent(@PathVariable Long id) {
        agentMinistereService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}