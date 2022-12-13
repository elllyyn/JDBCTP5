package controleur;

import java.util.Map;

import modele.Materiel;

public class GestionMagasin {
	
	public static Map<Materiel, Integer> afficherContenu(){
		return MagasinDAO.contenu();
	}


}
