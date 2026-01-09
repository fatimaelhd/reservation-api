# Reservation API
Une API backend développée en **Java 17 / Spring Boot** pour gérer les réservations de ressources partagées au sein d'une organisation. 

## Objectif
Permettre aux utilisateurs de réserver des ressources (salles, équipements) sur des créneaux temporels, tout en garantissant :
- La cohérence des données
- L'absence de conflits dans les réservations
- La robustesse face aux accès concurrents

## Fonctionnalités principales
- Gestion des **utilisateurs** (création, consultation)
- Gestion des **ressources** (création, consultation)
- Création et consultation des **réservations**
- Vérification automatique des **conflits de réservation**
- Statut de réservation (CONFIRMED, éventuellement CANCELLED ou PENDING)

## Technologies utilisées
- Java 17
- Spring Boot
- Spring Data JPA / Hibernate
- H2 Database (pour développement et tests)
- API REST

## Lancer le projet
1. Cloner le repository :
```bash
git clone https://github.com/fatimaelhd/reservation-api.git

