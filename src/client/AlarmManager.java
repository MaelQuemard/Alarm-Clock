package client;

import java.util.ArrayList;

import extension.SimpleAlarm;

public class AlarmManager 
{
	private ArrayList<IAlarm> alarms;
	
	public AlarmManager()
	{
		alarms = new ArrayList<IAlarm>();
	}
	
	public ArrayList<IAlarm> getAlarms()
	{
		return alarms;
	}
	
	public void addAlarm(long at, Time t, String type)
	{
		int as = t.getTotalS(); // alarm time total seconds
		boolean put = false;
		
		//int diff = t.getTotalS() - ct.getTotalS();
		
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
		if (as > at)
		{
			int i = 0;
			
			while(i<alarms.size() && !put)
			{

				Time ti = alarms.get(i).getTime();
				int tis = ti.getTotalS();
				
				if (tis < at)
				{
					alarms.add(i,alarm);
					put = true;
				}
				else if(as < tis)
				{
					alarms.add(i,alarm);
					put = true;
				}
				
				++i;
			}
		}
		else // Si l'alarme est entre 00:00 et l'heure actuelle
		{
			int i = 0;
			
			System.out.println("alarms.size : " + alarms.size());
			
			
			while(i<alarms.size() && !put)
			{
				Time ti = alarms.get(i).getTime();
				int tis = ti.getTotalS();
				
				if (tis < at)
				{
					if (as < tis)
					{
						alarms.add(i,alarm);
						put = true;
					}
				}
	
				++i;
			}
		}
		
		if (!put)
		{
			alarms.add(alarm);
		}
	}
	
	//organise le tableau des alarmes en fonction de l'heure actuelle
	public void organize(long at) {
		//TODO 
	}
	
	public void ring()
	{
		alarms.get(0).ring();
		//alarms.remove(0);
		IAlarm alarmToMove = alarms.get(0);
		alarms.add(alarmToMove);
		alarms.remove(0);
		
	}
	
	public boolean shouldRing(long at)
	{
		System.out.println("alarm time : " + alarms.get(0).getTime().getTotalS());
		System.out.println("current time : " + at);
		
		int diff = (int)at - alarms.get(0).getTime().getTotalS();
		
		System.out.println(" diff : " + diff);
		
		return (diff <= 5 && diff >= 0);
		

		//return this.t.getTotalSec()
	}

}
