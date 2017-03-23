package extension;

import java.util.Date;
import client.ITime;

public class EarthTime implements ITime
{

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
		actualTime = (System.currentTimeMillis() - initialTime)%86400000;
		if (actualTime < 0)
		{
			actualTime = 86400000 + actualTime;
		}
	}
	
	public String toString(){
		actualTime = (System.currentTimeMillis() - initialTime)%86400000;
		if (actualTime < 0)
		{
			actualTime = 86400000 + actualTime;
		}
		System.out.println("they call me : Timer.toString : " + (actualTime/3600000)%24 + ":" +(actualTime/60000)%60 + ":"+(actualTime/1000)%60);
		return( (actualTime/3600000)%24 + ":" +(actualTime/60000)%60 + ":"+(actualTime/1000)%60);
		//return ( h + ":" + m + ":" + s );
	}
	
	public void addHour() {
		initialTime -= 3600000;
	}
	
	public void addMin() {
		initialTime -= 60000;
	}
	
	public void decHour() {
		initialTime += 3600000;
	}
	
	public void decMin() {
		initialTime += 60000;
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
		return ((this.h * 3600) + (this.m * 60) + this.s);
	}

	
	public long getActualTime() {
		return actualTime;
	}

	public void setActualTime(long actualTime) {
		this.actualTime = actualTime;
	}
	

}
