package controller;

import java.awt.Component;
import java.awt.event.MouseEvent;

public class Controller {

	
	/*this guy had big dreams in the beginning but it turns out I had most of his job already written in my game.java which is in the model folder but probably
	 * should be in the controller folder
	 * 
	 * So all this guy does is determine which component was called connected to which event then tells the model
	 * he receives the event from Boardview.java and sends the component to Game.java
	 * 
	 */
	
	
	
	static public void mouseClick(MouseEvent e){


		Component component = e.getComponent();
		//String ob = new String(e.getComponent().getName());
		//System.out.println(ob+" was clicked");
		Controller.eventHandler(component);
	}
	
	public static void eventHandler(Component c) {
		switch (c.getName()){

		case "Main Street":
		case "Saloon":
		case "Jail":
		case "General Store":
		case "Ranch":
		case "Trailer":
		case "Train Station":
		case "Bank":
		case "Office":
		case "Secret Hideout":
		case "Hotel":
		case "Church":
			Game.getInstance().move(c.getName().toLowerCase());
			break;

		case "cash4":
			Game.getInstance().upgradeDollars(2);
			break;
		case "cash10":
			Game.getInstance().upgradeDollars(3);
			break;
		case "cash18":
			Game.getInstance().upgradeDollars(4);
			break;
		case "cash28":
			Game.getInstance().upgradeDollars(5);
			break;
		case "cash40":
			Game.getInstance().upgradeDollars(6);
			break;
		case "credit5":
			Game.getInstance().upgradeCrdit(2);
			break;
		case "credit10":
			Game.getInstance().upgradeCrdit(3);
			break;
		case "credit15":
			Game.getInstance().upgradeCrdit(4);
			break;
		case "credit20":
			Game.getInstance().upgradeCrdit(5);
			break;
		case "credit25":
			Game.getInstance().upgradeCrdit(6);
			break;

		case "end turn":
			Game.getInstance().endTurn();
			break;
		case "rehearse":
			Game.getInstance().rehearse();
			break;
		default:
			switch (c.getName().substring(0, 4)) {
			case ("role"):
				Game.getInstance().work(c.getName().substring(5));
				break;
			}
			break;

		}

	}
	
	
	
	
	
}
