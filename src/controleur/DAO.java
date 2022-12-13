package controleur;

import java.sql.Connection;

import Utils.Utils;

public class DAO {
private static Utils connection = new Utils("root", "root", "Entreprise", 0);
	
	public static Connection connection() {
		return connection.c;
		
	}

}
