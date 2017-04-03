package client;

/** Interface d'implémentation du Temps.
 * @author Gillier Pierre-Olivier, Gomez Killian, Jain Edwin, Ngamije Emmanuel, Quémard Maël, Vuylsteke Sylvain
 *
 */
public interface ITime {
	
	/** actualise le temps
	 * 
	 */
	public void actualizeTime();
	
	/** methode toString de ITime
	 * @return string de description de l'objet
	 */
	public String toString();
	
	/** ajout d'une heure
	 * 
	 */
	public void addHour();
	/** ajout d'une minute
	 * 
	 */
	public void addMin();
	
	/** enlève une heure
	 * 
	 */
	public void decHour();
	/** enlève une minute
	 * 
	 */
	public void decMin();

	/** getter de l'heure
	 * @return l'heure, de type int
	 */
	public int getH();
	/** setter de l'heure
	 * @param h int, set l'heure
	 */
	public void setH(int h);

	/** getter des minutes
	 * @return int, les minutes
	 */
	public int getM();
	/** setter des minutes
	 * @param m int, minutes a set
	 */
	public void setM(int m);
	
	/** getter des secondes
	 * @return int, return les secondes
	 */
	public int getS();
	/** setter des seconds
	 * @param s int, secondes à set
	 */
	public void setS(int s);
	
	/** getter le temps en secondes
	 * @return int, le temps total en seconde
	 */
	public int getTotalS();

	/** retourne le temps actuel
	 * @return long, le temps actuel en millisecondes
	 */
	public long getActualTime();

	/** setter du temps actuel
	 * @param actualTime long, le temps actuel a set en milliseconde
	 */
	public void setActualTime(long actualTime);
	
	/** retourne le temps d'une seconde du ITime (le temps entre chaque refresh)
	 * @return int, le temps en milliseconde d'une seconde de l'implémentation du ITime
	 */
	public int getRefresh();

}
