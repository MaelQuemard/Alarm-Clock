package framework;

import java.util.List;

public class DecoratorLog extends ExtensionLoader{

	private static ExtensionLoader instance;
	private DecoratorLog(){}
	
	public static ExtensionLoader getInstance() {
		if (instance == null) {
			instance = new DecoratorLog();
		}
		return instance;
	}
	
	public List<DescriptionPlugin> getExtension(Constraint c1) {
		List<DescriptionPlugin> listPluginsValid;
		listPluginsValid = super.getExtension(c1); 
		// My log system
		System.out.println("/* ------------------------------------------------------- */");
		System.out.println("Les plugins suivants suivent les contraintes fournies");
		System.out.println("Contraintes : " + c1.toString());
		System.out.println("Plugins : " + listPluginsValid.toString());
		System.out.println("/* ------------------------------------------------------- */");

		return listPluginsValid;		
	}
	
	public Object load(DescriptionPlugin p)
	{
		Object o = null;
		o = super.load(p);
		System.out.println("Le plugin suivant a été chargé : " + p.getNom());
		return o;
	}
	
}
