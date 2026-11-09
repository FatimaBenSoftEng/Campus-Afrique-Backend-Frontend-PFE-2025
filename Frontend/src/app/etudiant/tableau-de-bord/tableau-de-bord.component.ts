import {
  Component, OnInit, AfterViewInit, Inject
} from '@angular/core';
import { CommonModule, isPlatformBrowser } from '@angular/common';
import { RouterModule } from '@angular/router';
import { PLATFORM_ID } from '@angular/core';
import { BaseChartDirective } from 'ng2-charts';
import { ChartData, ChartOptions } from 'chart.js';
import {
  Chart, PieController, ArcElement, Tooltip, Legend,
  LinearScale, CategoryScale, LineController, PointElement,
  LineElement, BarController, BarElement, RadarController,
  RadialLinearScale, Filler
} from 'chart.js';

import { SidebarEtudiantFixComponent } from '../../shared/sidebar-etudiant-fix/sidebar-etudiant-fix.component';
import { NavbarEtudiantComponent } from '../../shared/navbar-etudiant/navbar-etudiant.component';
import { FooterEtudiantComponent } from '../../shared/footer-etudiant/footer-etudiant.component';
import { SidebarEvenementsComponent } from '../../shared/sidebar-evenements/sidebar-evenements.component';

import { DashboardService, DashboardStats } from '../../services/dashboard.service';
import { EtudiantService } from '../../services/etudiant.service';

Chart.register(
  PieController, ArcElement, Tooltip, Legend,
  LinearScale, CategoryScale, LineController,
  PointElement, LineElement, BarController,
  BarElement, RadarController, RadialLinearScale, Filler
);

@Component({
  selector: 'app-tableau-de-bord',
  standalone: true,
  imports: [
    CommonModule, RouterModule, BaseChartDirective,
    SidebarEtudiantFixComponent, NavbarEtudiantComponent,
    FooterEtudiantComponent, SidebarEvenementsComponent
  ],
  templateUrl: './tableau-de-bord.component.html',
  styleUrls: ['./tableau-de-bord.component.scss']
})
export class TableauDeBordComponent implements OnInit, AfterViewInit {
  sidebarVisible = true;
  isBrowser = false;
  nomComplet = '';
  stats = {
    totalCandidatures: 0,
    enAttente: 0,
    totalBourses: 0,
    totalTransports: 0 // ✅ Ajout
  };

  pieChartData: ChartData<'pie'> = {
    labels: ['Validée', 'En attente', 'Refusée', 'À compléter'],
    datasets: [{ data: [], backgroundColor: ['#2ECC71', '#F1C40F', '#E74C3C', '#3498DB'], borderWidth: 2, borderColor: '#fff' }]
  };

  pieChartOptions: ChartOptions<'pie'> = {
    responsive: true,
    maintainAspectRatio: false,
    plugins: {
      legend: {
        position: 'bottom',
        labels: { padding: 20, usePointStyle: true }
      }
    }
  };

  barChartData: ChartData<'bar'> = {
    labels: ['Préinscription', 'Bourse', 'Hébergement', 'Visa', 'Transport'], // ✅ Ajouté Transport
    datasets: [{ label: 'Demandes', data: [], backgroundColor: '#3498DB' }]
  };

  barChartOptions: ChartOptions<'bar'> = {
    responsive: true,
    maintainAspectRatio: false,
    plugins: { legend: { display: false } },
    scales: { y: { beginAtZero: true, ticks: { stepSize: 1 } }, x: {} }
  };

  radarChartData: ChartData<'radar'> = {
    labels: ['Préinscription', 'Bourse', 'Hébergement', 'Visa', 'Transport'], // ✅ Ajouté Transport
    datasets: [{ label: 'Utilisation (%)', data: [], backgroundColor: 'rgba(243, 189, 0, 0.4)', borderColor: '#F3BD00', pointBackgroundColor: '#0C2B4B' }]
  };

  radarChartOptions: ChartOptions<'radar'> = {
    responsive: true,
    maintainAspectRatio: false,
    scales: { r: { suggestedMin: 0, suggestedMax: 100 } }
  };

  stackedChartData: ChartData<'bar'> = {
    labels: ['Préinscription', 'Bourse', 'Hébergement', 'Visa', 'Transport'], // ✅ Ajouté Transport
    datasets: [
      { label: 'Validée', data: [], backgroundColor: '#2ECC71' },
      { label: 'En attente', data: [], backgroundColor: '#F1C40F' },
      { label: 'Refusée', data: [], backgroundColor: '#E74C3C' }
    ]
  };

  stackedChartOptions: ChartOptions<'bar'> = {
    responsive: true,
    maintainAspectRatio: false,
    plugins: { legend: { position: 'bottom' } },
    scales: { x: { stacked: true }, y: { stacked: true, beginAtZero: true } }
  };

  datesImportantes: { titre: string; date: Date }[] = [];
  documents: { label: string; count: number }[] = [];

  constructor(
    @Inject(PLATFORM_ID) private platformId: Object,
    private dashboardService: DashboardService,
    private etudiantService: EtudiantService
  ) {
    this.isBrowser = isPlatformBrowser(this.platformId);
  }

  ngOnInit(): void {
    this.etudiantService.getProfil().subscribe((data) => {
      this.nomComplet = data.nom + ' ' + data.prenom;
    });

    this.dashboardService.getDashboardStats().subscribe((data: DashboardStats) => {
      console.log('📊 Données dashboard reçues :', data);

      this.stats.totalCandidatures = data.totalCandidatures;
      this.stats.enAttente = data.enAttente;
      this.stats.totalBourses = data.totalBourses;
      this.stats.totalTransports = data.demandesParType['Transport'] || 0; // ✅ Nouvelle ligne

      this.pieChartData.datasets[0].data = Object.values(data.repartitionStatuts);
      this.barChartData.datasets[0].data = Object.values(data.demandesParType);
      this.radarChartData.datasets[0].data = Object.values(data.demandesParType);

      const valides: number[] = [], enAttentes: number[] = [], refusees: number[] = [];
      const types = ['Préinscription', 'Bourse', 'Hébergement', 'Visa', 'Transport']; // ✅ Nouvelle liste

      for (const type of types) {
        const group = data.statutsParType[type] || {};
        valides.push(group['Validée'] || 0);
        enAttentes.push(group['En attente'] || 0);
        refusees.push(group['Refusée'] || 0);
      }

      this.stackedChartData.datasets[0].data = valides;
      this.stackedChartData.datasets[1].data = enAttentes;
      this.stackedChartData.datasets[2].data = refusees;

      this.datesImportantes = data.datesImportantes.map((e: string) => {
        const [dateStr, titre] = e.split(" - ");
        return { date: new Date(dateStr.split('/').reverse().join('-')), titre };
      });

      this.documents = data.documentsRecents.map((d: string) => {
        const match = d.match(/(.+?) \((\d+)\)/);
        return match ? { label: match[1], count: +match[2] } : { label: d, count: 0 };
      });
    });
  }

  ngAfterViewInit(): void {}
  toggleSidebar() { this.sidebarVisible = !this.sidebarVisible; }
}
