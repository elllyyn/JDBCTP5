package controleur;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modele.Categorie;
import modele.Composant;
import modele.Magasin;
import modele.Materiel;

public class MaterielDAO extends DAO{
	/**
	 * renvoi le matériel s'il existe dans un magasin
	 * @param materiel
	 * @param magasin
	 * @return
	 */
	public static Materiel materielexist(Materiel materiel, Magasin magasin) {
		try {			
			String sql = "SELECT m.NomMat, mat.NomCat, mat.MaterielSubstitution substitution, NomCom FROM ContenuMagasin m INNER JOIN ContenuMateriel c INNER JOIN Materiel mat ON(m.nomMat = c.nomMat AND mat.NomMateriel = m.nomMat AND m.NomMag= ? ) WHERE m.nomMat = ?;";
			PreparedStatement pstmt = connection().prepareStatement(sql);
			pstmt.setString(1, magasin.getNom());
			pstmt.setString(2, materiel.getNom());
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				materiel.setCategorie(new Categorie(rs.getString("mat.NomCat")));
				materiel.setSubstitution(new Materiel(rs.getString("substitution"), null));
				
				ArrayList<Composant> composants = new ArrayList<>();
				composants.add(new Composant(rs.getString("NomCom")));
				while(rs.next()) {
					composants.add(new Composant(rs.getString("NomCom")));
				}
				materiel.setComposants(composants);
				
				pstmt.close();
				rs.close();
				return materiel;
			}
			
		} catch (SQLException e1) {			
			e1.printStackTrace();
		}
		return null;
	}
	/**
	 * renvoi une liste de matériel avec un composant donné
	 * @param composant
	 * @return
	 */
	public static List<Materiel> materielAvecComposant(Composant composant){
		ArrayList<Materiel> materiels = new ArrayList<Materiel>();
		
		try {
			String sql = "SELECT * FROM ContenuMateriel c INNER JOIN Materiel m ON (c.NomMat = m.NomMateriel) WHERE NomCom = ?;";
			PreparedStatement pstmt;
			pstmt = connection().prepareStatement(sql);
			pstmt.setString(1, composant.getNom());
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				materiels.add(new Materiel(rs.getString("c.NomMat"), new Categorie(rs.getString("m.NomCat"))));

			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return materiels;
	}

}
