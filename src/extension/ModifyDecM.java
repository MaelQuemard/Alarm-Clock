package extension;

import client.IModify;
import client.ITimeManager;

public class ModifyDecM implements IModify{

	private String name;
	private ITimeManager it;
	
	public ModifyDecM() {
		name = "-1 min";
	}

	/* (non-Javadoc)
	 * @see client.IModify#modify()
	 */
	@Override
	public int modify()
	{
		it.getTime().decMin();
		return 1;
	}

	/* (non-Javadoc)
	 * @see client.IModify#getName()
	 */
	@Override
	public String getName() {
		return name;
	}

	/* (non-Javadoc)
	 * @see client.IModify#setName(java.lang.String)
	 */
	public void setName(String name) {
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see client.IModify#getITimeManager()
	 */
	public ITimeManager getITimeManager() {
		return it;
	}

	/* (non-Javadoc)
	 * @see client.IModify#setITimeManager(client.ITimeManager)
	 */
	public void setITimeManager(ITimeManager it) {
		this.it = it;
	}
}