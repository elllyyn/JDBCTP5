package controleur;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map.Entry;

import modele.Commande;
import modele.Materiel;

public class CommandeDAO extends DAO{
	/**
	 * creer une commande, insère dans contenuCommande et retire dans contenuMagasin
	 * @param commande
	 */
	public static void creerCommande(Commande commande) {		
		try {
			String sql = "INSERT INTO Commande(NomClient) VALUES (?);";
			PreparedStatement pstmt = connection().prepareStatement(sql);
			pstmt.setString(1, commande.getClient().getNom());
			pstmt.execute();
			
			sql = "SELECT IdCommande FROM Commande WHERE NomClient = ?";
			pstmt = connection().prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			pstmt.setString(1, commande.getClient().getNom());
			ResultSet rs = pstmt.executeQuery();
			rs.afterLast();
			rs.previous();
			commande.setId(rs.getInt("IdCommande"));
			for(Entry<Materiel, Integer> m : commande.getMateriels().entrySet()) {
				sql = "INSERT INTO ContenuCommande(IdCommande,NomMateriel,quantCom) VALUES (?,?,?);";	
				pstmt = connection().prepareStatement(sql);
				pstmt.setInt(1, commande.getId());
				pstmt.setString(2, m.getKey().getNom());
				pstmt.setInt(3, m.getValue());
				pstmt.execute();
				
				//retirer le contenu du magasin
				int quantStock = MagasinDAO.quantiteMaterielDansMagasin(commande.getClient().getMagasin().getNom(), m.getKey().getNom());
				int quantCmd = m.getValue();
				if(quantCmd > quantStock) {
					int substock = MagasinDAO.quantiteSubstitutionMateriel(commande.getClient().getMagasin().getNom(), m.getKey().getNom());
					
					sql = "UPDATE contenuMagasin SET quantStock = ? WHERE NomMateriel = ?;";
					pstmt = connection().prepareStatement(sql);
					pstmt.setInt(1, substock - quantCmd + quantStock);
					pstmt.setString(2, m.getKey().getSubstitution().getNom());
					pstmt.execute();
					quantCmd = quantStock;
				}
					
				sql = "UPDATE contenuMagasin SET quantStock = ? WHERE NomMateriel = ?;";
				pstmt = connection().prepareStatement(sql);
				pstmt.setInt(1, quantStock - quantCmd);
				pstmt.setString(2, m.getKey().getNom());
				pstmt.execute();
			}
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


}
