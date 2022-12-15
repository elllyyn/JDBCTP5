package controleur;

import modele.Composant;

public class GestionComposant {
	/**
	 * renvoi un composant obtenue avec le DAO
	 * @param composant
	 * @return
	 */
	public static Composant compexist(Composant composant) {
		return ComposantDAO.composantExist(composant);
	}

}
