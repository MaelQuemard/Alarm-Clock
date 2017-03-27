package framework;

import java.util.List;

/** class decrivant un plugin, son nom (chemin d'accés au .class) et ses tags
 * @author 
 *
 */
public class DescriptionPlugin {
	
	private String nom; 
	private List<String> tags;
	private boolean killable;
	
	public DescriptionPlugin(String nom, List<String> tags, boolean killable) {
		this.nom = nom;
		this.tags = tags;
		this.killable = killable;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public boolean isKillable() {
		return killable;
	}

	public void setKillable(boolean killable) {
		this.killable = killable;
	}

	@Override
	public String toString() {
		return "DescriptionPlugin [nom=" + nom + ", tags=" + tags + "]";
	}
}
