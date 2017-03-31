package framework;

import java.util.List;

public interface ISignalMonitor {
	
	/**
	 * Méthode qui permet d'activer le monitor
	 * @param m, monitor concret
	 */
	public void turnMonitor(IMonitor m);
	
	/**
	 * Méthode que permet de récuperer les noms plugins de l'application
	 * @return Liste des noms des plugins
	 */
	public List<String> getAttributsPlugin();
	
	/**
	 * Cette méthode permet de changer un plugin dynamiquement
	 * @param attibut, Nom du plugin a remplacer
	 * @param newAttribut, Plugin qui va prendre la place de celui présent
	 * @warning Pour que cela fonctionne il faut que l'application soit bien implémenter
	 */
	public void modifyAttribut(String attibut, Object newAttribut);
	
	/**
	 * Cette méthode permet de supprimer un plugin
	 * @param plugin, nom de l'interface du plugin a supprimer
	 */
	public void kill(String plugin);
	
	/**
	 * Cette méthode permet de supprimer un plugin 
	 * @param subPluginToKill, nom de l'interface du plugin a supprimer
	 * @param nameSubPlugin, nom du plugin
	 */
	public void kill(String subPluginToKill, String nameSubPlugin);
}
