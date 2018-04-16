import static org.junit.Assert.*;

import org.junit.Test;

import place.Room;
import thing.Thing;
import world.World;
import actor.Person;


public class Test1 {
	private World world;
	public Test1() {
	}

	@Test
	public void test() {
		world = new World();
		
		Person nazi = new Person("Nazi");
		
		Person john = new Person("John");
		
		Person sara = new Person("Sara");
		
		Room num1 = new Room("resturant",1,"some foods");
		
		Room num2 = new Room("label2",2,"some furniture");
		
		Room num3 = new Room("label3",3,"some weapon");
		
		Thing furniture = new Thing("furniture","desk");
		
		Thing drink = new Thing("drink","juice");
		
		Thing weapon = new Thing("weapon","gun");
		
		//Room forest = new Room("forest",0,"alien");
		//forest.addObserver(this);

		world.addRoom(num1);
		world.addRoom(num2);
		world.addRoom(num3);
		
		
		world.addPerson(nazi);
		world.addPerson(john);
		world.addPerson(sara);
		
		world.addThing(drink);
		world.addThing(furniture);
		world.addThing(weapon);
		
		
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
		
		assertSame("nazi did not go to room num1",nazi.location(), num2);
		assertFalse("Nazi did drop the drink",nazi.inventory().contains(drink));
	
	}

}
