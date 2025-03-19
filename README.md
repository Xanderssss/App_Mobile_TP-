# MegaQuizz

MegaQuizz est une application mobile Android permettant aux utilisateurs de tester leurs connaissances à travers une série de questions à choix multiples.

## 📱 Fonctionnement de l'application
L'application suit un parcours simple :
1. **Écran d'accueil** : L'utilisateur doit entrer un pseudo avant de commencer le quiz.
2. **Lancement du quiz** : Une série de questions est posée, chaque question ayant 4 choix de réponse.
3. **Validation des réponses** : L'utilisateur sélectionne une réponse, qui est immédiatement validée.
4. **Affichage des résultats** : Une fois toutes les questions répondues, l'utilisateur voit ses résultats et peut rejouer.

## ⚙️ Fonctionnalités implémentées
- Entrée du pseudo avant de commencer le quiz.
- Chargement de questions avec 4 réponses possibles.
- Vérification automatique des réponses avec feedback visuel (vert pour correct, rouge pour incorrect).
- Affichage des résultats en fin de quiz avec correction des réponses.
- Possibilité de rejouer après la fin du quiz.

## 🛠️ Bugs ou limites connues
- Actuellement, les questions sont codées en dur dans l'application.
- Pas de sauvegarde des scores ou du pseudo après fermeture de l'application.
- Pas de base de données ni d'API pour charger dynamiquement les questions.
- Absence d'un chronomètre ou d'un mode contre-la-montre.

## 📂 Structure du projet
```
MegaQuizz/
├── model/
│   ├── MainActivity.java
│   ├── WelcomeFragment.java
│   ├── ResultsFragment.java
├── ui/
│   ├── QuizzFragment.java
│   ├── UserAnswer.java
├── Question/
│   ├── Question.java
├── res/
│   ├── layout/
│   │   ├── activity_main.xml
│   │   ├── fragment_welcome.xml
│   │   ├── fragment_quizzfragment.xml
│   │   ├── fragment_result.xml
```

## 🔧 Améliorations futures
- Ajout d'une API ou d'une base de données pour récupérer des questions dynamiquement.
- Implémentation d'un mode multijoueur.
- Ajout d'un mode chronométré pour plus de challenge.
- Sauvegarde des scores et pseudo dans une base locale ou en ligne.
