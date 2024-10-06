package snack;

import javax.swing.JFrame;

public class snackframe extends JFrame {
	Snackpanel panel = new Snackpanel();
	snackframe(){
	panel.requestFocusInWindow();
	this.add(panel);this.pack();
	this.setResizable(false);
	this.setLocationRelativeTo(null);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setVisible(true);
	}
}
