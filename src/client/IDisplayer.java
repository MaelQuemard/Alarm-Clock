package client;




public interface IDisplayer {
	
	public void showInfo(String time);
	public void addButtons(String nameButton, int numButton);
	public void notifyCore(int numButton);
	public void setCore(ITimeManager ic);
}
