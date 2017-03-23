package client;

import java.util.Date;

public class Time{

	private int h;
	private int m;
	private int s;
	
	private long initialTime;
	
	
	public Time() {
		//Date d = new Date();
		this.setH(0);
		this.setM(0);
		this.setS(0);
		this.initialTime = System.currentTimeMillis();
		
	}
	
	@SuppressWarnings("deprecation")
	public Time(int hourLimit, int minuteLimit, int secondLimit)
	{
		Date d = new Date();
		this.setH(d.getHours() % hourLimit);
		this.setM(d.getMinutes() % minuteLimit);
		this.setS(d.getSeconds() % secondLimit);
		initialTime = System.currentTimeMillis();
	}
	
	@SuppressWarnings("deprecation")
	public void actualizeTime()
	{
		Date d = new Date();
		this.setH(d.getHours());
		this.setM(d.getMinutes());
		this.setS(d.getSeconds());
		System.currentTimeMillis(); // time in millisecond
	}
	
	public String toString(){
		long actualTime = System.currentTimeMillis() - initialTime;
		//System.out.println("they call me : Timer.toString : " + (actualTime/3600000)%24 + ":" +(actualTime/60000)%60 + ":"+(actualTime/1000)%60);
		return( (actualTime/3600000)%24 + ":" +(actualTime/60000)%60 + ":"+(actualTime/1000)%60);
		//return ( h + ":" + m + ":" + s );
	}
	
	public void addHour() {
		initialTime -= 3600000;
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


}
