package visuel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import controleur.GestionClient;
import controleur.GestionCommande;
import controleur.GestionComposant;
import controleur.GestionMagasin;
import controleur.GestionMateriel;
import modele.Client;
import modele.Composant;
import modele.Magasin;
import modele.Materiel;



public class Visuel {
	private static Scanner sf;

	public static void main(String[] args) {
		Visuel.choix();
	}

	public static void choix() {
		sf = new Scanner(System.in);
		System.out.println("Choisissez une action : " + '\n' 
				+ "0 - afficher le contenu d'un magasin" + '\n'
				+ "1 - creer une commande" + '\n' 
				+ "2 - obtenir la quantité disponible d'un materiel dans un magasin" + '\n'
				+ "3 - afficher les matériaux avec un composant" + '\n'
				+ "default - ne rien faire");
		int choix = sf.nextInt();
		switch (choix) {
		case 0:
			afficherContenu();
			break;
		case 1:
			creerCmd();
			break;
		case 2:
			quantiteMat();
			break;
		case 3:
			afficherMatAvecComposant();
			break;
		default:
			System.out.println("Rien choisit !");
			break;
		}
		sf.close();

	}
	
	public static void afficherContenu() {
		Map<Materiel, Integer>  contenuMagasin= null;
		while(contenuMagasin == null) {
			System.out.println("Donner le nom du Magasin : ");
			String MonMag = sf.nextLine();
			contenuMagasin = GestionMagasin.afficherContenuMagasin(MonMag);
			if(contenuMagasin==null)
				System.out.println("Ce magasin n'existe pas dans la base, recommencer");
		}
	}
	
	public static void creerCmd() {
		Client client= null;
		while(client == null) {
			System.out.println("Donner le nom du client : ");
			String nomClient = sf.nextLine();
			client = GestionClient.clientexist(new Client(nomClient, null));
			if(client==null)
				System.out.println("Ce client n'existe pas dans la base, recommencer");
		}
		client.getMagasin().setContenu(GestionMagasin.afficherContenuMagasin(client.getMagasin().getNom()));
		Boolean ajouter = true;
		Map<Materiel, Integer> materiels = new HashMap<Materiel, Integer>();
		while(ajouter) {
			Materiel materiel = null;
			while(materiel == null) {
				System.out.println("Donner nom materiel : ");
				String nomMat = sf.next();
				materiel = GestionMateriel.materielexist(new Materiel(nomMat, null), client.getMagasin());
				if(materiel==null)
					System.out.println("Ce materiel n'existe pas dans la magasin, recommencer");
			}	
			
			System.out.println("Donner le nombre à commander : ");
			int nombreMateriel = sf.nextInt();
			//seulmax + try catch
			//et verif dispo
			materiels.put(materiel,nombreMateriel);
			System.out.println("Voulez ajouter un matériel à votre commande : true pour oui et false sinon ");
			ajouter = sf.nextBoolean();
		}
		GestionCommande.creerCmd();
	}
	
	public static void quantiteMat() {
		
	}
	
	public static void afficherMatAvecComposant() {
		Composant composant = null;
		while(composant == null) {
			System.out.println("Donner le nom du composant : ");
			String nomComp = sf.next();
			composant = GestionComposant.compexist(new Composant(nomComp));
			if(composant ==null)
				System.out.println("Ce materiel n'existe pas dans la magasin, recommencer");
		}
		List<Materiel> materiels = GestionMateriel.materielAvecComposant(composant);
		for(Materiel m : materiels) {
			System.out.println("Materiel : "+m.getNom()+" de la catégorie "+ m.getCategorie().getNom());
		}
	}

}
