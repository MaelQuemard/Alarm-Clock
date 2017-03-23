package extension;

import client.IModify;
import client.ITimeManager;

public class ModifyDecH implements IModify{

	private String name;
	private ITimeManager it;
	
	public ModifyDecH() {
		name = "-1 hour";
	}

	@Override
	public int modify()
	{
		it.getTime().decHour();
		it.updateAff();
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
