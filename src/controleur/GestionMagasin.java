package controleur;

import java.util.Map;

import modele.Materiel;

public class GestionMagasin {
	
	public static Map<Materiel, Integer> afficherContenuMagasin(String NomMag){
		return MagasinDAO.contenu(NomMag);
	}
	public static boolean  magasinExiste(String NomMag) {
		return MagasinDAO.magasinExiste(NomMag);
	}
	public static int  quantiteDansMagasin(String NomMag,String NomMat) {
		return MagasinDAO.quantiteMaterielDansMagasin(NomMag, NomMat);
	}
}
