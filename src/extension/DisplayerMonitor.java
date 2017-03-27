package extension;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;

import client.IApp;
import framework.ExtensionLoader;
import framework.IMonitor;
import framework.ISignalMonitor;

public class DisplayerMonitor {

	private JFrame frame;
	private JPanel panel;
	private JPanel panel2;
	
	public DisplayerMonitor()
	{
		frame = new JFrame();
		panel = new JPanel();
		panel2 = new JPanel();
		panel.add(panel2);
		frame.add(panel);
		frame.setSize(400, 400);
		frame.setVisible(true);
	}
	
	public void doStuff()
	{
		for(IApp i : ExtensionLoader.getInstance().getListApp())
		{
			System.out.println(i.getName());
			//panel.add(new JLabel(i.getName()));
			
			GridLayout gl = new GridLayout(0, 2);
			JPanel p = new JPanel();
			p.setLayout(gl);
			p.add(new JLabel(i.getName()));
			
			JButton button = new JButton("Voir sous plugins");
			button.addActionListener(new MyActionListener(i, this, p));
			p.add(button);
			panel2.add(p);
		}
		frame.setVisible(true);
	}
	
	public void displaySubPlugin(IApp i, JPanel p) {
		List<String> subPlugins = null;
		for (Object appRunning : ExtensionLoader.getInstance().getListApp()) {
			System.out.println("DisplaySubPlugin:: i : " + i.toString() + "appRunnig : "+appRunning);
			if (i == appRunning) {
				System.out.println("DisplaySubPlugin");
				subPlugins = ((ISignalMonitor) appRunning).getAttributsPlugin();
			}
		}
		for ( String s : subPlugins ) {
			JButton plugin = new JButton(s);
			plugin.addActionListener(new MyActionListener(i, this, p));
			p.add(plugin);
		}
		frame.setVisible(true);
	}
}
