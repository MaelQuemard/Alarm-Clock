package extension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import client.IDisplayer;
import client.ITimeManager;


public class Displayer implements IDisplayer {

	private JFrame frame;
	private JPanel panel;
	private JLabel label;
	private ITimeManager ic;
	
	public Displayer() {
		frame = new JFrame();
		panel = new JPanel();
		label = new JLabel();
		panel.add(label);
		frame.add(panel);
		frame.setSize(800, 800);
		frame.setVisible(true);
	}
	
	@Override
	public void showInfo(String time) {
		label.setText(time);
	}
	
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
	
	@Override
	public void notifyCore(int numButton) {
		ic.IAmNotify(numButton);
	}

	@Override
	public void setCore(ITimeManager ic) {
		this.ic = ic;
	}
}
