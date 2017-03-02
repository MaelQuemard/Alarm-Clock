
public class SimpleAlarm
{
	private Time t;
	private String behaviour;

	public SimpleAlarm(Time t)
	{
		this.t = t;
		this.behaviour = "RING RING";
	}

	public void ring()
	{
		System.out.println(this.behaviour);
	}
}