package view;

import java.util.LinkedList;

public class Animation<E> {
	
	LinkedList<E> frames = new LinkedList<E>();
	Object o;
	
	
	public Animation(LinkedList<E> frames, Object o){
		this.frames = frames;
	}
	
	public void animate(){
	}
}
