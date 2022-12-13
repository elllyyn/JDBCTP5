package controleur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Utils.Utils;
import modele.Categorie;
import modele.Composant;
import modele.Magasin;
import modele.Materiel;

public class MaterielDAO {
private static Utils connection = new Utils("ellyn", "ellyn", "Entreprise", 0);
	
	public static Connection connection() {
		return connection.c;
		
	}

	public static Materiel materielexist(Materiel materiel, Magasin magasin) {
		try {
			java.sql.Statement stmt = connection().createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Materiel;");

			while (rs.next()) {
				if(materiel.getNom().equalsIgnoreCase(rs.getString("NomMateriel"))) {
					materiel.setCategorie(new Categorie(rs.getString("NomCat")));
					String sql = "SELECT NomCom FROM ContenuMagasin m INNER JOIN ContenuMateriel c "
							+ "ON(m.nomMat = c.nomMat AND m.NomMag= ? ) WHRERE m.nomMateriel = ?;";
					PreparedStatement pstmt = connection().prepareStatement(sql);
					pstmt.setString(1, magasin.getNom());
					pstmt.setString(2, materiel.getNom());
					rs = pstmt.executeQuery();
					ArrayList<Composant> composants = new ArrayList<>();
					while(rs.next()) {
						composants.add(new Composant(rs.getString("NomCat")));
					}
					materiel.setComposants(composants);
					pstmt.close();
					stmt.close();
					rs.close();
					return materiel;
				}
					
			}
			
			stmt.close();
			rs.close();
		} catch (SQLException e1) {			
			e1.printStackTrace();
		}
		return null;
	}
	
	public static List<Materiel> materielAvecComposant(Composant composant){
		ArrayList<Materiel> materiels = new ArrayList<Materiel>();
		
		try {
			String sql = "SELECT * FROM ContenuMateriel WHERE NomComp = ?;";
			PreparedStatement pstmt;
			pstmt = connection().prepareStatement(sql);
			pstmt.setString(1, composant.getNom());
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				materiels.add(null);

			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return materiels;
	}

}
