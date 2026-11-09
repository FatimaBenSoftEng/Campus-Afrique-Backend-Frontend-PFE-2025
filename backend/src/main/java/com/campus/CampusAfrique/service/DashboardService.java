package com.campus.CampusAfrique.service;

import com.campus.CampusAfrique.dto.DashboardStatsDTO;
import com.campus.CampusAfrique.model.Etudiant;
import com.campus.CampusAfrique.model.StatutPreinscription;
import com.campus.CampusAfrique.model.StatutDemande;
import com.campus.CampusAfrique.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DashboardService {

    @Autowired private PreinscriptionRepository preinscriptionRepo;
    @Autowired private DemandeBourseRepository bourseRepo;
    @Autowired private DemandeHebergementRepository hebergementRepo;
    @Autowired private DemandeVisaRepository visaRepo;
    @Autowired private DemandeTransportRepository transportRepo; // ✅ Nouveau

    public DashboardStatsDTO getDashboardStats(Etudiant etudiant) {
        DashboardStatsDTO dto = new DashboardStatsDTO();
        Long etuId = etudiant.getId();

        // ✅ Récupération des totaux
        int totalPreinscriptions = preinscriptionRepo.countByEtudiantId(etuId);
        int totalBourses = bourseRepo.countByEtudiantId(etuId);
        int totalHebergements = hebergementRepo.countByEtudiantId(etuId);
        int totalVisas = visaRepo.countByEtudiantId(etuId);
        int totalTransports = transportRepo.countByEtudiantId(etuId); // ✅

        int totalDemandes = totalPreinscriptions + totalBourses + totalHebergements + totalVisas + totalTransports;
        dto.setTotalCandidatures(totalDemandes);
        dto.setTotalBourses(totalBourses);

        // ✅ Comptage des en attente
        int enAttente =
                preinscriptionRepo.countByEtudiantIdAndStatut(etuId, StatutPreinscription.EN_ATTENTE) +
                        bourseRepo.countByEtudiantIdAndStatut(etuId, StatutDemande.EN_ATTENTE) +
                        hebergementRepo.countByEtudiantIdAndStatut(etuId, StatutDemande.EN_ATTENTE) +
                        visaRepo.countByEtudiantIdAndStatut(etuId, StatutDemande.EN_ATTENTE) +
                        transportRepo.countByEtudiantIdAndStatut(etuId, StatutDemande.EN_ATTENTE); // ✅
        dto.setEnAttente(enAttente);

        // ✅ Pie chart (basé uniquement sur préinscription)
        Map<String, Integer> statuts = new LinkedHashMap<>();
        statuts.put("Validée", preinscriptionRepo.countByEtudiantIdAndStatut(etuId, StatutPreinscription.VALIDEE));
        statuts.put("En attente", preinscriptionRepo.countByEtudiantIdAndStatut(etuId, StatutPreinscription.EN_ATTENTE));
        statuts.put("Refusée", preinscriptionRepo.countByEtudiantIdAndStatut(etuId, StatutPreinscription.REFUSEE));
        statuts.put("À compléter", preinscriptionRepo.countByEtudiantIdAndStatut(etuId, StatutPreinscription.COMPLEMENT));
        dto.setRepartitionStatuts(statuts);

        // ✅ Bar chart (par type de demande)
        Map<String, Integer> parType = new LinkedHashMap<>();
        parType.put("Préinscription", totalPreinscriptions);
        parType.put("Bourse", totalBourses);
        parType.put("Hébergement", totalHebergements);
        parType.put("Visa", totalVisas);
        parType.put("Transport", totalTransports); // ✅
        dto.setDemandesParType(parType);

        // ✅ Stacked bar chart (statuts par type)
        Map<String, Map<String, Integer>> parTypeEtStatut = new LinkedHashMap<>();
        List<String> types = Arrays.asList("Préinscription", "Bourse", "Hébergement", "Visa", "Transport");

        for (String type : types) {
            Map<String, Integer> s = new LinkedHashMap<>();
            s.put("Validée", getCountByTypeAndStatut(etuId, type, "VALIDEE"));
            s.put("En attente", getCountByTypeAndStatut(etuId, type, "EN_ATTENTE"));
            s.put("Refusée", getCountByTypeAndStatut(etuId, type, "REFUSEE"));
            parTypeEtStatut.put(type, s);
        }
        dto.setStatutsParType(parTypeEtStatut);

        // ✅ Dates importantes
        dto.setDatesImportantes(List.of(
                "30/09/2025 - Fin des candidatures",
                "15/10/2025 - Fin des demandes de bourse",
                "20/10/2025 - Début procédure visa"
        ));

        // ✅ Documents récents
        dto.setDocumentsRecents(List.of(
                "Diplôme bac (1)",
                "Lettre de motivation (1)",
                "Attestation d’inscription (1)"
        ));

        return dto;
    }

    private int getCountByTypeAndStatut(Long etuId, String type, String statutEnum) {
        switch (type) {
            case "Préinscription":
                return preinscriptionRepo.countByEtudiantIdAndStatut(etuId, StatutPreinscription.valueOf(statutEnum));
            case "Bourse":
                return bourseRepo.countByEtudiantIdAndStatut(etuId, StatutDemande.valueOf(statutEnum));
            case "Hébergement":
                return hebergementRepo.countByEtudiantIdAndStatut(etuId, StatutDemande.valueOf(statutEnum));
            case "Visa":
                return visaRepo.countByEtudiantIdAndStatut(etuId, StatutDemande.valueOf(statutEnum));
            case "Transport": // ✅ Ajout
                return transportRepo.countByEtudiantIdAndStatut(etuId, StatutDemande.valueOf(statutEnum));
            default:
                return 0;
        }
    }
}
