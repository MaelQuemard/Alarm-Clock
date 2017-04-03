package extension;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import client.IApp;
import framework.ExtensionLoader;

public class ActionListenerMonitor implements ActionListener {

	private IApp i;
	private DisplayerMonitor dm;
	private String namePlugin;
	private String nameSubPlugin;
	
	/** constructeur de ActionListenerMonitor
	 * @param i IApp
	 * @param dm DisplayerMonitor
	 * @param namePlugin String
	 * @param nameSubPlugin String
	 */
	public ActionListenerMonitor(IApp i, DisplayerMonitor dm, String namePlugin, String nameSubPlugin) {
		this.namePlugin = namePlugin;
		this.nameSubPlugin = nameSubPlugin;
		this.i = i;
		this.dm = dm;
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if (nameSubPlugin == null) {
			ExtensionLoader.getInstance().getMonitor().kill(i, namePlugin);
			dm.doStuff();
		} else {
			ExtensionLoader.getInstance().getMonitor().kill(i, namePlugin, nameSubPlugin);
			dm.doStuff();
		}
	}

}
