package thing;

import java.util.Observable;
/**
 * A class that define characteristics of things 
 * these things will add to the Room class and World class.
 * and it is a subclass of Observable .
 * @author nazi
 *
 */
public class Thing extends Observable{
	private String name;
	private String description;
	/**
	 * A constructor that initialize the name and description of things as a string.
	 * @param name and of things
	 * @param description of things 
	 */
	public Thing(String name, String description){
		this.name = name;
		this.description = description;
	}
/**
 * A method to call the name of a thing
 * @return name
 */
	public String name() {
		// TODO Auto-generated method stub
		return name;
	}
/**
 * A method that call the description of thing
 * @return description
 */
	public String description() {
		return description;
	}
	
	public String toString(){
		return "name:" + name +"description :" + description;
	}

}
