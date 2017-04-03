package client;

import java.util.List;

import framework.DescriptionPlugin;

/**
 * @author Gillier Pierre-Olivier, Gomez Killian, Jain Edwin, Ngamije Emmanuel, Quémard Maël, Vuylsteke Sylvain
 *
 */
public interface IApp {
	
	/** 
	 * 
	 */
	void run();
	String getName();
	
	/** getter sur le TimeManager, implémentant le ITimeManager
	 * @return ITimeManager, getter de timeManager
	 */
	public ITimeManager getTimeManager();
	
	/** set du TimeManager
	 * @param timeManager ITimeManager, setter de Timemanager
	 */
	public void setTimeManager(ITimeManager timeManager);
	
	/** return la liste des IModify
	 * @return List<IModify>, getter de la liste de Modifiers
	 */
	public List<IModify> getModify();
	
	/** ajout d'un Modifier
	 * @param modify IModify, ajout de modify dans la liste des modifiers
	 */
	public void addModify(IModify modify);
	
	/** enlève modify dans la liste des modifiers
	 * @param modify IModify, suppression de modify dans la liste
	 */
	public void removeModify(IModify modify);
	
	/** return du Displayer, qui s'occupe de l'affichage graphique
	 * @return IDisplayer, l'afficheur
	 */
	public IDisplayer getDisplayer();
	
	/** setter de Displayer
	 * @param displayer IDisplayer, setter de dispalyer
	 */
	public void setDisplayer(IDisplayer displayer);
	
	/** setter du DescriptionPlugin choisit par l'utilisateur
	 * @param dp DescritpionPlugin, DescriptionPlugin choisit par l'utilisateur
	 */
	public void setDescriptionPluginChooseByUser(DescriptionPlugin dp);
	
	/** indique que l'utilisateur à choisit un plugin dans le displayer.
	 * 
	 */
	public void setConfiguration();

}
