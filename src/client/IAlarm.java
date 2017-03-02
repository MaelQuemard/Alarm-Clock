package client;


public interface IAlarm {
	
	public Time getTime();
	public void setTime(Time t);

	public String getBehaviour();
	public void setBehaviour(String b);

	public void ring();


}
