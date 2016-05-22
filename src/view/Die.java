package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Die implements ActionListener{
	
	
	private JLabel dieLabel;
	ImageIcon[] dieFaces;
	
	
	public Die(JLabel dieLabel){
		this.dieLabel = dieLabel;
		dieFaces = new ImageIcon[7];
		for( int i = 0; i < 6; i++){
			dieFaces[i] = Resources.getInstance().getNewDiceImage('r',i+1);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}

}
