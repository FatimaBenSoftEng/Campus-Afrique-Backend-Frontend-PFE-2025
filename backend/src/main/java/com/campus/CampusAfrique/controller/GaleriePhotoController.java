package com.campus.CampusAfrique.controller;

import com.campus.CampusAfrique.model.GaleriePhoto;
import com.campus.CampusAfrique.service.GaleriePhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/galerie-photos")
public class GaleriePhotoController {

    @Autowired
    private GaleriePhotoService galeriePhotoService;

    @PostMapping("/add")
    public ResponseEntity<GaleriePhoto> createPhoto(@RequestBody GaleriePhoto photo) {
        return ResponseEntity.ok(galeriePhotoService.save(photo));
    }

    @GetMapping("/all")
    public ResponseEntity<List<GaleriePhoto>> getAllPhotos() {
        return ResponseEntity.ok(galeriePhotoService.findAll());
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<GaleriePhoto> getPhotoById(@PathVariable Long id) {
        return galeriePhotoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/find/etablissement/{etablissementId}")
    public ResponseEntity<List<GaleriePhoto>> getPhotosByEtablissement(@PathVariable Long etablissementId) {
        return ResponseEntity.ok(galeriePhotoService.findByEtablissementId(etablissementId));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<GaleriePhoto> updatePhoto(@PathVariable Long id, @RequestBody GaleriePhoto photo) {
        try {
            return ResponseEntity.ok(galeriePhotoService.update(id, photo));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePhoto(@PathVariable Long id) {
        galeriePhotoService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
