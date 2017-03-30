package client;

import java.util.ArrayList;
import java.util.List;

import extension.Displayer;
import extension.EarthManager;
import extension.ModifyIncH;
import extension.EarthTime;
import framework.Constraint;
import framework.DescriptionPlugin;
import framework.ExtensionLoader;
import framework.Plateform;

public class main {
	
	public static void main(String[] args) {
		
		ITimeManager timeManager;
		IModify modify,modify2,modify3,modify4;
		IDisplayer displayer;
		//TODO : Dynamisme avec loadBean ou autre 
		
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
		modify2 = (IModify) ExtensionLoader.getInstance().load(l.get(1)); //FIXME with d
		modify3 = (IModify) ExtensionLoader.getInstance().load(l.get(2)); //FIXME with d
		modify4 = (IModify) ExtensionLoader.getInstance().load(l.get(3)); //FIXME with d
		
		
		
		modify.setITimeManager(timeManager);
		modify2.setITimeManager(timeManager);
		modify3.setITimeManager(timeManager);
		modify4.setITimeManager(timeManager);
		
		displayer.setCore(timeManager);
		
		timeManager.setAffichage(displayer);
		timeManager.addModifier(modify);
		timeManager.addModifier(modify2);
		timeManager.addModifier(modify3);
		timeManager.addModifier(modify4);
		timeManager.addModifiers();
		timeManager.updateAff();
		
		// Test fonctionnement alarme
		AlarmManager al = new AlarmManager();
		al.addAlarm(timeManager.getTime().getActualTime()/1000,new EarthTime(12,0,0,true),"Simple"); // A implementer sur l'interface graphique
		al.addAlarm(timeManager.getTime().getActualTime()/1000,new EarthTime(15,0,0,true),"Simple");
		al.addAlarm(timeManager.getTime().getActualTime()/1000,new EarthTime(12,36,0,true),"Simple");
		al.addAlarm(timeManager.getTime().getActualTime()/1000,new EarthTime(8,0,10,true),"Simple");

		while (true) 
		{
			timeManager.getTime().actualizeTime();
			
			if (al.shouldRing(timeManager.getTime().getActualTime()/423))
			{
				al.ring();
			}
			
			timeManager.updateAff();
			try {
				Thread.sleep(423);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
