package controller;
import java.util.Observable;
import java.util.Observer;

public class DayListener implements Observer {
  public void observe(Observable o) {
    o.addObserver(this);
  }

  @Override
  public void update(Observable o, Object arg) {
    
    System.out.println("SCENE CARD REMOVED");
  }
}