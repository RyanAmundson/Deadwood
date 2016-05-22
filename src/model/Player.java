package model;

import view.BoardView;

import java.util.*;
//import java.util.Observer;

public class Player extends Observable {

	private boolean actionPerformed = false;
	private int rank = 1;
	private int credits = 0;
	private int cash = 0;
	private Room currentRoom;
	private int rehearseCount = 0;
	private String color;
	private Role currentRole = null;

	public Player(String color){

		this.color = color;
		this.currentRoom = Board.startingRoom;

	}

	/*
	 * act() 1. generates "dice" 2. checks if they have role, rolls dice and
	 * determines whether roll was high enough to succeed 3.pays out on success
	 * or failure
	 */
	public int act() {
		int roll = 0;
		Dice dice = new Dice(1);
		
		if(actionPerformed){
			System.out.println("You cant perform or rehearse anymore this turn.");
		}
		else if (currentRole != null) {
			roll = dice.roll()[0];
			System.out.println(" You rolled: " + roll + " + (" + rehearseCount
					+ "), You needed: " + currentRoom.getBudget());
			
			if ((roll + rehearseCount) >= currentRoom.getBudget()) {
				System.out.println("You worked successfully!");
				int[] reward = new int[2];
				reward = currentRole.getRewardSuccess();
				credits = credits + reward[0];
				cash = cash + reward[1];
				currentRoom.shotSuccess();
				actionPerformed = true;
			} else {

				System.out.println("you failed to perform...better luck next time.");
				currentRole.getRewardFailure();
				actionPerformed = true;
				roll = 0;
			}
		} else {
			System.out.println("You are not on that role.");
		}
		return roll;

	}

	/*
	 * move() removes player from current rooms player list changes room adds
	 * self to new rooms player list
	 */
	public boolean move(Room room) {
		boolean moved = false;
		if (hasRole()) {
			System.out.println("You are on a role and cannot move until it is complete!");
			moved = false;
		} else if (actionPerformed) {
			System.out.println("You have already moved, acted or rehearsed this turn!");
			moved = false;
		} else {
			assert (currentRole == null);
			assert (actionPerformed == false);
			currentRoom = room;
			System.out.println("moved successfully");
			rehearseCount = 0;
			actionPerformed = true;
			moved = true;
			setChanged();
			notifyObservers(this);
		}
		return moved;
	}

	// upgrade with dollars
	public void upgradeDol(int level) {
		System.out.println(rank < level);
		System.out.println(CastingOffice.getCashRequirement(level - 2));
		if (currentRoom.getName().equals("office")) {

			if (rank < level
					&& cash >= CastingOffice.getCashRequirement(level - 2)) {
				this.cash = this.cash
						- CastingOffice.getCashRequirement(level - 2);
				this.rank = level;
				System.out.println("upgraded successfully");
				BoardView.getInstance().rankUp(color, level);

			} else {
				System.out.println("failed to upgrade");
			}
		}

	}

	// upgrade with credits
	public void upgradeCr(int level) {
		if (currentRoom.getName().equals("office")) {
			if (rank < level
					&& this.credits >= CastingOffice
							.getCreditRequirement(level - 2)) {
				this.credits = this.credits
						- CastingOffice.getCashRequirement(level - 2);
				this.rank = level;
				System.out.println("upgraded successfully");

			} else {
				System.out.println("failed to upgrade");
			}
		} else {
			System.out.println("you must be in the Casting Office to rank up!");
		}

	}

	// rehearse
	public boolean rehearse() {
		if (currentRole != null && actionPerformed == false) {
			if (currentRoom.getBudget() - rehearseCount > 0) {
				System.out.println("rehearsed successfully");
				rehearseCount++;
				actionPerformed = true;
				return true;
			} else {
				System.out.println("you cant rehearse for this role anymore!");

			}
		} else {
			System.out.println("You are not on a Role or you cant rehearse this turn");
		}
		return false;
	}

	public Room getCurrentRoom() {
		return currentRoom;
	}

	public void setCurrentRoom(Room r) {
		currentRoom = r;
	}

	public int getRank() {
		return rank;
	}

	public int getCredits() {
		return credits;
	}

	public void addCredits(int c) {
		credits = credits + c;
	}

	public int getCash() {
		return cash;
	}

	public void addCash(int c) {
		cash = cash + c;
	}

	public boolean hasRole() {
		return (currentRole != null);
	}

	public boolean hasRole(String name) {
		if(currentRole != null){
			return currentRole.getName().toLowerCase().equals(name);
		}
		return false;
	}

	public Role getCurrentRole() {
		return currentRole;
	}

	public void turnEnd() {
		actionPerformed = false;
	}

	/*
	 * checks if their are roles available and sets your current role if you
	 * meet requirements
	 */
	public boolean selectRole(String roleName) {
		roleName = roleName.toLowerCase();
		boolean success = false;
		if(currentRoom.getName().equals("trailer") || currentRoom.getName().equals("office")){

		}
		else if (currentRoom.hasSceneCard() && currentRole == null){
			Role r = currentRoom.getRoleByName(roleName);
			if( r == null){
				r = currentRoom.getCurrentCard().getRoleByName(roleName);
			}
			if ((currentRoom.getRoles().contains(r) || Arrays.asList(currentRoom.getCurrentCard().getRoles()).contains(r)) && !r.isOccupied()) {
				if(rank >= r.getRank()){
					currentRole = r;
					success = true;
					r.setOccupied();
					BoardView.getInstance().moveToRole(color,currentRoom.getName(), r.getName());
				}else{
					System.out.println("You need a rank of "+ r.getRank() + " to work that role, you are rank "+ rank);
				}
			}
		}
		return success;

	}

	public String getColor() {
		return color;
	}

	public void leaveRole() {
		currentRole = null;
	}

	public void newDay() {
		rehearseCount = 0;
		currentRole = null;
		setCurrentRoom(Board.startingRoom);
	}

	public int getRehearseCount() {
		return rehearseCount;
	}

}
