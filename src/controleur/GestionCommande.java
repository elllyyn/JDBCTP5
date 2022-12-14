package controleur;

import modele.Commande;

public class GestionCommande {

	public static void creerCmd(Commande commande) {
		CommandeDAO.creerCommande(commande);
	}

}
