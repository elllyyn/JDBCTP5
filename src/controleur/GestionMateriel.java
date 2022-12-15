package controleur;

import java.util.List;

import modele.Composant;
import modele.Magasin;
import modele.Materiel;

public class GestionMateriel {
	/**
	 * renvoi un mat�riel obtenue avec le DAO
	 * @param materiel
	 * @param magasin
	 * @return
	 */
	public static Materiel materielexist(Materiel materiel, Magasin magasin) {
		return MaterielDAO.materielexist(materiel, magasin);
	}
	/**
	 * renvoi la liste de mat�riel avec un composant donn� obtenue avec le DAO
	 * @param composant
	 * @return
	 */
	public static List<Materiel> materielAvecComposant(Composant composant){
		return MaterielDAO.materielAvecComposant(composant);
	}

}
