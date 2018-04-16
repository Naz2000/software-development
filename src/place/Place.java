package place;

import java.util.Observable;
/**
 * An Abstract class place that Room class will define its behavior.
 * @author nazi
 *
 */
public abstract class Place extends Observable {
	protected String label;
	protected String description;
	protected double width;
	protected double depth;
	
	public void setLabel(String label){
		this.label = label;
   }

	public String label() {
		return label;
	}
	/**
	 * A method that set description of room
	 * @param description of place
	 */
	public void setDescription(String description){
		this.description = description;
	}
	public String description(){
		return null;
	}
	/**
	 * A method that gives width and depth of a room
	 * @param width of room
	 * @param depth of room
	 */
	public void setSize(double width, double depth){
		this.width = width;
		this.depth = depth;
		
	}
	/**
	 * A method that calculate the area Of room
	 * @return area
	 */
	public double area() {
		return width*depth;
	}
	
}
