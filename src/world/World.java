package world;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Observable;
import java.util.Observer;

import actor.Person;
import place.Room;
import thing.Thing;
/**
 * World class is a subclass of Observable and implement Observer Interface
 * add collection of places , things and people to the world
 * set changes and update them to be obvious   
 * 
 * @author nazi
 *
 */
public class World extends Observable implements Observer{
	private Collection<Room> places = new ArrayList<Room>(); //attach rooms
	private Collection<Thing> things = new ArrayList<Thing>(); //attach thing
	private Collection<Person> people = new ArrayList<Person>(); //attach people
	/**
	 * public constructor to Set up a demoWorld
	 * @param makeDemo is shown
	 */
	public World(boolean makeDemo) {
		// TODO Auto-generated constructor stub
		if(makeDemo) {
			demoWorld();
			
		};
	}
	/**
	 * public constructor
	 */
	public World() {

	}
	/**
	 * A method that call places .
	 * @return places
	 */
	public Collection<Room> places() {
		return places;
	}
	/**
	 * A method that call actors. 
	 * @return people
	 */
	public Collection<Person> actors() {
		return people;			
	}
	/**
	 * A method that call items .
	 * @return items
	 */
	public Collection<Thing> items() {
		return things;
	}
	/**
	 * this method Adds an observer from super class and update all the observable 
	 */
	public void addObserver(Observer o){
		super.addObserver(o);
		updateAllObservable(o);	
	}
	/**
	 *This method Adds an observer to the set of observers for this object, 
	 *provided that it is not the same as some observer already in the set. 
	 * otherwise ignored.
	 * @param observer set observer 
	 */
	public void updateAllObservable(Observer observer) {
		Collection<Observable> observable = new ArrayList<Observable>();
		observable.addAll(places());
		observable.addAll(actors());
		observable.addAll(items());
		
		for (Observable i : observable) {
			i.addObserver(observer);
		}
	}
		
	/**
	 * method that add rooms set changes to rooms and notify to observers
	 * @param room add to world
	 */
	public void addRoom(Room room) {
		places.add(room);
		this.setChanged();
		this.notifyObservers();
	}
	/**
	 * method that add person set changes to them and notify to observers
	 * @param person add to world
	 */
	public void addPerson(Person person) {
		people.add(person);
		this.setChanged();
		this.notifyObservers();
	}
	/**
	 * method that add thing set changes to them and notify to observers
	 * @param thing add to world
	 */
	public void addThing(Thing thing) {
		things.add(thing);
		this.setChanged();
		this.notifyObservers();
	}
	/**
	 * add new room, people and thing to the world with some changes
	 */
	public void demoWorld() {
		//this.add(new Room("test",0,""));
		// TODO Auto-generated constructor stub
		
		
		Person nazi = new Person("Nazi");
		nazi.addObserver(this);
		Person john = new Person("John");
		john.addObserver(this);
		Person sara = new Person("Sara");
		sara.addObserver(this);
		Room num1 = new Room(" resturant ",1," some foods ");
		num1.addObserver(this);
		Room num2 = new Room(" house ",2," some furniture ");
		num2.addObserver(this);
		Room num3 = new Room(" forest ",3," some weapon ");
		num3.addObserver(this);
		Thing furniture = new Thing(" furniture "," desk ");
		furniture.addObserver(this);
		Thing drink = new Thing(" drink "," juice ");
		drink.addObserver(this);
		Thing weapon = new Thing(" weapon "," gun ");
		weapon.addObserver(this);
		// To check the JScrollpanel
		
		//for (int i=0;i<100;i++){
			//Room r = new Room("test room", i," a test room in building");
			//places.add(r);
		//}
		
		//for (int i=0;i<100;i++){
			//Person p = new Person("test"+i);
			//people.add(p);
		//}
		
		//for (int i=0;i<100;i++){
			//Thing t = new Thing("test"+i,"test"+i);
			//num1.add(t);
			//addThing(t);
		//}
		

		places.add(num1);
		places.add(num2);
		places.add(num3);
		
		
		people.add(nazi);
		people.add(john);
		people.add(sara);
		
		things.add(drink);
		things.add(furniture);
		things.add(weapon);
		
		
		num1.add(drink);
		num2.add(furniture);
		num3.add(weapon);
		
		
		nazi.moveTo(num1);
		nazi.take(drink);
		nazi.moveTo(num2);
		john.moveTo(num2);
		nazi.drop(drink);
		john.take(drink);
		john.take(furniture);
		sara.moveTo(num3);
		sara.take(weapon);
		

	}
	
	
	/**
	 * this method update the changes that happen and observes them
	 */
	public void update(Observable arg0, Object arg1) {
		setChanged();
		notifyObservers();
	}
	
}

		
		
		
	

	
	
	


