package controleur;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import Utils.Utils;
import modele.Client;
import modele.Magasin;

public class ClientDAO {
private static Utils connection = new Utils("ellyn", "ellyn", "Entreprise", 0);
	
	public static Connection connection() {
		return connection.c;
		
	}
	public static Client clientexist(Client client) {
		try {
			java.sql.Statement stmt = connection().createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Client;");

			while (rs.next()) {
				if(client.getNom().equalsIgnoreCase(rs.getString("NomClient"))) {
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
}
