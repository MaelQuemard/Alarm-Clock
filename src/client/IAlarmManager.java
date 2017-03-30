package client;

import java.util.ArrayList;

public interface IAlarmManager {

	ArrayList<IAlarm> getAlarms();

	void addAlarm(long at, ITime t, String type);

	//organise le tableau des alarmes en fonction de l'heure actuelle
	void organize(long at);

	void ring();

	boolean shouldRing(long at);

}