package controleur;

import modele.Client;

public class GestionClient {

	public static Client clientexist(Client client) {
		return ClientDAO.clientexist(client);
	}
}
