package com.campus.CampusAfrique.dto;

import lombok.Data;
import java.util.List;
import java.util.Map;

@Data
public class DashboardStatsDTO {
    private int totalCandidatures;
    private int enAttente;
    private int totalBourses;

    private Map<String, Integer> repartitionStatuts;
    private Map<String, Integer> demandesParType;
    private Map<String, Map<String, Integer>> statutsParType;

    private List<String> datesImportantes; // format "30/09/2025 - Fin des candidatures"
    private List<String> documentsRecents; // format "Diplôme bac (1)"
}
