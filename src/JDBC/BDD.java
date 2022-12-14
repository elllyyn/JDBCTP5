package JDBC;
import java.sql.ResultSet;

import java.sql.SQLException;

import Utils.Utils;

public class BDD {
	
	public static Utils connection;//permet la connexion BDD
	private java.sql.Statement stmt;
	ResultSet rs;
	
	public BDD() {
		connection = new Utils("root", "root", "Entreprise", 1);
		try {
			
			stmt = connection.c.createStatement();
	
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void createTable() {
			
			try {
				stmt.executeUpdate("DROP TABLE IF EXISTS Categorie;");
				stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Categorie ("
						+ "NomCategorie varchar(30),"
						+ "PRIMARY KEY (NomCategorie)"
						+ ");");
				stmt.executeUpdate("DROP TABLE IF EXISTS Materiel;");
				stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Materiel ("
						+ "NomMateriel varchar(30),"
						+ "NomCat varchar(30),"
						+ "MaterielSubstitution varchar(30),"
						+ "PRIMARY KEY (NomMateriel),"
						+ "FOREIGN KEY (NomCat) REFERENCES Categorie(NomCategorie),"
						+ "FOREIGN KEY (MaterielSubstitution) REFERENCES Materiel(NomMateriel)"
						+ ");");
				
				stmt.executeUpdate("DROP TABLE IF EXISTS Magasin;");
				stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Magasin" 
						+ "(" 
						+ "NomMagasin varchar(30),"
						+ "PRIMARY KEY (NomMagasin)"
						+ ");");
				stmt.executeUpdate("DROP TABLE IF EXISTS Client;");
				stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Client" 
						+ "(" 
						+ "NomClient varchar(30),"
						+ "NomMag varchar(30),"
						+ "PRIMARY KEY (NomClient),"
						+ "FOREIGN KEY (NomMag) REFERENCES Magasin(NomMagasin)"
						+ ");");
				stmt.executeUpdate("DROP TABLE IF EXISTS Composant;");
				stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Composant" 
						+ "(" 
						+ "NomComposant varchar(30),"
						+ "PRIMARY KEY (NomComposant)"
						+ ");");
				stmt.executeUpdate("DROP TABLE IF EXISTS ContenuMagasin;");
				stmt.executeUpdate("CREATE TABLE IF NOT EXISTS ContenuMagasin" 
						+ "("
						+ "NomMag varchar(30),"
						+ "NomMat varchar(30),"
						+ "quantStock int,"
						+ "FOREIGN KEY (NomMag) REFERENCES Magasin(NomMagasin),"
						+ "FOREIGN KEY (NomMat) REFERENCES Materiel(NomMateriel)"
						+ ");");
				stmt.executeUpdate("DROP TABLE IF EXISTS Commande;");
				stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Commande" 
						+ "(" 
						+ "IdCommande int AUTO_INCREMENT,"
						+ "NomCli varchar(30),"
						+ "PRIMARY KEY (IdCommande),"
						+ "FOREIGN KEY (NomCli) REFERENCES Client(NomClient)"
						+ ");");
				stmt.executeUpdate("DROP TABLE IF EXISTS ContenuCommande;");
				stmt.executeUpdate("CREATE TABLE IF NOT EXISTS ContenuCommande" 
						+ "(" 
						+ "IdCom int,"
						+ "NomMat varchar(30),"
						+ "quantCom int,"
						+ "FOREIGN KEY (IdCom) REFERENCES Commande(IdCommande),"
						+ "FOREIGN KEY (NomMat) REFERENCES Materiel(NomMateriel)"
						+ ");");
				stmt.executeUpdate("DROP TABLE IF EXISTS ContenuMateriel;");
				stmt.executeUpdate("CREATE TABLE IF NOT EXISTS ContenuMateriel" 
						+ "(" 
						+ "NomCom varchar(30),"
						+ "NomMat varchar(30),"
						+ "FOREIGN KEY (NomCom) REFERENCES Composant(NomComposant),"
						+ "FOREIGN KEY (NomMat) REFERENCES Materiel(NomMateriel)"
						+ ");");
				stmt.executeUpdate("DROP TABLE IF EXISTS SeuilMax;");
				stmt.executeUpdate("CREATE TABLE IF NOT EXISTS SeuilMax" 
						+ "(" 
						+ "NomCli varchar(30),"
						+ "NomCat varchar(30),"
						+ "seuil int,"
						+ "FOREIGN KEY (NomCli) REFERENCES Client(NomClient),"
						+ "FOREIGN KEY (NomCat) REFERENCES Categorie(NomCategorie)"
						+ ");");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	public void createData() {
		try {
			stmt.executeUpdate("INSERT INTO Categorie(NomCategorie) VALUES "
					+"('Isolation'),('Decoration');");
			stmt.executeUpdate("INSERT INTO Materiel(NomMateriel,NomCat, MaterielSubstitution) VALUES "
					+"('Plaque','Isolation',null),('Bougie2','Decoration',null),('Bougie','Decoration','Bougie2'),('Mousse','Isolation','Plaque');");
			stmt.executeUpdate("INSERT INTO Magasin(NomMagasin) VALUES "
					+"('BricoDepot'),('LeRoyMerlin'),('LaChaiseLongue');");
			stmt.executeUpdate("INSERT INTO Client(NomClient,NomMag) VALUES "
					+"('Bob','LaChaiseLongue'),('Charlotte','LeRoyMerlin'),('Axel','LeRoyMerlin');");
			stmt.executeUpdate("INSERT INTO Composant(NomComposant) VALUES "
					+"('Bois'),('Plastique'),('Cire'),('Corde');");
			stmt.executeUpdate("INSERT INTO ContenuMagasin(NomMag,NomMat,quantStock) VALUES "
					+"('LaChaiseLongue','Bougie',40),('LaChaiseLongue','Bougie2',10),('BricoDepot','Plaque',4),('LeRoyMerlin','Mousse',30);");
			stmt.executeUpdate("INSERT INTO Commande(NomCli) VALUES "
					+"('Charlotte'),('Bob'),('Axel');");
			stmt.executeUpdate("INSERT INTO ContenuCommande(IdCom,NomMat,quantCom) VALUES "
					+"(1,'Plaque',3),(2,'Plaque',1),(3,'Bougie',10);");
			stmt.executeUpdate("INSERT INTO ContenuMateriel(NomCom,NomMat) VALUES "
					+"('Bois','Plaque'),('Cire','Bougie'),('Corde','Bougie'),('Cire','Bougie2'),('Corde','Bougie2'),('Plastique','Mousse');");
			stmt.executeUpdate("INSERT INTO SeuilMax(NomCli,NomCat,seuil) VALUES "
					+"('Bob','Isolation',100),('Bob','Decoration',50),('Axel','Decoration',100),('Axel','Isolation',100),('Charlotte','Isolation',100),('Charlotte','Decoration',100);");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void JDBC() {
		createTable();
		createData();
		
	}
	
	public static void main(String[] args) {
		BDD b = new BDD();
		b.JDBC();
	}

}