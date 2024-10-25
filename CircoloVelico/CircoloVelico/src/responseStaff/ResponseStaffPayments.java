package responseStaff;

import java.io.Serializable;
import java.util.ArrayList;

import javaClass.FeePayment;
/**
 * The {@code ResponseStaffPayments} class is a Response class and it used to send a response from the server to the client.
 * This Response class is used to send this kind of request:
 * 1)Send an array list that contains all payments drawn from the server<br>
 * 2)Send an integer value, that means if an operation produced a positive result or not<br>
 * @author Spagni Alex Zuelli Arianna
 * 
 */

public class ResponseStaffPayments implements Serializable{

	private static final long serialVersionUID = 1L;
	private int ris;
	private ArrayList<FeePayment> payment;
	/**
	 * Class constructor
	 * @param ris  an integer value that means if an operation produced a positive result or not.
	 * @param payment array list that contains all payments drawn from the server.
	 */
	public ResponseStaffPayments(int ris,ArrayList<FeePayment>payment) {
		this.ris=ris;
		this.payment=payment;
		
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
	public ArrayList<FeePayment> getPayment() {
		return payment;
	}

	}

