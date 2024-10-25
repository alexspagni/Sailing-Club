package responseStaff;

import java.io.Serializable;
import java.util.ArrayList;

import javaClass.Boat;
/**
 * The {@code ResponseStaffManageBoat} class is a Response class and it used to send a response from the server to the client.
 * This Response class is used to send this kind of request:
 * 1)Send an array list that contains all boats fee drawn from the server<br>
 * 2)Send an integer value, that means if an operation produced a positive result or not<br>
 * @author Spagni Alex Zuelli Arianna
 * 
 */
public class ResponseStaffManageBoat implements Serializable{

	private static final long serialVersionUID = 1L;
	private int ris;
	private ArrayList<Boat> boats;
	/**
	 * Class constructor
	 * @param ris  an integer value that means if an operation produced a positive result or not.
	 * @param boats array list that contains all boats drawn from the server.
	 */
	public  ResponseStaffManageBoat(int ris,ArrayList<Boat>boats) {
		this.ris=ris;
		this.boats=boats;
	}
	/**
	 * Class constructor
	 * @param ris  an integer value that means if an operation produced a positive result or not.
	 */
	public  ResponseStaffManageBoat(int ris) {
		this.ris=ris;
	
	}
	/**
	 * @return
	 * it return an integer value
	 */
	public int getRis() {
		return ris;
	}

	/**
	 * @return
	 * It return a list of objects
	 */
	public ArrayList<Boat> getBoats() {
		return boats;
	}

}
