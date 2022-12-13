package controleur;

import java.util.List;

import modele.Composant;
import modele.Magasin;
import modele.Materiel;

public class GestionMateriel {

	public static Materiel materielexist(Materiel materiel, Magasin magasin) {
		return MaterielDAO.materielexist(materiel, magasin);
	}
	
	public static List<Materiel> materielAvecComposant(Composant composant){
		return MaterielDAO.materielAvecComposant(composant);
	}

}
