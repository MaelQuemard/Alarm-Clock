package extension;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import client.IApp;
import framework.ExtensionLoader;

public class MyActionListener implements ActionListener {

	private IApp i;
	private DisplayerMonitor dm;
	private JPanel p;
	
	public MyActionListener(IApp i, DisplayerMonitor dm, JPanel p) {
		this.i = i;
		this.dm = dm;
		this.p = p;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if (command.equals("Voir sous plugins")) {
			dm.displaySubPlugin(i, p);
		} else {
			ExtensionLoader.getInstance().getMonitor().kill(i, command);
		}
	}

}
