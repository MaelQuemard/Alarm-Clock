package client;

import java.util.ArrayList;
import java.util.List;

import extension.Displayer;
import extension.EarthManager;
import extension.ModifyIncH;
import framework.Constraint;
import framework.DescriptionPlugin;
import framework.ExtensionLoader;
import framework.Plateform;

public class main {
	
	public static void main(String[] args) {
		
		ITimeManager timeManager;
		IModify modify;
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
		
		
		
		modify.setITimeManager(timeManager);
		
		displayer.setCore(timeManager);
		
		timeManager.setAffichage(displayer);
		timeManager.addModifier(modify);
		timeManager.addModifiers();
		timeManager.updateAff();
		
		// Test fonctionnement alarme
		AlarmManager al = new AlarmManager();
		al.addAlarm(timeManager.getTime().getActualTime()/1000,new Time(20,0,0,true),"Simple"); // A implementer sur l'interface graphique
		al.addAlarm(timeManager.getTime().getActualTime()/1000,new Time(22,0,0,true),"Simple");
		al.addAlarm(timeManager.getTime().getActualTime()/1000,new Time(23,0,0,true),"Simple");
		al.addAlarm(timeManager.getTime().getActualTime()/1000,new Time(12,0,10,true),"Simple");

		while (true) 
		{
			timeManager.getTime().actualizeTime();
			
			if (al.shouldRing(timeManager.getTime().getActualTime()/1000))
			{
				al.ring();
			}
			
			timeManager.updateAff();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
