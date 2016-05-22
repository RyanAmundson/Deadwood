package model;

import java.util.Observable;
import java.util.Observer;

public class RoomChangeListener implements Observer{
	
	private Observable player;
	
	  public void observe(Observable o) {
		    o.addObserver(this);
	  }

	  @Override
	  public void update(Observable o, Object arg) {
		    System.out.println("room change observed");
		    System.out.println(arg);
	  }
}
