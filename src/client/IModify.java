package client;


/**
 * @author Gillier Pierre-Olivier, Gomez Killian, Jain Edwin, Ngamije Emmanuel, Quémard Maël, Vuylsteke Sylvain
 *
 */
public interface IModify {
	
	/** modifie le ITime
	 * @return int, 
	 */
	public int modify();
	
	/** getter du nom du modifier
	 * @return String, nom du modifieur
	 */
	public String getName();
	
	/** setter du nom de modifier
	 * @param s String, nouveau nom du modifier
	 */
	public void setName(String s);
	
	/** setter de ITimeManager
	 * @param it ITimeManager, le nouveau TimeManager 
	 */
	public void setITimeManager(ITimeManager it);
	
	/** return le TimeManager implémentant ITimeManger
	 * @return ITimeManager, getter de ITimeManger
	 */
	public ITimeManager getITimeManager();
		
}
