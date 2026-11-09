package com.campus.CampusAfrique.service;

import com.campus.CampusAfrique.model.Formation;
import com.campus.CampusAfrique.repository.FormationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class FormationService {

    @Autowired
    private FormationRepository formationRepository;

    public Formation save(Formation formation) {
        return formationRepository.save(formation);
    }

    public List<Formation> findAll() {
        return formationRepository.findAll();
    }

    public Optional<Formation> findById(Long id) {
        return formationRepository.findById(id);
    }

    public List<Formation> findByEtablissementId(Long etablissementId) {
        return formationRepository.findByEtablissementId(etablissementId);
    }


    public List<Formation> findByNomContaining(String nom) {
        return formationRepository.findByNomContaining(nom);
    }

    public List<Formation> findByDuree(int duree) {
        return formationRepository.findByDuree(duree);
    }

    public void deleteById(Long id) {
        formationRepository.deleteById(id);
    }

    public Formation update(Long id, Formation formation) {
        if (formationRepository.existsById(id)) {
            formation.setId(id);
            return formationRepository.save(formation);
        }
        throw new RuntimeException("Formation non trouvée avec l'id: " + id);
    }
}