package extension;

import client.IModify;
import client.ITimeManager;

public class ModifyIncH implements IModify{

	private String name;
	private ITimeManager it;
	
	public ModifyIncH() {
		name = "+1 hour";
	}

	@Override
	public int modify()
	{
		it.getTime().addHour();
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
