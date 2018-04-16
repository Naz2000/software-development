package ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import place.Room;
import thing.Thing;
import world.World;
import actor.Person;

/**
 * Prototype controller for Worlds.  Observes updates in World and
 * supports browsing of World. Supports some simple actions.
 * If a person and place are selected then move allows relocation.
 * A person can take or drop a thing from their current location.
 * 
 *  Needs facilities to allow creation and editing of worlds.
 *  
 * @see world 
 * @author Nazanin
 *
 */
public class WorldControl implements Observer {
	
	private World w;
	
	private Person selectedPerson;
	private Room selectedRoom;
	private Thing selectedThing;
	
	private JFrame jf = new JFrame("World Control");
	
	private JList roomList = new JList();
	private JList actorList = new JList();
	private JList stuff = new JList();
	private JList contents = new JList();
	
	private JTextField textfieldPersonName  = new JTextField(10); 
	
	private JTextField textfieldRoomLabel  = new JTextField(5); 
	private JTextField textfieldRoomDesc  = new JTextField(5);
	private JTextField textfieldRoomLevel  = new JTextField(5);
	
	private JTextField textfieldThingName  = new JTextField(10);
	private JTextField textfieldThingDesc  = new JTextField(10);
	
	JScrollPane jsp;
	JPanel jp;
	/**
	 * make a GUI, panel,layout and scrolPanel
	 */
	private void buildGui() {
		jp = new JPanel();
		
		jp.setLayout(new GridLayout(3,3));
		jsp = new JScrollPane(jp);
		
		buildRooms();
		buildPeople();
		buildContents();
		buildStuff();
		buildControls();
		addRoom();
		addPerson();
		addThing();
		
		jf.add(jsp);
		jf.pack();
		jf.setVisible(true);
	}
	/**
	 * private addRoom method that add new room to the list of rooms
	 */
	private void addRoom() {
		JPanel jpc = new JPanel();
	
		TitledBorder b = BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder(), "room");
		jpc.setBorder(b);
		jpc.add(textfieldRoomLabel);
		jpc.add(textfieldRoomLevel);
		jpc.add(textfieldRoomDesc);
		
		
		JButton addButton = new JButton("Add");
		addButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				Room r = new Room(textfieldRoomLabel.getText(),
						Integer.parseInt(textfieldRoomLevel.getText()), 
						textfieldRoomDesc.getText()
						);
				w.addRoom(r);
				r.addObserver(w);
				
			}
		});
		jpc.add(addButton);
		 jp.add(jpc);
		 //jp.add(jsp);
	}
	/**
	 * private addPerson method that add a new person to a room
	 */
	private void addPerson() {
		JPanel jpc = new JPanel();
		TitledBorder b = BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder(), "person");
		jpc.setBorder(b);
		jpc.add(new JLabel("Name:"));
		jpc.add(textfieldPersonName);
		JButton addButton = new JButton("Add");
		addButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				//code goes here
				//selectedRoom;
				Person p = new Person(textfieldPersonName.getText());
				w.addPerson(p);
				selectedRoom.add(p);
			}
		});
		
		jpc.add(addButton);
		jp.add(jpc);
		
	}
	/**
	 * private addThing method that add new thing to selected room and take it by a person
	 */
	private void addThing() {
		JPanel jpc = new JPanel();
		TitledBorder b = BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder(), "thing");
		jpc.setBorder(b);
		
		jpc.add(new JLabel("Name:"));
		jpc.add(textfieldThingName);
		
		jpc.add(new JLabel("Desc: "));
		jpc.add(textfieldThingDesc);
		
		
		
		JButton addButton = new JButton("Add");
		addButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				//code goes here
				//selectedRoom;
				Thing t = new Thing(textfieldThingName.getText(),textfieldThingDesc.getText());
				if(selectedRoom != null) selectedRoom.add(t);
				if(selectedPerson != null) selectedPerson.take(t);
				w.addThing(t);
			}
		});
		
		jpc.add(addButton);
		jp.add(jpc);
		
	}
	
	/**
	 * private method that generate Room Contents frame with a scrollPane
	 */
	
	private void buildContents() {
		JPanel jpc = new JPanel();
		TitledBorder b = BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder(), "Room Contents");
		jpc.setBorder(b);
		if(selectedRoom != null) {
			b.setTitle(b.getTitle() + "[" + selectedRoom.label());
		}
		
		JScrollPane jsp = new JScrollPane(contents);
		jpc.add(jsp);
		jp.add(jpc);
	}
	
	/**
	 * private method that generate Actor Inventory frame with a scrollPane 
	 */
	private void buildStuff() {
		JPanel jps = new JPanel();
		jps.setBorder(BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder(), "Actor Inventory"));
		
		JScrollPane jsp = new JScrollPane(stuff);
		jps.add(jsp);
		//jpr.add(roomList);
		jp.add(jps);
		
	}
	
	private void buildPeople(){
		actorList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		actorList.addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent lse) {
				selectedPerson = (Person) actorList.getSelectedValue();
				if(selectedPerson != null) {
					stuff.setListData(selectedPerson.inventory().toArray(new Thing[0]));
					roomList.setSelectedValue(selectedPerson.location(), true);
				}
			}
		});
		
		JPanel jpp = new JPanel();
		jpp.setBorder(BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder(), "Actors"));

		//jpp.add(actorList);
		//jp.add(jpp);
		
		JScrollPane jsp = new JScrollPane(actorList);
		jpp.add(jsp);
		//jpr.add(roomList);
		jp.add(jpp);
	}
	
	private void buildRooms() {
		roomList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		roomList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		roomList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent lse) {
				Room r = (Room)roomList.getSelectedValue();
				selectedRoom = r;
				if(r != null) {
					contents.setListData(r.contents().toArray(new Thing[0]));
				}
			}
		});
	
		
		JPanel jpr = new JPanel();
		jpr.setBorder(BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder(), "Places"));

		JScrollPane jsp = new JScrollPane(roomList);
		jpr.add(jsp);
		//jpr.add(roomList);
		jp.add(jpr);
		
	}
	/**
	 * private buildWorld method that call places and actors in world
	 * and demo it 
	 */
	private void buildWorld() {
		w = new World();
		w.demoWorld();
		
		roomList.setListData( w.places().toArray(new Room[0]));
		System.out.println(w.places());
		actorList.setListData(w.actors().toArray(new Person[0]));
		System.out.println(w.actors());
		w.addObserver(this);
		// A separate view
		WorldView wv = new WorldView(w);
	}

	/**
	 * private buildControls method that create buttons
	 * and give action to them
	 */
	private void buildControls() {
		/*
		 * Moves selected person to selected room.
		 */
		JButton go = new JButton("Go To");
		go.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae) {
				Object o = roomList.getSelectedValue();
				if (o instanceof Room) {
					if(selectedPerson == null){
						System.err.println("No actor selected");
					} else {
						selectedRoom = (Room)o;
						System.out.println("moving " + selectedPerson.name() + " from " + 
								selectedPerson.location() + " to " + ((Room)roomList.getSelectedValue()));
						selectedPerson.moveTo((Room) roomList.getSelectedValue());
					}
				} else {
					System.err.println("Not a Room---you can't go there");
				}
			}
		});
		
		/*
		 * Selected person can take selected thing from selected room
		 *
		 */
		JButton take = new JButton("Take");
		take.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if(selectedRoom == null) {
					System.err.println("Can't take: no Room selected");
					return;
				} else if (selectedPerson == null)  {
					System.err.println("Can't take: no Actor selected");
					return;
				} else if(selectedPerson.location() != selectedRoom) {
					System.err.println("Must be in " + selectedRoom + " to take this");
					return;
				} else {	
					selectedThing = (Thing)contents.getSelectedValue();
					if(selectedThing != null) {
						selectedPerson.take(selectedThing);
					}
				}
			}
		});
		
		/*
		 * Selected person can drop selected thing for inventory
		 */
		JButton drop = new JButton("Drop");
		drop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if(selectedRoom == null) {
					System.err.println("Can't drop: no Room selected");
					return;
				} else if (selectedPerson == null)  {
					System.err.println("Can't drop: no Actor selected");
					return;
				} 
				if(selectedPerson.location() != selectedRoom) {
					// will drop it where we are, irrespective of list state
					roomList.setSelectedValue(selectedRoom, true);
				}	
				selectedThing = (Thing)stuff.getSelectedValue();
				if(selectedThing != null) {
					selectedPerson.drop(selectedThing);
				}
			}
		});
		
		
		
		JButton clear = new JButton("Clear");
		clear.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				actorList.clearSelection();
				roomList.clearSelection();
				contents.clearSelection();
				stuff.clearSelection();
			}
		});
		
		JButton quit = new JButton("Quit");
		quit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae) {
				jf.setVisible(false);
				jf.dispose();
				System.exit(0);
				
			}
		});
		
		JPanel buttons = new JPanel();
		JPanel otherButtons = new JPanel();
		
		buttons.add(go);
		buttons.add(take);
		buttons.add(drop);
		
		
		otherButtons.add(clear);
		otherButtons.add(quit);
		
		jp.add(buttons);
		jp.add(otherButtons);
		
	}
	
	
	
	/**
	 * Update actors list after possible room change.
	 */
	private void refreshActors() {
		actorList.setListData(w.actors().toArray(new Person[0]));
		actorList.clearSelection();
		stuff.clearSelection();
		stuff.setListData(new Thing[0]);
	}
	/**
	 * Update rooms list after possible room change.
	 */
	private void refreshRooms() {
		roomList.setListData(w.places().toArray(new Room[0]));
		roomList.clearSelection();
		contents.setListData(new Room[1]);
	}

	/**
	 * Assemble a demo world (since we don't have world editing capability yet).
	 * GUI includes separate WorldView viewer.
	 * @param args not used
	 */
	public static void main(String[] args) {
		WorldControl me = new WorldControl();
		me.buildGui();
		me.buildWorld();
		
	}

	/**
	 * Something in the world has changed.  Update GUI content accordingly.
	 */
	//@Override
	public void update(Observable arg0, Object arg1) {
		// Something in the world changed
		System.out.println("Arg0: " + arg0);
		System.out.println("Arg1: " + arg1);
		
		refreshActors();
		refreshRooms();
		
	}

}
