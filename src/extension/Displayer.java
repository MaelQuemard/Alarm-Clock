package extension;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import client.IAlarm;
import client.IAlarmManager;
import client.IDisplayer;
import client.IPlugin;
import client.ITimeManager;
import framework.DescriptionPlugin;


/**
 * @author quemard
 *
 */
public class Displayer implements IDisplayer, IPlugin {
	//TODO : faire le panel config

	private JFrame frame;
	private JPanel panel;
	private JLabel label;
	private JPanel panelAlarm;
	private JComboBox<String> combo;
	private ITimeManager ic;
	private JList<String> jList;
	
	
	private DescriptionPlugin descPlug;
	private String nameDP = "";
	
	/** Constructeur Displayer
	 * 
	 */
	public Displayer() {
		frame = new JFrame();frame.setLayout(new GridLayout());
		panel = new JPanel();
		label = new JLabel();
		panelAlarm = new JPanel();
		combo = new JComboBox<String>();
		
		panel.add(label);
		frame.add(panel);
		frame.setSize(400, 400);
		frame.setLocation(401, 0);
		frame.setVisible(true);
	}
	
	/* (non-Javadoc)
	 * @see client.IDisplayer#showInfo(java.lang.String)
	 */
	@Override
	public void showInfo(String time) {
		label.setText(time);
	}
	
	/* (non-Javadoc)
	 * @see client.IDisplayer#addButtons(java.lang.String, int)
	 */
	@Override
	public void addButtons(String nameButton, final int numButton) {
		JButton button = new JButton(nameButton);
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				notifyCore(numButton);
			}
			
		});
		panel.add(button);
	}
	
	/* (non-Javadoc)
	 * @see client.IDisplayer#removeButton(java.lang.String)
	 */
	@SuppressWarnings("deprecation")
	public void removeButton(String nameButton) {
		for (Component c : panel.getComponents()) {
			if (c instanceof JButton) {
				if (((JButton) c).getLabel().equals(nameButton)) {
					panel.remove(c);
				}
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see client.IDisplayer#notifyCore(int)
	 */
	@Override
	public void notifyCore(int numButton) {
		ic.IAmNotify(numButton);
	}
	
	/* (non-Javadoc)
	 * @see client.IDisplayer#selectMultiPlugin(java.util.List, extension.AppAlarm)
	 */
	public void selectMultiPlugin(List<DescriptionPlugin> listdp,AppAlarm app)
	{
		List<String> ls = new ArrayList<String>();
		System.out.println("Displayer::selectMultiPlugin");
		for(DescriptionPlugin d : listdp)
		{
			ls.add(d.getNom());
		}
		
		jList = new JList<String>(new Vector<String>(ls));
		panel.add(jList);
		JButton butt = new JButton("Valider");
		butt.addActionListener(new ActionListenerMultiLoading(listdp,app,butt));
		panel.add(butt);
		panel.revalidate();
		panel.repaint();
	}
	
	
	/* (non-Javadoc)
	 * @see client.IDisplayer#selectedPlugin(java.util.List, extension.AppAlarm)
	 */
	public void selectedPlugin(List<DescriptionPlugin> listdp,AppAlarm app){
		//DescriptionPlugin selectedPl;
		System.out.println("Displayer::selectedPlugin()" + combo.toString());
		combo.removeAllItems();
		panel.add(combo);
		combo.setVisible(true);
		
		for(DescriptionPlugin pl : listdp){
			combo.addItem(pl.getNom());			
		}
		panel.add(combo);
		
		combo.addActionListener(new ActionListenerLoading(listdp,app));
	}
	

	/* (non-Javadoc)
	 * @see client.IDisplayer#setCore(client.ITimeManager)
	 */
	@Override
	public void setCore(ITimeManager ic) {
		this.ic = ic;
	}

	/** return le DP
	 * @return DescriptionPlugin
	 */
	public DescriptionPlugin getDescPlug() {
		return descPlug;
	}

	/** setter de DecriptionPlugin
	 * @param descPlug DescriptionPlugin, nouveau DescriptionPlugin
	 */
	public void setDescPlug(DescriptionPlugin descPlug) {
		this.descPlug = descPlug;
	}

	/** getter de DescriptionPlugin
	 * @return String, nom de DescriptionPlugin
	 */
	public String getNameDP() {
		return nameDP;
	}

	/** setter du nom du DescritptionPlugin
	 * @param nameDP String, nom du DescriptionPlugin
	 */
	public void setNameDP(String nameDP) {
		this.nameDP = nameDP;
	}
	
	/* (non-Javadoc)
	 * @see client.IDisplayer#setAlarm(client.IAlarmManager, client.ITimeManager)
	 */
	@Override
	public void setAlarm(IAlarmManager ia, ITimeManager it) {
		ic = it;
		List<String> listAlarm = new ArrayList<String>();
		for(IAlarm alarm : ia.getAlarms())
		{
			listAlarm.add("Alarme prévue à : " + alarm.getTime().getH() +":"+alarm.getTime().getM() +":"+alarm.getTime().getS()
					+"\n Elle va :" + alarm.getBehaviour());
			
		}
		panelAlarm.removeAll();
		panelAlarm.add(new JList<String>(new Vector<String>(listAlarm)));
		
		JComboBox<Integer> comboHour = new JComboBox<Integer>();
		JComboBox<Integer> comboMin = new JComboBox<Integer>();
		JComboBox<Integer> comboS = new JComboBox<Integer>();
		for(int i = 0; i<it.getHourLimit() ; ++i)
		{
			comboHour.addItem(i);
		}
		for(int i = 0; i<it.getMinuteLimit() ; ++i)
		{
			comboMin.addItem(i);
		}
		for(int i = 0; i<it.getSecondLimit() ; ++i)
		{
			comboS.addItem(i);
		}
		
		JButton button = new JButton("Ajouter alarme");
		button.addActionListener(new ActionListenerAlarmAdd(comboHour, comboMin, comboS,ia));
		panelAlarm.add(comboHour);
		panelAlarm.add(comboMin);
		panelAlarm.add(comboS);
		panelAlarm.add(button);
		
		JButton buttonRemove = new JButton("Supprimer les alarmes");
		buttonRemove.addActionListener(new ActionListenerAlarmRemove(ia));
		panelAlarm.add(buttonRemove);
		
		frame.add(panelAlarm);
		
	}
	
	//  CLASS ActionListener
	public String getName() {
		return "Displayer";
	}
	
	public void dispose() {
		this.frame.dispose();
	}
	
	public class ActionListenerLoading implements ActionListener {

		AppAlarm a;
		List<DescriptionPlugin> l;
		
		ActionListenerLoading(List<DescriptionPlugin> list, AppAlarm alarm )
		{
			a = alarm;
			l = list;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			for(DescriptionPlugin dp : l){
				if(combo.getSelectedItem() == dp.getNom() ){
					a.setDescriptionPluginChooseByUser(dp);
					a.setConfiguration();
					break;
				}
			}
			panel.remove(combo);
		}
		
	}
	
	public class ActionListenerMultiLoading implements ActionListener {

		AppAlarm a;
		List<DescriptionPlugin> l;
		JButton b;
		
		ActionListenerMultiLoading(List<DescriptionPlugin> list, AppAlarm alarm, JButton jb )
		{
			a = alarm;
			b = jb;
			l = list;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			List<DescriptionPlugin> pluginToLoad = new ArrayList<DescriptionPlugin>();
			int[] tabIndice = jList.getSelectedIndices();
			for(int i = 0 ; i<tabIndice.length ; ++i)
			{
				pluginToLoad.add(l.get(tabIndice[i]));
			}
			a.setPluginsChooseByUser(pluginToLoad);
			a.setConfiguration();
			panel.remove(jList);
			panel.remove(b);
			panel.revalidate();
			panel.repaint();
			panel.setVisible(true);
		}
		
	}

	public class ActionListenerAlarmRemove implements ActionListener{
		
		IAlarmManager manager;
		ActionListenerAlarmRemove(IAlarmManager ia)
		{
			manager = ia;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			manager.removeAllAlarm();
			setAlarm(manager,ic);
			
		}
	}
	
	public class ActionListenerAlarmAdd implements ActionListener {

		JComboBox<Integer> comboHour;
		JComboBox<Integer> comboMin;
		JComboBox<Integer> comboS;
		IAlarmManager ia;
		
		ActionListenerAlarmAdd(JComboBox<Integer> comboHour, JComboBox<Integer> comboMin,
				JComboBox<Integer> comboS, IAlarmManager ia)
		{
			this.comboHour= comboHour;
			this.comboMin = comboMin;
			this.comboS = comboS;
			this.ia = ia;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			//TODO proposer les differents types d'alarm
			ia.addAlarm(System.currentTimeMillis()/1000,
					ic.getITime((Integer)comboHour.getSelectedItem(), (Integer)comboMin.getSelectedItem(),(Integer) comboS.getSelectedItem(), true),
					"Simple");
			setAlarm(ia,ic);
			
		}
		
	}
	
	
}
