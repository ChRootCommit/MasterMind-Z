package mastermind.jeu;

import java.util.Arrays;


public class Mastermind {
	
	private int     nbPions;
	private int     nbCouleurs;
	private int[]   rangeeMystere;
	private boolean autorisePlusieursPionsMemeCouleur;
	
	// ***********************************************************************
	// ** CONSTRUCTEURS : Cr�er un nouveau jeu
	// ***********************************************************************

	/**
	 * Constructeur par d�faut.
	 * Cr�e une partie de Mastermind avec les param�tres par d�faut.
	 * 
	 * @see mastermind.jeu.Mastermind#configurationParDefaut()
	 */
	public Mastermind() {
		this.configurationParDefaut();
	}
	
	/**
	 * Constructeur param�tr�.
	 * Cr�e une partie de Mastermind avec une configuration sp�cifique
	 * @param nbPions                           le nombre souhait� (compris entre [4..8] et toujours <= au nombre de couleurs)
	 * @param nbCouleurs                        le nombre de couleurs [nbPions..12]
	 * @param autorisePlusieursPionsMemeCouleur est-il possible d'avoir plusieurs fois la m�me couleur dans la rang�e myst�re
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
	 * Constructeur param�tr�.
	 * Cr�e une partie de Mastermind avec une configuration sp�cifique
	 * en respectant les m�mes r�gles que pour le constructeur � 3 param�tres.
	 * 
	 * @param parametresMastermind tous les param�tres regroup� dans un objet de type parametresMastermind
	 */
	public Mastermind(ParametresMastermind paramatresMastermind) {
		this (paramatresMastermind.getNbPions(), paramatresMastermind.getNbCouleurs(), paramatresMastermind.isAutorisePlusieursPionsMemeCouleur() );
	}

	
	
	// ***********************************************************************
	// ** METHODES PRINCIPALES : Jouer au mastermind
	// ***********************************************************************

	/**
	 * Teste une proposition par rapport � la rang�e myst�re.
	 * 
	 * Si la proposition ne contient pas autant de case que la rang�e myst�re (erreur de programmation) , la r�ponse sera (0,0,0)
	 *  
	 * @param   tabProposition tableau de couleur propos�
	 * @return  un objet ReponseMastermind indiquant le nombre de couleurs bien plac�es, juste bonne ou fausses.
	 * @see     mastermind.jeu.ReponseMastermind
	 */
	public ReponseMastermind testerProposition( int[] tabProposition ) {
		if (tabProposition.length != this.rangeeMystere.length) {
			new ReponseMastermind(0,0,0);
		}
		int nbOK=0, nbOKOK=0;
		int[] clonePropo   = Arrays.copyOf(tabProposition, tabProposition.length);
		int[] cloneMystere = Arrays.copyOf(this.rangeeMystere, this.rangeeMystere.length);
		// d�nombrement des "OKOK" (bonne couleur et bon emplacement)
		for (int i=0; i<this.rangeeMystere.length; i++) {
			if ( clonePropo[i]==cloneMystere[i] ) {
				nbOKOK++;
				clonePropo[i]   = -1;
				cloneMystere[i] = -2;
			}
		}
		// d�nombrement des "OK" (bonne couleur mais mauvais emplacement)
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
	 * Une nouvelle rang�e myst�re est cr��e.
	 */
	public void nouvellePartie() {
		this.rangeeMystere = new int[ this.nbPions ];
		if (this.autorisePlusieursPionsMemeCouleur) {
			// si plusieurs pions de m�me couleur sont autoris�s le tirage est tr�s simple
			for ( int i=0; i<this.nbPions; i++) {
				int couleur           = (int)( Math.random() * this.nbCouleurs );
				this.rangeeMystere[i] = couleur;
			}
		} else {
			// sinon, a chaque tirage on v�rifie que la couleur n'est pas d�j� dans la rangeeMystere
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
	 * Conna�tre la rang�e myst�re.
	 * 
	 * Cette m�thode n'est pas indispensable dans le jeu mais elle a �t� ajout�e pour que les d�veloppeurs puissent plus facilement tester leur interface graphique en connaissant � l'avance la solution.
	 * En temps que d�veloppeur, vous remarquerez d'ailleurs qu'elle a �t� cod�e en �vitant de "briser l'encapsulation".
	 * 
	 * @return la rang�e myst�re actuelle sous forme d'un tableau d'entier positifs repr�sentant les couleurs des nbPions � trouver.
	 * 
	 */
	public int [] getSolution() {
		return Arrays.copyOf( this.rangeeMystere, this.nbPions );
	}
	
	// ***********************************************************************
	// ** ACCESSEURS : conna�tre ou modifier la configuration du jeu
	// ***********************************************************************

	/**
	 * Conna�tre le nombre de pions de la rang�e myst�re
	 * @return le nombre de pions
	 */
	public int getNbPions() {
		return nbPions;
	}

	/**
	 * Fixer le nombre de case de la rang�e myst�re
	 * @param nbPions le nombre souhait� (compris entre [4..8] et toujours <= au nombre de couleurs)
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
	 * Conna�tre le nombre de couleurs possibles pour chaque pion de la rang�e myst�re
	 * @return le nombre de couleurs
	 */
	public int getNbCouleurs() {
		return nbCouleurs;
	}

	/**
	 * Fixer le nombre de couleurs possibles pour chaque pion de la rang�e myst�re
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
	 * Savoir si plusieurs pions de m�me couleur sont autoris�s dans la rang�e myst�re
	 * @return true si plusieurs pions de m�me couleur sont autoris�s (false sinon)
	 */
	public boolean isAutorisePlusieursPionsMemeCouleur() {
		return autorisePlusieursPionsMemeCouleur;
	}

	/**
	 * Fixer si il est possible d'avoir plusieurs pions de m�me couleur dans la rang�e myst�re
	 * @param true si on souhaite autoriser plusieurs pions de m�me couleur (false sinon)
	 */
	public void setAutorisePlusieursPionsMemeCouleur(boolean autorisePlusieursPionsMemeCouleur) {
		this.autorisePlusieursPionsMemeCouleur = autorisePlusieursPionsMemeCouleur;
	}

	
	// ***********************************************************************
	// ** METHODES INTERNES : m�thodes utilitaires de la classe (priv�es)
	// ***********************************************************************

	/**
	 * Configuration par d�faut d'un jeu de Mastermind (m�thode priv�e)
	 */
	private void configurationParDefaut() {
		this.nbPions                           = 4;
		this.nbCouleurs                        = 6;
		this.autorisePlusieursPionsMemeCouleur = true;
		nouvellePartie();
	}
}
