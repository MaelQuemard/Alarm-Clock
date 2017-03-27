package framework;

import java.util.List;

import client.IApp;

public interface IMonitor {
	/** Methode qui permet l'affichage de log dans l'IHM du monitor
	 * @param log la string a afficher
	 */
	void writeLog(String log);
	
	/**
	 * 
	 */
	void test();
	
	/**
	 * Cette méthode permet de kill plugin associé a une application
	 * @param appRunning, application ou se trouve le plugin a kill
	 * @param subPluginToKill plugin a kill
	 */
	void kill(IApp appRunning, String subPluginToKill);
	
	/** methode permettant de remplacer un plugin par un autre
	 * @param appRunning l'application où l'on souhaite modifier un plugin
	 * @param subPluginToReplace le plugin a remplacer
	 * @param replacePlugin le plugn de remplacement
	 * @warning verifier que le nouveau plugin partage bien l'interface commune au deux plugins {@link ExtensionLoader} et la methode getExtension
	 */
	public void replace(IApp appRunning, String subPluginToReplace, String replacePlugin);
	
	/**
	 * Methode qui permet d'obtenir les subPlugins d'une application
	 * @param appRunning l'application où l'on souhaite modifier un plugin
	 */
	public List<String> getSubPlugin(IApp appRunning);
}
