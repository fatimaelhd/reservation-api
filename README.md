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
- Maven pour la gestion des dépendances

## 🏗️ Architecture

- **Entities** : `User`, `Resource`, `Reservation`
- **Repositories** : JPA Repositories pour la persistance
- **Services** : Logique métier, vérification des conflits, gestion transactionnelle
- **Controllers** : Exposition des endpoints REST
- **Tests unitaires** : sécurisation de la logique métier

---

## ⚙️ Règles métier

- Une **ressource ne peut être réservée** que si aucun autre utilisateur ne l’a réservée sur le même créneau.
- Les **créneaux horaires** doivent être valides (`startTime < endTime`).
- Chaque réservation est associée à un **utilisateur et une ressource**.
- Les **ID** des entités sont générés automatiquement pour éviter les collisions.

---

## 🧪 Tests

- Les tests unitaires vérifient la logique métier (création, conflits, validation des horaires).
- Les endpoints REST ont été testés avec **Postman** pour garantir la circulation correcte des données.
- La base H2 en mémoire permet de lancer rapidement des tests sans configuration externe.

---


## Lancer le projet
1. Cloner le repository :
```bash
git clone https://github.com/fatimaelhd/reservation-api.git

