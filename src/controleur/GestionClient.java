package controleur;

import java.util.Map;

import modele.Client;
import modele.Materiel;

public class GestionClient {

	public static Client clientexist(Client client) {
		return ClientDAO.clientexist(client);
	}
	
	public static Boolean seuilMaxAtteint(Client client, Map<Materiel, Integer> materiels, Materiel materiel, int nb) {
		System.out.println("seuil de la cdm : "+ClientDAO.seuilMax(client, materiels, materiel));
		if(ClientDAO.seuilMax(client, materiels, materiel)>=nb)
			return false;
		else return true;
	}
}
