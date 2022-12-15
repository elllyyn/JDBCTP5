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
						+ "NomCategorie varchar(30),"
						+ "MaterielSubstitution varchar(30),"
						+ "PRIMARY KEY (NomMateriel),"
						+ "FOREIGN KEY (NomCategorie) REFERENCES Categorie(NomCategorie),"
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
						+ "NomMagasin varchar(30),"
						+ "PRIMARY KEY (NomClient),"
						+ "FOREIGN KEY (NomMagasin) REFERENCES Magasin(NomMagasin)"
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
						+ "NomMagasin varchar(30),"
						+ "NomMateriel varchar(30),"
						+ "quantStock int,"
						+ "FOREIGN KEY (NomMagasin) REFERENCES Magasin(NomMagasin),"
						+ "FOREIGN KEY (NomMateriel) REFERENCES Materiel(NomMateriel)"
						+ ");");
				stmt.executeUpdate("DROP TABLE IF EXISTS Commande;");
				stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Commande" 
						+ "(" 
						+ "IdCommande int AUTO_INCREMENT,"
						+ "NomClient varchar(30),"
						+ "PRIMARY KEY (IdCommande),"
						+ "FOREIGN KEY (NomClient) REFERENCES Client(NomClient)"
						+ ");");
				stmt.executeUpdate("DROP TABLE IF EXISTS ContenuCommande;");
				stmt.executeUpdate("CREATE TABLE IF NOT EXISTS ContenuCommande" 
						+ "(" 
						+ "IdCommande int,"
						+ "NomMateriel varchar(30),"
						+ "quantCom int,"
						+ "FOREIGN KEY (IdCommande) REFERENCES Commande(IdCommande),"
						+ "FOREIGN KEY (NomMateriel) REFERENCES Materiel(NomMateriel)"
						+ ");");
				stmt.executeUpdate("DROP TABLE IF EXISTS ContenuMateriel;");
				stmt.executeUpdate("CREATE TABLE IF NOT EXISTS ContenuMateriel" 
						+ "(" 
						+ "NomComposant varchar(30),"
						+ "NomMateriel varchar(30),"
						+ "FOREIGN KEY (NomComposant) REFERENCES Composant(NomComposant),"
						+ "FOREIGN KEY (NomMateriel) REFERENCES Materiel(NomMateriel)"
						+ ");");
				stmt.executeUpdate("DROP TABLE IF EXISTS SeuilMax;");
				stmt.executeUpdate("CREATE TABLE IF NOT EXISTS SeuilMax" 
						+ "(" 
						+ "NomClient varchar(30),"
						+ "NomCategorie varchar(30),"
						+ "seuil int,"
						+ "FOREIGN KEY (NomClient) REFERENCES Client(NomClient),"
						+ "FOREIGN KEY (NomCategorie) REFERENCES Categorie(NomCategorie)"
						+ ");");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	public void createData() {
		try {
			stmt.executeUpdate("INSERT INTO Categorie(NomCategorie) VALUES "
					+"('Isolation'),('Decoration');");
			stmt.executeUpdate("INSERT INTO Materiel(NomMateriel,NomCategorie, MaterielSubstitution) VALUES "
					+"('Plaque','Isolation',null),('Bougie2','Decoration',null),('Bougie','Decoration','Bougie2'),('Mousse','Isolation','Plaque');");
			stmt.executeUpdate("INSERT INTO Magasin(NomMagasin) VALUES "
					+"('BricoDepot'),('LeRoyMerlin'),('LaChaiseLongue');");
			stmt.executeUpdate("INSERT INTO Client(NomClient,NomMagasin) VALUES "
					+"('Bob','LaChaiseLongue'),('Charlotte','BricoDepot'),('Axel','LeRoyMerlin');");
			stmt.executeUpdate("INSERT INTO Composant(NomComposant) VALUES "
					+"('Bois'),('Plastique'),('Cire'),('Corde');");
			stmt.executeUpdate("INSERT INTO ContenuMagasin(NomMagasin,NomMateriel,quantStock) VALUES "
					+"('LaChaiseLongue','Bougie',40),('LaChaiseLongue','Bougie2',20),('BricoDepot','Plaque',4),('LeRoyMerlin','Mousse',30);");
			stmt.executeUpdate("INSERT INTO Commande(NomClient) VALUES "
					+"('Charlotte'),('Bob'),('Axel');");
			stmt.executeUpdate("INSERT INTO ContenuCommande(IdCommande,NomMateriel,quantCom) VALUES "
					+"(1,'Plaque',3),(2,'Plaque',1),(3,'Bougie',10);");
			stmt.executeUpdate("INSERT INTO ContenuMateriel(NomComposant,NomMateriel) VALUES "
					+"('Bois','Plaque'),('Cire','Bougie'),('Corde','Bougie'),('Cire','Bougie2'),('Corde','Bougie2'),('Plastique','Mousse');");
			stmt.executeUpdate("INSERT INTO SeuilMax(NomClient,NomCategorie,seuil) VALUES "
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