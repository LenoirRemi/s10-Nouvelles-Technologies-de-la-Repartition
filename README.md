# TP Nouvelles Technologies de la Répartition

Video de présentation : https://www.youtube.com/watch?v=WUrFa3_vHEI

### Serveur Tomcat
#### Prérequis

| BDD | MySQL5.7 |
| ------ | ------ |
| Schema | banq_db |
| user | admin |
| mdp | admin |

OU

Changer les paramètres de connection à la BDD situés dans:
> tomcat-server\src\main\resources\application.properties

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

#### Prérequis

> JDK 8

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
#### HTTP Requests
> GET
>> Accès aux informations de l'ensemble des utilisateurs en JSON: http://localhost:8081/users
>
>> Accès aux informations d'un utilisateur spécifique: http://localhost:8081/users/1 (pour l'utilisateur avec l'id 1)
>
>> Accès aux informations de l'ensemble des utilisateurs en XML: http://localhost:8081/users_xml
>
>> Accès au WSDL: http://localhost:8080/wildfly-server-1.0-SNAPSHOT/BanqServiceImpl?wsdl

> POST
>> Réaliser une transaction entre 2 utilisateurs: http://localhost:8081/transaction 
>> 
>> Exemple de paramètres:
>> ```xml
>><Users>
>>	<User id="1">	
>>		<balance>-100</balance>
>>	</User>
>>	<User id="2">
>>		<balance>100</balance>
>>	</User>
>></Users>	
>>```
>> <em>L'utilisateur avec l'id 1 envoit 100 à l'utilisateur avec l'id 2</em>

Une fois les deux serveur disponibles, les tests sont présents dans ``/main/src/test``.

- ``Main.java`` contient une fonction main avec les tests des fonctionnalitées XML et JSON du serveur Tomcat. Il appelle les deux fichiers suivant :
	- ``MainJson.java``
	- ``MainXml.java``
- ``MainWildflyService.java`` contient une fonction main avec les tests des fonctionnalitées SOAP du serveur Wildfly.
