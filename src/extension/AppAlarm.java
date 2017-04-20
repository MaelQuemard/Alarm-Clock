package extension;

import java.util.ArrayList;
import java.util.List;
import client.IAlarmManager;
import client.IApp;
import client.IDisplayer;
import client.IModify;
import client.IPlugin;
import client.ITimeManager;
import framework.Constraint;
import framework.DescriptionPlugin;
import framework.ExtensionLoader;

public class AppAlarm implements IApp, IPlugin {

	private ITimeManager timeManager;
	private List<IModify> modify;
	private IDisplayer displayer;
	private String name;
	

	public DescriptionPlugin pluginChooseByUser;
	public List<DescriptionPlugin> pluginsChooseByUser;
	
	public IAlarmManager alarmManager;
	public boolean inConfig =true;
	
	/** Constructeur d'AppAlarm
	 * 
	 */
	public AppAlarm() {
		modify = new ArrayList<IModify>();
		name = "AppAlarm";
		Constraint c1 = new Constraint();
		List<String> tags = new ArrayList<String>();
		List<DescriptionPlugin> l;
		
		// Chargement de l'affichage
				
		tags.add("IDisplayer");
		c1.setConstraints(tags);
		
		l = ExtensionLoader.getInstance().getExtension(c1); 
		
		// TODO do it with a pop up, currently set by default
		displayer = (IDisplayer) ExtensionLoader.getInstance().load(l.get(0)); 
		
		// Chargement du TimeManager
		tags.clear();
		tags.add("ITimeManager");
		c1.setConstraints(tags);
		// get list descriptionPlugin
		l = ExtensionLoader.getInstance().getExtension(c1); 
		// affichage des choix possible
		
		inConfig = true;
		displayer.selectedPlugin(l,this);
		while(inConfig){try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}}
		
		//loading du plugin cible
		timeManager = (ITimeManager) ExtensionLoader.getInstance().load(pluginChooseByUser);
		
		// Chargement du modifieur
		tags.clear();
		tags.add("IModify");
		c1.setConstraints(tags);
		l = ExtensionLoader.getInstance().getExtension(c1); 
		
		setConfiguration();
		displayer.selectMultiPlugin(l, this);
		
		while(inConfig){try {
			System.out.println("ExtensionLoader::inConfig::Imodifier::test");
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}}
		
		for(DescriptionPlugin desc : pluginsChooseByUser)
		{
			modify.add((IModify)ExtensionLoader.getInstance().load(desc));
		}
		for (IModify im : modify) {
			im.setITimeManager(timeManager);
			timeManager.addModifier(im);
		}
		
		
		

		tags.clear();
		tags.add("IAlarmManager");
		c1.setConstraints(tags);
		l = ExtensionLoader.getInstance().getExtension(c1);
		System.out.println("AppAlarm::loadngAlarm"+l.toString());
		

		setConfiguration();
		displayer.selectedPlugin(l, this);
		
		while(inConfig){try {
			Thread.sleep(1000);
			} catch (InterruptedException e) {}
		}
		

		alarmManager = (IAlarmManager)ExtensionLoader.getInstance().load(pluginChooseByUser);
		displayer.setAlarm(alarmManager,timeManager);
		/* AlarmManager al = new AlarmManager();
		al.addAlarm(timeManager.getTime().getActualTime()/1000,new EarthTime(12,0,0,true),"Simple"); // A implementer sur l'interface graphique
		al.addAlarm(timeManager.getTime().getActualTime()/1000,new EarthTime(15,0,0,true),"Simple");
		al.addAlarm(timeManager.getTime().getActualTime()/1000,new EarthTime(12,36,0,true),"Simple");
		al.addAlarm(timeManager.getTime().getActualTime()/1000,new EarthTime(8,0,10,true),"Simple");*/
		
		

		displayer.setCore(timeManager);
		
		timeManager.setAffichage(displayer);
		timeManager.addModifiers();
		timeManager.updateAff();
	}

	/* (non-Javadoc)
	 * @see client.IApp#run()
	 */
	@Override
	public void run() {
		timeManager.getTime().actualizeTime();
		timeManager.updateAff();
		if(alarmManager!=null && alarmManager.shouldRing(timeManager.getTime().getActualTime() / timeManager.getRefresh()))
		{
			System.out.println("AppAlarm::run() __ RINGRINGRIGN");
			alarmManager.ring();
		}
		
		try {
			System.out.println("Sleep :: timeManager.getRefresh() : "+timeManager.getRefresh());
			Thread.sleep(timeManager.getRefresh());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see client.IApp#getTimeManager()
	 */
	@AnnotationPlugin(value=true)
	public ITimeManager getTimeManager() {
		return timeManager;
	}

	/* (non-Javadoc)
	 * @see client.IApp#setTimeManager(client.ITimeManager)
	 */
	@AnnotationPlugin(value=true)
	public void setTimeManager(ITimeManager timeManager) {
		for (IModify im : modify) {
			this.timeManager.removeModifier(im);
		}
		this.timeManager = timeManager;
		this.displayer.setCore(this.timeManager);
		this.timeManager.setAffichage(this.displayer);
		for (IModify im : modify) {
			im.setITimeManager(this.timeManager);
			this.timeManager.addModifier(im);
		}
		this.alarmManager.removeAllAlarm();
		this.timeManager.addModifiers();
		this.displayer.setAlarm(alarmManager, timeManager);
	}

	/* (non-Javadoc)
	 * @see client.IApp#getModify()
	 */
	@AnnotationPlugin(value=true)
	public List<IModify> getModify() {
		return modify;
	}
	
	@AnnotationPlugin(value=true)
	public void setModify(List<IModify> modify) {
		this.modify = modify;
	}

	/* (non-Javadoc)
	 * @see client.IApp#addModify(client.IModify)
	 */
	@AnnotationPlugin(value=true)
	public void addModify(IModify modify) {
		this.modify.add(modify);
	}
	
	/* (non-Javadoc)
	 * @see client.IApp#removeModify(client.IModify)
	 */
	@AnnotationPlugin(value=true)
	public void removeModify(IModify modify) {
		timeManager.removeModifier(modify);
		this.modify.remove(modify);
	}

	/* (non-Javadoc)
	 * @see client.IApp#getDisplayer()
	 */
	@AnnotationPlugin(value=true)
	public IDisplayer getDisplayer() {
		return displayer;
	}

	/* (non-Javadoc)
	 * @see client.IApp#setDisplayer(client.IDisplayer)
	 */
	@AnnotationPlugin(value=true)
	public void setDisplayer(IDisplayer displayer) {
		this.displayer.dispose();
		this.displayer = displayer;
		this.timeManager.setAffichage(displayer);
		this.displayer.setCore(timeManager);
		this.timeManager.addModifiers();
		this.displayer.setAlarm(alarmManager,timeManager);
	}

	/* (non-Javadoc)
	 * @see client.IApp#getName()
	 */
	@AnnotationPlugin(value=false)
	public String getName() {
		return name;
	}

	/* (non-Javadoc)
	 * @see client.IApp#setDescriptionPluginChooseByUser(framework.DescriptionPlugin)
	 */
	@AnnotationPlugin(value=false)
	public void setDescriptionPluginChooseByUser(DescriptionPlugin dp) {
		pluginChooseByUser = dp;
	}

	/* (non-Javadoc)
	 * @see client.IApp#setConfiguration()
	 */
	@AnnotationPlugin(value=false)
	public void setConfiguration() {
		inConfig = !inConfig;
	}

	@AnnotationPlugin(value=false)
	public List<DescriptionPlugin> getPluginsChooseByUser() {
		return pluginsChooseByUser;
	}

	@AnnotationPlugin(value=false)
	public void setPluginsChooseByUser(List<DescriptionPlugin> pluginsChooseByUser) {
		this.pluginsChooseByUser = pluginsChooseByUser;
	}

	@AnnotationPlugin(value=true)
	public IAlarmManager getAlarmManager() {
		return alarmManager;
	}

	@AnnotationPlugin(value=true)
	public void setAlarmManager(IAlarmManager alarm) {
		this.alarmManager = alarm;
	}
	
	
}
