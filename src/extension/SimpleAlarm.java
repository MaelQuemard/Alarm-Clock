package extension;

import client.IAlarm;
import client.Time;

public class SimpleAlarm implements IAlarm
{
	private Time t;
	private String behaviour;

	public SimpleAlarm(Time t)
	{
		this.setTime(t);
		this.behaviour = "RING RING";
	}

	public Time getTime() 
	{
		return t;
	}

	public void setTime(Time t) 
	{
		this.t = t;
	}
	
	public void ring()
	{
		System.out.println(this.behaviour);
	}

	@Override
	public String getBehaviour() 
	{
		return this.behaviour;
	}

	@Override
	public void setBehaviour(String b)
	{
		this.behaviour = b;
	}
}