Cette application suit une architecture Hexagonale et correspond à l'exercice backend "bank" 

Afin de pouvoir tester les différentes apis ces données ont été insérées au démarrage de l'app dans H2 :

un custumer (id=1)
Un account(id=1 , solde=0€) 
Un deuxiéme account (id=2, solde =0) : pour le virement entre compte user

Au totale 5 apis ont été dévéloppées pour répondre au besoin de l'énoncé.

Enoncé: 
Bonne nouvelle, la société X a décidé de créer sa propre banque en ligne.
L'exercice consiste à créer son logiciel à partir des user stories ci-dessous :

Préambule :
Je suis un client déjà authentifié sur le portail (ce use case n’est pas à traiter).
La devise de toutes les transactions est l’euro.

User stories :
• En tant que client, je veux pouvoir accéder à la liste de mes comptes
• En tant que client, je veux pouvoir déposer des devises sur mes comptes.
• En tant que client, je veux pouvoir retirer des devises depuis mes comptes.
• En tant que client, je veux pouvoir effectuer des virements entre mes différents comptes.
• En tant que client, je veux pouvoir consulter l’historique des opérations effectuées sur mes comptes (date, montant, type, par qui, solde…).
Si une opération de retrait ou virement fait dépasser le seuil du découvert, celle-ci est refusée.

