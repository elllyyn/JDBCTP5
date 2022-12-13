package controleur;

import java.util.Map;

import modele.Materiel;

public class GestionMagasin {
	
	public static Map<Materiel, Integer> afficherContenuMagasin(String NomMag){
		return MagasinDAO.contenu(NomMag);
	}
}
