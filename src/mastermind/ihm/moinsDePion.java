/*****
 * Listener qui permet de diminuer le nombre de pion pour une partie personnalisé <BR>
 * @author Jonathan
 * 
 */

package mastermind.ihm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class moinsDePion implements ActionListener {

	private JButton nombreDePion;
	
	/***
	 * 
	 * Constructeur paramétré permettant de donner le button contenant le nombre pions
	 * 
	 * @param pNbPion
	 */
	public moinsDePion(JButton pNbPion) {
		this.nombreDePion = pNbPion;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.removeP();
	}
	
	/*******
	 * sous-programme qui permet à l'utilisateur de diminuer le nombre de pions jusqu'à 4 lorsque <BR>
	 * l'utilisateur clique le button de l'interface avec le signe -
	 */
	public void removeP() {
		int nbPion  = Integer.parseInt(this.nombreDePion.getText());
		if(nbPion > 4) {
			nbPion -- ;
		}
			
		this.nombreDePion.setText(String.valueOf(nbPion));
	}
}
