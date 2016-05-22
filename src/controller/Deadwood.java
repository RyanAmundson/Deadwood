package controller;

import java.io.*;

import javax.xml.parsers.*;

import org.w3c.dom.*;

import java.util.*;

import org.xml.sax.SAXException;

import view.BoardView;
import model.*;

public class Deadwood {
	
	public static int numberOfDays = 4;
	public static int numberOfPlayers;	
	public static Game G;
	public static final int MAX_PLAYERS = 8;
	private static boolean gameOver = false;


	// The is the factory function that builds the correct type.
	public static Game build(int numberOfPlayers, int numberOfDays) throws Exception {
		G = (Game) new Game(numberOfPlayers, numberOfDays);
		return G;
	}

	// Very simple main program that parses the console game's language.
	// You could probably do this better with regular expressions, but since it
	// is
	// only for testing, this is good enough.
	public static void main(String[] args) throws Exception {
		if (args.length == 0) {
			numberOfPlayers = 2;
		} else {
			numberOfPlayers = Integer.parseInt(args[0]);
		}
		System.out.println("starting a game for " + numberOfPlayers);		
		
		Deadwood dw = build(numberOfPlayers, numberOfDays); // build board
		
		
		while(!gameOver){
			
			//System.out.println(Thread.currentThread());
			Thread.sleep(500);
		}
		
	}
}
