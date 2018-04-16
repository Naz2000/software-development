package place;

import java.util.ArrayList;
import java.util.Collection;
import actor.Person;
import thing.Thing;

/**
 * A Room class that define the area of the room 
 * and add person and things to each room and set the changes.
 * This class extend Abstract Place class. 
 * @see world
 * @see thing
 * @author nazi
 *
 */

public class Room extends Place {
	private int room1;
	
	private int level ;
	private Collection<Thing> contents = new ArrayList<Thing>();
	private Collection<Person> people = new ArrayList<Person>();
	
	public Collection<Thing> getContents(){
		return contents;
	}	
	/**
	 * A constructor of room class that gets three parameters and initialize them.
	 * @param label of the room
	 * @param level of the room
	 * @param description of the room
	 */
	public Room(String label,int level,String description){
		this.label = label;
		this.level = level;
		this.description = description;	
	}
	/**
	 * A method that set level of a room
	 * @param level of the room
	 */	
	public void setLevel(int level) {
		this.level = level;
	}
	/**
	 * A method that return the level of a room
	 * @return this level
	 */
	public int level() {
		return this.level;
	}
	@Override
	public String toString(){
		return "Label: " + label +" Des: "+ description+ " Level: " + level ;
	}
	/**
	 * A method that return the contents of the room.
	 * @return contents of things
	 */
	public Collection<Thing> contents() {
		return contents;
		
	}
	/**
	 * A method that add person to the room and set the changes.
	 * @param p add person to room
	 */
	public void add(Person p){
		p.moveTo(this);
		setChanged();
		notifyObservers("hi");
	}
	/**
	 * A method that add thing to the room.
	 * @param k add thing to room
	 */
	public void add(Thing k){
		contents.add(k);
	}
	public String contentsList(){
		return new String();
	}

	
	
}
