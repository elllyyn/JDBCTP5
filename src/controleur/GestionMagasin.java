package controleur;

import java.util.Map;

import modele.Materiel;

public class GestionMagasin {

	public static Map<Materiel, Integer> afficherContenuMagasin(String NomMag) {
		return MagasinDAO.contenu(NomMag);
	}

	public static boolean magasinExiste(String NomMag) {
		return MagasinDAO.magasinExiste(NomMag);
	}

	public static int quantiteDansMagasin(String NomMag, String NomMat) {
		return MagasinDAO.quantiteMaterielDansMagasin(NomMag, NomMat);
	}

	/**
	 * renvoi le nombre de produit de substitution disponible
	 */
	public static int substitution(String NomMag, String NomMat) {
		return MagasinDAO.quantiteSubstitutionMateriel(NomMag, NomMat);
	}

	/**
	 * renvoi le nombre de produit de substitution utilisé
	 */
	public static int substitutionUtilisé(String NomMag, String NomMat, int nbCmd) {
		int nbsub = 0;
		int mag = quantiteDansMagasin(NomMag, NomMat);
		if (nbCmd <= mag)
			return nbsub;
		else {
			nbCmd = nbCmd - mag;
			if(nbCmd <= substitution(NomMag, NomMat))
				return nbCmd;
			else
				return -1;
		}
	}
}
