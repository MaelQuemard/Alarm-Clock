package extension;

import java.util.ArrayList;
import java.util.List;

import client.IApp;
import client.IDisplayer;
import framework.Constraint;
import framework.DescriptionPlugin;
import framework.ExtensionLoader;
import framework.IMonitor;
import framework.ISignalMonitor;

public class Monitor implements IMonitor {
	
	public Monitor()
	{
		
	}
	
	public void writeLog(String log) {
		System.out.println("MONITOR : " + log);
	}
	
	public void test() {
		List<String> tags = new ArrayList<String>();
		Constraint c1 = new Constraint();
		List<String> listAttribut = ((ISignalMonitor) ExtensionLoader.getInstance().getListApp().get(0))
				.getAttributsPlugin();
		System.out.println(listAttribut.toString());
		tags.clear();
		tags.add("I"+listAttribut.get(0));
		c1.setConstraints(tags);
		((ISignalMonitor) ExtensionLoader.getInstance().getListApp().get(0)).modifyAttribut(
				listAttribut.get(0), 
				ExtensionLoader.getInstance().load(ExtensionLoader.getInstance().getExtension(c1).get(0)));
		this.kill(ExtensionLoader.getInstance().getListApp().get(0), "TimeManager");
	}
	
	/**
	 * Cette méthode permet de kill plugin associé a une application
	 * @param appRunning, application ou se trouve le plugin a kill
	 * @param subPluginToKill plugin a kill
	 */
	public void kill(IApp appRunning, String subPluginToKill) {
		((ISignalMonitor) appRunning).kill(subPluginToKill);
	}

}
