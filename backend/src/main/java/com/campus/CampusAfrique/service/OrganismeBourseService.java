package com.campus.CampusAfrique.service;


import com.campus.CampusAfrique.model.OrganismeBourse;
import com.campus.CampusAfrique.repository.OrganismeBourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class OrganismeBourseService {

    @Autowired
    private OrganismeBourseRepository organismeBourseRepository;

    public OrganismeBourse save(OrganismeBourse organisme) {
        return organismeBourseRepository.save(organisme);
    }

    public List<OrganismeBourse> findAll() {
        return organismeBourseRepository.findAll();
    }

    public Optional<OrganismeBourse> findById(Long id) {
        return organismeBourseRepository.findById(id);
    }

    public Optional<OrganismeBourse> findByEmail(String email) {
        return organismeBourseRepository.findByEmail(email);
    }

    public List<OrganismeBourse> findByPays(String pays) {
        return organismeBourseRepository.findByPays(pays);
    }

    public List<OrganismeBourse> findByType(String type) {
        return organismeBourseRepository.findByType(type);
    }

    public Optional<OrganismeBourse> findByNom(String nom) {
        return organismeBourseRepository.findByNom(nom);
    }

/*
    public List<OrganismeBourse> findByStatut(String statut) {
        return organismeBourseRepository.findByStatut(statut);
    }
*/

    public void deleteById(Long id) {
        organismeBourseRepository.deleteById(id);
    }

    public boolean existsByEmail(String email) {
        return organismeBourseRepository.existsByEmail(email);
    }

    public OrganismeBourse update(Long id, OrganismeBourse organisme) {
        if (organismeBourseRepository.existsById(id)) {
            organisme.setId(id);
            return organismeBourseRepository.save(organisme);
        }
        throw new RuntimeException("Organisme de bourse non trouvé avec l'id: " + id);
    }
}
