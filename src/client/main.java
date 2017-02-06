package client;

import extension.Displayer;
import extension.EarthManager;
import extension.ModifyIncH;
import framework.IDisplayer;
import framework.IModify;
import framework.ITimeManager;

public class main {

	public static void main(String[] args) {
		
		//TODO : Dynamisme avec loadBean ou autre 
		ITimeManager e = new  EarthManager();
		IModify m = new ModifyIncH();
		IDisplayer a =  new Displayer();
		
		a.setCore(e);
		
		e.setAffichage(a);
		e.addModifier(m);
		e.addModifiers();
		e.updateAff();
	}

}
