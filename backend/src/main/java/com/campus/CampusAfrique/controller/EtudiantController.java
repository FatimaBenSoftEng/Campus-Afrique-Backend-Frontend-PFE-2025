package com.campus.CampusAfrique.controller;

import com.campus.CampusAfrique.dto.EtudiantDTO;
import com.campus.CampusAfrique.model.Etudiant;
import com.campus.CampusAfrique.repository.EtudiantRepository;
import com.campus.CampusAfrique.security.JwtService;
import com.campus.CampusAfrique.service.EtudiantService;
import com.campus.CampusAfrique.service.NiveauScolaireService;
import com.campus.CampusAfrique.security.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.io.IOException;



@RestController
@RequestMapping("/api/etudiants")
public class EtudiantController {

    private final EtudiantService etudiantService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private NiveauScolaireService niveauScolaireService;

    @Autowired
    private UserService userService;

    @Autowired
    private EtudiantRepository etudiantRepository;

    public EtudiantController(EtudiantService etudiantService) {
        this.etudiantService = etudiantService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<EtudiantDTO>> getAllEtudiants() {
        return ResponseEntity.ok(etudiantService.findAllEtudiants()
                .stream().map(this::toDto).collect(Collectors.toList()));
    }

    @PostMapping("/add")
    public ResponseEntity<EtudiantDTO> createEtudiant(@RequestBody EtudiantDTO dto) {
        Etudiant e = new Etudiant();
        e.setNom(dto.getNom());
        e.setPrenom(dto.getPrenom());
        e.setEmail(dto.getEmail());
        e.setMotDePasse(dto.getMotDePasse());
        e.setPaysOrigine(dto.getPaysOrigine());
        e.setDateNaissance(dto.getDateNaissance());
        e.setAnneeObtention(dto.getAnneeObtention());
        e.setNationalite(dto.getNationalite());
        e.setPhotoProfil(dto.getPhotoProfil());
        e.setStatut(dto.getStatut());
        e.setTelephone(dto.getTelephone());
        e.setAdresse(dto.getAdresse());
        e.setVille(dto.getVille());
        e.setGenre(dto.getGenre());
        e.setDernierDiplome(dto.getDernierDiplome());
        e.setSpecialite(dto.getSpecialite());
        e.setMoyenne(dto.getMoyenne());
        e.setTypeIdentite(dto.getTypeIdentite());
        e.setNumeroIdentite(dto.getNumeroIdentite());
        e.setMatricule(dto.getMatricule());


        niveauScolaireService.findById(dto.getNiveauScolaire().getId()).ifPresent(e::setNiveauScolaire);
        userService.findById(dto.getUser().getId()).ifPresent(e::setUser);

        Etudiant saved = etudiantService.saveEtudiant(e);
        return new ResponseEntity<>(toDto(saved), HttpStatus.CREATED);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<EtudiantDTO> getEtudiantById(@PathVariable Long id) {
        Etudiant e = etudiantService.findEtudiantById(id);
        return new ResponseEntity<>(toDto(e), HttpStatus.OK);
    }

    @GetMapping("/find/pays-origine/{paysOrigine}")
    public ResponseEntity<List<EtudiantDTO>> getEtudiantsByPaysOrigine(@PathVariable String paysOrigine) {
        return ResponseEntity.ok(etudiantService.findByPaysOrigine(paysOrigine)
                .stream().map(this::toDto).collect(Collectors.toList()));
    }

    @GetMapping("/find/niveau-scolaire/{niveauScolaireId}")
    public ResponseEntity<List<EtudiantDTO>> getEtudiantsByNiveauScolaire(@PathVariable Long niveauScolaireId) {
        return ResponseEntity.ok(etudiantService.findByNiveauScolaireId(niveauScolaireId)
                .stream().map(this::toDto).collect(Collectors.toList()));
    }

    @GetMapping("/find/ville/{ville}")
    public ResponseEntity<List<EtudiantDTO>> getEtudiantsByVille(@PathVariable String ville) {
        return ResponseEntity.ok(etudiantService.findByVille(ville)
                .stream().map(this::toDto).collect(Collectors.toList()));
    }

    @GetMapping("/me")
    public ResponseEntity<EtudiantDTO> getCurrentEtudiant(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        String email = jwtService.extractUsername(token);
        Etudiant etudiant = etudiantService.findByEmail(email);
        return ResponseEntity.ok(toDto(etudiant));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<EtudiantDTO> updateEtudiant(
            @PathVariable Long id,
            @RequestParam("nom") String nom,
            @RequestParam("prenom") String prenom,
            @RequestParam("email") String email,
            @RequestParam("telephone") String telephone,
            @RequestParam("dateNaissance") String dateNaissance,
            @RequestParam("nationalite") String nationalite,
            @RequestParam("adresse") String adresse,
            @RequestParam("paysOrigine") String paysOrigine,
            @RequestParam("genre") String genre,
            @RequestParam("dernierDiplome") String dernierDiplome,
            @RequestParam("specialite") String specialite,
            @RequestParam("anneeObtention") String anneeObtention,
            @RequestParam("moyenne") String moyenne,
            @RequestParam(value = "photo", required = false) MultipartFile photo,
            @RequestParam("typeIdentite") String typeIdentite,
            @RequestParam("numeroIdentite") String numeroIdentite,
            @RequestParam("matricule") String matricule

            ) {
        Etudiant etudiant = etudiantService.findEtudiantById(id);
        etudiant.setNom(nom);
        etudiant.setPrenom(prenom);
        etudiant.setEmail(email);
        etudiant.setTelephone(telephone);
        etudiant.setDateNaissance(LocalDate.parse(dateNaissance));
        etudiant.setNationalite(nationalite);
        etudiant.setAdresse(adresse);
        etudiant.setPaysOrigine(paysOrigine);
        etudiant.setGenre(genre);
        etudiant.setDernierDiplome(dernierDiplome);
        etudiant.setSpecialite(specialite);
        etudiant.setAnneeObtention(LocalDate.parse(anneeObtention));
        etudiant.setMoyenne(Float.parseFloat(moyenne));
        etudiant.setTypeIdentite(typeIdentite);
        etudiant.setNumeroIdentite(numeroIdentite);
        etudiant.setMatricule(matricule);

        if (photo != null && !photo.isEmpty()) {
            String filename = System.currentTimeMillis() + "_" + photo.getOriginalFilename(); // évite les doublons
            String uploadDir = new File("src/main/resources/static/uploads").getAbsolutePath();
            File dest = new File(uploadDir + File.separator + filename);
            dest.getParentFile().mkdirs(); // crée le dossier si besoin

            try {
                photo.transferTo(dest);
                etudiant.setPhotoProfil(filename); // enregistrer le nom en base
            } catch (IOException e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }


        Etudiant saved = etudiantService.saveEtudiant(etudiant);
        return ResponseEntity.ok(toDto(saved));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteEtudiant(@PathVariable Long id) {
        etudiantService.deleteEtudiant(id);
        return ResponseEntity.ok().build();
    }

    public Etudiant getEtudiantByEmail(String email) {
        return etudiantRepository.findByUserEmail(email)
                .orElseThrow(() -> new RuntimeException("Étudiant non trouvé pour l'email : " + email));
    }

    private EtudiantDTO toDto(Etudiant e) {
        EtudiantDTO dto = new EtudiantDTO();
        dto.setId(e.getId());
        dto.setNom(e.getNom());
        dto.setPrenom(e.getPrenom());
        dto.setEmail(e.getEmail());
        dto.setMotDePasse(e.getMotDePasse());
        dto.setPaysOrigine(e.getPaysOrigine());
        dto.setDateNaissance(e.getDateNaissance());
        dto.setAnneeObtention(e.getAnneeObtention());
        dto.setNationalite(e.getNationalite());
        dto.setPhotoProfil(e.getPhotoProfil());
        dto.setStatut(e.getStatut());
        dto.setTelephone(e.getTelephone());
        dto.setAdresse(e.getAdresse());
        dto.setVille(e.getVille());
        dto.setGenre(e.getGenre());
        dto.setDernierDiplome(e.getDernierDiplome());
        dto.setSpecialite(e.getSpecialite());
        dto.setMoyenne(e.getMoyenne());
        dto.setNiveauScolaire(e.getNiveauScolaire());
        dto.setUser(e.getUser());
        dto.setTypeIdentite(e.getTypeIdentite());
        dto.setNumeroIdentite(e.getNumeroIdentite());
        dto.setMatricule(e.getMatricule());

        return dto;
    }
}
