package model;

import java.util.Random;

public class Dice{
	
	final int MAX_DICE = 5;
	private int numberOfDice = 0;
	public int[] lastRoll;
	
	public Dice(int numberOfDice){
		
		this.numberOfDice = numberOfDice;
		
	}
	
	public int[] roll(){
		int[] result = new int[numberOfDice];
		Random r = new Random();
		for(int i =0; i<numberOfDice; i++){
			result[i] = r.nextInt(5) + 1;
			System.out.println(result[i]);
		}
		lastRoll = result;
		return result;
	}
	
	
	
	
	
	
	
	
}
