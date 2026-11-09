package com.campus.CampusAfrique.service;


import com.campus.CampusAfrique.model.ResponsableEtablissement;
import com.campus.CampusAfrique.repository.ResponsableEtablissementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ResponsableEtablissementService {

    @Autowired
    private ResponsableEtablissementRepository responsableEtablissementRepository;

    public ResponsableEtablissement save(ResponsableEtablissement responsable) {
        return responsableEtablissementRepository.save(responsable);
    }

    public List<ResponsableEtablissement> findAll() {
        return responsableEtablissementRepository.findAll();
    }

    public Optional<ResponsableEtablissement> findById(Long id) {
        return responsableEtablissementRepository.findById(id);
    }

    public Optional<ResponsableEtablissement> findByEmail(String email) {
        return responsableEtablissementRepository.findByEmail(email);
    }

    public List<ResponsableEtablissement> findByEtablissementId(Long etablissementId) {
        return responsableEtablissementRepository.findByEtablissementId(etablissementId);
    }

   /* public List<ResponsableEtablissement> findByStatut(String statut) {
        return responsableEtablissementRepository.findByStatut(statut);
    }*/

    public void deleteById(Long id) {
        responsableEtablissementRepository.deleteById(id);
    }

    public boolean existsByEmail(String email) {
        return responsableEtablissementRepository.existsByEmail(email);
    }

    public ResponsableEtablissement update(Long id, ResponsableEtablissement responsable) {
        if (responsableEtablissementRepository.existsById(id)) {
            responsable.setId(id);
            return responsableEtablissementRepository.save(responsable);
        }
        throw new RuntimeException("Responsable d'établissement non trouvé avec l'id: " + id);
    }

    /*public List<ResponsableEtablissement> findByStatut(String statut) {
        return List.of();
    }*/
}
