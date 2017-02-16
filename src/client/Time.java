package client;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Time{

	private int h;
	private int m;
	private int s;
	
	private long initialTime;
	
	
	@SuppressWarnings("deprecation")
	public Time() {
		//Date d = new Date();
		this.h = 0;
		this.m = 0;
		this.s = 0;
		this.initialTime = System.currentTimeMillis();
		
	}
	
	public Time(int hourLimit, int minuteLimit, int secondLimit)
	{
		Date d = new Date();
		this.h = d.getHours() % hourLimit;
		this.m = d.getMinutes() % minuteLimit;
		this.s = d.getSeconds() % secondLimit;
		initialTime = System.currentTimeMillis();
	}
	
	@SuppressWarnings("deprecation")
	public void actualizeTime()
	{
		Date d = new Date();
		this.h = d.getHours();
		this.m = d.getMinutes();
		this.s = d.getSeconds();
		System.out.println();
		System.currentTimeMillis(); // time in millisecond
	}
	
	public String toString(){
		long actualTime = System.currentTimeMillis() - initialTime;
		System.out.println("they call me : Timer.toString : " + (actualTime/3600000)%24 + ":" +(actualTime/60000)%60 + ":"+(actualTime/1000)%60);
		return( (actualTime/3600000)%24 + ":" +(actualTime/60000)%60 + ":"+(actualTime/1000)%60);
		//return ( h + ":" + m + ":" + s );
	}
	
	@SuppressWarnings("deprecation")
	public void addHour() {
		initialTime -= 3600000;
	}


}
