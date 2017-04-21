package extension;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import client.IApp;
import client.IPlugin;
import framework.Constraint;
import framework.DescriptionPlugin;
import framework.ExtensionLoader;
import framework.ISignalMonitor;

public class DisplayerMonitor {

	private JFrame frame;
	@SuppressWarnings("unused")
	private BoxLayout bxLayoutFrame;
	
	/** Constructeur DisplayerMonitor
	 * 
	 */
	public DisplayerMonitor()
	{
		frame = new JFrame();
		bxLayoutFrame = new BoxLayout(frame, 0);
		frame.setSize(800, 360);
	}
	
	/** Methode permettant de charger la fenetre graphique.
	 * 
	 */
	public void buildDisplayer()
	{
		int j = 0;
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
			
			Map<String, Class<?>> subPlugins = null;
			subPlugins = ((ISignalMonitor) i).getAttributsPlugin();

			GridLayout gdLayoutSubPlugin = new GridLayout(subPlugins.size()+5, 3);
			panelSubPlugins.setLayout(gdLayoutSubPlugin);
			
			for ( String s : subPlugins.keySet() ) {
				
				if (subPlugins.get(s) == List.class) {
					
					for (Object im : (List<Object>)(((ISignalMonitor) i).getAttribut(s))) {
							JLabel labelSubPlugin = new JLabel(((IPlugin) im).getName());
							JButton killPlugin = new JButton("kill");
							JLabel labelReplace = new JLabel("Non remplacable");
							killPlugin.addActionListener(new ActionListenerKill(i, panelSubPlugins, labelSubPlugin, killPlugin, labelReplace, s, ((IPlugin) im).getName()));
							panelSubPlugins.add(labelSubPlugin);
							panelSubPlugins.add(killPlugin);
							panelSubPlugins.add(labelReplace);
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
					
					killPlugin.addActionListener(new ActionListenerKill(i, panelSubPlugins, labelSubPlugin, killPlugin, cbPlugin, s, null));
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
		
		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			for (DescriptionPlugin p : ExtensionLoader.getInstance().getListPlugins()) {
				if (p.getNom().equals(cb.getSelectedItem())) {
					ExtensionLoader.getInstance().getMonitor().replace(appRunning, nameCurrentPlugin, ExtensionLoader.getInstance().load(p));
					buildDisplayer();
				}
			}
		}
		
	}
	
	public class ActionListenerKill implements ActionListener {

		private IApp i;
		private JPanel panelContent;
		private Component c1;
		private Component c2;
		private Component c3;
		private String namePlugin;
		private String nameSubPlugin;
		
		public ActionListenerKill(IApp i, JPanel panelContent, Component c1, Component c2, Component c3, String namePlugin, String nameSubPlugin) {
			this.namePlugin = namePlugin;
			this.nameSubPlugin = nameSubPlugin;
			this.i = i;
			this.panelContent = panelContent;
			this.c1 = c1;
			this.c2 = c2;
			this.c3 = c3;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if (nameSubPlugin == null) {
				ExtensionLoader.getInstance().getMonitor().kill(i, namePlugin);
				panelContent.remove(c1);
				panelContent.remove(c2);
				panelContent.remove(c3);
				panelContent.repaint();
			} else {
				ExtensionLoader.getInstance().getMonitor().kill(i, namePlugin, nameSubPlugin);
				panelContent.remove(c1);
				panelContent.remove(c2);
				panelContent.remove(c3);
				panelContent.repaint();
			}
		}

	}
}
