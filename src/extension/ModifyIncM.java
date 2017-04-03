package extension;

import client.IModify;
import client.IPlugin;
import client.ITimeManager;

public class ModifyIncM implements IModify, IPlugin {

	private String name;
	private ITimeManager it;
	
	public ModifyIncM() {
		name = "+1 min";
	}

	@Override
	public int modify()
	{
		it.getTime().addMin();
		return 1;
	}

	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ITimeManager getITimeManager() {
		return it;
	}

	public void setITimeManager(ITimeManager it) {
		this.it = it;
	}
}