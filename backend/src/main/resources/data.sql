-- Données pour NiveauScolaire
INSERT INTO niveau_scolaire (nom, description) VALUES
('Licence 1', 'Première année de licence'),
('Doctorat', 'Cycle doctoral'),
('BTS 1', 'Première année de BTS');


-- Données pour Etablissement
INSERT INTO etablissement (nom, adresse, description, email, site_web, bourse_disponible, ville, pays, nom_abr, type) VALUES
('Université de Dakar', '123 Avenue de l''Université', 'Université publique', 'contact@udakar.sn', 'www.udakar.sn', true, 'Dakar', 'Sénégal', 'UD', 'Université'),
('Institut Supérieur de Technologie', '45 Rue des Sciences', 'Institut technique', 'contact@ist.ci', 'www.ist.ci', true, 'Abidjan', 'Côte d''Ivoire', 'IST', 'Institut'),
('Université de Lomé', '23 Boulevard de l''Université', 'Université publique', 'contact@ulome.tg', 'www.ulome.tg', true, 'Lomé', 'Togo', 'UL', 'Université');

-- Données pour Formation
INSERT INTO formation (nom, description, duree, frais_annuel, frais_max, etablissement_id) VALUES
('Informatique', 'Formation en développement informatique', 3, 2000, 2500, 1),
('Gestion', 'Formation en gestion d''entreprise', 3, 1800, 2200, 2),
('Marketing', 'Formation en marketing digital', 2, 1500, 1800, 3);

-- Données pour Etudiant
INSERT INTO etudiant (nom, prenom, email, mot_de_passe, pays_origine, date_naissance, nationalite, photo_profil, statut, telephone, adresse, ville, niveau_scolaire_id) VALUES
('Diallo', 'Amadou', 'amadou.diallo@email.com', 'password123', 'Sénégal', '2000-05-15', 'Sénégalaise', 'photo1.jpg', 'Actif', '+221 77 123 4567', '123 Rue de Dakar', 'Dakar', 1),
('Kouamé', 'Marie', 'marie.kouame@email.com', 'password123', 'Côte d''Ivoire', '2001-03-20', 'Ivoirienne', 'photo2.jpg', 'Actif', '+225 07 234 5678', '45 Avenue Abidjan', 'Abidjan', 2),
('Ndiaye', 'Fatou', 'fatou.ndiaye@email.com', 'password123', 'Sénégal', '1999-11-10', 'Sénégalaise', 'photo3.jpg', 'Actif', '+221 76 345 6789', '78 Rue de Saint-Louis', 'Saint-Louis', 3);

-- Données pour OrganismeBourse
INSERT INTO organisme_bourse (nom, email, pays, type) VALUES
('Fondation Education Plus', 'contact@edplus.org', 'Sénégal', 'Fondation'),
('Bourse Excellence', 'contact@bourse-excellence.org', 'Côte d''Ivoire', 'Association'),
('Fonds Africain', 'contact@fonds-africain.org', 'Mali', 'Organisation');

-- Données pour ResponsableEtablissement
INSERT INTO responsable_etablissement (nom,email, mot_de_passe, etablissement_id) VALUES
('Diop','directeur@udakar.sn', 'password123', 1),
('Kouamé','directeur@ist.ci', 'password123', 2),
('Ndiaye','directeur@esc.cm', 'password123', 3);

-- Données pour Admin
INSERT INTO admin (nom, email, telephone, mot_de_passe) VALUES
('Admin1', 'admin1@campusafrique.com', '+221 77 111 1111', 'admin123');

-- Données pour AgentMinistere
INSERT INTO agent_ministere (nom, email, mot_de_passe) VALUES
('Agent1', 'agent1@ministere.sn', 'agent123'),
('Agent2', 'agent2@ministere.sn', 'agent123'),
('Agent3', 'agent3@ministere.sn', 'agent123');

-- Données pour AgentAmbassade
INSERT INTO agent_ambassade (nom, pays_responsable, email, mot_de_passe) VALUES
('Ambassadeur1', 'Sénégal', 'amb1@ambassade.sn', 'amb123'),
('Ambassadeur2', 'Côte d''Ivoire', 'amb2@ambassade.ci', 'amb123'),
('Ambassadeur3', 'Mali', 'amb3@ambassade.ml', 'amb123');

-- Données pour Dossier
INSERT INTO dossier (date_creation, statut, date_miseajour, type_dossier, etudiant_id) VALUES
('2024-01-15', 'En cours', '2024-01-15', 'Inscription', 1),
('2024-01-16', 'Validé', '2024-01-20', 'Bourse', 2),
('2024-01-17', 'En cours', '2024-01-17', 'Inscription', 3);

-- Données pour Document
INSERT INTO document (nom, type, chemin_fichier, date_ajout, statut, dossier_id) VALUES
('Diplôme', 'PDF', '/documents/diplome1.pdf', '2024-01-15', 'Validé', 1),
('Relevé de notes', 'PDF', '/documents/notes1.pdf', '2024-01-15', 'Validé', 1),
('Lettre de motivation', 'PDF', '/documents/motivation1.pdf', '2024-01-15', 'Validé', 1);

-- Données pour Notification
INSERT INTO notification (destinataire, message, date_envoi, lue, etudiant_id) VALUES
('amadou.diallo@email.com', 'Votre dossier a été reçu', '2024-01-15', true, 1),
('marie.kouame@email.com', 'Votre bourse a été approuvée', '2024-01-16', true, 2),
('fatou.ndiaye@email.com', 'Votre inscription est en cours de traitement', '2024-01-17', false, 3);

-- Données pour Candidature
INSERT INTO candidature (date_soumission, statut, message_motivation, etudiant_id, etablissement_id) VALUES
('2024-01-15', 'En cours', 'Je souhaite intégrer votre formation', 1, 1),
('2024-01-16', 'Acceptée', 'Passionné par votre programme', 2, 2),
('2024-01-17', 'En cours', 'Intéressé par votre établissement', 3, 3);

-- Données pour Formation
INSERT INTO formation (nom, description, duree, frais_annuel, etablissement_id) VALUES
('Informatique', 'Formation en développement informatique', 3, 2500, 1),
('Gestion', 'Formation en gestion d''entreprise', 3, 1800,2),
('Marketing', 'Formation en marketing digital', 2, 1500,2);

-- Données pour Preinscription
INSERT INTO preinscription (choix_formation, statut, ordre_preference, etudiant_id, formation_id, etablissement_id) VALUES
('Informatique', 'En cours', 1, 1, 1, 1),
('Gestion', 'Validée', 1, 2, 2, 2),
('Marketing', 'En cours', 1, 3, 3, 3);

-- Données pour Inscription
INSERT INTO inscription (date_inscription, montant_frais, statut, etudiant_id, etablissement_id, formation_id) VALUES
('2024-01-15', 2000, 'En cours', 1, 1, 1),
('2024-01-16', 1800, 'Validée', 2, 2, 2),
('2024-01-17', 1500, 'En cours', 3, 3, 3);

-- Données pour Paiement
INSERT INTO paiement (montant, date_paiement, statut, etudiant_id) VALUES
(2000, '2024-01-15', 'Validé', 1),
(1800, '2024-01-16', 'Validé', 2),
(1500, '2024-01-17', 'En cours', 3);

-- Données pour DemandeBourse
INSERT INTO demande_bourse (date_demande, statut, etudiant_id, organisme_bourse_id) VALUES
('2024-01-15', 'En cours', 1, 1),
('2024-01-16', 'Approuvée', 2, 2),
('2024-01-17', 'En cours', 3, 3);

-- Données pour Attestation
INSERT INTO attestation (type, date_emission, emis_par) VALUES
('Inscription', '2024-01-15', 'Université de Dakar'),
('Bourse', '2024-01-16', 'Fondation Education Plus');

-- Données pour GaleriePhoto
INSERT INTO galerie_photo (url, description, etablissement_id) VALUES
('/photos/udakar1.jpg', 'Campus principal', 1),
('/photos/ist1.jpg', 'Bâtiment administratif', 2),
('/photos/esc1.jpg', 'Salle de cours', 3);