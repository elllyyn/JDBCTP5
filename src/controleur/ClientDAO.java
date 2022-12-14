package controleur;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Map.Entry;

import modele.Client;
import modele.Magasin;
import modele.Materiel;

public class ClientDAO extends DAO {
	/**
	 * retourne un client s'il existe dans la base de donn?es 
	 * l'objet magasin n'est pas rempli, juste son nom est ins?r?
	 * @param client
	 * @return client ou null
	 */
	public static Client clientexist(Client client) {
		try {
			java.sql.Statement stmt = connection().createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Client;");

			while (rs.next()) {
				if (client.getNom().equalsIgnoreCase(rs.getString("NomClient"))) {
					client.setMagasin(new Magasin(rs.getString("NomMagasin")));
					return client;
				}

			}
			stmt.close();
			rs.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return null;
	}
	/**
	 * renvoi 0 si le seuilMax n'existe pas 
	 * @param client
	 * @param materiels
	 * @param materiel
	 * @return seuilMax
	 */
	public static int seuilMax(Client client, Map<Materiel,Integer> materiels, Materiel materiel) {
		String sql = "SELECT seuil FROM seuilMax s WHERE NomCategorie = ? AND NomClient = ?;";
		int seuil = 0;
		try {
			PreparedStatement pstmt = connection().prepareStatement(sql);
			pstmt.setString(1, materiel.getCategorie().getNom());
			pstmt.setString(2, client.getNom());
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
				seuil = rs.getInt("seuil");
			rs.close();
			pstmt.close();
			for(Entry<Materiel, Integer> m : materiels.entrySet()) {
				if(m.getKey().getCategorie().getNom().equalsIgnoreCase(materiel.getCategorie().getNom()))
					seuil-= m.getValue();				
			}
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return seuil;
	}
}
