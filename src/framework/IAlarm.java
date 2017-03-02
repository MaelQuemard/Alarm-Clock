package framework;



public interface IAlarm {
	
	private Time time;
	private String behaviour;

	public Time getTime();
	public void setTime(Time t);

	public String getBehaviour();
	public void setBehaviour(String b);

	public void ring();


}
