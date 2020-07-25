# Test technique pour Exomind

Vous développerez une application universelle (smartphone et tablette) avec trois écrans :
* La liste des utilisateurs (nom, prénom, mail, tel, site) avec une barre de recherche
* La liste des albums pour un utilisateur
* La liste des photos d’un album

L’API à utiliser:
* Récupérer la liste des utilisateurs -> https://jsonplaceholder.typicode.com/users
* Albums d’un utilisateur -> https://jsonplaceholder.typicode.com/users/{userID}/albums
* Photos d’un utilisateur -> https://jsonplaceholder.typicode.com/users/{userID}/photos
* Rechercher des utilisateurs -> https://jsonplaceholder.typicode.com/users?username={name}

Les données téléchargées devront être disponibles offline.
Une fois les albums/photos d’un utilisateur récupérés ne plus refaire l’appel à l'API

Vous pouvez utiliser des librairies externes si vous le jugez vraiment nécessaire.
