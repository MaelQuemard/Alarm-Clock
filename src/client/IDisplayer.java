package client;

import java.util.List;

import extension.AppAlarm;
import framework.DescriptionPlugin;




public interface IDisplayer {
	
	public void showInfo(String time);
	public void addButtons(String nameButton, int numButton);
	public void notifyCore(int numButton);
	public void setCore(ITimeManager ic);
	public void selectedPlugin(List<DescriptionPlugin> l,AppAlarm a);
}
