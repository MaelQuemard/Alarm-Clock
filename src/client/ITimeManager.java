package client;



/**
 * @author Gillier Pierre-Olivier, Gomez Killian, Jain Edwin, Ngamije Emmanuel, Quémard Maël, Vuylsteke Sylvain
 *
 */
public interface ITimeManager {
	
	/** Notification d'un modifieur
	 * @param i int, indice du modifieur
	 */
	public void IAmNotify(int i);
	
	/** ajout d'un Modifieur, implémentant l'interface IModify
	 * @param m IModify, ajout d'un Modify.
	 */
	public void addModifier(IModify m);
	
	/** ajout de tous les modifiers pour l'interface graphique
	 * 
	 */
	public void  addModifiers();
	
	/** update de l'interface graphique de type IDisplay
	 * 
	 */
	public void updateAff();
	
	/** setter d'e l'interface graphique
	 * @param a IDisplayer, ajout de l'affichage
	 */
	public void setAffichage(IDisplayer a);
	
	/** return l'extension de Time implémentant ITime
	 * @return ITime, get du Time
	 */
	public ITime getTime();
	
	/** set de Time implémentant l'interface ITime
	 * @param t ITime, setter de Time
	 */
	public void setTime(ITime t);
	
	/** getter sur modifyvalue, différence entre valeur modifié et temps de base
	 * @return int, la différence entre la valeur modifié et le temps de base
	 */
	public int getModifyValue();
	
	/** setter de modifyvalue.
	 * @param modifyValue int, différence entre temps de base et temps courent
	 */
	public void setModifyValue(int modifyValue);
	
	/** appel de getRefresh sur ITime
	 * @return
	 */
	public int getRefresh();
	public ITime getITime(int hour, int min, int s, boolean fixe);
	
	public int getHourLimit() ;

	public int getMinuteLimit();

	public int getSecondLimit();
	
	/**
	 * Cette methode permet de suprimer un modifieur
	 * @param im, modify a supprimer
	 */
	public void removeModifier(IModify im);
}
