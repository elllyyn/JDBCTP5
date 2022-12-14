package modele;

import java.util.ArrayList;
import java.util.List;

public class Materiel {
	private String nom;
	private Categorie categorie;
	private List<Composant> composants;
	private Materiel substitution;

	public Materiel(String nom, Categorie categorie) {
		this.nom = nom;
		this.categorie = categorie;
		composants = new ArrayList<Composant>();
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public List<Composant> getComposants() {
		return composants;
	}

	public void setComposants(List<Composant> composants) {
		this.composants = composants;
	}

	public Materiel getSubstitution() {
		return substitution;
	}

	public void setSubstitution(Materiel substitution) {
		this.substitution = substitution;
	}

}
