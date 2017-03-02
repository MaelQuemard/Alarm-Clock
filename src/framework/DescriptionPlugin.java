package framework;

import java.util.List;

public class DescriptionPlugin {
	
	private String nom;
	private List<String> tags;
	
	public DescriptionPlugin(String nom, List<String> tags) {
		this.nom = nom;
		this.tags = tags;
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

	@Override
	public String toString() {
		return "DescriptionPlugin [nom=" + nom + ", tags=" + tags + "]";
	}
}
