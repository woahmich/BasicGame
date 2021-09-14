package Game;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {

	LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	// run through each object created
	public void tick() {
		// runs through every object, and updates them
		for(int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			tempObject.tick();
		}
		
	}
	public void render(Graphics g) {
		// loops through every object and renders them
		for(int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			tempObject.render(g);
		}
		
	}
	
	// adds objects to LinkedList
	public void addObject(GameObject object) {
		this.object.add(object);
	}
	
	// removes object from LinkedList
	public void RemoveObject(GameObject object) {
		this.object.remove(object);
	}
	
}
