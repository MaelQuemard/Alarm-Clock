package extension;

import java.util.Date;
import client.ITime;

public class EarthTime implements ITime
{
	private static int totalSec = 86400000;

	private static int nbSMEarth = 60;
	private static int nbHEarth = 24;

	private static int sEarth = 1000;
	private static int mEarth = 60000;
	private static int hEarth = 3600000;

	private int h;
	private int m;
	private int s;
	
	private long initialTime;
	private long actualTime;
	
	
	public EarthTime() {
		//Date d = new Date();
		this.setH(0);
		this.setM(0);
		this.setS(0);
		this.initialTime = System.currentTimeMillis();
		this.actualTime = 0;
	}

	@SuppressWarnings("deprecation")
	public EarthTime(int hourLimit, int minuteLimit, int secondLimit, boolean fixe)
	{
		if (fixe)
		{
			this.h = hourLimit;
			this.m = minuteLimit;
			this.s = secondLimit;
		}
		else
		{
			Date d = new Date();
			this.setH(d.getHours() % hourLimit);
			this.setM(d.getMinutes() % minuteLimit);
			this.setS(d.getSeconds() % secondLimit);
			initialTime = System.currentTimeMillis(); // Choice made to start the clock at the current time on Earth
		}

	}

	
	@SuppressWarnings("deprecation")
	public void actualizeTime()
	{
		Date d = new Date();
		this.setH(d.getHours());
		this.setM(d.getMinutes());
		this.setS(d.getSeconds());
		System.out.println();
		//System.currentTimeMillis(); // time in millisecond
		actualTime = (System.currentTimeMillis() - initialTime)%totalSec;
		if (actualTime < 0)
		{
			actualTime = totalSec + actualTime;
		}
	}
	
	public String toString(){
		actualTime = (System.currentTimeMillis() - initialTime)%totalSec;
		if (actualTime < 0)
		{
			actualTime = totalSec + actualTime;
		}
		System.out.println("they call me : Timer.toString : " + (actualTime/hEarth)%nbHEarth + ":" +(actualTime/mEarth)%nbSMEarth + ":"+(actualTime/sEarth)%nbSMEarth);
		return( (actualTime/hEarth)%nbHEarth + ":" +(actualTime/mEarth)%nbSMEarth + ":"+(actualTime/sEarth)%nbSMEarth);
		//return ( h + ":" + m + ":" + s );
	}
	
	public void addHour() {
		initialTime -= hEarth;
	}
	
	public void addMin() {
		initialTime -= mEarth;
	}
	
	public void decHour() {
		initialTime += hEarth;
	}
	
	public void decMin() {
		initialTime += mEarth;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public int getM() {
		return m;
	}

	public void setM(int m) {
		this.m = m;
	}

	public int getS() {
		return s;
	}

	public void setS(int s) {
		this.s = s;
	}
	
	public int getTotalS()
	{
		return ((this.h * nbSMEarth * nbSMEarth) + (this.m * nbSMEarth) + this.s);
	}

	
	public long getActualTime() {
		return actualTime;
	}

	public void setActualTime(long actualTime) {
		this.actualTime = actualTime;
	}

	@Override
	public int getRefresh() {
		return sEarth;
	}
	

}
