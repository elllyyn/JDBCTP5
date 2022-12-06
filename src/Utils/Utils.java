package Utils;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import visual.Visual;

public class Utils {
	public Properties userInfo;
	public Connection c;
	private String user;
	private String password;
	private String database;

	public Utils(String user, String password, String database, int create) {
		this.user = user;
		this.password = password;
		this.database = database;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException ex1) {
			System.out.println("Pilote non trouvé!");
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

	public static void main(String args[]) {
		Utils.TP();
		
		
	}
	
	public static void TP() {
		//CreateBDD bdd = new CreateBDD();
		//bdd.TP();
		//lancer le visuel
		Visual v = new Visual();
		//v.print();
	}

}
