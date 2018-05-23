package mastermind.ihm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class moinsDeColor implements ActionListener{

	JButton nbColorBut;
	
	public moinsDeColor(JButton pNbColor) {
		this.nbColorBut = pNbColor;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		this.removeColor();
	}
	
	public void removeColor() {
		
		int nbColor = Integer.parseInt(this.nbColorBut.getText());
		
		if(nbColor > 6) {
			nbColor--; 
		}
		
		this.nbColorBut.setText(String.valueOf(nbColor));
		
	}
	
}
