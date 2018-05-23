package mastermind.ihm;

import java.util.Arrays;

import mastermind.jeu.Mastermind;
import mastermind.jeu.ReponseMastermind;

public class TesterMastermindConsole {

	public static void main(String[] args) {
/*
		// un jeu de mastermind
		Mastermind jeu = new Mastermind(4,6,false); // par d�faut : 4 cases, 6 couleurs, doublons autoris�s

		// une liste fixe de 6 propositions 
		int[][] grillesATester = {	{0,0,0,0},
									{0,1,2,3},
									{4,4,5,5},
									{5,4,3,2},
									{3,3,3,3},
									{3,2,3,2}	};

		// a chaque �tape on essayera "tabTest" et on obtiendra la r�ponse dans "reponse"
		int[]             tabTest; 
		ReponseMastermind reponse;
		
		System.out.println("==========================================================");
		System.out.println("== Essai d'utilisation de la classe Mastermind          ==");
		System.out.println("==========================================================");
		System.out.println("La solution : " + Arrays.toString( jeu.getSolution() ) );
		System.out.println("Les tests   :");
		for ( int i=0; i<grillesATester.length; i++) {
			
			tabTest = grillesATester[i];
			
			reponse = jeu.testerProposition( tabTest );
			
			System.out.print  (" Test "+i+" : " + Arrays.toString( tabTest ) );
			System.out.println(" R�ponse : "    + reponse.toString() );
			if ( reponse.getNbBonEmplacement() == 4 ) {
				System.out.println("YAHOUU c'est gagn� !!! (�a c'est vraiment de la chance) ");
			}
		}
		System.out.println("Bon, en trichant on a plus de chance de gagner : ");

		tabTest = jeu.getSolution();
		
		reponse = jeu.testerProposition( tabTest );

		System.out.print  (" Test X : " + Arrays.toString( tabTest ) );
		System.out.println(" R�ponse : "    + reponse.toString() );
		if ( reponse.getNbBonneCouleur()==0 && reponse.getNbErreur() == 0 ) {
			System.out.println("et voil� c'est gagn� !!! (si c'�tait pas le cas il y aurait un bug) ");
		}
		*/
		
		FrameMasterMind a = new FrameMasterMind();
		a.setVisible(true);
	}

}
