package framework;

import java.io.FileReader;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import client.IApp;

/**
 * @author PO Gillier - K Gomes - E Jain - E Ngamije - M Quémard - S Vuylsteke
 * @version 2.0
 * Class {@link ExtensionLoader} singleton permettant de load les plugins
 */
public class ExtensionLoader {

	private static ExtensionLoader instance;
	private static IMonitor monitor;
	private List<IApp> listApp;
	
	/**
	 * Constructeur par défaut
	 */
	protected ExtensionLoader() {
		listApp = new ArrayList<IApp>();
	}
	
	/**
	 * Methode permettant de retourner l'instance courante de ExtensionLoader
	 * @return instance
	 */
	public static ExtensionLoader getInstance() {
		if (instance == null) {
			instance = new ExtensionLoader();
			//instance = DecoratorLog.getInstance();
		}
		return instance;
	}

	/**
	 * Methode permettant d'obtenir la liste des extensions respectant les {@link Constraint}
	 * @param c1 contraintes des extensions
	 * @return liste d'extensions
	 */
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
	
	/**
	 * Methode permettant de charger le plugin correspondant à {@link DescriptionPlugin}
	 * @param p description du plugin à charger
	 * @return instance du plugin
	 */
	public Object load(DescriptionPlugin p)
	{
		Object o = null;
		// generate object with one of the item list	
		try {
			Class<?> cl = Class.forName(p.getNom());
			// choose the target plugin in the list	
			//o = cl.newInstance();
			
			if (p.getTags().contains("IApp")) {
				o = getProxyFor(cl.newInstance());
			} else {
				o = cl.newInstance();
			}
			//
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			System.out.println(e.getMessage());
		}
		return o;
	}
	
	/**
	 * Methode permettant de parser le fichier de configuration JSON contenant la liste de tous les plugins
	 * @return liste de {@link DescriptionPlugin}
	 */
	@SuppressWarnings("unchecked")
	public List<DescriptionPlugin> getListPlugins()
	{
		List<DescriptionPlugin> plugins = new ArrayList<DescriptionPlugin>();
		
		JSONParser parser = new JSONParser();
		 
        try {
            Object obj = parser.parse(new FileReader("PluginsDescription.json"));
            
            JSONObject jsonPlugins = (JSONObject) ((JSONObject) obj).get("Plugins");
            Iterator<JSONObject> iteratorPlugins = jsonPlugins.values().iterator();
            
            while (iteratorPlugins.hasNext()) {
            	JSONObject plugin = iteratorPlugins.next();
            	DescriptionPlugin descriptionPlugin = new DescriptionPlugin((String) plugin.get("Name"), (List<String>) plugin.get("Tags"), (boolean) plugin.get("Killable"));;
            	plugins.add(descriptionPlugin);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		return plugins;
	}
	
	/**
	 * Methode permettant de lancer tous les plugins ayant le tag "IApp"
	 */
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
			System.out.println("   autorun de ->"+d.toString());
			Object obj = instance.load(d);
			listApp.add((IApp) obj);
			System.out.println("   sucess autorun");
		}
		
		//instance.runApp();
	}
	
	/**
	 * Methode permettant d'exécuter un cycle de vie d'une application chargée lors de l'autorun
	 */
	private void runApp() {
		for (IApp a : listApp) {
			System.out.println("RUNAPP : " + a.toString());
			a.run();
		}
	}
	
	/** getter de ListApp
	 * @return
	 */
	public List<IApp> getListApp()
	{
		return this.listApp;
	}
	
	public static void main(String[] args) {
		
		ExtensionLoader.getInstance().autorun();
		
		List<String> tags = new ArrayList<String>();
		List<DescriptionPlugin> l;
		Constraint c1 = new Constraint();
		

		// Chargement du monitor
		IMonitor monitor;
		tags.add("IMonitor");
		c1.setConstraints(tags);
		l = ExtensionLoader.getInstance().getExtension(c1);
		monitor = (IMonitor) ExtensionLoader.getInstance().load(l.get(0)); // FIXME with d
		ExtensionLoader.getInstance().setMonitor(monitor);
		((ISignalMonitor) ExtensionLoader.getInstance().getListApp().get(0)).turnMonitor(monitor);
		
		// run of 5 cycle of allPlugin*/
		int i = 0;
		while(i<5)
		{
			System.out.println("ExtensionLoader::main -- actual run : " +i);
			ExtensionLoader.getInstance().runApp();
			++i;
		}
		((ISignalMonitor) ExtensionLoader.getInstance().getListApp().get(0)).turnMonitor(monitor);
	//	monitor.test();
		i = 0;
		while(i<100)
		{
			ExtensionLoader.getInstance().runApp();
			++i;
		}		
	}
	
	/**
	 * Methode permettant de créer un proxy pour sur un objet
	 * @param o Objet à encapsuler
	 * @return Le {@link Proxy} créé 
	 */
	public static Object getProxyFor(Object o) {
		System.out.println("ExtensionLoader::load()");
		System.out.println("	Mes interfaces sont les suivantes :");
		for( Class<?> c :  o.getClass().getInterfaces())
		{
			System.out.println("	 - " + c.getName());
		}
		return Proxy.newProxyInstance(
				o.getClass().getClassLoader(), 
				concat(o.getClass().getInterfaces(), ISignalMonitor.class), 
				new MonitorHandler(o)
			);
	}
	
	
	/**
	 * Methode permettant de concatener un tableau de Class<?> avec un nouvel element Class<?>
	 * @param oldIntefaces le tableau initial
	 * @param newInterface l'element a ajouter
	 * @return le nouveau tableau
	 */
	public static Class<?>[] concat(Class<?>[] oldInterfaces, Class<?> newInterface) {
		Class<?>[] interfaces = new Class<?>[oldInterfaces.length+1];
		int i = 0;
		for (Class<?> in : oldInterfaces) {
			System.out.println("Concat : "+in.getName());
			interfaces[i] = in;
			i++;
		}
		interfaces[interfaces.length-1] = newInterface;
		
		return interfaces;
	}
	
	public void setMonitor(IMonitor m)
	{
		monitor = m;
	}
	
	public IMonitor getMonitor()
	{
		return this.monitor;
	}
}
