package controller;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import model.Board;
import model.Deck;
import model.Player;
import model.Room;
import model.RoomChangeListener;
import view.BoardView;

public class Game extends Deadwood {

	private static boolean isOver = false;
	private Board gameBoard;
	private Player currentPlayer;
	public static int currentDay = 0;
	private int playerPointer = 0;
	private Player[] playerList;
	private Deck deck;
	private JFrame mainFrame;
	private BoardView bV;
	static Game instance;
	public int sceneCardsRemaining = 10;
	public static final String[] colors = new String[] { "red", "blue", "green",
		"cyan", "orange", "pink", "purple", "yellow" };
	
	
	
	
	// =================Constructor===================================================
	
	public Game(int numberOfPlayers,int numberOfDays) throws Exception {
		Game.instance = this;
		
		Scanner in = new Scanner(System.in);

		File xml = new File("resources/board.xml");
		File cardxml = new File("resources/cards.xml");

		Document dom;
		Document cardDom;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		
		DayListener dayChangeObserver = new DayListener();
		RoomChangeListener roomChangeObserver = new RoomChangeListener();
		

		try {

			DocumentBuilder db = dbf.newDocumentBuilder();
			dom = db.parse(xml);
			cardDom = db.parse(cardxml);

			Element cardDoc = cardDom.getDocumentElement();
			Element doc = dom.getDocumentElement();
			
			
			gameBoard = new Board(numberOfPlayers, doc); // make the game board	
			dayChangeObserver.observe(gameBoard);
			deck = new Deck(cardDoc); // make the deck
			
			playerList = new Player[numberOfPlayers];
			for(int i = 0; i < numberOfPlayers; i++){
				playerList[i] = new Player(colors[i]);
				roomChangeObserver.observe(playerList[i]);
				
			}
			this.currentPlayer = playerList[0];
			


		} catch (ParserConfigurationException pce) {
			System.out.println(pce.getMessage());
		} catch (SAXException se) {
			System.out.println(se.getMessage());
		} catch (IOException ioe) {
			System.err.println(ioe.getMessage());
		}
		
		
		this.mainFrame = new JFrame();
		bV = new BoardView();
		mainFrame.setTitle("DeadWood");
		mainFrame.setSize(1465, 940);
		mainFrame.setBackground(Color.LIGHT_GRAY);
		mainFrame.getContentPane().add(bV);
		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		mainFrame.setVisible(true);
		mainFrame.setResizable(true);
		bV.requestFocus();

		currentPlayer = whosTurn();
		bV.showCurrentPlayer(currentPlayer);
	}
	

	
	

	// =================functions=====================================================
	
	public static void updateUI(){
		instance.mainFrame.repaint();
	}
	public static Game getInstance() {
		return instance;
	}

	public Player whosTurn(){
		return currentPlayer;
	}
		
	public boolean gameOver() {
		return isOver;
	}


	public void newDay() {
		//gameBoard.newDay();
		Game.instance = this;
		// this.mainFrame = new JFrame();
		mainFrame.getContentPane().removeAll();
		try {
			this.bV = new BoardView();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		mainFrame.getContentPane().add(bV);
		mainFrame.repaint();
		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		mainFrame.setVisible(true);
		mainFrame.setResizable(false);
		bV.requestFocus();
		for(Player p :playerList){
			p.newDay();
		}
		gameBoard.newDay();
		currentDay++;
	}

	public void move(String to) {
		//eventually check if correct persons turn (network)
		to = to.toLowerCase();
		Room destination = gameBoard.getRoomByName(to);
		Room currentRoom = currentPlayer.getCurrentRoom();
		
		
		if(gameBoard.move(currentPlayer,currentRoom, destination)){
			bV.leftRoom(currentRoom.getName(), currentPlayer.getColor());
			bV.enteredRoom(destination.getName(), currentPlayer.getColor());
		}
		
		
		
		
		//if (destination.equals(currentRoom)) {
		//	System.out.println("***You are already at " + to + "***");
		//} else {
		//	System.out.println("  Move to the \"" + to + "\"");
		//	gameBoard.move(currentPlayer,currentRoom, destination);
		//}
	}

	public void work(String part) {

		part = part.toLowerCase();
		if(currentPlayer.hasRole(part)){
			int result = currentPlayer.act();
			bV.rollDice(result, currentPlayer.getCurrentRoom().getName(), currentPlayer.getCurrentRoom().shotsRemaining());
		}else{
			currentPlayer.selectRole(part);
		}
		
	}

	public void upgradeDollars(int level) {
		System.out.format("Upgrade to %d with dollars\n", level);
		currentPlayer.upgradeDol(level);

	}

	public void upgradeCrdit(int level) {
		System.out.format("Upgrade to %d with credits\n", level);
		currentPlayer.upgradeCr(level);
	}

	public void rehearse() {
		currentPlayer.rehearse();
	}

	public void endTurn() {
		currentPlayer.turnEnd();
		if(playerPointer == playerList.length - 1){
			playerPointer = 0;
		}else{
			playerPointer++;
		}
		currentPlayer = playerList[playerPointer];
		bV.showCurrentPlayer(currentPlayer);
	}

	public static void end() {
		System.out.println("But all the magic I have known");
		System.out.println("I've had to make myself.");
		System.out.println("           ---Shel Silverstein");
		Game.isOver = true;
	}

	public int getCurrentDay() {
		return currentDay;
	}

}
