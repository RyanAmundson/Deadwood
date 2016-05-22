package model;

import javax.swing.JLabel;

import org.w3c.dom.*;
import java.util.*;
import java.util.Observable;

public class Board extends Observable {

	public Player[] players = new Player[8];
	HashMap<String, Room> rooms = new HashMap<String, Room>();
	public Room trailer;
	public Room office;
	HashMap<String, Room> sets = new HashMap<String, Room>();
	private Room[] roomList = new Room[12];
	// public Deck gameDeck;
	public Room[][] roomAdjMatrix = new Room[12][12];
	private int playerCount;
	public static Room startingRoom;
	public static int sceneCardsRemaining = 10;
	private static int playerPointer = 0;

	public final String[] colors = new String[] { "red", "blue", "green",
			"cyan", "orange", "pink", "purple", "yellow" };

	// ==============================================================================

	/*
	 * Board constructor called by the game class builds all the room and player
	 * objects not as ugly as room but still pretty bad
	 * 
	 * 1.builds sets then trailer then office2.sets the index of each room in
	 * the global room map held in board3.sets the trailer to the starting room
	 * 4.hold the map index of trailer for after player creation 5.create
	 * players 6. sets first players turn, currently first player is always red
	 */

	public Board(int playerCount, Element e) {

		this.playerCount = playerCount;

		NodeList setNodes = e.getElementsByTagName("set");
		Node trailerNode = e.getElementsByTagName("trailer").item(0);
		Node officeNode = e.getElementsByTagName("office").item(0);

		trailer = Room.build((Element) trailerNode);
		startingRoom = trailer;
		office = Room.build((Element) officeNode);

		rooms.put("trailer", trailer);
		rooms.put("office", office);

		for (int j = 0; j < setNodes.getLength(); j++) {

			Room createdRoom = Room.build((Element) setNodes.item(j));
			sets.put(createdRoom.getName().toLowerCase(), createdRoom);
			rooms.put(createdRoom.getName().toLowerCase(), createdRoom);

		}

		for (int j = 0; j < playerCount; j++) {
			players[j] = new Player(colors[j]);
		}
	}

	// =========================================================================
	public void removeCard() {
		sceneCardsRemaining--;
		setChanged();
		notifyObservers(this);
	}

	public void newDay() {

		for (Room r : roomList) {
			if (!r.getName().equals("office") && !r.getName().equals("trailer") && r.getCurrentCard() != null) {
				r.removeCard();
			}
		}
	}

	public void setPlayerCount(int count) {
		playerCount = count;
	}

	public Room[] getRoomList() {
		return roomList;
	}

	public boolean move(Player player, Room current, Room destination) {
		boolean success = true;

		if (current.getNeighbors().contains(destination.getName()) && player.move(destination)) {
			current.removePlayer(player);
			destination.addPlayer(player);
			System.out.println(player + "Moved from "+ current + " to " + destination);
		} else {
			System.out.println("You cant move there right now.");
			success = false;
		}
		return success;
	}

	public static Room getStartingRoom() {
		return startingRoom;
	}

	public void setStartingRoom(Room r) {
		startingRoom = r;
	}

	public Room getRoomByName(String name) {
		return rooms.get(name);
	}

	public synchronized int getSceneCardsRemaining() {
		return sceneCardsRemaining;
	}

}
