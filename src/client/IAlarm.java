package client;

/** Interface gérant le temps
 * @author Quémard Maël, Jain Edwin, Gomez Killian, Ngamije Emmanuel, Vuylsteke Sylvain
 * 
 */
public interface IAlarm {
	
	
	/**
	 * @return le time de type ITime
	 */
	public ITime getTime();
	/** setter de ITime
	 * @param t ITime a ajouter
	 */
	public void setTime(ITime t);

	/** getter sur behaviour, comportement lors d'une sonnerie
	 * @return behaviour
	 */
	public String getBehaviour();
	/** setter sur behaviour, comportement lors de sonnerie.
	 * @param b String, set behaviour
	 */
	public void setBehaviour(String b);

	/** sonnerie de l'alarme.
	 * 
	 */
	public void ring();


}
