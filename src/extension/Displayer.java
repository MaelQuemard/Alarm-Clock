package extension;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import client.IDisplayer;
import client.ITimeManager;
import framework.DescriptionPlugin;


/**
 * @author quemard
 *
 */
public class Displayer implements IDisplayer {
	//TODO : faire le panel config

	private JFrame frame;
	private JPanel panel;
	private JLabel label;
	private JComboBox<String> combo;
	private ITimeManager ic;
	
	private DescriptionPlugin descPlug;
	private String nameDP = "";
	
	public Displayer() {
		frame = new JFrame();
		panel = new JPanel();
		label = new JLabel();
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
	
	
	public void selectedPlugin(List<DescriptionPlugin> listdp,AppAlarm app){
		//DescriptionPlugin selectedPl;

		panel.add(combo);
		combo.setVisible(true);
		
		for(DescriptionPlugin pl : listdp){
			combo.addItem(pl.getNom());			
		}
		panel.add(combo);
		
		combo.addActionListener(new ActionListenerLoading(listdp,app));
		frame.setVisible(true);
	}
	

	/* (non-Javadoc)
	 * @see client.IDisplayer#setCore(client.ITimeManager)
	 */
	@Override
	public void setCore(ITimeManager ic) {
		this.ic = ic;
	}

	public DescriptionPlugin getDescPlug() {
		return descPlug;
	}

	public void setDescPlug(DescriptionPlugin descPlug) {
		this.descPlug = descPlug;
	}

	public String getNameDP() {
		return nameDP;
	}

	public void setNameDP(String nameDP) {
		this.nameDP = nameDP;
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

	
	
}
