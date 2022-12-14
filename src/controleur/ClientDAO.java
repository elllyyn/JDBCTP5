package controleur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Utils.Utils;
import modele.Client;
import modele.Magasin;
import modele.Materiel;

public class ClientDAO extends DAO {

	public static Client clientexist(Client client) {
		try {
			java.sql.Statement stmt = connection().createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Client;");

			while (rs.next()) {
				if (client.getNom().equalsIgnoreCase(rs.getString("NomClient"))) {
					client.setMagasin(new Magasin(rs.getString("NomMag")));
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

	public static int seuilMax(Client client, Materiel materiel) {
		String sql = "SELECT seuil FROM seuilMax s WHERE NomCat = ? AND NomCli = ?;";
		PreparedStatement pstmt;
		int seuil = -1;
		try {
			pstmt = connection().prepareStatement(sql);
			pstmt.setString(1, materiel.getCategorie().getNom());
			pstmt.setString(2, client.getNom());
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
				seuil = rs.getInt("seuil");
			pstmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return seuil;
	}
}
