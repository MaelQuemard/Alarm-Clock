package client;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Time{

	private float h;
	private float m;
	private float s;
	
	
	@SuppressWarnings("deprecation")
	public Time() {
		Date d = new Date();
		this.h = d.getHours();
		this.m = d.getMinutes();
		this.s = d.getSeconds();
	}
	
	public void actualizeTime()
	{
		Date d = new Date();
		this.h = d.getHours();
		this.m = d.getMinutes();
		this.s = d.getSeconds();
	}
	
	public String toString(){
		return ( h + ":" + m + ":" + s );
	}


}
