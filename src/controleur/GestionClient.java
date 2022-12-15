package controleur;

import java.util.Map;

import modele.Client;
import modele.Materiel;

public class GestionClient {
	/**
	 * renvoi le client obtenue avec le DAO
	 * @param client
	 * @return
	 */
	public static Client clientexist(Client client) {
		return ClientDAO.clientexist(client);
	}
	/**
	 * renvoi le seuilMax obtenue avec le DAO
	 * @param client
	 * @param materiels
	 * @param materiel
	 * @param nb
	 * @return
	 */
	public static Boolean seuilMaxAtteint(Client client, Map<Materiel, Integer> materiels, Materiel materiel, int nb) {
		if(ClientDAO.seuilMax(client, materiels, materiel)>=nb)
			return false;
		else return true;
	}
}
