package extension;

import java.util.ArrayList;
import java.util.List;

import client.IApp;
import client.IDisplayer;
import framework.Constraint;
import framework.DescriptionPlugin;
import framework.ExtensionLoader;
import framework.IMonitor;

public class Monitor implements IMonitor {

	List<IApp> listAutorunApp;
	List<DescriptionPlugin> listLoadPlugins;
	
	public Monitor()
	{
		listAutorunApp = new ArrayList<IApp>();
		listLoadPlugins = new ArrayList<DescriptionPlugin>();
	}
	@Override
	public void autorun() {
		
		List<String> tags = new ArrayList<String>();
		List<DescriptionPlugin> l;
		Constraint c1 = new Constraint();
		
		// Chargement de l'affichage
				
		tags.add("autorun");
		c1.setConstraints(tags);
		
		l = ExtensionLoader.getInstance().getExtension(c1); 
		for(DescriptionPlugin d : l)
		{
			listAutorunApp.add((IApp) ExtensionLoader.getInstance().load(d));
		}
			
	}

	@Override
	public void runApp() {
		for(IApp a : listAutorunApp)
		{
			a.run();
		}
		
	}

	@Override
	public void kill(int i) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void killAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void isLoad(DescriptionPlugin p) {
		System.out.println(p.toString());
		
	}

}
