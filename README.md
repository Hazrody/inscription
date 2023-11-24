# inscription

Ce projet créé dans utilisateurs et les envoie à rabbitmq.

Dans la commande docker ci-dessous j'ai spécifié les ip pour la configuration avec le projet (Ça fonctionnait pas sans).

**Docker rabbitmq**: ``docker run -it --rm --name rabbitmq --ip 172.17.0.2 -p 5672:5672 -p 15672:15672 rabbitmq:3-management``

**Docker postgres**: ``docker run --name postgres --ip 172.17.0.7 -e POSTGRES_PASSWORD=admin -d postgres``

Dans le dossier sql se trouve un script pour initialiser la base de donnée.

* url=jdbc:postgresql://172.17.0.7:5432/postgres
* username=postgres
* password=admin

Sur docker desktop, sur le container postgres une fois créer dans la categorie ``exec``, on peut venir alimenter la base de donnée.
* psql -h 172.17.0.7 -U postgres
* password **admin**
* \c postgres   
* Ajouter les tables et les jeux d'essai

**URL Swagger**: http://localhost:8080/swagger-ui/index.html
