package view;
import java.io.*;

import javax.imageio.*;
import javax.swing.*;


/*
 * Resources.java
 * 
 * this class does most of the file retrieval and creating of image icons which it sends to boardview upon request
 * 
 * the next step is to move all the parsing which I accidentally put into boardview into here
 */
public class Resources {

	private ImageIcon board;
	private ImageIcon shotCounter;
	private ImageIcon[] dice = new ImageIcon[8];
	private ImageIcon[] cards = new ImageIcon[40];
	
	static Resources instance;
	static char[] colors = new char[]{'r','b','g','c','o','p','v','y'};
	
	private Resources(){	
		
		try{
			//parseCards();
			board = new ImageIcon(ImageIO.read(new File("resources/board.jpg")));
			
			for(int i = 1; i <= 40; i++){
				if (i < 10)
					cards[i-1] = new ImageIcon(ImageIO.read(new File(String.format("resources/cards/0%d.png",(i)))));
				else
					cards[i-1] = new ImageIcon(ImageIO.read(new File(String.format("resources/cards/%d.png",(i)))));	
			}
			for(int i = 0; i < 8; i++){			
				dice[i] = new ImageIcon(ImageIO.read(new File(String.format("resources/dice/%s1.png",colors[i]))));
			}
			shotCounter = new ImageIcon(ImageIO.read(new File("resources/shot.png")));
			
		}catch (IOException e){
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public ImageIcon getBoard() {
		    return board;
	}
	
	public ImageIcon[] getDice(){
		return dice;
	}
	
	public ImageIcon getShotCounter(){
		return shotCounter;
	}
	
	public ImageIcon getCard(String file){
		ImageIcon card = null;
		try{
			 card = new ImageIcon(ImageIO.read(new File(String.format("resources/cards/%s",file))));
		}catch (IOException e){
			e.printStackTrace();
			System.exit(1);
		}
		
		return card;
	}
	
	public static Resources getInstance() {
		    if (instance == null) 
		      instance = new Resources();
		    return instance;
	}
	
	public ImageIcon getNewDiceImage(char color, int level){
		ImageIcon diceImage = null;
		try{
			diceImage = new ImageIcon(ImageIO.read(new File(String.format("resources/dice/%s%d.png",color,level))));
		}catch (IOException e){
			e.printStackTrace();
			System.exit(1);
		}
		return diceImage;
		
	}
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
