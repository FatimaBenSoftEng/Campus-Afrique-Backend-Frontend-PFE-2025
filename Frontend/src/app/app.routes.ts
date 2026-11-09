import { Routes } from '@angular/router';
import { IndexComponent } from './pages/index/index.component';
import { AboutComponent } from './pages/about/about.component';
import { ContactComponent } from './pages/contact/contact.component';
import { EtablissementComponent } from './pages/etablissement/etablissement.component';
import { ErrorComponent } from './pages/error/error.component';
import { AppointmentComponent } from './pages/appointment/appointment.component';
import { FeatureComponent } from './pages/feature/feature.component';
import { TeamComponent } from './pages/team/team.component';
import { TestimonialComponent } from './pages/testimonial/testimonial.component';
import { LogComponent } from './auth/log/log.component';
import { SignComponent } from './auth/sign/sign.component';
import { ForgotPasswordComponent } from './auth/forgot-password/forgot-password.component';
import { ConditionsGeneralesComponent } from './auth/conditions-generales/conditions-generales.component';
// Route pour Etudiant
import { ListEtablissementComponent } from './etudiant/list-etablissement/list-etablissement.component';
import { FormulairePreinscriptionComponent} from './etudiant/formulaire-preinscription/formulaire-preinscription.component';
import { DetailEtablissementComponent } from './etudiant/detail-etablissement/detail-etablissement.component';
import { FormulaireHebergementComponent} from './etudiant/formulaire-hebergement/formulaire-hebergement.component';
import { FormulaireTransportComponent} from './etudiant/formulaire-transport/formulaire-transport.component';
import { FormulaireVisaComponent} from './etudiant/formulaire-visa/formulaire-visa.component';
import { FormulaireBourseComponent} from './etudiant/formulaire-bourse/formulaire-bourse.component';
import { FormulaireProfilComponent} from './etudiant/formulaire-profil/formulaire-profil.component';
import { EtatDemandeComponent} from './etudiant/etat-demande/etat-demande.component';
import { ComplementDemandeComponent } from './etudiant/complement-demande/complement-demande.component';
import { FavorisComponent} from './etudiant/favoris/favoris.component';
import { DocumentsComponent} from './etudiant/documents/documents.component';
import { TableauDeBordComponent} from './etudiant/tableau-de-bord/tableau-de-bord.component';
import { EtatDemandeBourseComponent} from './etudiant/etat-demande-bourse/etat-demande-bourse.component';
import { EtatDemandeVisaComponent} from './etudiant/etat-demande-visa/etat-demande-visa.component';
import { EtatDemandeTransportComponent} from './etudiant/etat-demande-transport/etat-demande-transport.component';
import { EtatDemandeHebergementComponent} from './etudiant/etat-demande-hebergement/etat-demande-hebergement.component';

// Imports pour Etablissement
import { CandidaturesComponent } from './etablissement/candidatures/candidatures.component';
import { FormationsComponent } from './etablissement/formations/formations.component';
import { PaiementsComponent } from './etablissement/paiements/paiements.component';
import { ProfilEtablissementComponent } from './etablissement/profil-etablissement/profil-etablissement.component';
import { RapportComponent } from './etablissement/rapport/rapport.component';
import { DashboardEtablissementComponent } from './etablissement/dashboard-etablissement/dashboard-etablissement.component';
import { AccessStudentDocumentsComponent } from './etablissement/access-student-documents/access-student-documents.component';
import { ActiveFormationComponent } from './etablissement/active-formation/active-formation.component';
import { AjouterFormationComponent } from './etablissement/ajouter-formation/ajouter-formation.component';
import { AllFormationComponent } from './etablissement/all-formation/all-formation.component';
import { ApplicationValidationComponent } from './etablissement/application-validation/application-validation.component';
import { InactiveFormationComponent } from './etablissement/inactive-formation/inactive-formation.component';
import { ListConfirmedRegistrantsComponent } from './etablissement/list-confirmed-registrants/list-confirmed-registrants.component';
import { ListPendingPaymentsComponent } from './etablissement/list-pending-payments/list-pending-payments.component';
import { OrganizationInterviewsComponent } from './etablissement/organization-interviews/organization-interviews.component';
import { PaymentValidationComponent } from './etablissement/payment-validation/payment-validation.component';
import { PreregistrationCertificateComponent } from './etablissement/preregistration-certificate/preregistration-certificate.component';
import { PreregistrationListComponent } from './etablissement/preregistration-list/preregistration-list.component';
import { RegistrationCertificateComponent } from './etablissement/registration-certificate/registration-certificate.component';
import { ReviewFormationComponent } from './etablissement/review-formation/review-formation.component';
import { UpdateFormationComponent } from './etablissement/update-formation/update-formation.component';
import { NavbarEtablissementComponent} from './shared/navbar-etablissement/navbar-etablissement.component';
import { FooterEtablissementComponent} from './shared/footer-etablissement/footer-etablissement.component';
import { SlidebarEtablissementFixComponent} from './shared/slidebar-etablissement-fix/slidebar-etablissement-fix.component';
import { NavbarEtudiantComponent} from './shared/navbar-etudiant/navbar-etudiant.component';

// Imports pour Administrateur Fonctionnel
import { FooterAdminComponent} from './shared/footer-admin/footer-admin.component';
import { NavbarAdminComponent} from './shared/navbar-admin/navbar-admin.component';
import { SlidebarAdminFixComponent} from './shared/slidebar-admin-fix/slidebar-admin-fix.component';
import { DashboardAdminComponent} from './admin/dashboard-admin/dashboard-admin.component';



export const routes: Routes = [
  { path: '', component: IndexComponent },
  { path: 'about', component: AboutComponent },
  { path: 'contact', component: ContactComponent },
  { path: 'appointment', component: AppointmentComponent },
  { path: 'etablissement', component: EtablissementComponent  },
  { path: 'error', component: ErrorComponent },
  { path: 'feature', component: FeatureComponent },
  { path: 'team', component: TeamComponent },
  { path: 'testimonial', component: TestimonialComponent },
  { path: 'log', component: LogComponent },
  { path: 'sign', component: SignComponent },
  { path: 'forgot-password', component: ForgotPasswordComponent },
  { path: 'conditions-generales', component: ConditionsGeneralesComponent },

  // PAGES ETUDIANT
  { path: 'list-etablissement', component: ListEtablissementComponent },
  { path: 'formulaire-preinscription', component: FormulairePreinscriptionComponent },
  { path: 'formulaire-hebergement', component: FormulaireHebergementComponent },
  { path: 'formulaire-transport', component: FormulaireTransportComponent },
  { path: 'formulaire-visa', component: FormulaireVisaComponent },
  { path: 'formulaire-bourse', component: FormulaireBourseComponent },
  { path: 'formulaire-profil', component: FormulaireProfilComponent },
  { path: 'detail-etablissement/:id', component: DetailEtablissementComponent },
  { path: 'etat-demande', component: EtatDemandeComponent },
  { path: 'complement-demande', component: ComplementDemandeComponent },
  { path: 'favoris', component: FavorisComponent },
  { path: 'documents', component: DocumentsComponent },
  { path: 'tableau-de-bord', component: TableauDeBordComponent },
  { path: 'etat-demande-bourse',component: EtatDemandeBourseComponent},
  { path: 'etat-demande-visa',component: EtatDemandeVisaComponent},
  { path: 'etat-demande-transport',component: EtatDemandeTransportComponent},
  { path: 'etat-demande-hebergement',component: EtatDemandeHebergementComponent},

  // PAGES Établissement
  { path: 'candidatures', component: CandidaturesComponent },
  { path: 'formations', component: FormationsComponent },
  { path: 'paiements', component: PaiementsComponent },
  { path: 'profil', component: ProfilEtablissementComponent },
  { path: 'rapport', component: RapportComponent },
  { path: 'dashboard-etablissement', component: DashboardEtablissementComponent },
  { path: 'navbar-etablissement', component: NavbarEtablissementComponent },
  { path: 'footer-etablissement', component: FooterEtablissementComponent},
  { path: 'slidebar-etablissement-fix', component: SlidebarEtablissementFixComponent},
  { path: 'navbar-etudiant', component: NavbarEtudiantComponent},
  { path: 'ajouter-formation', component: AjouterFormationComponent },
  { path: 'active-formation', component: ActiveFormationComponent },
  { path: 'inactive-formation', component: InactiveFormationComponent },
  { path: 'all-formation', component: AllFormationComponent },
  { path: 'application-validation', component: ApplicationValidationComponent },
  { path: 'payment-validation', component: PaymentValidationComponent },
  { path: 'access-student-documents', component: AccessStudentDocumentsComponent },
  { path: 'list-confirmed-registrants', component: ListConfirmedRegistrantsComponent },
  { path: 'list-pending-payments', component: ListPendingPaymentsComponent },
  { path: 'organization-interviews', component: OrganizationInterviewsComponent },
  { path: 'preregistration-certificate', component: PreregistrationCertificateComponent },
  { path: 'preregistration-list', component: PreregistrationListComponent },
  { path: 'registration-certificate', component: RegistrationCertificateComponent },
  { path: 'review-formation', component: ReviewFormationComponent },
  { path: 'update-formation', component: UpdateFormationComponent },

  // PAGES Administrateur Fonctionnel
  { path: 'dashboard-admin', component: DashboardAdminComponent },
  { path: 'footer-admin', component: FooterAdminComponent },
  { path: 'navbar-admin', component: NavbarAdminComponent },
  { path: 'slidebar-admin-fix', component: SlidebarAdminFixComponent },

 { path: '**', redirectTo: 'error' }
];
