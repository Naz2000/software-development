package actor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Observable;

import place.Room;
import thing.Thing;
/**
 * A class that define person in the world and 
 * is a subclass of Observable calss 
 * @author nazi
 *
 */
public class Person extends Observable {
	public String name;
	public Room location;
	private Collection<Thing> inventory = new ArrayList<Thing>();
	/**
	 * A constructor that initialized the string name
	 * @param name of person
	 */
	public Person (String name){
		this.name = name;	
	}
	/**
	 * A method that move a person to a room and set changes and notifyObservers
	 * @param destination of room
	 */
	public void moveTo(Room destination) {
		location = destination;
		setChanged();
		notifyObservers("change the room");
	}
	/**
	 * A method that define the location of room and return it.
	 * @return location
	 */
	public Room location() {
		return location;
	}
	/**
	 * A method that allow person to take thing and then add it to inventory and set changes.
	 * @param t take thing
	 */
	public void take(Thing t) {
		if(location != null)
		if(location.contents().contains(t)) {
			inventory.add(t);
			location.contents().remove(t);
			setChanged();
			notifyObservers(this.name + " take the " + t.name());
		} else {}
	}
	/**
	 * A method that allow person to drop thing then remove it from inventory and set the changes.
	 * @param t drop thing
	 * @return t thing
	 */
	public Thing drop(Thing t) {
		inventory.remove(t);
		location.add(t);
		setChanged();
		notifyObservers(this.name + " drop the " + t.name());
		
		return t;
		
	}
	/**
	 * A method that call inventory 
	 * @return inventory
	 */
	public Collection<Thing> inventory(){
		return inventory;
	}
	/**
	 * A method that gives a string name to the person
	 * @return name
	 */
	public String name(){
		return name;
	}
	/**
	 * A method that return name of a person 
	 * @return string name
	 */
	public String toString(){
		return "name :" + name;
	}
	
	
	

}
