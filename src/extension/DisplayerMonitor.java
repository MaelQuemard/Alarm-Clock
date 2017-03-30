package extension;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;

import client.IApp;
import framework.Constraint;
import framework.DescriptionPlugin;
import framework.ExtensionLoader;
import framework.IMonitor;
import framework.ISignalMonitor;

public class DisplayerMonitor {

	private JFrame frame;
	private BoxLayout bxLayoutFrame;
	
	public DisplayerMonitor()
	{
		frame = new JFrame();
		bxLayoutFrame = new BoxLayout(frame, 0);
		frame.setSize(800, 360);
	}
	
	public void doStuff()
	{
		for(IApp i : ExtensionLoader.getInstance().getListApp())
		{
			System.out.println(i.getName());
			JPanel panelApp = new JPanel();;
			GridLayout gdLayoutApp = new GridLayout(2, 0);
			panelApp.setLayout(gdLayoutApp);
			JPanel header = new JPanel();
			header.add(new JLabel(i.getName()));
			panelApp.add(header);
			
			JPanel panelSubPlugins = new JPanel();
			
			List<String> subPlugins = null;
			for (Object appRunning : ExtensionLoader.getInstance().getListApp()) {
				System.out.println("DisplaySubPlugin:: i : " + i.toString() + "appRunnig : "+appRunning);
				if (i == appRunning) {
					System.out.println("DisplaySubPlugin");
					subPlugins = ((ISignalMonitor) appRunning).getAttributsPlugin();
				}
			}

			List<String> tags = new ArrayList<String>();
			List<DescriptionPlugin> l;
			Constraint c1 = new Constraint();
			
			GridLayout gdLayoutSubPlugin = new GridLayout(subPlugins.size(), 3);
			panelSubPlugins.setLayout(gdLayoutSubPlugin);
			
			for ( String s : subPlugins ) {
				
				tags.add("I" + s);
				c1.setConstraints(tags);
				l = ExtensionLoader.getInstance().getExtension(c1);
				JLabel labelSubPlugin = new JLabel(s);
				JButton killPlugin = new JButton("kill");
				JComboBox<String> cbPlugin = new JComboBox<String>();
				
				for (DescriptionPlugin dp : l) {
					cbPlugin.addItem(dp.getNom());
				}
				
				killPlugin.addActionListener(new ActionListenerMonitor(i, this, s));
				panelSubPlugins.add(labelSubPlugin);
				panelSubPlugins.add(killPlugin);
				panelSubPlugins.add(cbPlugin);
			}
			panelApp.add(panelSubPlugins);
			frame.add(panelApp);
		}
		frame.setVisible(true);
	}
}
