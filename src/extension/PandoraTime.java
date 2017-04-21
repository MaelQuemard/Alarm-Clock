package extension;

import java.util.Date;

import client.IPlugin;
import client.ITime;

public class PandoraTime implements ITime, IPlugin
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
	
	
	/** Constructeur PandoraTime
	 * 
	 */
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

	
	/* (non-Javadoc)
	 * @see client.ITime#actualizeTime()
	 */
	@SuppressWarnings("deprecation")
	public void actualizeTime()
	{
		Date d = new Date();
		this.setH(d.getHours());
		this.setM(d.getMinutes());
		this.setS(d.getSeconds());
		actualTime = (System.currentTimeMillis() - initialTime)%totalSec;
		if (actualTime < 0)
		{
			actualTime = totalSec + actualTime;
		}
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		actualTime = (System.currentTimeMillis() - initialTime)%totalSec;
		if (actualTime < 0)
		{
			actualTime = totalSec + actualTime;
		}
		return( (actualTime/hPandora)%nbHPandora + ":" +(actualTime/mPandora)%nbSMPandora + ":"+(actualTime/sPandora)%nbSMPandora);
	}
	
	/* (non-Javadoc)
	 * @see client.ITime#addHour()
	 */
	public void addHour() {
		initialTime -= hPandora;
	}
	
	/* (non-Javadoc)
	 * @see client.ITime#addMin()
	 */
	public void addMin() {
		initialTime -= mPandora;
	}
	
	/* (non-Javadoc)
	 * @see client.ITime#decHour()
	 */
	public void decHour() {
		initialTime += hPandora;
	}
	
	/* (non-Javadoc)
	 * @see client.ITime#decMin()
	 */
	public void decMin() {
		initialTime += mPandora;
	}

	/* (non-Javadoc)
	 * @see client.ITime#getH()
	 */
	public int getH() {
		return h;
	}

	/* (non-Javadoc)
	 * @see client.ITime#setH(int)
	 */
	public void setH(int h) {
		this.h = h;
	}

	/* (non-Javadoc)
	 * @see client.ITime#getM()
	 */
	public int getM() {
		return m;
	}

	/* (non-Javadoc)
	 * @see client.ITime#setM(int)
	 */
	public void setM(int m) {
		this.m = m;
	}

	/* (non-Javadoc)
	 * @see client.ITime#getS()
	 */
	public int getS() {
		return s;
	}

	/* (non-Javadoc)
	 * @see client.ITime#setS(int)
	 */
	public void setS(int s) {
		this.s = s;
	}
	
	/* (non-Javadoc)
	 * @see client.ITime#getTotalS()
	 */
	public int getTotalS()
	{
		return ((this.h * nbSMPandora * nbSMPandora) + (this.m * nbSMPandora) + this.s);
	}

	
	/* (non-Javadoc)
	 * @see client.ITime#getActualTime()
	 */
	public long getActualTime() {
		return actualTime;
	}

	/* (non-Javadoc)
	 * @see client.ITime#setActualTime(long)
	 */
	public void setActualTime(long actualTime) {
		this.actualTime = actualTime;
	}

	/* (non-Javadoc)
	 * @see client.ITime#getRefresh()
	 */
	@Override
	public int getRefresh() {
		return sPandora;
	}

	@Override
	public String getName() {
		return "PandoraTime";
	}
	

}