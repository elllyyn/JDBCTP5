package controleur;

import modele.Commande;

public class GestionCommande {
	/**
	 * envoi la commande à créer au DAO
	 * @param commande
	 */
	public static void creerCmd(Commande commande) {
		CommandeDAO.creerCommande(commande);
	}

}
