package controleur;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import Utils.Utils;
import modele.Composant;
import modele.Magasin;

public class ComposantDAO {
	private static Utils connection = new Utils("ellyn", "ellyn", "Entreprise", 0);

	public static Connection connection() {
		return connection.c;
	}

	public static Composant composantExist(Composant composant) {
		try {
			java.sql.Statement stmt = connection().createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Composant;");

			while (rs.next()) {
				if (composant.getNom().equalsIgnoreCase(rs.getString("NomComposant"))) {
					return composant;
				}

			}
			stmt.close();
			rs.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return null;
	}

}
