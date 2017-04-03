package client;

import framework.DescriptionPlugin;

public interface IApp {
	
	void run();
	String getName();
	public void setDescriptionPluginChooseByUser(DescriptionPlugin dp);
	public void setConfiguration();

}
