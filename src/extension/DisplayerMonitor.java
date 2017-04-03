package extension;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;

import client.IApp;
import client.IModify;
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
		bxLayoutFrame = new BoxLayout(frame, BoxLayout.Y_AXIS);
		frame.setSize(800, 360);
	}
	
	public void doStuff()
	{
		int j = 0;
		System.out.println("DisplayerMonitor::ListAppRunning : "+ExtensionLoader.getInstance().getListApp().toString());
		for(IApp i : ExtensionLoader.getInstance().getListApp())
		{
			++j;
			JPanel panelApp = new JPanel();;
			GridLayout gdLayoutApp = new GridLayout(2, 0);
			panelApp.setLayout(gdLayoutApp);
			JPanel header = new JPanel();
			header.add(new JLabel(i.getName() + "__" + j));
			panelApp.add(header);
			
			JPanel panelSubPlugins = new JPanel();
			
			List<String> subPlugins = null;
			subPlugins = ((ISignalMonitor) i).getAttributsPlugin();


			GridLayout gdLayoutSubPlugin = new GridLayout(subPlugins.size()+5, 3);
			panelSubPlugins.setLayout(gdLayoutSubPlugin);
			
			for ( String s : subPlugins ) {
				
				if (s.equals("Modify")) {
					List<String> tags = new ArrayList<String>();
					List<DescriptionPlugin> l;
					Constraint c1 = new Constraint();
					tags.add("I" + s);
					c1.setConstraints(tags);
					l = ExtensionLoader.getInstance().getExtension(c1);
					
					for (IModify im : ((IApp) i).getModify()) {
						JLabel labelSubPlugin = new JLabel(im.getName());
						JButton killPlugin = new JButton("kill");
						JComboBox<String> cbPlugin = new JComboBox<String>();
						for (DescriptionPlugin dp : l) {
							cbPlugin.addItem(dp.getNom());
						}
						killPlugin.addActionListener(new ActionListenerMonitor(i, this, s, im.getName()));
						cbPlugin.addActionListener(new ActionListenerComboBox(i, s, cbPlugin));
						panelSubPlugins.add(labelSubPlugin);
						panelSubPlugins.add(killPlugin);
						panelSubPlugins.add(cbPlugin);
					}
				} else {
					List<String> tags = new ArrayList<String>();
					List<DescriptionPlugin> l;
					Constraint c1 = new Constraint();
					tags.add("I" + s);
					c1.setConstraints(tags);
					l = ExtensionLoader.getInstance().getExtension(c1);
					JLabel labelSubPlugin = new JLabel(s);
					JButton killPlugin = new JButton("kill");
					JComboBox<String> cbPlugin = new JComboBox<String>();
					
					for (DescriptionPlugin dp : l) {
						cbPlugin.addItem(dp.getNom());
					}
					
					killPlugin.addActionListener(new ActionListenerMonitor(i, this, s, null));
					cbPlugin.addActionListener(new ActionListenerComboBox(i, s, cbPlugin));
					panelSubPlugins.add(labelSubPlugin);
					panelSubPlugins.add(killPlugin);
					panelSubPlugins.add(cbPlugin);
				}				
			}
			panelApp.add(panelSubPlugins);
			frame.add(panelApp);
		}
		frame.setVisible(true);		
	}
	
	public class ActionListenerComboBox implements ActionListener {
		
		private IApp appRunning;
		private JComboBox cb;
		private String nameCurrentPlugin;
		
		public ActionListenerComboBox(IApp appRunning , String nameCurrentPlugin, JComboBox cb) {
			this.appRunning = appRunning;
			this.nameCurrentPlugin = nameCurrentPlugin;
			this.cb = cb;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			for (DescriptionPlugin p : ExtensionLoader.getInstance().getListPlugins()) {
				if (p.getNom().equals(cb.getSelectedItem())) {
					System.out.println("ActionListenerComboBox:: descrPlugin :" + p.getNom());
					ExtensionLoader.getInstance().getMonitor().replace(appRunning, nameCurrentPlugin, ExtensionLoader.getInstance().load(p));
					doStuff();
				}
			}
		}
		
	}
}
