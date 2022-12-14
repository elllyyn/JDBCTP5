package modele;

import java.util.Map;

public class Commande {
	private int id;
	private Client client;
	private Map<Materiel, Integer> materiels;

	public Commande(int id, Client client, Map<Materiel, Integer> materiels) {
		this.id = id;
		this.client = client;
		this.materiels = materiels;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Map<Materiel, Integer> getMateriels() {
		return materiels;
	}

	public void setMateriels(Map<Materiel, Integer> materiels) {
		this.materiels = materiels;
	}

}
