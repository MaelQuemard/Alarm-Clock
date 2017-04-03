package framework;

import java.util.Map;

import client.IApp;

public interface IMonitor {
	/** Methode qui permet l'affichage de log dans l'IHM du monitor
	 * @param log la string a afficher
	 */
	void writeLog(String log);
	
	/**
	 * Cette méthode permet de kill plugin associé a une application
	 * @param appRunning, application ou se trouve le plugin a kill
	 * @param subPluginToKill plugin a kill
	 */
	void kill(IApp appRunning, String subPluginToKill);
	
	/**
	 * Cette méthode permet de kill plugin associé a une application
	 * @param appRunning, application ou se trouve le plugin a kill
	 * @param subPluginToKill plugin a kill
	 */
	void kill(IApp appRunning, String subPluginToKill, String nameSubPlugin);
	
	/** methode permettant de remplacer un plugin par un autre
	 * @param appRunning l'application où l'on souhaite modifier un plugin
	 * @param subPluginToReplace le plugin a remplacer
	 * @param newPlugin le plugn de remplacement
	 * @warning verifier que le nouveau plugin partage bien l'interface commune au deux plugins {@link ExtensionLoader} et la methode getExtension
	 */
	public void replace(IApp appRunning, String subPluginToReplace, Object newPlugin);
	
	/**
	 * Methode qui permet d'obtenir les subPlugins d'une application
	 * @param appRunning l'application où l'on souhaite modifier un plugin
	 */
	public Map<String, Class<?>> getSubPlugin(IApp appRunning);
}
