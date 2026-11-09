package com.campus.CampusAfrique.repository;

import com.campus.CampusAfrique.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
    List<Document> findByDossierId(Long dossierId);
    List<Document> findByType(String type);
    List<Document> findByStatut(String statut);
}
