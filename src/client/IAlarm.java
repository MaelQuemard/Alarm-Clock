package client;

public interface IAlarm {
	
	public ITime getTime();
	public void setTime(ITime t);

	public String getBehaviour();
	public void setBehaviour(String b);

	public void ring();


}
