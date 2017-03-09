package framework;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ExtensionLoader {

	private static ExtensionLoader instance;
	
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
}
