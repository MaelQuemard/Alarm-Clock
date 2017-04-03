package extension;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
	
	/* (non-Javadoc)
	 * @see framework.IMonitor#kill(client.IApp, java.lang.String)
	 */
	public void kill(IApp appRunning, String subPluginToKill) {
		((ISignalMonitor) appRunning).kill(subPluginToKill);
	}
	
	/* (non-Javadoc)
	 * @see framework.IMonitor#kill(client.IApp, java.lang.String, java.lang.String)
	 */
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
	public Map<String, Class<?>> getSubPlugin(IApp appRunning)
	{
		return ((ISignalMonitor) appRunning).getAttributsPlugin();
	}
}
