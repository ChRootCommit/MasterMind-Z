/**
 * Interface IHM permettant à l'utilisateur de jouer au MasterMind
 * 
 * @author Jonathan
 * @version 1.0
 */

package mastermind.ihm;
import javax.management.MalformedObjectNameException;
import javax.swing.*
;
import java.util.HashSet;
import java.util.Set;
import mastermind.jeu.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectOutputStream.PutField;
public class FrameMasterMind extends JFrame implements ActionListener {
	
	
	//les commandes d'actions
	public final String START_NEW_GAME  = "nouvelle partie";
	public final String CHEAT_ACTION = "tricher";
	public final String PUT_YOUR_COLOR = "Joue une couleur";
	public final String CANCEL_ACTION ="annuler";
	public final String VALIDATE_ACTION = "valider";
	public final String MORE_INFO = "Plus d'information";
	public final String EXIT_GAME = "quitter le jeu";
	public final String NEW_CUSTOM_GAME = "Nouvelle partie personnalise";
	 
	//la liste de couleurs possédé
	public Set<Couleurs> cList;
	private Mastermind game;
	//boolean permettant définir si oui ou non l'utilisateur peut jouer. L'utilisateur ne peut jouer que si une partie est crée et qu'il n'a pas perdu.
	public boolean peutContinuerGame = false;

	//la pluspart des composant je les met en attribut au cas oï¿½ j'ai vraiment besoins de les rï¿½utiliser
	
	public int indiceCouleur; //indice de la case où on veut proposer une couleurs
	public int putIterator; //variable permettant de parcourrir une ligne de pion 
	public int tentative; // le nombre de tentative qui incrementé au fur et à mesur du jeu
	public int indiceIndice; //L'index actuelle de des indices menant à la solution
	
	private JPanel solutionP;
	private JPanel milieuCase;
	private JPanel bottomP;
	private	JPanel secondLineBot;
	private JPanel topP;
	private JPanel firstLineBot;
	private JPanel thirdLineBot;
	private JPanel indiceP;
	
	private JButton cheat; //bouton de triche
	
	private JMenu menuI;
	private JMenuBar menuB;
	private JMenuItem infoI;
	private JMenu menuSetting;
	private JMenuItem newGame;
	private JMenuItem exit;
	
	
	private JButton moinsDeCase;
	private JButton plusDecase; 
	private JButton nbPion;
	private JButton moinsColor;
	private JButton plusColor;
	private JButton valide;
	private JButton cancel;
	private JButton[] color;
	private JButton nbColorBut;
	private JButton customGame;
	//les images de cercle de couleurs que nous avons téléchargé seront entré en parametre dans les labes afin qu'elles puissent etre affiché
	private JLabel[] solution; 
	private JLabel[] caseGame;
	private JLabel[] indice;
	
	
	private ParametresMastermind setting; //paramètres du jeux
	
	
	
	
	//jeu par défault donc 4 case par défaut et 6 couleurs par défault
	
	/******
	 * Constructeur par défaut permettant d'initialiser tout les composants dont l'interface a besoins <BR>
	 * pour que l'utilisateur puisse jouer à Mastermind
	 * 
	 * 
	 */
	public FrameMasterMind() {
		setting = new ParametresMastermind();
		this.cList = new HashSet<Couleurs> ();
		this.solution = new JLabel[setting.getNbPions()];
		this.setImage(this.solution,"img/db.png");
		this.getContentPane().setBackground(new Color(0, 139, 232));
		this.setLayout(new BorderLayout());
		this.infoI = new JMenuItem("Information");
		this.infoI.setActionCommand(MORE_INFO);
		this.infoI.addActionListener(this);
		this.setTitle("MasterMind 1.0");
		
		this.menuI = new  JMenu("?");
		this.menuI.add(this.infoI);
		
		this.menuSetting = new JMenu("options"); 
		this.newGame = new JMenuItem("Nouvelle partie");
		this.newGame.setActionCommand(START_NEW_GAME);
		this.exit = new JMenuItem("Quitter");
		this.menuSetting.add(this.newGame);
		this.menuSetting.add(this.exit);
		this.exit.setActionCommand(EXIT_GAME);
		this.exit.addActionListener(this);
		this.menuB = new JMenuBar();
		this.menuB.add(this.menuI);
		this.menuB.add(this.menuSetting);
		
		this.solutionP = new JPanel();
		this.solutionP.setLayout(new FlowLayout());
		this.solutionP.setBackground(new Color(0,139,232));
		this.solutionP.setBorder(BorderFactory.createLineBorder(Color.black));
		this.addImageToPanel(this.solutionP, this.solution);
		this.cheat = new JButton("Tricher");
		this.cheat.setActionCommand(CHEAT_ACTION);
		this.solutionP.add(this.cheat);
		this.topP = new JPanel();
		this.topP.setLayout(new GridLayout(2,2));
		this.topP.add(this.menuB);
		this.topP.add(this.solutionP);
		
		
		this.milieuCase = new JPanel();
		this.caseGame = new JLabel[setting.getNbPions() * setting.getNbTentatives()];
		this.setImage(this.caseGame, "img/CircleColor/rondVide.png");
		
		this.milieuCase.setLayout(new GridLayout(this.setting.getNbTentatives(),this.setting.getNbPions()));
		this.addImageToPanel(this.milieuCase, this.caseGame);
		this.milieuCase.setBackground(new Color(0,139,232));
		
		this.bottomP = new JPanel();
		this.bottomP.setLayout(new GridLayout(3,0));
		this.plusDecase = new JButton("+");
		this.moinsDeCase = new JButton("-");
		JLabel labelPion = new JLabel("Nombre de Pion:");
		JPanel infoPion = new JPanel();
		infoPion.setLayout(new FlowLayout());
		infoPion.add(labelPion);
		this.nbPion = new JButton(String.valueOf(setting.getNbPions()));
		infoPion.add(this.nbPion);
		
		this.plusColor = new JButton("+");
		this.moinsColor = new JButton("-");
		JLabel infoColorL = new JLabel("Nombre de couleur");
		JPanel infoCouleur = new JPanel();
		this.nbColorBut = new JButton(String.valueOf(setting.getNbCouleurs()));
		this.customGame = new JButton("Nouvelle partie personnalisé");
		infoCouleur.setLayout(new FlowLayout());
		infoCouleur.add(infoColorL);
		infoCouleur.add(this.nbColorBut);
		
		JPanel modifColor = new JPanel();
		modifColor.setLayout(new BorderLayout());
		modifColor.add(this.moinsColor, BorderLayout.WEST);
		modifColor.add(infoCouleur, BorderLayout.CENTER);
		modifColor.add(this.plusColor, BorderLayout.EAST);
		modifColor.add(this.customGame, BorderLayout.SOUTH);
		
		this.cancel = new JButton("Annuler");
		this.cancel.setActionCommand(CANCEL_ACTION);
		this.cancel.addActionListener(this);
		
		
		this.thirdLineBot = new JPanel();
		this.thirdLineBot.setLayout(new BorderLayout());
		this.thirdLineBot.add(infoPion, BorderLayout.CENTER);
		this.thirdLineBot.add(this.moinsDeCase, BorderLayout.WEST);
		this.thirdLineBot.add(this.plusDecase, BorderLayout.EAST);
		this.thirdLineBot.add(modifColor , BorderLayout.SOUTH);
		
		this.firstLineBot = new JPanel();
		this.firstLineBot.setLayout(new FlowLayout());
	
				
		this.valide = new JButton("Valider");
		this.valide.setActionCommand(VALIDATE_ACTION);
		this.valide.addActionListener(this);
		this.firstLineBot.add(this.valide);
		this.firstLineBot.add(this.cancel);
		
		
		
		this.indiceP = new JPanel();
		this.indiceP.setLayout(new GridLayout(this.setting.getNbTentatives(),this.setting.getNbPions()));
		this.indiceP.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Indice"));
		this.indice = new JLabel[this.setting.getNbPions()*this.setting.getNbTentatives()];
		this.setImage(this.indice, "img/rondIndiceVide.png");
		this.addImageToPanel(this.indiceP, this.indice);
		
		this.color = new JButton[Couleurs.NOMBRE];
		this.setColor();
		this.secondLineBot = new JPanel();
		this.secondLineBot.setLayout(new FlowLayout());
		
		this.firstLineBot.setBackground(new Color(0, 139, 232));
		this.secondLineBot.setBackground(new Color(0, 139, 232));
		this.thirdLineBot.setBackground(new Color(0, 139, 232));
		this.bottomP.add(this.firstLineBot);
		
		for(int i = 0 ; i < 6; i++) {this.secondLineBot.add(this.color[i]);}
		this.bottomP.add(this.secondLineBot);
		this.bottomP.add(this.thirdLineBot);
		this.bottomP.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Jouez"));
		
		this.add(this.topP, BorderLayout.NORTH);
		this.add(this.milieuCase, BorderLayout.CENTER);
		this.add(this.bottomP, BorderLayout.SOUTH);
		this.add(this.indiceP, BorderLayout.EAST);
		this.plusDecase.addActionListener(new plusDePion(this.nbPion, this.nbColorBut));


		this.moinsDeCase.addActionListener(new moinsDePion(this.nbPion));
		this.plusColor.addActionListener(new plusDeColor(this.nbColorBut));
		this.moinsColor.addActionListener(new moinsDeColor(this.nbColorBut));
		
		this.customGame.setActionCommand(NEW_CUSTOM_GAME);
		this.customGame.addActionListener(this);
		
		this.newGame.addActionListener(this);
		this.cheat.addActionListener(this);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		this.pack();
	}
		
	
	/*****
	 *  Permet de mettre nos images(qui représentent en faite nos couleurs) dans une liste de labels
	 *  
	 *  @param JLabel[] pCase
	 *  @param String image
	 */
	public void setImage(JLabel[] pCase,String image) {
		for(int i = 0; i< pCase.length; i++) {
//			this.solution[i] = new ImageIcon(getClass().getResource("db.png"));
			pCase[i] = new JLabel();
			pCase[i].setIcon(new ImageIcon(image));
		}
	}
	
	/*************************
	 * permet d'ajouter une liste labels contenant une image vers un panel
	 * 
	 * @param JPanel pPanel 
	 * @param JLabel[] pImage
	 * 
	 */
	public void addImageToPanel(JPanel pPanel, JLabel[] pImage) {
		for (int i = 0; i < pImage.length ; i++) {
			pPanel.add(pImage[i]);
			
		}
	}

	/**
	 * Methode dont le but est d'intancer, de configurer une commande à chaque boutons de choix de couleurs <BR>
	 * et de rattacher aux boutons une images couleurs.
	 * 
	 */
	public void setColor() {
		for(int i = 0; i < Couleurs.NOMBRE; i++) {
			this.color[i] = new JButton(Couleurs.values()[i].toString());
			this.color[i].setActionCommand(PUT_YOUR_COLOR);
			this.color[i].addActionListener(this);
			this.color[i].setIcon(new ImageIcon("img/circleColor/"+Couleurs.values()[i].toString()+".png"));
		}
	}

	public void startTheGame() {
		this.game = new Mastermind(4, 6, true);
		this.setting.setNbCouleurs(6);
		this.setting.setNbPions(4);
		this.setting.setAutorisePlusieursPionsMemeCouleur(true);
		//reset des cases du jeu
		this.indice = new JLabel[10*4];
		this.caseGame = new JLabel[10*4];
		this.solution = new JLabel[4];
		this.setImage(this.caseGame, "img/circleColor/rondVide.png");
		this.setImage(this.indice, "img/rondIndiceVide.png");
		this.setImage(this.solution, "img/db.png");
		// On vite les panels où nous devons ajouter nos composant pour éviter tout problemes 
		this.milieuCase.removeAll();
		this.milieuCase.setLayout(new GridLayout(10,4));
		this.addImageToPanel(this.milieuCase, this.caseGame);
		
		this.indiceP.removeAll();
		this.indiceP.setLayout(new GridLayout(10, 4));
		this.addImageToPanel(this.indiceP, this.indice);
		
		this.solutionP.removeAll();
		this.addImageToPanel(this.solutionP, this.solution);
		this.solutionP.add(this.cheat);
		this.secondLineBot.removeAll();
		for(int i = 0 ; i < 6; i++) {this.secondLineBot.add(this.color[i]);}

		
		
		indiceCouleur = 0; // ainsi tout est reset et on peut replacer nos cercle depuis le tout début
		putIterator = 0;
		
		indiceIndice = 0; 
		tentative = 0;
		peutContinuerGame = true;
		System.out.println("game started");
		
	}
	/**
	 * méthode permettant de démarrer une partie personnalisé en avec le nombre de pions et de couleurs<BR>
	 * choisi au préalable
	 */
	public void startCustomGame() {
		setting.setNbCouleurs(Integer.parseInt(this.nbColorBut.getText()));
		setting.setNbPions(Integer.parseInt(this.nbPion.getText()));
		setting.setNbCouleurs(Integer.parseInt(this.nbColorBut.getText()));
		for(int i = 0 ; i < setting.getNbCouleurs(); i++) {this.secondLineBot.add(this.color[i]);}

		int reponse = JOptionPane.showConfirmDialog(null, "Voulez vous autoriser ou non les doublons de couleurs", "Doublon de couleur", JOptionPane.YES_NO_OPTION);
		if(reponse == JOptionPane.YES_OPTION) {
			setting.setAutorisePlusieursPionsMemeCouleur(true);
		}
		else {
			setting.setAutorisePlusieursPionsMemeCouleur(false);
		}
		
		this.game = new Mastermind(this.setting.getNbPions(), this.setting.getNbCouleurs(), this.setting.isAutorisePlusieursPionsMemeCouleur());
		this.caseGame = new JLabel[this.setting.getNbPions() * this.setting.getNbTentatives()];;
		this.solution = new JLabel[this.setting.getNbPions()];
		this.indice = new JLabel[this.setting.getNbPions() * this.setting.getNbTentatives()];;
		this.setImage(this.caseGame, "img/circleColor/rondVide.png");
		this.setImage(this.solution, "img/db.png");
		this.setImage(this.indice, "img/rondIndiceVide.png");
		
		this.milieuCase.removeAll();
		this.milieuCase.setLayout(new GridLayout(this.setting.getNbTentatives(),this.setting.getNbPions()));
		this.addImageToPanel(this.milieuCase, this.caseGame);
		
		this.indiceP.removeAll();
		this.indiceP.setLayout(new GridLayout(this.setting.getNbTentatives(), this.setting.getNbPions()));
		this.addImageToPanel(this.indiceP, this.indice);
		
		this.solutionP.removeAll();
		this.addImageToPanel(this.solutionP, this.solution);
		this.solutionP.add(this.cheat);
		
		indiceCouleur = 0; // ainsi tout est reset et on peut replacer nos cercle depuis le tout début
		putIterator = 0;
		indiceIndice = 0; 
		tentative = 0;
		peutContinuerGame = true; 
		System.out.println("game started");
	}
	
	/**
	 * methode permettant à l'utilisateur de voir la réponse du jeu.
	 * 
	 */
	public void toCheat() {
		int[] response = this.game.getSolution();
		System.out.println(response.length);
		for(int i = 0; i<this.solution.length; i++) {
						this.solution[i].setIcon(new ImageIcon("img/putColor/"+Couleurs.values()[response[i]] +".png"));
		}
	}
	
	/**
	 * methode qui permet proposer une couleur sur une case.
	 */
	public void putColor(JButton colorToSet) {
		if(putIterator < this.setting.getNbPions() && this.caseGame.length > indiceCouleur) {
			this.caseGame[indiceCouleur].setIcon(colorToSet.getIcon());
			System.out.println(this.caseGame[indiceCouleur].getIcon().toString());
			putIterator++;
			indiceCouleur++;
			
		}
		System.out.println("Indice couleur: " + indiceCouleur + " iteration de la ligne:" + putIterator);
		
	}
	/**
	 * Methode permettant d'annuler notre proposition de couleur sur uene case
	 * 
	 * 
	 */
	public void toCancel() {
		if(putIterator >0) {
			indiceCouleur--;
			putIterator--;
		}
		System.out.println("Indice couleur" + indiceCouleur);
		
		this.caseGame[indiceCouleur].setIcon(new ImageIcon("img/circleColor/rondVide.png"));
		
		
	}
	
	/**
	 * 
	 * Methode permettant de valider notre proposition de couleurs. <BR>
	 * pour pouvoir valider, le joueur doit avoir remplit tout les cases de sa tentatives. <BR>
	 * S'il gagne il peux choisir de recommencer une partie classique ou personnalisé.
	 */
	
	public void validerParcours() {
		if(peutContinuerGame == true) {
			if(putIterator < this.setting.getNbPions()){
				
				System.out.println("Tu n'as pas tout rempli");
			}	
			
			if(putIterator == this.setting.getNbPions() && indiceCouleur < this.caseGame.length+1*this.setting.getNbPions()){
				String[] mesCouleurs = this.getColorCase();
				int[] mesReponses = new int[this.setting.getNbPions()];
				boolean find = false;
				int k = 0;
				for(int i = 0; i < mesCouleurs.length; i++) {
					find = false;
					k = 0;
					while(find == false && k < Couleurs.NOMBRE) {
						
						if(mesCouleurs[i].equals(Couleurs.values()[k].toString())) {
							mesReponses[i] = k;
							find = true;
						}
						else {
						k++;
						}
					}	
				}
				for(int i = 0; i< this.game.getSolution().length; i++) {
					System.out.print(this.game.getSolution()[i]);
				}	System.out.println("");
				System.out.println(mesReponses.length);
				
				for(int i = 0; i < mesReponses.length; i++) {	System.out.println(mesReponses[i]);
					System.out.println(mesCouleurs[i]);}
				
				
				ReponseMastermind answer = this.game.testerProposition(mesReponses);
				System.out.println("bonne couleur" + answer.getNbBonneCouleur());
				 if(answer.getNbBonEmplacement() >0) {
					if(answer.getNbBonEmplacement() == this.setting.getNbPions()) {
					 for(int i = 0 ; i < answer.getNbBonEmplacement();i++) {
							this.indice[indiceIndice].setIcon(new ImageIcon("img/indice/rondNoir.jpg")); //Lorsqu'un pion est bien placé, ce sont les rond noir qui s'affichent dans la liste des indices.
							indiceIndice++;
						}
						
						System.out.println("GG wp");
						this.toCheat(); //le joueur a gagné donc on affiche la solution
						JOptionPane WinnerBox = new JOptionPane();
						int validation = WinnerBox.showConfirmDialog(null,"Créer une nouvelle partie", "Vous avez gagné bravo", JOptionPane.YES_NO_OPTION);
						
						if(validation == JOptionPane.YES_OPTION) {
							peutContinuerGame = true; 
							this.startTheGame();
						}else {
							peutContinuerGame = false;
						}
					}
					else {
						for(int i = 0 ; i < answer.getNbBonEmplacement();i++) {
							this.indice[indiceIndice].setIcon(new ImageIcon("img/indice/rondNoir.jpg"));
							indiceIndice++;
						}
						
						System.out.println("pas loins");}
					}
				 
				 
				 
				 if(answer.getNbBonneCouleur() > 0) {
					for(int i = 0 ; i < answer.getNbBonneCouleur();i++) {
						this.indice[indiceIndice].setIcon(new ImageIcon("img/indice/rondBlanc.png")); //La couleurs blanc apparait aux niveaux des indices si une couleurs fait partie de la bonne réponse mais n'est pas bien place
						indiceIndice++;
					}
				}
			 	
				 //on va à la ligne suivante autrement dis à la tentative suivante.
				putIterator = 0;
				tentative++;
				indiceIndice = tentative * this.setting.getNbPions();
				System.out.println("Indice " + indiceIndice + " Tentative " +tentative);
			}
			if(tentative == this.setting.getNbTentatives()) {
				peutContinuerGame = false;
				System.out.println("Pas de chance tu as perdu");
				int loose = JOptionPane.showConfirmDialog(null, "Pas de chance tu as perdu, veut tu continuer ", "Tu as perdu", JOptionPane.YES_OPTION);
				if(loose == JOptionPane.YES_OPTION) {
					this.startTheGame();
				}
			}
	}
	}
	/***
	 * cette methode permet d'obtenir le nom d'une couleur à partir de son nom de fichier png <BR>
	 * car chaque fichier images est nommé avec le nom de la couleurs aproprié <BR>
	 * @param String imageFile
	 * @return colorImageFile[0]
	 */
	public String colorParser(String imageFile) {
		String[] colorStringTab = imageFile.split("/");
		String[] colorImageFile = colorStringTab[colorStringTab.length-1].split("\\.");
		return colorImageFile[0];
	}
	
	/**
	 * Méthode permettant d'obtenir la liste des nom de couleurs qui sont la ligne de pion <BR>
	 * actuel que l'utilisateur a proposé.
	 * 
	 * 
	 * @return String[] myColors
	 */
	public String[] getColorCase() {
		String[] myColors = new String[this.setting.getNbPions()];
		int f = indiceCouleur-this.setting.getNbPions();
		for(int i = 0; i < putIterator; i++) {
			myColors[i] = this.colorParser(this.caseGame[f].getIcon().toString()); //on obtient le nom de chaque couleur d'une case
			f++;
		}
		return myColors;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		switch(e.getActionCommand()) {
			case START_NEW_GAME:
				this.startTheGame();
				break;
			case CHEAT_ACTION:
				this.toCheat();
				break;
			case PUT_YOUR_COLOR:
				if(peutContinuerGame == true) {
					int i = 0;
					boolean find = false;
					while(i<this.color.length && find == false) {
						if(e.getSource().toString().equals(this.color[i].toString())) {
							this.putColor(this.color[i]);
							find =true;
						}
						i++;
					}
				}
				break;
				
			case VALIDATE_ACTION:
				this.validerParcours();
				break;
				
			case CANCEL_ACTION:
				this.toCancel();
				break;
			
			case MORE_INFO:
				JOptionPane.showMessageDialog(null, "Mastermind developped by Jonathan PHAM", "Mastermind 1.0", JOptionPane.INFORMATION_MESSAGE);
				break;
			
			case EXIT_GAME: 
				System.exit(0);
				break;
				
			case NEW_CUSTOM_GAME: 
				this.startCustomGame();
				break;
		
		}
		
	}	
	
}
