package controleur;

import modele.Client;
import modele.Materiel;

public class GestionClient {

	public static Client clientexist(Client client) {
		return ClientDAO.clientexist(client);
	}
	
	public static Boolean seuilMaxAtteint(Client client, Materiel materiel, int nb) {
		if(ClientDAO.seuilMax(client, materiel)>nb)
			return false;
		else return true;
	}
}
