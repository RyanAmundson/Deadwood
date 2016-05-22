package view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.TimerTask;
import java.util.Timer;
import java.awt.event.*;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.imageio.*;

import model.Board;
import model.Player;
import model.Role;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.*;
import java.awt.image.*;
import java.awt.Color;

import controller.Controller;
import controller.Deadwood;
import controller.Game;

import javax.swing.*;

public class DiceRoll implements Runnable{
	
	private Thread thread = null;
	private Timer timer;
	private volatile boolean isRunning = false;
	private static final long serialVersionUID = 1L;
	public final static int ONE_SECOND = 1000;
	private int currentImage = 0;
	boolean running = false;

	HashMap<String, JLabel> diceLabels = new HashMap<String, JLabel>();
	
	ImageIcon[] diceFaces;

	
	public DiceRoll(HashMap<String, JLabel> rollingDice){
		
		this.diceLabels = rollingDice;
		diceFaces = new ImageIcon[7];
		for( int i = 0; i < 6; i++){
			diceFaces[i] = Resources.getInstance().getNewDiceImage('r',i+1);
		}
		
	}
	
	
	@Override
	public void run() {
		Random r = new Random();
		running = true;
		timer = new Timer();
		int count = 0;
		while(running){
			count++;
			diceLabels.get("1").setIcon(diceFaces[r.nextInt(5)+1]);
			diceLabels.get("2").setIcon(diceFaces[r.nextInt(5)+1]);
			diceLabels.get("3").setIcon(diceFaces[r.nextInt(5)+1]);
			diceLabels.get("4").setIcon(diceFaces[r.nextInt(5)+1]);
			diceLabels.get("5").setIcon(diceFaces[r.nextInt(5)+1]);
			diceLabels.get("1").getParent().validate();
			diceLabels.get("1").getParent().repaint();
			

			if (count > 30) {
				running = false;	
			}

			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
		}

	}
	
	private void pause(int sleepTime) {
	    try {
	        Thread.sleep(sleepTime);
	    } catch (InterruptedException e) {
	        System.exit(-1);
	    }
	}

    
    public void stop() {
        if (thread != null) {
            isRunning = false;
            try {
                thread.join();
            } catch (InterruptedException ie) {
                // empty
            }
            thread = null;
        }
    }

	

	
	
	
}





//while(running){

/*	public void actionPerformed(ActionEvent evt) {
		setIcon(diceFaces[currentImage]);
		currentImage = r.nextInt(6);
		revalidate();
		repaint();
		time++;
		if (time > 30) {
			System.out.println("timer ended!");
			running = false;
        	timer.stop();
        	setIcon(diceFaces[rollResult]);
        	time = 0;
        	//...Update the GUI...
    	}
	}    
});
timer.start();*/

//}
