package client;


public interface IModify {
	
	public int modify();
	public String getName();
	public void setName(String s);
	public void setITimeManager(ITimeManager it);
	public ITimeManager getITimeManager();
		
}
