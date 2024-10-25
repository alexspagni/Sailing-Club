package responseStaff;

import java.io.Serializable;
import java.util.ArrayList;

import CircoloVelico.unipr.it.Member;
/**
 * The {@code ResponseStaffManagemember} class is a Response class and it used to send a response from the server to the client.
 * This Response class is used to send this kind of request:
 * 1)Send an array list that contains all Members drawn from the server<br>
 * 2)Send an integer value, that means if an operation produced a positive result or not<br>
 * @author Spagni Alex Zuelli Arianna
 * 
 */
public class ResponseStaffManageMember implements Serializable{

	private static final long serialVersionUID = 1L;
	private int ris;
	ArrayList<Member>socii;
	/**
	 * Class constructor
	 * @param ris  an integer value that means if an operation produced a positive result or not.
	 */
	public ResponseStaffManageMember(int ris) {
		this.ris=ris;
	}
	/**
	 * Class constructor
	 * @param ris  an integer value that means if an operation produced a positive result or not.
	 * @param socii array list that contains all Socii drawn from the server.
	 */
	public ResponseStaffManageMember(int ris,ArrayList<Member>socii) {
		this.ris=ris;
		this.socii=socii;
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
	 *  It return a list of objects
	 */
	public ArrayList<Member> getSocii() {
		return socii;
	}


}
