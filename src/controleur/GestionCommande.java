package controleur;

import modele.Commande;

public class GestionCommande {
	/**
	 * envoi la commande � cr�er au DAO
	 * @param commande
	 */
	public static void creerCmd(Commande commande) {
		CommandeDAO.creerCommande(commande);
	}

}
