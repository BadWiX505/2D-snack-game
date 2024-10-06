package snack;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.*;

public class Snackpanel extends JPanel implements ActionListener,KeyListener {
	Graphics g;
	int squaresize = 20;
	int[] x = new int[100];
	int[] y = new int[100];
	Random rand = new Random();
	int appleX;
	int appleY;
	int bodyparts=4;
	Timer timer;
	int movingX = squaresize;
	int movingY = squaresize;
	char direction;
	int n=0;
	int working=1;
	
	// constructor
     Snackpanel(){ 	
   	  this.setFocusable(true);
    	 timer = new Timer(100,this);
    	 this.setPreferredSize(new Dimension(600,600));
    	 this.setBackground(Color.black);
    	 this.addKeyListener(this);
    	 timer.start();
    	 x[0]=240;
    	  y[0]=120;
    	  setapple();
     }
     // 
     public void paint(Graphics g) {
    	  super.paint(g);
    	  if(working == 1) {
    	  g.setColor(Color.green);
    	  g.fillOval(appleX, appleY,squaresize,squaresize);
    	  for(int i=0;i<bodyparts;i++) {
    		  if(i==0) {
    			  g.setColor(Color.red);
    		  }
    		  else {
    	    	  g.setColor(new Color(200,0,0));  	  
    		  }
    		  g.fillRect(x[i],y[i],squaresize,squaresize);
    	  }
    	  }
    	  else {
    		  g.setFont(new Font("Poppins",Font.BOLD,24));
    		  g.setColor(Color.white);
    		  g.drawString("Score : "+(bodyparts-4)+"\n click enter to repeate",120,300);
    	  }
     }
     
void setapple() {
	do {
	appleX = (rand.nextInt(29)+0)*squaresize;
	appleY= (rand.nextInt(29)+0)*squaresize;
	}while( checkacppleposition()==false);
}

boolean checkacppleposition() {
	for(int i=0;i<bodyparts;i++) {
		if(x[i]==appleX && y[i]==appleY) {
			return false;
		}
	}
	return true;
}
public void actionPerformed(ActionEvent e) {
	move(direction);
	if(checkfood()==1) {setapple();bodyparts++; }
	if(checkcolusion()==1) {timer.stop();working=0;}
	SIIIR();
	repaint();
}
@Override
public void keyTyped(KeyEvent e) {	
}
@Override
public void keyPressed(KeyEvent e) {
	
}
@Override
public void keyReleased(KeyEvent e) {
	switch(e.getKeyChar()) {
	case 'w' :if(direction!='D') direction='U';break;
	case 'd' : if(direction!='L')direction='R';break;
	case 's' :if(direction!='U') direction='D';break;
	case 'a' :if(direction!='R') direction='L';break;
	}
	if(e.getKeyCode()==10 && working ==0) {
		working=1;
		bodyparts=4;
		x[0]=240;
  	  y[0]=120;
		timer.start();
	}
}

public void move(char direction) {
	for(int i=bodyparts;i>0;i--) {
		x[i]=x[i-1];
		y[i]=y[i-1];		
	}
	switch(direction) {
	case 'U' : if(movingY>0) movingY=-movingY;n=1;break;
	case 'R' : if(movingX<0) movingX=-movingX;n=2;break;
	case 'D' : if(movingY<0) movingY=-movingY;n=1;break;
	case 'L' : if(movingX>0) movingX=-movingX;n=2;break;
	}
	if(n==1) {
		y[0]=y[0]+movingY;
	}
	else {
		x[0]=x[0]+movingX;	
	}
}
public int checkfood() {
	if(appleX==x[0]&& appleY==y[0]) {
		return 1;
	}
	return 0;
}
public int checkcolusion() {
	for(int i=1;i<bodyparts;i++) {
		if(x[0]==x[i]&&y[0]==y[i]) {
			return 1;
		}
	}
	return 0;
}
public void SIIIR() {
	
	if((x[0]<=-(bodyparts-1))){
		x[0]=580;
	}
	else if((x[0]>=(bodyparts-1)+580)) {
		x[0]=0;
	}
	if((y[0]<=-(bodyparts-1))){
		y[0]=580;
	}
	else if((y[0]>=(bodyparts-1)+580)) {
		y[0]=0;
	}
}
}
