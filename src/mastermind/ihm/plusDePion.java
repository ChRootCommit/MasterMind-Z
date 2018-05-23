/**
 * Listener donc le but est d'ajouter le nombre de couleur
 * @Author Jonathan
 */
package mastermind.ihm;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;




public class plusDePion implements ActionListener{
	private JButton  nbPionBut;
	private JButton nbColor;
	
	/**********************
	 * Constructeur parametre du Listener. <BR>
	 * le bouton representant le nombre de couleurs et le nombre pion sont donne
	 * 
	 * @param pGameP
	 * @param pNbColor
	 */
	public plusDePion(JButton pGameP, JButton pNbColor) {
		this.nbColor = pNbColor;
		this.nbPionBut = pGameP;
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.addPions();
		
	}
	
	/***
	 * Sous-programme qui va s'exuter lorsque l'utilisateur appuiera sur le button + de l'interface  <BR>
	 * Le sous-programme augmente le nombre de pion que l'utilisateur souhaite pour une partie personnalisé <Br>
	 * le nombre de pions doit être inférieur ou égal au  nombre de couleur et ne doit pas etre supérieur à 8
	 */
	public void addPions() {
		int nbPion = Integer.parseInt(this.nbPionBut.getText());
		if(nbPion <8 && nbPion < Integer.parseInt(this.nbColor.getText())) {
			nbPion++;
		}
		this.nbPionBut.setText(String.valueOf(nbPion));
	}
	
	

}
