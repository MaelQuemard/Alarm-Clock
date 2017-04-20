package client;

import java.util.ArrayList;

/** Manager des Alarms
 * @author Gillier Pierre-Olivier, Gomez Killian, Jain Edwin, Ngamije Emmanuel, Quémard Maël, Vuylsteke Sylvain
 *
 */
public interface IAlarmManager {

	ArrayList<IAlarm> getAlarms();

	/** ajout d'une alarme
	 * @param at long, temps actuel en ms
	 * @param t ITime, temps de l'alarme
	 * @param type String, type de l'alarme.
	 */
	void addAlarm(long at, ITime t, String type);

	//organise le tableau des alarmes en fonction de l'heure actuelle
	/** organise le tableau des alarmes en fonction de l'heure actuelle
	 * @param at long, le temps actuel de milliseconde
	 */
	void organize(long at);

	/** sonnerie de l'alarme, et gestion de la liste d'alarme
	 * 
	 */
	void ring();

	/** verifie que l'alarm devrait sonner ou non
	 * @param at long, temps de ms
	 * @return boolean, true si devrait sonner, false sinon
	 */
	boolean shouldRing(long at);
	
	/**
	 * Supprime toutes les alarmes du manager
	 */
	public void removeAllAlarm();

}