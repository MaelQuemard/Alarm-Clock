package extension;

import client.IAlarm;
import client.ITime;

public class SimpleAlarm implements IAlarm
{
	private ITime t;
	private String behaviour;

	public SimpleAlarm(ITime t)
	{
		this.setTime(t);
		this.behaviour = "RING RING";
	}

	public ITime getTime() 
	{
		return t;
	}

	public void setTime(ITime t2) 
	{
		this.t = t2;
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