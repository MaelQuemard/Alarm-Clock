package client;

import java.util.ArrayList;
import java.util.List;

import extension.Displayer;
import extension.EarthManager;
import extension.ModifyIncH;
import framework.Constraint;
import framework.ExtensionLoader;
import framework.IDisplayer;
import framework.IModify;
import framework.ITimeManager;
import framework.Plateform;

public class main {
	
	public static void main(String[] args) {
		
		ITimeManager timeManager;
		IModify modify;
		IDisplayer displayer;
		//TODO : Dynamisme avec loadBean ou autre 
		
		/*timeManager = (ITimeManager) Plateform.loadExtension("configManager.txt", ITimeManager.class);
		modify = (IModify) Plateform.loadExtension("configModifier.txt", IModify.class);
		displayer = (IDisplayer) Plateform.loadExtension("configDisplayer.txt", IDisplayer.class);
		*/
		Constraint c1 = new Constraint();
		
		// Chargement du TimeManager
		List<String> tags = new ArrayList<String>();
		tags.add("ITimeManager");
		c1.setConstraints(tags);
		timeManager = (ITimeManager) ExtensionLoader.getInstance().getExtension(c1);
		
		// Chargement du modifieur
		tags.clear();
		tags.add("IModify");
		c1.setConstraints(tags);
		modify = (IModify) ExtensionLoader.getInstance().getExtension(c1);
		
		// Chargement de l'affichage
		tags.clear();
		tags.add("IDisplayer");
		c1.setConstraints(tags);
		displayer = (IDisplayer) ExtensionLoader.getInstance().getExtension(c1);
		
		modify.setITimeManager(timeManager);
		
		displayer.setCore(timeManager);
		
		timeManager.setAffichage(displayer);
		timeManager.addModifier(modify);
		timeManager.addModifiers();
		timeManager.updateAff();
		
		while (true) {
			timeManager.getTime().actualizeTime();
			timeManager.updateAff();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
