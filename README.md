# Bati-Cuisine: Application d'estimation des coûts de construction des cuisines

## Description

Bati-Cuisine est une application Java conçue pour les professionnels du bâtiment, spécialisée dans la gestion et l'estimation des coûts des projets de construction et de rénovation de cuisines. Elle permet de gérer des projets, des clients, des matériaux, la main-d'œuvre, ainsi que de générer des devis détaillés.

## Fonctionnalités principales

### 1. Gestion des Projets

- Créer et gérer des projets de construction ou de rénovation.
- Associer un client à chaque projet.
- Ajouter des matériaux et de la main-d'œuvre.
- Calculer le coût total du projet, incluant la marge bénéficiaire et les taxes.

### 2. Gestion des Composants (Matériaux et Main-d'œuvre)

- Ajouter des matériaux avec des détails tels que le coût unitaire, la quantité, la TVA, et les frais de transport.
- Ajouter de la main-d'œuvre avec des détails tels que le taux horaire, le nombre d'heures travaillées et la productivité des ouvriers.

### 3. Gestion des Clients

- Enregistrer et gérer les informations des clients, qu'ils soient professionnels ou particuliers.
- Appliquer des remises spécifiques en fonction du type de client.

### 4. Création de Devis

- Générer des devis avec les coûts des matériaux, de la main-d'œuvre et les taxes.
- Inclure des dates d'émission et de validité des devis.

### 5. Calcul des Coûts

- Calculer les coûts totaux d'un projet en tenant compte des matériaux, de la main-d'œuvre, des taxes et de la marge bénéficiaire.

### 6. Affichage des Détails et Résultats

- Afficher un récapitulatif des coûts totaux, incluant la main-d'œuvre, les matériaux, la TVA et la marge bénéficiaire.

## Exigences Fonctionnelles

### Gestion des projets

- Création de projets avec nom, marge bénéficiaire, coût total et état (En cours, Terminé, Annulé).

### Gestion des composants

- Ajout de matériaux et de main-d'œuvre, calcul des coûts en fonction des quantités, taux horaire, qualité des matériaux, etc.

### Gestion des clients

- Différencier les clients particuliers et professionnels, enregistrer leurs informations et calculer les remises spécifiques.

### Création de devis

- Génération de devis avec estimation des coûts et validation par le client.

### Calcul des coûts

- Intégration des taxes, remises et marges bénéficiaires dans les coûts totaux.

### Affichage des détails

- Présentation des coûts détaillés avec un récapitulatif final.

## Utilisation

### Exemple de scénario d'utilisation

1. Créer un nouveau projet.
2. Ajouter un client et des matériaux (exemple : Carrelage, Peinture).
3. Ajouter de la main-d'œuvre (exemple : Ouvrier de base, Spécialiste).
4. Générer un devis incluant la TVA et la marge bénéficiaire.
5. Afficher un récapitulatif détaillé avec les coûts finaux.

## Architecture du projet

Le projet est structuré en plusieurs couches :

- **UI Layer** : Interface utilisateur et interaction via la console.
- **Service Layer** : Logique métier (calculs des coûts, gestion des clients, matériaux).
- **DAO Layer** : Accès aux données via PostgreSQL pour la gestion des clients, projets, matériaux, etc.
- **Validation** : Gestion de la validation des entrées utilisateur.

## Auteur

Ayoub Snini
