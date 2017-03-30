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
	
	public ActionListenerMonitor(IApp i, DisplayerMonitor dm, String s) {
		this.namePlugin = s;
		this.i = i;
		this.dm = dm;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		ExtensionLoader.getInstance().getMonitor().kill(i, namePlugin);
	}

}
