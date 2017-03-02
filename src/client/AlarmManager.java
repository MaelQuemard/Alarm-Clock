package client;

import java.util.ArrayList;

import extension.SimpleAlarm;

public class AlarmManager 
{
	private ArrayList<IAlarm> alarms;
	
	public AlarmManager()
	{
		//TODO
	}
	
	
	public void addAlarm(Time ct, Time t, String type)
	{
		int diff = t.getTotalS() - ct.getTotalS();
		
		IAlarm alarm = null;
		
		if(type.equals("Simple"))
		{
			alarm = new SimpleAlarm(t);
		}
		/*
		else if (type.equals("autre chose...")
		{
		...}
		
		*/
		
		
		// Si l'alarme est entre l'heure actuelle et 23:59
		if (diff > 0)
		{
			int i = 0;
			
			while(i<alarms.size())
			{
				Time ti = alarms.get(i).getTime();
				
				if(diff < ti.getTotalS()-ct.getTotalS())
				{
					alarms.add(alarm);
				}
				++i;
			}
		}
		else // Si l'alarme est entre 00:00 et l'heure actuelle
		{
			//TODO
		}
	}
	
	public boolean shouldRing(Time t)
	{
		
		return false;
		//return this.t.getTotalSec()
	}

}
