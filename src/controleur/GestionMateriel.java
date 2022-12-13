package controleur;

import modele.Magasin;
import modele.Materiel;

public class GestionMateriel {

	public static Materiel materielexist(Materiel materiel, Magasin magasin) {
		return MaterielDAO.materielexist(materiel, magasin);
	}

}
