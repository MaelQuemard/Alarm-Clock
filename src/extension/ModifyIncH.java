package extension;

import framework.IModify;

public class ModifyIncH implements IModify{

	private String name;
	
	public ModifyIncH() {
		name = "";
	}

	@Override
	public int modify()
	{
		return 1;
	}

	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

}
