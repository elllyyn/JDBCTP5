package modele;

import java.util.Map;

public class Commande {
	private int id;
	private Map<Materiel, Integer> materiels;

	public Commande(int id, Map<Materiel, Integer> materiels) {
		this.id = id;
		this.materiels = materiels;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Map<Materiel, Integer> getMateriels() {
		return materiels;
	}

	public void setMateriels(Map<Materiel, Integer> materiels) {
		this.materiels = materiels;
	}

}
