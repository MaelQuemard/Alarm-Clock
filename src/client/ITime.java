package client;

public interface ITime {
	
	public void actualizeTime();
	
	public String toString();
	
	public void addHour();
	public void addMin();
	
	public void decHour();
	public void decMin();

	public int getH();
	public void setH(int h);

	public int getM();
	public void setM(int m);
	
	public int getS();
	public void setS(int s);
	
	public int getTotalS();

	public long getActualTime();

	public void setActualTime(long actualTime);

}
