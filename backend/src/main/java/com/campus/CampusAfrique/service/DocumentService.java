package com.campus.CampusAfrique.service;


import com.campus.CampusAfrique.model.Document;
import com.campus.CampusAfrique.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    public Document save(Document document) {
        return documentRepository.save(document);
    }

    public List<Document> findAll() {
        return documentRepository.findAll();
    }

    public Optional<Document> findById(Long id) {
        return documentRepository.findById(id);
    }

    public List<Document> findByDossierId(Long dossierId) {
        return documentRepository.findByDossierId(dossierId);
    }

    public List<Document> findByType(String type) {
        return documentRepository.findByType(type);
    }

    public List<Document> findByStatut(String statut) {
        return documentRepository.findByStatut(statut);
    }

    public void deleteById(Long id) {
        documentRepository.deleteById(id);
    }

    public Document update(Long id, Document document) {
        if (documentRepository.existsById(id)) {
            document.setId(id);
            return documentRepository.save(document);
        }
        throw new RuntimeException("Document non trouvé avec l'id: " + id);
    }
}
