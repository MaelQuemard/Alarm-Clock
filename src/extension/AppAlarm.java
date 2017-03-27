package extension;

import java.util.ArrayList;
import java.util.List;

import client.IApp;
import client.IDisplayer;
import client.IModify;
import client.ITimeManager;
import framework.Constraint;
import framework.DescriptionPlugin;
import framework.ExtensionLoader;
//import framework.Test;

public class AppAlarm implements IApp {

	private ITimeManager timeManager;
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
			
		modify.setITimeManager(timeManager);
		
		displayer.setCore(timeManager);
		
		timeManager.setAffichage(displayer);
		timeManager.addModifier(modify);
		timeManager.addModifiers();
		timeManager.updateAff();
	}

	@Override
	public void run() {
		timeManager.getTime().actualizeTime();
		timeManager.updateAff();
		try {
			Thread.sleep(1000);
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
