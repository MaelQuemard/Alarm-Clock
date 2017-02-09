package client;

import extension.Displayer;
import extension.EarthManager;
import extension.ModifyIncH;
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
		
		timeManager = (ITimeManager) Plateform.loadExtension("configManager.txt", ITimeManager.class);
		modify = (IModify) Plateform.loadExtension("configModifier.txt", IModify.class);
		displayer = (IDisplayer) Plateform.loadExtension("configDisplayer.txt", IDisplayer.class);
		
		modify.setITimeManager(timeManager);
		
		displayer.setCore(timeManager);
		
		timeManager.setAffichage(displayer);
		timeManager.addModifier(modify);
		timeManager.addModifiers();
		timeManager.updateAff();
		
	/*	ITimeManager e = new  EarthManager();
		IModify m = new ModifyIncH();
		IDisplayer a =  new Displayer();
		
		a.setCore(e);
		
		e.setAffichage(a);
		e.addModifier(m);
		e.addModifiers();
		e.updateAff();*/
	}

}
