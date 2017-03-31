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

/**
 * @author quemard
 *
 */
public class Monitor implements IMonitor {
	
	DisplayerMonitor aff;
	public Monitor()
	{
		aff = new DisplayerMonitor();
		aff.doStuff();
	}
	
	/* (non-Javadoc)
	 * @see framework.IMonitor#writeLog(java.lang.String)
	 */
	public void writeLog(String log) {
		System.out.println("MONITOR : " + log);
	}
	
	// TODO: Virer cette methode
	/* (non-Javadoc)
	 * @see framework.IMonitor#test()
	 */
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
		//this.kill(ExtensionLoader.getInstance().getListApp().get(0), "TimeManager");
	}
	
	/* (non-Javadoc)
	 * @see framework.IMonitor#kill(client.IApp, java.lang.String)
	 */
	public void kill(IApp appRunning, String subPluginToKill) {
		((ISignalMonitor) appRunning).kill(subPluginToKill);
	}
	
	public void kill(IApp appRunning, String subPluginToKill, String nameSubPlugin) {
		((ISignalMonitor) appRunning).kill(subPluginToKill, nameSubPlugin);
	}
	
	/* (non-Javadoc)
	 * @see framework.IMonitor#replace(client.IApp, java.lang.String, java.lang.String)
	 */
	public void replace(IApp appRunning, String subPluginToReplace, Object replacePlugin)
	{
		((ISignalMonitor) appRunning).modifyAttribut(subPluginToReplace, replacePlugin);
	}
	
	/* (non-Javadoc)
	 * @see framework.IMonitor#getSubPlugin()
	 */
	public List<String> getSubPlugin(IApp appRunning)
	{
		return ((ISignalMonitor) appRunning).getAttributsPlugin();
	}
}
