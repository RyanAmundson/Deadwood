package model;

import java.io.*;
import java.lang.Object;

import javax.xml.parsers.*;

import org.w3c.dom.*;

import view.BoardView;

import java.util.*;

public class Room{

	private String name;
	private int neighborCount;
	private int mapIndex;
	private List<String> neighbors;
	protected LinkedList<Player> currentPlayers = new LinkedList<Player>();
	private final static int MAX_NEIGHBORS = 4;
	

	// ==========================Constructors========================================
	public Room(String name, LinkedList<String> neighbors) {
		this.name = name;
		this.neighbors = neighbors;
	}

	protected Room(int nbCount) {
		this.neighborCount = nbCount;
	}

	// ==========================Methods=============================================

	/*
	 * static build() takes in the xml for a single room and uses it to build
	 * rooms and the roles in each room.
	 * 
	 * it also adds the name of each room to the static room map held in board
	 * 
	 * why does parsing make this code so ugly??
	 */
	@SuppressWarnings("unchecked")
	public static Room build(Element room) {

		LinkedList neighborList = new LinkedList<String>();
		int nbCount = room.getElementsByTagName("neighbor").getLength();
		String roomName = new String();
		LinkedList<Role> roleList = new LinkedList<Role>();

		NodeList neighborNodes = room.getElementsByTagName("neighbor");
		NodeList roleNodes = room.getElementsByTagName("part");

		switch (room.getTagName()) {

		case "set":
			roomName = room.getAttributes().getNamedItem("name")
					.getTextContent();

			for (int i = 0; i < nbCount; i++) {

				String neighbor = neighborNodes.item(i).getAttributes()
						.getNamedItem("name").getTextContent();
				neighborList.add(neighbor);
			}
			// Board.addRoomToMap(roomName, nL[0], nL[1], nL[2], nL[3]);
			for (int i = 0; i < roleNodes.getLength(); i++) {

				String type = "extra";
				String name = roleNodes.item(i).getAttributes()
						.getNamedItem("name").getTextContent();
				int level = Integer.parseInt(roleNodes.item(i).getAttributes()
						.getNamedItem("level").getTextContent());

				Role role = new Role(type, name, level);
				roleList.add(role);

			}
			int shotCounter = room.getElementsByTagName("take").getLength();
			return new Set(roomName, roleList, shotCounter, neighborList);

		case "trailer":
			roomName = room.getTagName();

			for (int i = 0; i < nbCount; i++) {

				String neighbor = neighborNodes.item(i).getAttributes()
						.getNamedItem("name").getTextContent();
				neighborList.add(neighbor);

			}
			// Board.addRoomToMap(roomName, nL[0], nL[1], nL[2], nL[3]);
			return new Trailer("trailer", neighborList);

		case "office":
			roomName = room.getTagName();

			for (int i = 0; i < nbCount; i++) {

				String neighbor = neighborNodes.item(i).getAttributes()
						.getNamedItem("name").getTextContent();
				neighborList.add(neighbor);

			}
			// Board.addRoomToMap(roomName, nL[0], nL[1], nL[2], nL[3]);
			return new CastingOffice("office", neighborList);

		}
		return null;
	}

	public LinkedList<Player> getPlayers() {
		return currentPlayers;
	}

	public void addPlayer(Player p) {

		currentPlayers.add(p);
		

	}

	public void removePlayer(Player p) {

		currentPlayers.remove(p);
		

	}

	public void removeCard() {
	}

	public String getName() {
		return name;
	}

	public List<String> getNeighbors() {
		return neighbors;
	}

	public int getNeighborCount() {
		return neighborCount;
	}

	// public void addNeighbor(Room r) {
	// this.neighbors.add(r);
	// }

	// public int getMapIndex() {
	// return mapIndex;
	// }

	// public void setMapIndex(int i) {
	// this.mapIndex = i;
	// }

	/*
	 * hasNeighbor() returns true if the name provided is a neighbor of the room
	 * it was called on
	 */
	// public boolean hasNeighbor(String neighbor) {
	// for (String name : Board.getRoomMap()[mapIndex]) {
	// if (name.toLowerCase().equals(neighbor.toLowerCase())) {
	// return true;
	// }
	// }
	// return false;
	// }
	// =======================================================================================================

	// these are all the methods overridden in the derived class. maybe I should
	// just
	// make this class abstract?
	public int getBudget() {
		return 0;
	}

	public boolean hasSceneCard() {
		return false;
	}

	public boolean drawCardAllowed() {
		return false;
	}

	public void showRoles() {
	}

	public void drawCard() {
	}

	public LinkedList<Role> getRoles() {
		return null;
	}

	public SceneCard getCurrentCard() {
		return null;
	}

	public void shotSuccess() {
	}

	public int shotsRemaining() {
		return 0;
	}

	public void setBudget(int budget) {
	}
	public Role getRoleByName(String name) {
		return null;
	}
}
