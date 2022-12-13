package modele;

import java.util.HashMap;
import java.util.Map;

public class Magasin {
	private String nom;
	private Map<Materiel, Integer> contenu;

	public Magasin(String nom) {
		this.nom = nom;
		contenu = new HashMap<>();
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Map<Materiel, Integer> getContenu() {
		return contenu;
	}

	public void setContenu(Map<Materiel, Integer> contenu) {
		this.contenu = contenu;
	}

}
