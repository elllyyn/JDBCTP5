package visuel;

import java.util.Scanner;


public class Visuel {
	Scanner sf;

	public Visuel() {
		sf = new Scanner(System.in);
	}

	public void livre() {
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
		
	}
	
	public static void creerCmd() {
		
		
	}
	
	public static void quantiteMat() {
		
	}
	
	public static void afficherMatAvecComposant() {
		
	}

}
