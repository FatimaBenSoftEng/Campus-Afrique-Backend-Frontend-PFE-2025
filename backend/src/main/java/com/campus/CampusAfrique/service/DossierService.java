package com.campus.CampusAfrique.service;

import com.campus.CampusAfrique.model.Dossier;
import com.campus.CampusAfrique.repository.DossierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DossierService {

    @Autowired
    private DossierRepository dossierRepository;

    public Dossier save(Dossier dossier) {
        return dossierRepository.save(dossier);
    }

    public List<Dossier> findAll() {
        return dossierRepository.findAll();
    }

    public Optional<Dossier> findById(Long id) {
        return dossierRepository.findById(id);
    }

    public List<Dossier> findByEtudiantId(Long etudiantId) {
        return dossierRepository.findByEtudiantId(etudiantId);
    }

    public List<Dossier> findByType(String type) {
        return dossierRepository.findByTypeDossier(type);
    }

    public List<Dossier> findByStatut(String statut) {
        return dossierRepository.findByStatut(statut);
    }

    public void deleteById(Long id) {
        dossierRepository.deleteById(id);
    }

    public Dossier update(Long id, Dossier dossier) {
        if (dossierRepository.existsById(id)) {
            dossier.setId(id);
            return dossierRepository.save(dossier);
        }
        throw new RuntimeException("Dossier non trouvé avec l'id: " + id);
    }
}