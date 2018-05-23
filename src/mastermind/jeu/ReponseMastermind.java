package mastermind.jeu;


public class ReponseMastermind {
	private int nbBonEmplacement;
	private int nbBonneCouleur;
	private int nbErreur;
	
	/**
	 * Constructeur VISIBILITE PACKAGE (un objet ReponseMastermind ne peut être créé que dans le package mastermind.jeu)
	 * @param nbBonEmplacement
	 * @param nbBonneCouleur
	 * @param nbErreur
	 */
	ReponseMastermind(int nbBonEmplacement, int nbBonneCouleur, int nbErreur) {
		super();
		this.nbBonEmplacement = nbBonEmplacement;
		this.nbBonneCouleur = nbBonneCouleur;
		this.nbErreur = nbErreur;
	}

	public int getNbBonEmplacement() {
		return this.nbBonEmplacement;
	}

	public int getNbBonneCouleur() {
		return this.nbBonneCouleur;
	}

	public int getNbErreur() {
		return this.nbErreur;
	}
	
	@Override
	public String toString() {
		return this.nbBonEmplacement+ " bien placé(s) / " + this.nbBonneCouleur + " mal placé(s) / " + this.nbErreur + " faux";
	}

}
