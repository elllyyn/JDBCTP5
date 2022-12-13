package modele;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Client {
	private String nom;
	private Magasin magasin;
	private List<Commande> commandes;
	private Map<Categorie, Integer> seuilMax;

	public Client(String nom, Magasin magasin) {
		this.nom = nom;
		this.magasin = magasin;
		commandes = new ArrayList<Commande>();
		seuilMax = new HashMap<>();
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Magasin getMagasin() {
		return magasin;
	}

	public void setMagasin(Magasin magasin) {
		this.magasin = magasin;
	}

	public List<Commande> getCommandes() {
		return commandes;
	}

	public void setCommandes(List<Commande> commandes) {
		this.commandes = commandes;
	}

	public Map<Categorie, Integer> getSeuilMax() {
		return seuilMax;
	}

	public void setSeuilMax(Map<Categorie, Integer> seuilMax) {
		this.seuilMax = seuilMax;
	}

}
