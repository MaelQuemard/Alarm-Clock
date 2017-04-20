package extension;

import java.util.ArrayList;

import client.IAlarm;
import client.IAlarmManager;
import client.ITime;

public class AlarmManager implements IAlarmManager 
{
	private ArrayList<IAlarm> alarms;
	
	
	/** Constructeur d'AlarmManager
	 * 
	 */
	public AlarmManager()
	{
		alarms = new ArrayList<IAlarm>();
	}
	
	/* (non-Javadoc)
	 * @see client.IAlarmManager#getAlarms()
	 */
	@Override
	public ArrayList<IAlarm> getAlarms()
	{
		return alarms;
	}
	
	/* (non-Javadoc)
	 * @see client.IAlarmManager#addAlarm(long, client.ITime, java.lang.String)
	 */
	@Override
	public void addAlarm(long at, ITime t, String type)
	{
		int as = t.getTotalS(); // alarm time total seconds
		boolean put = false;
		
		IAlarm alarm = null;
		
		if(type.equals("Simple"))
		{
			alarm = new SimpleAlarm(t);
		}
		if (as > at)
		{
			int i = 0;
			
			while(i<alarms.size() && !put)
			{
				ITime ti = alarms.get(i).getTime();
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
				ITime ti = alarms.get(i).getTime();
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
	/* (non-Javadoc)
	 * @see client.IAlarmManager#organize(long)
	 */
	@Override
	public void organize(long at) {
		//TODO 
	}
	
	/* (non-Javadoc)
	 * @see client.IAlarmManager#ring()
	 */
	@Override
	public void ring()
	{
		alarms.get(0).ring();
		IAlarm alarmToMove = alarms.get(0);
		alarms.add(alarmToMove);
		alarms.remove(0);
		
	}
	
	/* (non-Javadoc)
	 * @see client.IAlarmManager#shouldRing(long)
	 */
	@Override
	public boolean shouldRing(long at)
	{
		if(alarms.size()==0) return false;
		System.out.println("alarm time : " + alarms.get(0).getTime().getTotalS());
		System.out.println("current time : " + at);
		
		int diff = (int)at - alarms.get(0).getTime().getTotalS();
		
		System.out.println(" diff : " + diff);
		
		return (diff <= 5 && diff >= 0);
		

		//return this.t.getTotalSec()
	}
	
	public void removeAllAlarm()
	{
		alarms.clear();
	}

}
