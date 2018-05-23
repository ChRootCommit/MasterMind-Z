package mastermind.ihm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class plusDeColor implements ActionListener{
	
	private JButton nbOfColor;
	
	public plusDeColor(JButton pNbColor) { 
		this.nbOfColor = pNbColor;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e ) {
		this.addColor();
	}
	
	public void addColor() {
		int nbColor = Integer.parseInt(this.nbOfColor.getText());
		if(nbColor < 12 ) {
			nbColor ++;
		}
		this.nbOfColor.setText(String.valueOf(nbColor));
	}
	
	
	
}
