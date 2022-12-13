package controleur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import Utils.Utils;
import modele.Categorie;
import modele.Composant;
import modele.Magasin;
import modele.Materiel;

public class MagasinDAO {

private static Utils connection = new Utils("ellyn", "ellyn", "TP4JDBC", 0);
	
	public static Connection connection() {
		return connection.c;
		
	}
	public static Map<Materiel, Integer> contenu(String NomMag) {
		
		if(magasinExiste(NomMag)!=true) {
			return null;
		}
		ResultSet r1 = null;
		Map<Materiel, Integer> contenu = new HashMap<Materiel, Integer>();
		
		try {
			String sql = "SELECT * FROM ContenuMagasin c INNER JOIN Materiel m ON (NomMateriel) WHERE c.NomMagasin = ?;";
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
				Materiel m = new Materiel(r1.getString("NomMat"), c);
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
	public static Categorie categorieDansListe(String cat, List<Categorie> listCat) {	
		for(Categorie c : listCat) {
			if(c.getNom().equals(cat)) {
				return c;
			}
		}
		return null;
	}
	public static Materiel materielDansMap(String mat,Map<Materiel, Integer> contenu) {
		for(Entry<Materiel,Integer> e : contenu.entrySet()) {
			if(e.getKey().getNom().equals(mat)) {
				return e.getKey();
			}
		}
		return null;
	}
	public static boolean magasinExiste(String magasin) {
		try {
			java.sql.Statement stmt = connection().createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Magasin;");

			while (rs.next()) {
				if(rs.getString("NomMagasin").equals(magasin)) {
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

}
