package responseMembers;

import java.io.Serializable;
import java.util.ArrayList;

import javaClass.Boat;
/**
 * The {@code ResponseBoatMember} class is a Response class and it used to send a response from the server to the client.
 * This Response class is used to send this kind of request:
 * 1)Send an array list that contains all boat drawn from the server<br>
 * 2)Send an integer value, that means if an operation produced a positive result or not<br>
 * @author Spagni Alex Zuelli Arianna
 * 
 */
public class ResponseBoatMember implements Serializable{
	private static final long serialVersionUID = 1L;
	private int resp;
	private ArrayList<Boat> boatArrayList=null;
	/**
	 * Class constructor
	 * @param resp an integer value that means if an operation produced a positive result or not.
	 */
	public ResponseBoatMember(int resp) {
		this.resp=resp;
	}
	/**
	 * Class constructor
	 * @param resp an integer value that means if an operation produced a positive result or not.
	 * @param boatArrayList array list that contains all boat drawn from the server.
	 */
	public ResponseBoatMember(int resp, ArrayList<Boat> boatArrayList) {
		this.resp=resp;
		this.boatArrayList=boatArrayList;
	}
	/**
	 * @return
	 * it return an integer value
	 */
	public int getResp() {
		return resp;
	}
	/**
	 * @return
	 * It return a list of objects
	 */
	public ArrayList<Boat> getBoatArrayList() {
		return boatArrayList;
	}

}
