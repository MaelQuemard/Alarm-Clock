package extension;

import client.IAlarm;
import client.ITime;

public class SimpleAlarm implements IAlarm
{
	private ITime t;
	private String behaviour;

	/** Constructeur de SimpleAlarm
	 * @param t ITime, temps
	 */
	public SimpleAlarm(ITime t)
	{
		this.setTime(t);
		this.behaviour = "RING RING";
	}

	/* (non-Javadoc)
	 * @see client.IAlarm#getTime()
	 */
	public ITime getTime() 
	{
		return t;
	}

	/* (non-Javadoc)
	 * @see client.IAlarm#setTime(client.ITime)
	 */
	public void setTime(ITime t2) 
	{
		this.t = t2;
	}
	
	/* (non-Javadoc)
	 * @see client.IAlarm#ring()
	 */
	public void ring()
	{
		System.out.println(this.behaviour);
	}

	/* (non-Javadoc)
	 * @see client.IAlarm#getBehaviour()
	 */
	@Override
	public String getBehaviour() 
	{
		return this.behaviour;
	}

	/* (non-Javadoc)
	 * @see client.IAlarm#setBehaviour(java.lang.String)
	 */
	@Override
	public void setBehaviour(String b)
	{
		this.behaviour = b;
	}
}