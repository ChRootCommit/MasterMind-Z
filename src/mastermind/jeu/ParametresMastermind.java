package mastermind.jeu;


public class ParametresMastermind {
	
	private int     nbCouleurs;
	private int     nbPions;
	private int     nbTentatives;
	private boolean autorisePlusieursPionsMemeCouleur;
	
	public ParametresMastermind() {
		this (6,4,10,true);
	}

	public ParametresMastermind(int nbCouleurs, int nbPions, int nbTentatives, boolean autorisePlusieursPionsMemeCouleur) {
		this.nbCouleurs = nbCouleurs;
		this.nbPions = nbPions;
		this.nbTentatives = nbTentatives;
		this.autorisePlusieursPionsMemeCouleur = autorisePlusieursPionsMemeCouleur;
	}
	public int getNbCouleurs() {
		return nbCouleurs;
	}
	public void setNbCouleurs(int nbCouleurs) {
		this.nbCouleurs = nbCouleurs;
	}
	public int getNbPions() {
		return nbPions;
	}
	public void setNbPions(int nbPions) {
		this.nbPions = nbPions;
	}
	public int getNbTentatives() {
		return nbTentatives;
	}
	public void setNbTentatives(int nbTentatives) {
		this.nbTentatives = nbTentatives;
	}
	public boolean isAutorisePlusieursPionsMemeCouleur() {
		return autorisePlusieursPionsMemeCouleur;
	}
	public void setAutorisePlusieursPionsMemeCouleur(boolean autorisePlusieursPionsMemeCouleur) {
		this.autorisePlusieursPionsMemeCouleur = autorisePlusieursPionsMemeCouleur;
	}

}
