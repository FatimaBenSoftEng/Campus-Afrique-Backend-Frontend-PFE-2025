package com.campus.CampusAfrique.service;

import com.campus.CampusAfrique.model.Etablissement;
import com.campus.CampusAfrique.repository.EtablissementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EtablissementService {

    @Autowired
    private EtablissementRepository etablissementRepository;

    public Etablissement save(Etablissement etablissement) {
        return etablissementRepository.save(etablissement);
    }

    public List<Etablissement> findAll() {
        return etablissementRepository.findAll();
    }

    public Optional<Etablissement> findById(Long id) {
        return etablissementRepository.findById(id);
    }

    public List<Etablissement> findByPays(String pays) {
        return etablissementRepository.findByPays(pays);
    }

    public List<Etablissement> findByVille(String ville) {
        return etablissementRepository.findByVille(ville);
    }

    public List<Etablissement> findByType(String type) {
        return etablissementRepository.findByType(type);
    }

    public List<Etablissement> searchByKeyword(String keyword) {
        return etablissementRepository.searchByKeyword(keyword);
    }


/*
    public List<Etablissement> findByStatut(String statut) {
        return etablissementRepository.findByStatut(statut);
    }
*/

    public void deleteById(Long id) {
        etablissementRepository.deleteById(id);
    }

    public Etablissement update(Long id, Etablissement etablissement) {
        if (etablissementRepository.existsById(id)) {
            etablissement.setId(id);
            return etablissementRepository.save(etablissement);
        }
        throw new RuntimeException("Etablissement non trouvé avec l'id: " + id);
    }
}
