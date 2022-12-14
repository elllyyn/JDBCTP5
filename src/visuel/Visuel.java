package visuel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import controleur.GestionClient;
import controleur.GestionCommande;
import controleur.GestionComposant;
import controleur.GestionMagasin;
import controleur.GestionMateriel;
import modele.Client;
import modele.Commande;
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
			System.out.println("Rien choisi !");
			break;
		}
		sf.close();

	}
	
	public static void afficherContenu() {
		Map<Materiel, Integer>  contenuMagasin= null;
		while(contenuMagasin == null) {
			System.out.println("Donner le nom du Magasin : ");
			String MonMag = sf.next();
			contenuMagasin = GestionMagasin.afficherContenuMagasin(MonMag);
			if(contenuMagasin==null)
				System.out.println("Ce magasin n'existe pas dans la base, recommencer");
		}
		System.out.print("Nom materiel :      ");
		System.out.println("Quantité materiel :");

		for(Entry<Materiel,Integer> e : contenuMagasin.entrySet()) {
			String nomMat = e.getKey().getNom();
			int nbMat = e.getValue();
			System.out.print(nomMat);
			System.out.print("      ");
			System.out.println(nbMat);
		}
	}
	
	public static void creerCmd() {
		Client client = connexionClient();
		client.getMagasin().setContenu(GestionMagasin.afficherContenuMagasin(client.getMagasin().getNom()));
		
		Map<Materiel, Integer> materiels = faireCmd(client);
		
		Commande commande = new Commande(0, client, materiels);
		GestionCommande.creerCmd(commande);
	}
	
	public static Map<Materiel, Integer> faireCmd(Client client){
		Boolean ajouter = true;
		Map<Materiel, Integer> materiels = new HashMap<Materiel, Integer>();
		// continue de demander des produits tant que l'on veut ajouter un produit
		while(ajouter) {
			//verification que le produit existe bien dans le magasin associé au client
			Materiel materiel = choisirMat(client);	
			int nombreMaterielDepart = 0;
			// si dans la commande le produit a déjà été ajouté on récupère le nombre déjà récupéré
			for(Entry<Materiel, Integer> m : materiels.entrySet()) {
				if(m.getKey().getNom().equalsIgnoreCase(materiel.getNom()))
					System.out.println("Vous avez déjà choisit ce produit, choisissez le nombre de produit à ajouter");
				nombreMaterielDepart = m.getValue();		
			}
			
			System.out.println("Donner le nombre à commander : ");
			int nombreMateriel = nombreMaterielDepart + sf.nextInt();
			int sub = 0;
			//verification que le matériel et/ou son substitue est en stock
			while(true) {
				if(nombreMateriel < GestionMagasin.quantiteDansMagasin(client.getMagasin().getNom(), materiel.getNom())){
					break;
				}else {
					sub = GestionMagasin.substitutionUtilisé(client.getMagasin().getNom(), materiel.getNom(), nombreMateriel);
					if(sub ==-1) {
						System.out.println("Il n'y a pas assez de produit, donner un nombre inférieur : ");
						nombreMateriel = nombreMaterielDepart + sf.nextInt();
					}else {
						break;
					}
				}
			}
			//verification que le seuil max du client pour la commande n'est pas dépassé
			while(GestionClient.seuilMaxAtteint(client, materiels, materiel, nombreMateriel-nombreMaterielDepart)) {
				System.out.println("Le seuil maximal est dépassé, donner un nombre inférieur à commander : ");
				nombreMateriel = nombreMaterielDepart +sf.nextInt();
			}
			//mise à jour ou ajout du nombre de produit demandé
			if(nombreMaterielDepart>0) {
				for(Entry<Materiel, Integer> m : materiels.entrySet()) {
					if(m.getKey().getNom().equalsIgnoreCase(materiel.getNom()))
						materiels.put(m.getKey(), nombreMateriel);
				}
			}else
				materiels.put(materiel,nombreMateriel);
			System.out.println("Voulez-vous ajouter un autre matériel à votre commande ? true pour oui et false sinon ");
			ajouter = sf.nextBoolean();
		}
		return materiels;
	}
	
	public static Client connexionClient() {
		Client client= null;
		while(client == null) {
			System.out.println("Donner le nom du client : ");
			String nomClient = sf.next();
			client = GestionClient.clientexist(new Client(nomClient, null));
			if(client==null)
				System.out.println("Ce client n'existe pas dans la base, recommencer");
		}
		return client;
	}
	
	public static Materiel choisirMat(Client client) {
		Materiel materiel = null;
		while(materiel == null) {
			System.out.println("Donner nom materiel : ");
			String nomMat = sf.next();
			materiel = GestionMateriel.materielexist(new Materiel(nomMat, null), client.getMagasin());
			if(materiel==null)
				System.out.println("Ce materiel n'existe pas dans la magasin, recommencer");
		}
		
		return materiel;
	}
	
	public static void quantiteMat() {
		String NomMag = " ";
		String NomMat = " ";

		while(NomMag == " " ) {
			System.out.println("Donner le nom du magasin : ");
			String Mag = sf.next();
			if(GestionMagasin.magasinExiste(Mag)!=true) {
				System.out.println("Ce magasin n'existe pas.");
			}else {
				NomMag = Mag;
			}
		}
		while(NomMat==" ") {
			System.out.println("Donner le nom du materiel : ");
			String Mat = sf.next();
			Magasin magasin = new Magasin(NomMag);
			Materiel materiel = new Materiel(Mat, null);
			if(GestionMateriel.materielexist(materiel,magasin)==null) {
				System.out.println("Ce materiel n'existe pas dans ce magasin.");
			}
			else {
				NomMat = Mat;
			}
		}
		int nbMat = GestionMagasin.quantiteDansMagasin(NomMag, NomMat);
		System.out.println("La quantité de ce materiel dans ce magasin est de "+nbMat);
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
