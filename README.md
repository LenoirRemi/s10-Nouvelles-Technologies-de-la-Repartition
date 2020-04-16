# TP Nouvelles Technologies de la Répartition
#### Prérequis

| BDD | MySQL5.7 |
| ------ | ------ |
| Schema | banq_db |
| user | admin |
| mdp | admin |

OU

Changer les paramètres de connection à la BDD situés dans:
> tomcat-server\src\main\resources\application.properties

### Serveur Tomcat
Deploiement du serveur tomcat qui heberge les services XML et JSON ainsi que les interfaces web. (http://localhost:8081)
```sh
$ cd tomcat-server
```
```sh
$ mvn install
```
```sh
$ mvn package
```
```sh
$ cd target
```
```sh
$ java -jar banq-0.0.1-SNAPSHOT.jar
```



### Serveur Wildfly
Déploiement du web service sur Wildfly qui hébérge des services pour les sites web de commerce. (http://localhost:8080)

(Pour deployer, un serveur Wildfly doit etre lancé : ``$ ./standalone.sh``)

```sh
$ cd wildfly-server
```
```sh
$ mvn install
```
```sh
$ mvn package wildfly:deploy
```

(Eventuellement pour les tests lancer le main dans le fichier ``BanqServiceStarter.java``.)

### Tests
#### Prérequis

Créer au moins 2 utilisateurs en base, via http://localhost:8081/registration

Une fois les deux serveur disponibles, les tests sont présents dans ``/main/src/test``.

- ``Main.java`` contient une fonction main avec les tests des fonctionnalitées XML et JSON du serveur Tomcat. Il appelle les deux fichiers suivant :
	- ``MainJson.java``
	- ``MainXml.java``
- ``MainWildflyService.java`` contient une fonction main avec les tests des fonctionnalitées SOAP du serveur Wildfly.
