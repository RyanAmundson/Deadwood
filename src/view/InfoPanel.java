package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Player;
import controller.Controller;
import controller.Game;

public class InfoPanel extends JPanel{
	
	private JLabel location;
	private JLabel cash;
	private JLabel credits;
	private JLabel rehearseCount;
	private JLabel currentPlayerLabel;
	private JLabel day;
	private JButton rehearsed;
	private JButton endTurn;
	private JLabel rollBox;
	private JLabel blank2;
	private JLabel blank;

	
	public InfoPanel(){
		setBounds(1200, 0, 250, 900);		
		currentPlayerLabel = new JLabel("Current Player");
		currentPlayerLabel.setOpaque(true);
		currentPlayerLabel.setHorizontalAlignment(WIDTH/2);
		currentPlayerLabel.setBackground(Color.WHITE);
		currentPlayerLabel.setPreferredSize(new Dimension(250,50));
		add(currentPlayerLabel);

	
		
//		location = new JLabel("Location");
//		location.setOpaque(true);
//		location.setHorizontalAlignment(WIDTH / 2);
//		location.setBackground(Color.LIGHT_GRAY);
//		add(location);
//		location.setBounds(0, 100, 250, 50);
//
		cash = new JLabel("Cash");
		cash.setOpaque(true);
		cash.setHorizontalAlignment(WIDTH / 2);
		cash.setBackground(Color.LIGHT_GRAY);
		add(cash);
		cash.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));

//		cash.setBounds(0,50,125,50);
		cash.setPreferredSize(new Dimension(110,30));

		credits = new JLabel("Credits");
		credits.setOpaque(true);
		credits.setHorizontalAlignment(WIDTH / 2);
		credits.setBackground(Color.LIGHT_GRAY);
		add(credits);
		credits.setPreferredSize(new Dimension(110,30));
		
		blank = new JLabel();
		blank.setOpaque(true);
		blank.setHorizontalAlignment(WIDTH / 2);
		blank.setBackground(Color.GRAY);
		add(blank);
		blank.setPreferredSize(new Dimension(250,200));
		

		rehearsed = new JButton("Rehearse");
		rehearsed.setOpaque(true);
		rehearsed.setPreferredSize(new Dimension(200,30));
		add(rehearsed);
		rehearsed.setName("rehearse");
		rehearsed.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Controller.mouseClick(e);
			}
		});
		
		rehearseCount = new JLabel("0");
		rehearseCount.setOpaque(true);
		rehearseCount.setHorizontalAlignment(WIDTH / 4);
		rehearseCount.setBackground(Color.LIGHT_GRAY);
		add(rehearseCount);
		rehearseCount.setPreferredSize(new Dimension(40,30));
		blank2 = new JLabel();
		blank2.setOpaque(true);
		blank2.setHorizontalAlignment(WIDTH / 2);
		blank2.setBackground(Color.GRAY);
		add(blank2);
		blank2.setPreferredSize(new Dimension(250,300));
		day = new JLabel("Day");
		day.setOpaque(true);
		day.setHorizontalAlignment(WIDTH / 2);
		day.setBackground(Color.GRAY);
		add(day);
		day.setPreferredSize(new Dimension(250,50));
		
		endTurn = new JButton("End Turn");
		endTurn.setOpaque(true);
		endTurn.setPreferredSize(new Dimension(250,100));
		add(endTurn);
		endTurn.setName("end turn");
		endTurn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Controller.mouseClick(e);
			}
		});
		

		
//		rollBox = new JLabel();
//		rollBox.setBackground(Color.darkGray);
//		rollBox.setVisible(true);
//		rollBox.setOpaque(true);
//		//rollBox.setBorder(new Border(new BorderStroke()));
//		rollBox.setBounds(0,350,250, 150);
//		add(rollBox);
		
		
		
			
		
	}
	
	
	
	
	
	public void test(MouseEvent e) {
//		infoPanel.setText(e.getComponent().getName());
	}

	public void showCurrentPlayer(Player currentPlayer) {
		currentPlayerLabel.setText("Current Turn: " + currentPlayer.getColor());
//		location.setText("Location: " + currentPlayer.getCurrentRoom().getName());
		cash.setText("Cash: " + Integer.toString(currentPlayer.getCash()));
		credits.setText("Credits: " + Integer.toString(currentPlayer.getCredits()));
		rehearseCount.setText(Integer.toString(currentPlayer.getRehearseCount()));
		//if(Deadwood.currentDay == 5){
		//	day.setText("GAME OVER!!!");
		//}else{
		day.setText("Day: " +Integer.toString(Game.currentDay).toUpperCase());
		//}
		
		Color color = new Color(0);
		
		
		switch(currentPlayer.getColor()){
			case "red":
				color = Color.RED;
				break;
			case "blue":
				color = Color.BLUE;
				break;
			case "green":
				color = Color.GREEN;
				break;
			case "yellow":
				color = Color.YELLOW;
				break;
			case "cyan":
				color = Color.CYAN;
				break;
			case "orange":
				color = Color.ORANGE;
				break;
			case "purple":
				color = Color.RED;
				break;
			case "pink":
				color = Color.PINK;
				break;
			default:
				break;
				
		}
//		setBackground(color);
		currentPlayerLabel.setBackground(color);
//		blank.setBackground(color);
//		blank2.setBackground(color);
		
	}
	

	
	
	
	
	
	
	public void rollDice(Player currentPlayer){
		
		
		cash.setText("Cash: " + Integer.toString(currentPlayer.getCash()));
		credits.setText("Credits: " + Integer.toString(currentPlayer.getCredits()));
		revalidate();
		repaint();
		updateUI();
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
