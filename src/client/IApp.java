package client;

import java.util.List;

import framework.DescriptionPlugin;

public interface IApp {
	
	void run();
	String getName();
	
	public ITimeManager getTimeManager();
	public void setTimeManager(ITimeManager timeManager);
	public List<IModify> getModify();
	public void addModify(IModify modify);
	public void removeModify(IModify modify);
	public IDisplayer getDisplayer();
	public void setDisplayer(IDisplayer displayer);
	public void setDescriptionPluginChooseByUser(DescriptionPlugin dp);
	public void setConfiguration();

}
