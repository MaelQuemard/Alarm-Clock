package extension;

import java.util.ArrayList;
import java.util.List;
import extension.Displayer;
import extension.EarthManager;
import extension.ModifyIncH;
import extension.EarthTime;
import client.IApp;
import client.IDisplayer;
import client.IModify;
import client.ITimeManager;
import framework.Constraint;
import framework.DescriptionPlugin;
import framework.ExtensionLoader;

public class AppAlarm implements IApp {
	//TODO: Ajouter état configuration (Gerer dans le main)

	private ITimeManager timeManager;
	//TODO : Creer une liste de modifieur, modifier le Handler pour gerer l'ajout dans liste (verification du type de l'attribut, si list alors ...)
	private IModify modify;
	private IDisplayer displayer;
	private String name;
	
	public AppAlarm() {
		//TODO : Dynamisme avec loadBean ou autre 
		name = "AppAlarm";
		Constraint c1 = new Constraint();
		List<String> tags = new ArrayList<String>();
		List<DescriptionPlugin> l;
		DescriptionPlugin d;
		
		// Chargement de l'affichage
				
		tags.add("IDisplayer");
		c1.setConstraints(tags);
		
		l = ExtensionLoader.getInstance().getExtension(c1); 
		//d = ???
		
		displayer = (IDisplayer) ExtensionLoader.getInstance().load(l.get(0)); // FIXME with d
		
		// Chargement du TimeManager
		tags.clear();
		tags.add("ITimeManager");
		c1.setConstraints(tags);
		// get list descriptionPlugin
		l = ExtensionLoader.getInstance().getExtension(c1); 
		// affichage des choix possible
		// TODO get it with afficher
		// d = ???
		//loading du plugin cible
		timeManager = (ITimeManager) ExtensionLoader.getInstance().load(l.get(0)); // FIXME with d
		
		// Chargement du modifieur
		tags.clear();
		tags.add("IModify");
		c1.setConstraints(tags);
		l = ExtensionLoader.getInstance().getExtension(c1); 
		//d = ???
		modify = (IModify) ExtensionLoader.getInstance().load(l.get(0)); //FIXME with d
		IModify modify2 = (IModify) ExtensionLoader.getInstance().load(l.get(1)); //FIXME with d
		modify.setITimeManager(timeManager);
		modify2.setITimeManager(timeManager);
		
		displayer.setCore(timeManager);
		
		timeManager.setAffichage(displayer);
		timeManager.addModifier(modify);
		timeManager.addModifier(modify2);
		timeManager.addModifiers();
		timeManager.updateAff();
		
		//TODO: Passer en IAlarmManager et utiliser loader
		// Test fonctionnement alarme
		AlarmManager al = new AlarmManager();
		al.addAlarm(timeManager.getTime().getActualTime()/1000,new EarthTime(12,0,0,true),"Simple"); // A implementer sur l'interface graphique
		al.addAlarm(timeManager.getTime().getActualTime()/1000,new EarthTime(15,0,0,true),"Simple");
		al.addAlarm(timeManager.getTime().getActualTime()/1000,new EarthTime(12,36,0,true),"Simple");
		al.addAlarm(timeManager.getTime().getActualTime()/1000,new EarthTime(8,0,10,true),"Simple");
	}

	@Override
	public void run() {
		timeManager.getTime().actualizeTime();
		timeManager.updateAff();
		try {
			Thread.sleep(timeManager.getRefresh());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public ITimeManager getTimeManager() {
		return timeManager;
	}

	public void setTimeManager(ITimeManager timeManager) {
		System.out.println("APPALARM TIME MANAGER : "+timeManager);
		this.timeManager = timeManager;
		this.timeManager.setAffichage(this.displayer);
	}

	public IModify getModify() {
		return modify;
	}

	public void setModify(IModify modify) {
		this.modify = modify;
	}

	public IDisplayer getDisplayer() {
		return displayer;
	}

	public void setDisplayer(IDisplayer displayer) {
		this.displayer = displayer;
	}

	@Override
	public String getName() {
		return name;
	}
}
