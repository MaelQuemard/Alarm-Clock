package framework;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import client.IApp;

public class ExtensionLoader {

	private static ExtensionLoader instance;
	private static IMonitor monitor;
	private List<IApp> listApp;
	
	protected ExtensionLoader() {}
	
	public static ExtensionLoader getInstance() {
		if (instance == null) {
			instance = DecoratorLog.getInstance();
		}
		return instance;
	}

	public List<DescriptionPlugin> getExtension(Constraint c1) {
		// get listPlugin (respectent contraintes)
		
		List<DescriptionPlugin> listPlugins = this.getListPlugins();
		List<DescriptionPlugin> listPluginsValid = new ArrayList<DescriptionPlugin>();
		for(DescriptionPlugin d : listPlugins) {
			if (d.getTags().containsAll(c1.getConstraints()))
				listPluginsValid.add(d);
		}
		return listPluginsValid;		
	}
	
	public Object load(DescriptionPlugin p)
	{
		Object o = null;
		// generate object with one of the item list	
		try {
			Class<?> cl = Class.forName(p.getNom());
			// choose the target plugin in the list	
			o = cl.newInstance();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			System.out.println(e.getMessage());
		}
		if(monitor != null) monitor.isLoad(p);
		return o;
	}
	
	@SuppressWarnings("unchecked")
	private List<DescriptionPlugin> getListPlugins()
	{
		List<DescriptionPlugin> plugins = new ArrayList<DescriptionPlugin>();
		
		JSONParser parser = new JSONParser();
		 
        try {
            Object obj = parser.parse(new FileReader("PluginsDescription.json"));
            
            JSONObject jsonPlugins = (JSONObject) ((JSONObject) obj).get("Plugins");
            Iterator<JSONObject> iteratorPlugins = jsonPlugins.values().iterator();
            
            while (iteratorPlugins.hasNext()) {
            	JSONObject plugin = iteratorPlugins.next();
            	DescriptionPlugin descriptionPlugin = new DescriptionPlugin((String) plugin.get("Name"), (List<String>) plugin.get("Tags"));;
            	plugins.add(descriptionPlugin);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		return plugins;
	}
	
	private void autorun() {
		List<String> tags = new ArrayList<String>();
		List<DescriptionPlugin> l;
		Constraint c1 = new Constraint();
		
		// Chargement de l'affichage
				
		tags.add("IApp");
		c1.setConstraints(tags);
		
		l = ExtensionLoader.getInstance().getExtension(c1); 
		System.out.println("DESCRIPTION    : " + l.toString());
		for(DescriptionPlugin d : l)
		{
			listApp.add((IApp) instance.load(d));
		}
		
		instance.runApp();
	}
	
	private void runApp() {
		for (IApp a : listApp) {
			a.run();
		}
	}
	
	public static void main(String[] args) {
		
		ExtensionLoader.getInstance().autorun();
		
		IMonitor monitor;
		
		List<String> tags = new ArrayList<String>();
		List<DescriptionPlugin> l;
		Constraint c1 = new Constraint();
		
		// Chargement du monitor
		tags.add("IMonitor");
		c1.setConstraints(tags);
		l = ExtensionLoader.getInstance().getExtension(c1);
		monitor = (IMonitor) ExtensionLoader.getInstance().load(l.get(0)); // FIXME with d
		
		// execution of autorun
		//monitor.autorun();
		
		int i = 0;
		
		// run of 5 cycle of allPlugin
		/*while(i<5)
		{
			monitor.runApp();
			++i;
		}*/
		monitor.kill(1);
		monitor.killAll();
	}
}
