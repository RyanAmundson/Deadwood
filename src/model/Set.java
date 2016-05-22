package model;

import java.io.*;
import java.lang.Object;

import javax.xml.parsers.*;

import org.w3c.dom.*;

import controller.Deadwood;
import view.BoardView;

import java.util.*;

public class Set extends Room {

	private boolean drawCardAllowed = true;
	private SceneCard currentCard = null;
	private boolean hasScene = false;
	private int shotCounter;
	private LinkedList<Role> roles;
	private int budget = 0;
	private int[] actorPayout;
	private int extraPayout;

	// ==============================================================================
	public Set(String name, LinkedList<Role> roles, int shotCounter,
			LinkedList<String> neighborList) {
		super(name, neighborList);
		this.roles = roles;
		this.shotCounter = shotCounter;
	}

	// ==============================================================================

	/*
	 * sceneWrap() 1.makes dice with Random lib/gets all players in room and
	 * roles on card 2."rolls" dice to determine the payout for actors on the
	 * scene card 3. sort payout array 4. for each player checks if they even
	 * have a role then checks that role against the roles on the card. gives
	 * player money from the payout array and if the budget aka the number of
	 * dice is more than 3 it will give another payout in the same manner. 5.
	 * gives all extras their payout 6.forces all players to leave their roles
	 * as the card is now obselete 7.adjusts necessary attributes for removal of
	 * card. **maybe make 6 & 7 its own function?
	 */

	public void sceneWrap() {
		//Random dice = new Random();
		Dice dice = new Dice(budget);
		Role[] cardRoles = currentCard.getRoles();
		LinkedList<Player> playersInRoom = getPlayers();
		BoardView.getInstance().wrapScene(getName(), currentCard.getName(),playersInRoom, roles);
		
		actorPayout = dice.roll();
		Arrays.sort(actorPayout);

		for (Player p : playersInRoom) {
			if (p.hasRole()) {
				if (cardRoles.length > 2 && p.getCurrentRole().getName().equals(cardRoles[2].getName())) {// could cause issues with payout
					p.addCredits(actorPayout[budget - 1]);
					if (budget > 3) {
						p.addCredits(actorPayout[budget - 4]);
					}
				} else if (p.getCurrentRole().getName()
						.equals(cardRoles[1].getName())) {
					p.addCredits(actorPayout[budget - 2]);
					if (budget > 4) {
						p.addCredits(actorPayout[budget - 5]);
					}
				} else if (p.getCurrentRole().getName()
						.equals(cardRoles[0].getName())) {
					p.addCredits(actorPayout[budget - 3]);
					if (budget == 6) {
						p.addCredits(actorPayout[budget - 6]);
					}
				} else if (p.getCurrentRole().getType().equals("extra")) {
					p.addCash(p.getCurrentRole().getRank());
				}
			}
		}
		for (Player player : playersInRoom) {
			if (player != null) {
				player.leaveRole();
			}
		}
		Board.sceneCardsRemaining--;
		//notifyAll();
		if (Board.sceneCardsRemaining == 2) {
			//notify();
			//Deadwood.currentDay++;
		}
		System.out.println("Scene has Wrapped!");
		hasScene = false;
		budget = 100;
		currentCard = null;
		roles = null;
	}

	public void drawCard() {
		if (drawCardAllowed == true) {
			drawCardAllowed = false;
			// Deck.getInstance().shuffle();
			currentCard = Deck.draw();
			BoardView.getInstance().drawCard(this.getName(),
					currentCard.getImageNum(), currentCard.getPartInfo(),
					currentCard.getPartNames());
			// System.out.println("card budget: "+currentCard.getBudget());
			budget = currentCard.getBudget();
			// System.out.println("my budget:" +budget);
			actorPayout = new int[budget];
			hasScene = true;

		} else {
			System.out.println("room already has card");
		}
	}

	public void shotSuccess() {
		shotCounter--;
		if (shotCounter == 0) {
			sceneWrap();
		}
		// listener?
	}

	public void removeCard() {
		currentCard = null;
		hasScene = false;
		drawCardAllowed = true;
	}

	public SceneCard getCurrentCard() {
		return currentCard;
	}

	public int shotsRemaining() {
		return shotCounter;
	}

	public LinkedList<Role> getRoles() {
		return roles;
	}

	public void setBudget(int budget) {
		this.budget = budget;
	}

	public int getBudget() {
		return budget;
	}

	public boolean hasSceneCard() {
		return hasScene;
	}

	public boolean drawCardAllowed() {
		return drawCardAllowed;
	}
	@Override
	public void addPlayer(Player p){
		super.addPlayer(p);
		if (!hasSceneCard() && drawCardAllowed()) {
			drawCard();
		}
	}
	
	

	public Role getRoleByName(String name) {
		Role role = null;
		for (int i = 0; i < roles.size(); i++) {
			if(roles.get(i).getName().toLowerCase().equals(name)){
				role = roles.get(i);
			}
		}
		System.out.println(role);
		return role;
	}

	public void showRoles() {
		System.out.println("SecondaryRoles:");
		for (Role r : roles) {
			if (r != null && !r.isOccupied()) {
				System.out.println(r.getName() + " rank:" + r.getRank());
			}
		}
		if (hasScene) {
			System.out.println("PrimaryRoles:");
			for (Role r : currentCard.getRoles()) {
				if (r != null && !r.isOccupied()) {
					System.out.println(r.getName() + " rank:" + r.getRank());
				}
			}
		}
	}
}
