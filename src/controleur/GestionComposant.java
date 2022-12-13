package controleur;

import modele.Composant;

public class GestionComposant {

	public static Composant compexist(Composant composant) {
		return ComposantDAO.composantExist(composant);
	}

}
