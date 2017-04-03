package client;

import java.util.List;

import extension.AppAlarm;
import framework.DescriptionPlugin;




public interface IDisplayer {
	
	public void showInfo(String time);
	public void addButtons(String nameButton, int numButton);
	public void notifyCore(int numButton);
	public void setCore(ITimeManager ic);
	public void selectedPlugin(List<DescriptionPlugin> l,AppAlarm a);
	
	/**
	 * Cette méthode permet de supprimer un bouton grâce a son nom
	 * @param nameButton, nom du bouton a supprimer
	 */
	public void removeButton(String nameButton);
	
	/**
	 * Methode permettant de proposer plusieurs pugin, en esperant un choix multiple
	 * @param listdp list des plugins proposés
	 * @param app l'application qui requete
	 */
	public void selectMultiPlugin(List<DescriptionPlugin> listdp,AppAlarm app);
}
