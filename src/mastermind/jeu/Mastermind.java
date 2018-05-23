package mastermind.jeu;

import java.util.Arrays;


public class Mastermind {
	
	private int     nbPions;
	private int     nbCouleurs;
	private int[]   rangeeMystere;
	private boolean autorisePlusieursPionsMemeCouleur;
	
	// ***********************************************************************
	// ** CONSTRUCTEURS : Créer un nouveau jeu
	// ***********************************************************************

	/**
	 * Constructeur par défaut.
	 * Crée une partie de Mastermind avec les paramètres par défaut.
	 * 
	 * @see mastermind.jeu.Mastermind#configurationParDefaut()
	 */
	public Mastermind() {
		this.configurationParDefaut();
	}
	
	/**
	 * Constructeur paramétré.
	 * Crée une partie de Mastermind avec une configuration spécifique
	 * @param nbPions                           le nombre souhaité (compris entre [4..8] et toujours <= au nombre de couleurs)
	 * @param nbCouleurs                        le nombre de couleurs [nbPions..12]
	 * @param autorisePlusieursPionsMemeCouleur est-il possible d'avoir plusieurs fois la même couleur dans la rangée mystère
	 */
	public Mastermind(int nbPions, int nbCouleurs, boolean autorisePlusieursPionsMemeCouleur) {
		if (nbPions<4 || nbPions>8 || nbPions>nbCouleurs || nbCouleurs>12) {
			this.configurationParDefaut();
		} else {
			this.nbPions                           = nbPions;
			this.nbCouleurs                        = nbCouleurs;
			this.autorisePlusieursPionsMemeCouleur = autorisePlusieursPionsMemeCouleur;
			this.nouvellePartie();
		}
	}

	/**
	 * Constructeur paramétré.
	 * Crée une partie de Mastermind avec une configuration spécifique
	 * en respectant les mêmes règles que pour le constructeur à 3 paramètres.
	 * 
	 * @param parametresMastermind tous les paramètres regroupé dans un objet de type parametresMastermind
	 */
	public Mastermind(ParametresMastermind paramatresMastermind) {
		this (paramatresMastermind.getNbPions(), paramatresMastermind.getNbCouleurs(), paramatresMastermind.isAutorisePlusieursPionsMemeCouleur() );
	}

	
	
	// ***********************************************************************
	// ** METHODES PRINCIPALES : Jouer au mastermind
	// ***********************************************************************

	/**
	 * Teste une proposition par rapport à la rangée mystère.
	 * 
	 * Si la proposition ne contient pas autant de case que la rangée mystère (erreur de programmation) , la réponse sera (0,0,0)
	 *  
	 * @param   tabProposition tableau de couleur proposé
	 * @return  un objet ReponseMastermind indiquant le nombre de couleurs bien placées, juste bonne ou fausses.
	 * @see     mastermind.jeu.ReponseMastermind
	 */
	public ReponseMastermind testerProposition( int[] tabProposition ) {
		if (tabProposition.length != this.rangeeMystere.length) {
			new ReponseMastermind(0,0,0);
		}
		int nbOK=0, nbOKOK=0;
		int[] clonePropo   = Arrays.copyOf(tabProposition, tabProposition.length);
		int[] cloneMystere = Arrays.copyOf(this.rangeeMystere, this.rangeeMystere.length);
		// dénombrement des "OKOK" (bonne couleur et bon emplacement)
		for (int i=0; i<this.rangeeMystere.length; i++) {
			if ( clonePropo[i]==cloneMystere[i] ) {
				nbOKOK++;
				clonePropo[i]   = -1;
				cloneMystere[i] = -2;
			}
		}
		// dénombrement des "OK" (bonne couleur mais mauvais emplacement)
		for (int i=0; i<this.rangeeMystere.length; i++) {
			for (int j=0; j<this.rangeeMystere.length; j++) {
				if ( clonePropo[i]==cloneMystere[j] ) {
					nbOK++;
					clonePropo[i]   = -1;
					cloneMystere[j] = -2;
				}
			}
		}
		// au final, les erreurs correspondent au reste
		return new ReponseMastermind(nbOKOK, nbOK, this.rangeeMystere.length-nbOKOK-nbOK);		
	}

	/**
	 * Lancer une nouvelle partie.
	 * Une nouvelle rangée mystère est créée.
	 */
	public void nouvellePartie() {
		this.rangeeMystere = new int[ this.nbPions ];
		if (this.autorisePlusieursPionsMemeCouleur) {
			// si plusieurs pions de même couleur sont autorisés le tirage est très simple
			for ( int i=0; i<this.nbPions; i++) {
				int couleur           = (int)( Math.random() * this.nbCouleurs );
				this.rangeeMystere[i] = couleur;
			}
		} else {
			// sinon, a chaque tirage on vérifie que la couleur n'est pas déjà dans la rangeeMystere
			boolean doublonDeCouleur;
			int     couleur;

			for ( int i=0; i<this.nbPions; i++) {
				do {
					couleur = (int)( Math.random() * this.nbCouleurs );
					doublonDeCouleur = false;
					for (int j=0; j<i; j++) {
						if ( this.rangeeMystere[j] == couleur ) {
							doublonDeCouleur = true;
						}
					}
				} while (doublonDeCouleur);
				this.rangeeMystere[i] = couleur; 
			}
		}
	}
	
	/**
	 * Connaître la rangée mystère.
	 * 
	 * Cette méthode n'est pas indispensable dans le jeu mais elle a été ajoutée pour que les développeurs puissent plus facilement tester leur interface graphique en connaissant à l'avance la solution.
	 * En temps que développeur, vous remarquerez d'ailleurs qu'elle a été codée en évitant de "briser l'encapsulation".
	 * 
	 * @return la rangée mystère actuelle sous forme d'un tableau d'entier positifs représentant les couleurs des nbPions à trouver.
	 * 
	 */
	public int [] getSolution() {
		return Arrays.copyOf( this.rangeeMystere, this.nbPions );
	}
	
	// ***********************************************************************
	// ** ACCESSEURS : connaître ou modifier la configuration du jeu
	// ***********************************************************************

	/**
	 * Connaître le nombre de pions de la rangée mystère
	 * @return le nombre de pions
	 */
	public int getNbPions() {
		return nbPions;
	}

	/**
	 * Fixer le nombre de case de la rangée mystère
	 * @param nbPions le nombre souhaité (compris entre [4..8] et toujours <= au nombre de couleurs)
	 */
	public void setNbPions(int nbPions) {
		if (nbPions<4 || nbPions>8 || nbPions>this.nbCouleurs) {
			this.configurationParDefaut();
		} else {
			this.nbPions = nbPions;
			this.nouvellePartie();
		}
	}

	/**
	 * Connaître le nombre de couleurs possibles pour chaque pion de la rangée mystère
	 * @return le nombre de couleurs
	 */
	public int getNbCouleurs() {
		return nbCouleurs;
	}

	/**
	 * Fixer le nombre de couleurs possibles pour chaque pion de la rangée mystère
	 * @param nbCouleurs le nombre de couleurs [nbPions..12]
	 */
	public void setNbCouleurs(int nbCouleurs) {
		if (nbCouleurs<this.nbPions || nbCouleurs>12) {
			this.configurationParDefaut();
		} else {
			this.nbCouleurs = nbCouleurs;
			this.nouvellePartie();
		}
	}
	
	/**
	 * Savoir si plusieurs pions de même couleur sont autorisés dans la rangée mystère
	 * @return true si plusieurs pions de même couleur sont autorisés (false sinon)
	 */
	public boolean isAutorisePlusieursPionsMemeCouleur() {
		return autorisePlusieursPionsMemeCouleur;
	}

	/**
	 * Fixer si il est possible d'avoir plusieurs pions de même couleur dans la rangée mystère
	 * @param true si on souhaite autoriser plusieurs pions de même couleur (false sinon)
	 */
	public void setAutorisePlusieursPionsMemeCouleur(boolean autorisePlusieursPionsMemeCouleur) {
		this.autorisePlusieursPionsMemeCouleur = autorisePlusieursPionsMemeCouleur;
	}

	
	// ***********************************************************************
	// ** METHODES INTERNES : méthodes utilitaires de la classe (privées)
	// ***********************************************************************

	/**
	 * Configuration par défaut d'un jeu de Mastermind (méthode privée)
	 */
	private void configurationParDefaut() {
		this.nbPions                           = 4;
		this.nbCouleurs                        = 6;
		this.autorisePlusieursPionsMemeCouleur = true;
		nouvellePartie();
	}
}
