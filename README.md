# Cooking recipes around the world

## Composition de l'équipe-projet

* Axel LAFFITE (TPA22) **@axellaffite**
* Louis AYROLES (TPA22) **@imjustlouis**
* Thibaut BETEND (TPA22) **@thibautbetend**
* David ROYER (TPA22) **@Electroww**

## Remarques

* Le [`.gitignore`](.gitignore) fourni a été généré par la commande :

  ```
  curl -o .gitignore -L https://www.gitignore.io/api/intellij+all,java,maven,vuejs,node
  ```

* L'architecture de votre dépôt Git sera de type
  [monorepo](https://www.atlassian.com/git/tutorials/monorepos), donc
  un sous-dossier pour le backend + un sous-dossier pour le frontend,
  les deux sous-dossiers étant importés séparément dans deux projets
  de votre IDE.

## Documentation

* Afin de lancer le FrontEnd, il est nécessaire de mettre à jour votre version de nodejs.  
Vous pouvez procéder de la manière suivante : 

```bash
sudo apt remove nodejs
sudo apt update && sudo apt install curl -y
sudo sh -c "echo deb https://deb.nodesource.com/node_14.x groovy main > /etc/apt/sources.list.d/nodesource.list"
sudo apt-get update
sudo apt-get install nodejs
```

* Une fois que votre version de nodejs est mise à jour, vous pouvez lancer le projet
en vous positionnant dans le répertoire frontend et en lançant les commandes suivantes :
```bash
  npm install
  npm run serve
```

### Build
Afin de build le projet vous pouvez exécuter le fichier `build.sh` de la manière suivante :

```sh
./build.sh
# OU
sh build.sh
```

Sur Windows, vous devrez passer par le powershell pour que cela fonctionne.

Afin d'être totalement indépendant de la plateforme, vous pouvez effectuer les actions suivantes pour build
le projet :

 - Vous rendre dans le dossier frontend et lancer :
   - `npm install`
   - `npm run build`
 - Vous rendre dans le dossier backend et lancer 
   - `mvn clean package`

L'application devrait être disponible dans le répertoire racine dans le dossier `CookingTheWorld`.

Pour lancer l'application il ne reste plus qu'à faire `java -jar CookingTheWorld.jar` dans le dossier cité ci-dessus.