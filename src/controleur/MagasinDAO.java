package controleur;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import modele.Categorie;
import modele.Materiel;

public class MagasinDAO extends DAO{
	/**
	 * renvoi le contenu d'un magasin
	 * @param NomMag
	 * @return
	 */
	public static Map<Materiel, Integer> contenu(String NomMag) {
		
		if(magasinExiste(NomMag)!=true) {
			return null;
		}
		ResultSet r1 = null;
		Map<Materiel, Integer> contenu = new HashMap<Materiel, Integer>();
		
		try {
			String sql = "SELECT * FROM ContenuMagasin c INNER JOIN Materiel m ON (c.NomMat = m.NomMateriel) WHERE c.NomMag = ?;";
			PreparedStatement pstmt = connection().prepareStatement(sql);
			pstmt.setString(1, NomMag);
			List<Categorie> listCat = new ArrayList<Categorie>();
			r1 = pstmt.executeQuery();
			
			while(r1.next()){
				String NomMat = r1.getString("NomMat");
				String NomCat = r1.getString("NomCat");
				Categorie c = categorieDansListe(NomCat,listCat);
				if(c==null) {
					c = new Categorie(NomCat);
					listCat.add(c);
				}
				Materiel m = new Materiel(NomMat, c);
				int nbMat = r1.getInt("quantStock");
				contenu.put(m, nbMat);
			}
			r1.close();
			pstmt.close();
		} catch (SQLException e1) {			
			e1.printStackTrace();
		}
		return contenu;
		
	}
	/**
	 * renvoi la catégorie s'il elle existe dans une liste
	 * @param cat
	 * @param listCat
	 * @return
	 */
	public static Categorie categorieDansListe(String cat, List<Categorie> listCat) {	
		for(Categorie c : listCat) {
			if(c.getNom().equalsIgnoreCase(cat)) {
				return c;
			}
		}
		return null;
	}
	/**
	 * renvoi le matériel s'il existe dans une Map
	 * @param mat
	 * @param contenu
	 * @return
	 */
	public static Materiel materielDansMap(String mat,Map<Materiel, Integer> contenu) {
		for(Entry<Materiel,Integer> e : contenu.entrySet()) {
			if(e.getKey().getNom().equals(mat)) {
				return e.getKey();
			}
		}
		return null;
	}
	/**
	 * renvoi true si le matériel existe, sinon false
	 * @param magasin
	 * @return
	 */
	public static boolean magasinExiste(String magasin) {
		try {
			java.sql.Statement stmt = connection().createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Magasin;");

			while (rs.next()) {
				if(rs.getString("NomMagasin").equalsIgnoreCase(magasin)) {
					return true;
				}		
			}
			stmt.close();
			rs.close();
		} catch (SQLException e1) {			
			e1.printStackTrace();
		}
		return false;
	}
	/**
	 * renvoi la quantité de matériel disponible pour un matériel dans un magasin
	 * @param NomMag
	 * @param NomMat
	 * @return
	 */
	public static int quantiteMaterielDansMagasin(String NomMag, String NomMat) {
		
		ResultSet r1 = null;
		int nbMat = 0;
		
		try {
			String sql = "SELECT * FROM ContenuMagasin WHERE NomMag = ? AND NomMat = ?;";
			PreparedStatement pstmt = connection().prepareStatement(sql);
			pstmt.setString(1, NomMag);
			pstmt.setString(2, NomMat);

			r1 = pstmt.executeQuery();
			if(r1.next()) {
				nbMat = r1.getInt("quantStock");
			}
			r1.close();
			pstmt.close();
		} catch (SQLException e1) {			
			e1.printStackTrace();
		}
		return nbMat;
	}
	/**
	 * renvoi la quantité disponible du matériel de substitution d'un matériel donné dans un magasin
	 * @param NomMag
	 * @param NomMat
	 * @return
	 */
	public static int quantiteSubstitutionMateriel(String NomMag, String NomMat) {
		ResultSet r1 = null;
		int nbMat = 0;
		
		try {
			String sql = "SELECT MaterielSubstitution FROM ContenuMagasin c INNER JOIN Materiel m ON (c.NomMat = m.NomMateriel) WHERE NomMag = ? AND NomMat = ?;";
			PreparedStatement pstmt = connection().prepareStatement(sql);
			pstmt.setString(1, NomMag);
			pstmt.setString(2, NomMat);

			r1 = pstmt.executeQuery();
			String sub = null;
			if(r1.next()) {
				sub = r1.getString("MaterielSubstitution");
			}
			if(sub != null) {
				
				try {
					sql = "SELECT quantStock FROM ContenuMagasin WHERE NomMag = ? AND NomMat = ?;";
					pstmt = connection().prepareStatement(sql);
					pstmt.setString(1, NomMag);
					pstmt.setString(2, sub);

					r1 = pstmt.executeQuery();
					if(r1.next()) {
						nbMat = r1.getInt("quantStock");
					}
					r1.close();
					pstmt.close();
				} catch (SQLException e1) {			
					e1.printStackTrace();
				}
			}
			r1.close();
			pstmt.close();
		} catch (SQLException e1) {			
			e1.printStackTrace();
		}
		return nbMat;
	}
}
