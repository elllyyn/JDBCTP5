package controleur;

import java.util.Map;

import modele.Materiel;

public class GestionMagasin {
	/**
	 * renvoi le contenu d'un magasin obtenue avec le DAO
	 * @param NomMag
	 * @return
	 */
	public static Map<Materiel, Integer> afficherContenuMagasin(String NomMag) {
		return MagasinDAO.contenu(NomMag);
	}
	/**
	 * renvoi true le magasin existe et false sinon
	 * @param NomMag
	 * @return
	 */
	public static boolean magasinExiste(String NomMag) {
		return MagasinDAO.magasinExiste(NomMag);
	}
	/**
	 * renvoi la quantit� disponible d'un mat�riel dans un magasin
	 * @param NomMag
	 * @param NomMat
	 * @return
	 */
	public static int quantiteDansMagasin(String NomMag, String NomMat) {
		return MagasinDAO.quantiteMaterielDansMagasin(NomMag, NomMat);
	}

	/**
	 * renvoi le nombre de mat�riel de substitution disponible
	 * @param NomMag
	 * @param NomMat
	 * @return
	 */
	public static int substitution(String NomMag, String NomMat) {
		return MagasinDAO.quantiteSubstitutionMateriel(NomMag, NomMat);
	}

	/**
	 * renvoi le nombre de produit de substitution utilis� pour nombre de mat�riel command�
	 * @param NomMag
	 * @param NomMat
	 * @param nbCmd
	 * @return
	 */
	public static int substitutionUtilis�(String NomMag, String NomMat, int nbCmd) {
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
