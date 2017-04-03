package client;

import java.util.List;

import extension.AppAlarm;
import framework.DescriptionPlugin;

/** l'afficheur graphique de l'app
 * @author @author Gillier Pierre-Olivier, Gomez Killian, Jain Edwin, Ngamije Emmanuel, Quémard Maël, Vuylsteke Sylvain
 *
 */
public interface IDisplayer {
	
	/** affichage du temps
	 * @param time String, le temps à afficher 
	 */
	public void showInfo(String time);
	
	/** ajout d'un bouton
	 * @param nameButton String, nom du bouton
	 * @param numButton int, numero du bouton
	 */
	public void addButtons(String nameButton, int numButton);
	
	/** notifie le plugin correspondant au bouton 
	 * @param numButton int, numero du bouton
	 */
	public void notifyCore(int numButton);
	
	/** setter de TimeManager, background du programme
	 * @param ic ITimeManager, TimeManager a set
	 */
	public void setCore(ITimeManager ic);
	
	/** affichage des plugins possible pour l'application
	 * @param l List<DescriptionPlugin>, liste des plugins
	 * @param a AppAlarm, application 
	 */
	public void selectedPlugin(List<DescriptionPlugin> l,AppAlarm a);
	
	/**
	 * Cette méthode permet de supprimer un bouton grâce a son nom
	 * @param nameButton, nom du bouton a supprimer
	 */
	public void removeButton(String nameButton);
	
	/**
	 * Permet de fermer la fenetre
	 */
	public void dispose();
	
	/**
	 * Methode permettant de proposer plusieurs pugin, en esperant un choix multiple
	 * @param listdp list des plugins proposés
	 * @param app l'application qui requete
	 */
	public void selectMultiPlugin(List<DescriptionPlugin> listdp,AppAlarm app);
	
	/** Setting de l'alarm manager pour le displayer
	 * @param ia l'alarmeManager
	 * @param it le format de temps courant
	 */
	public void setAlarm(IAlarmManager ia, ITimeManager it);
}
