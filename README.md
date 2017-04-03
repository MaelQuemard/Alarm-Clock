# Alarm-Clock

## Lancement et utilisation

  Pour l’installer, veuillez dans un premier temps récupérer le projet à l’adresse suivante : https://github.com/MaelQuemard/Alarm-Clock

Ensuite, importez le projet dans eclipse : 
**“Clic droit”** -> import -> General -> existing project -> select path to Alarm-Clock

Des problèmes d’import peuvent apparaître, vérifiez bien que **“json-simple-1.1.1.jar”** est bien présent. Vérifiez aussi que le compilateur est bien dans une version supérieur ou égale à 1.7 ( How to ).

  Dans cette partie, nous introduiront un développeur lambda a notre application. Nous expliquerons comment l’utiliser, et ajouter par un exemple un plugin pour répondre à un besoin.

L’application globale, se découpe en 3 packages: **“Client”**, **“Extension”** , **“Framework”**. Dans le package **“Client”** on retrouve toutes le interfaces qu’un plugin peut être amené à avoir besoin, ou qu’il implémente. Dans “Extension”, on retrouve toutes les implémentations des interfaces de **“Client”**. Ces deux packages, sont ceux avec lesquels un développeur de plugin interagit.


## Description de la plateforme

### Package

Le dernier package, est le package **“Framework”** (plateforme). C’est ce qui permet de charger des plugins, sous demande. Pour cela, on y retrouve différentes classes. On peut les regrouper sous 3 catégories. Celle qui permet de charger des plugins, composée de **"Constraint"**, **"DescriptionPlugin"** et **"ExtensionLoader"**. Celle qui permet d’ajouter un proxy sur un plugin, composée de **"ISignalMonitor"**, **"MonitorHandler"** et **"ExtensionLoader"**. Et enfin la partie graphique du moniteur avec l’interface **"IMonitor"** qui décrit les besoin de l’affichage du moniteur, dont les implémentations se trouvent dans le package **"Extension"**.

#### Partie chargement plugin
**"Constraint"** et **"DescriptionPlugin"** sont des classes qui nous permettent de représenter les contraintes que doit respecter un plugin (voir la section Fichier configuration) qui doit être chargé. Elles permettent également, par la suite, de regrouper les informations contenues dans le fichier de configuration d’un plugin.
Le chargement se déroule dans la classe **"ExtensionLoader"**. C’est un singleton implémenté par un constructeur privé, et accessible par la méthode **"ExtensionLoader::getInstance()"**. Pour charger un plugin via **"ExtensionLoader::Load()"**, le plugin doit fournir en paramètre un “DescriptionPlugin”. Pour cela il doit faire appel à **"getExtension::getExtension()"**. Cette méthode va récupérer tous les plugins qui respectent les contraintes passées en paramètre grâce à un objet **"Constraint"**.

Une fois les descriptions fournies, il faut faire appel à la méthode **"ExtensionLoader::Load()"** avec la description du plugin voulu.

#### Partie proxy
**“ISignalMonitor”** et **“MonitorHandler”** sont les éléments qui permettent d’implémenter un proxy Java. Ce proxy va encapsuler l’objet voulu avec notre **“MonitorHandler”** et ajouter l’interface **“ISignalMonitor”**. De cette façon, notre proxy pourra répondre aux méthodes contenues dans l’interface **“ISignalMonitor”**, sans que l’objet n’en soit notifié et n’ai besoin de l’implémenter. 
La redéfinition de la méthode **“InvocationHandler::invoke()”** dans notre handler permet d’intercepter les méthodes de **“ISignalMonitor”**. Ainsi, le monitor peut agir sur l’objet encapsulé.

L’encapsulation se fait via **“ExtensionLoader::getProxyFor()”**, méthode qui va donc encapsuler l’objet passé en paramètre et retourner un objet qui est proxy. 

#### Partie moniteur
  Le monitor est une partie optionnel de notre **“ExtensionLoader”**. Il permet à un utilisateur de type admin d’avoir accès à des fonctionnalités supplémentaires sur les plugins en cours d'exécution. Dans notre implémentation, nous offrons la possibilité de pouvoir kill ou remplacer un plugin en cours d'exécution. L’utilisateur peut effectuer cette action grâce à une interface graphique listant les plugins d’une application en cours, ainsi que les action possible sur ces derniers (notamment proposer les plugins possible pour un remplacement).
  
Nous avons pensé à plusieurs évolutions pour le monitor. On pourrait, dans un premier temps, ajouter des fonctionnalités telles que load un plugin non chargé dans l’application. Ensuite, le monitor pourrait remplacer un modify (cela n’a pas été fait par manque de temps). Il serait possible de le faire en allant récupérer les **“IModify”** lancés et changer son implémentation en chargeant le nouveau **“IModify”** par la méthode load de notre **“ExensionLoader”**. Il faut également mettre à jour l’affichage et ajouter ce nouveau modify au **“TimeManager”**.


## ExtensionLoader (utilisation)

Le **“ExtensionLoader”** fournit à une application le chargement dynamique de plugin via la méthode **“Load()”**. Il permet aussi d’avoir un listing de plugins qui respectent des contraintes. 

**“ExtensionLoader”** agit aussi comme Main. C’est dans ce main que sont lancées toutes les applications qui ont pour tag **“autorun”**. Une fois ces applications lancées, **“ExtensionLoader”** applique de façon cyclique la méthode **“IApp::run()”** a chaque application, tour à tour. A noter que les applications sont lancées dans le même Thread, un axe d’évolution consisterait à les lancer dans des Threads séparés. 
Nous avons fait le choix d’en faire un singleton, car nous n’avons besoin que d’un seul loader.


### Fichier configuration

Le fichier de configuration est un listing de tous les plugins enregistrés. Il suit la structure suivante:

	"Plugins": {
   	"Affichage1": {
   		 "Name": "extension.Displayer",
   		 "Tags":  ["IDisplayer", "Fr"],
   		 "Killable": true
   	 	}
	[…]
    }

Il se compose du bloc “Plugins”. C’est dans ce bloc que chaque plugins doit être spécifié. Un plugin est un bloc qui se compose des champs suivants :

**Name :** le chemin d’accès dans eclipse du plugin

**Tags :** une liste de String qui constituent les tags associés à un plugin.

**Killable :** un booléen permettant de savoir si un plugin est killable en cours d'exécution, afin d'éviter des crashs éventuels.


## Manuel utilisateur

### Ajout d’un plugin

Premièrement, les besoin de notre application sont spécifiés par les interfaces présentes dans le packages “client”. 
La création d’un plugin revient donc à l’implémentation d’une classe qui respecte la structure définie par une des interfaces.

Prenons un exemple. Un développeur veut adapter l’application pour les utilisateurs daltoniens. Son but est donc d’implémenter un nouveau **“Displayer”**.
Pour ce faire, le développeur doit se référer à la classe **“IDisplayer”**. Sa nouvelle classe, appelons la “DisplayerColorBlind”, devra être placée dans le package “extensions” et devra implémenter l’interface **“IDisplayer”**, ainsi que toutes les méthodes de cette dernière, dans lesquelles il pourra spécifier le comportement souhaité.

Par la suite, il faut aussi modifier le fichier JSON **“PluginsDescription”**, qui est le fichier de configuration de l’application. Il faut en effet ajouter le “bloc” correspondant au nouveau plugin (comme expliqué dans la section Description de la plateforme).

### Utilisation du framework

  Tout d’abord, pour utiliser le framework développé dans notre application, il faudra respecter la “position” des classes et des interfaces dans les packages.

La première chose à faire pour un développeur est de spécifier une liste de contraintes. Pour ce faire il faut déclarer un objet de type **“Constraint”**, et utiliser la méthode **“setConstraints”** qui prends en paramètre une liste de **“String”**.

Par la suite on utilise notre **”ExtensionLoader”** et la méthode **“getExtension”** qui prends en paramètre l’objet **“Constraint”** paramétré précédemment. La méthode getExtension renverra une liste de **“DescriptionPlugin”** correspondant aux Plugins qui respectent les contraintes spécifiées.

On peut ensuite sélectionner le plugin voulu dans cette liste, et le charger en le donnant en paramètre à la méthode **“load”** de notre **”ExtensionLoader”**.

NB : **”ExtensionLoader”** est un **“singleton”**, c’est à dire qu’il faudra appliquer la méthode getInstance() a chaque fois que l’on veut appeler une méthode de l’**”ExtensionLoader”**.


Exemple : 

	  IDisplayer displayer;

    Constraint c = new Constraint();
   	List<String> tags = new ArrayList<String>();
    List<DescriptionPlugin> l;

    tags.add(“IDisplayer”);
    c.setConstraints(tags);

    l = ExtensionLoader.getInstance().getExtension(c);	
    
    // Séléctionne le premier IDisplayer de la liste (Choix par défaut)
	  displayer = (IDisplayer) ExtensionLoader.getInstance().load(l.get(0));
