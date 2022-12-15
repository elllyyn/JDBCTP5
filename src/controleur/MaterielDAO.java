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
			String sql = "SELECT m.NomMateriel, mat.NomCategorie, mat.MaterielSubstitution substitution, NomComposant FROM ContenuMagasin m INNER JOIN ContenuMateriel c INNER JOIN Materiel mat ON(m.nomMateriel = c.nomMateriel AND mat.NomMateriel = m.nomMateriel AND m.NomMagasin= ? ) WHERE m.nomMateriel = ?;";
			PreparedStatement pstmt = connection().prepareStatement(sql);
			pstmt.setString(1, magasin.getNom());
			pstmt.setString(2, materiel.getNom());
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				materiel.setCategorie(new Categorie(rs.getString("mat.NomCategorie")));
				materiel.setSubstitution(new Materiel(rs.getString("substitution"), null));
				
				ArrayList<Composant> composants = new ArrayList<>();
				composants.add(new Composant(rs.getString("NomComposant")));
				while(rs.next()) {
					composants.add(new Composant(rs.getString("NomComposant")));
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
			String sql = "SELECT * FROM ContenuMateriel c INNER JOIN Materiel m ON (c.NomMateriel = m.NomMateriel) WHERE NomComposant = ?;";
			PreparedStatement pstmt;
			pstmt = connection().prepareStatement(sql);
			pstmt.setString(1, composant.getNom());
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				materiels.add(new Materiel(rs.getString("c.NomMateriel"), new Categorie(rs.getString("m.NomCategorie"))));

			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return materiels;
	}

}
