package com.campus.CampusAfrique.service;


import com.campus.CampusAfrique.model.NiveauScolaire;
import com.campus.CampusAfrique.repository.NiveauScolaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class NiveauScolaireService {

    @Autowired
    private NiveauScolaireRepository niveauScolaireRepository;

    public NiveauScolaire save(NiveauScolaire niveau) {
        return niveauScolaireRepository.save(niveau);
    }

    public List<NiveauScolaire> findAll() {
        return niveauScolaireRepository.findAll();
    }

    public Optional<NiveauScolaire> findById(Long id) {
        return niveauScolaireRepository.findById(id);
    }

    public Optional<NiveauScolaire> findByNom(String nom) {
        return niveauScolaireRepository.findByNom(nom);
    }

 /*   public List<NiveauScolaire> findByCycle(String cycle) {
        return niveauScolaireRepository.findByCycle(cycle);
    }

    public List<NiveauScolaire> findByStatut(String statut) {
        return niveauScolaireRepository.findByStatut(statut);
    }*/

    public void deleteById(Long id) {
        niveauScolaireRepository.deleteById(id);
    }

    public NiveauScolaire update(Long id, NiveauScolaire niveau) {
        if (niveauScolaireRepository.existsById(id)) {
            niveau.setId(id);
            return niveauScolaireRepository.save(niveau);
        }
        throw new RuntimeException("Niveau scolaire non trouvé avec l'id: " + id);
    }
}
