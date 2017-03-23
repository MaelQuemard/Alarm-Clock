package extension;

import client.IModify;
import client.ITimeManager;

public class ModifyDecM implements IModify{

	private String name;
	private ITimeManager it;
	
	public ModifyDecM() {
		name = "-1 min";
	}

	@Override
	public int modify()
	{
		it.getTime().decMin();
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