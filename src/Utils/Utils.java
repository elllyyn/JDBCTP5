package Utils;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import visuel.Visuel;

public class Utils {
	public Properties userInfo;
	public Connection c;
	private String user;
	private String password;
	private String database;
	/**
	 * permet de se connecter ? une base de donn?es et cr?e la base de donn?es si create est ? 1
	 * @param user
	 * @param password
	 * @param database
	 * @param create
	 */
	public Utils(String user, String password, String database, int create) {
		this.user = user;
		this.password = password;
		this.database = database;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException ex1) {
			System.out.println("Pilote non trouv?!");
			System.exit(1);
		}
		userInfo = new Properties();
		userInfo.setProperty("user", user);
		userInfo.setProperty("password", password);
		if(create==1) {
			createBDD();
		}
		try {
			c = DriverManager.getConnection("jdbc:mysql://localhost/" + database, userInfo);
		} catch (SQLException ex2) {
			ex2.printStackTrace();
			System.exit(1);
		}
		

	}
	/**
	 * supprime la base de donn?es si elle existe puis la recr??e
	 */
	public void createBDD() {
		
		try {

			c = DriverManager.getConnection("jdbc:mysql://localhost/", userInfo);

			java.sql.Statement stmt = c.createStatement();

			stmt.executeUpdate("DROP DATABASE IF EXISTS " + database);
			stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS " + database);
		} catch (SQLException ex2) {
			ex2.printStackTrace();
			System.exit(1);
		}
	}

}
