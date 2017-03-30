package extension;

import java.util.Date;
import client.ITime;

public class PandoraTime implements ITime
{
	private static int totalSec = 86400000;

	private static int nbSMPandora = 100;
	private static int nbHPandora = 20;
	
	private static int sPandora = 423;
	private static int mPandora = 42300;
	private static int hPandora = 4230000;
	
	private int h;
	private int m;
	private int s;
	
	private long initialTime;
	private long actualTime;
	
	
	public PandoraTime() {
		//Date d = new Date();
		this.setH(0);
		this.setM(0);
		this.setS(0);
		this.initialTime = System.currentTimeMillis();
		this.actualTime = 0;
	}

	@SuppressWarnings("deprecation")
	public PandoraTime(int hourLimit, int minuteLimit, int secondLimit, boolean fixe)
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
		System.out.println("they call me : Timer.toString : " + (actualTime/hPandora)%nbHPandora + ":" +(actualTime/mPandora)%nbSMPandora + ":"+(actualTime/sPandora)%nbSMPandora);
		return( (actualTime/hPandora)%nbHPandora + ":" +(actualTime/mPandora)%nbSMPandora + ":"+(actualTime/sPandora)%nbSMPandora);
		//return ( h + ":" + m + ":" + s );
	}
	
	public void addHour() {
		initialTime -= hPandora;
	}
	
	public void addMin() {
		initialTime -= mPandora;
	}
	
	public void decHour() {
		initialTime += hPandora;
	}
	
	public void decMin() {
		initialTime += mPandora;
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
		return ((this.h * nbSMPandora * nbSMPandora) + (this.m * nbSMPandora) + this.s);
	}

	
	public long getActualTime() {
		return actualTime;
	}

	public void setActualTime(long actualTime) {
		this.actualTime = actualTime;
	}
	

}