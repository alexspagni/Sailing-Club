package responseStaff;

import java.io.Serializable;
import java.util.ArrayList;

import javaClass.Notification;
/**
 * The {@code ResponseStaffAddPayments} class is a Response class and it used to send a response from the server to the client.
 * This Response class is used to send this kind of request:
 * 1)Send an array list that contains all notification drawn from the server<br>
 * 2)Send an integer value, that means if an operation produced a positive result or not<br>
 * @author Spagni Alex Zuelli Arianna
 * 
 */
public class ResponseStaffAddPayments implements Serializable {

private static final long serialVersionUID = 1L;
private ArrayList<Notification> notifications;
private int ris;
/**
 * Class constructor
 * @param ris  an integer value that means if an operation produced a positive result or not.
 * @param notifications array list that contains all notification drawn from the server.
 */
public ResponseStaffAddPayments(ArrayList<Notification> notifications, int ris) {
	this.notifications=notifications;
	this.ris=ris;
}
/**
 * Class constructor
 * @param ris  an integer value that means if an operation produced a positive result or not.
 */
public ResponseStaffAddPayments( int ris) {
	this.ris=ris;
}
/**
 * @return
 * It return a list of objects
 */
public ArrayList<Notification> getNotifications() {
	return notifications;
}

/**
 * @return
 * it return an integer value
 */
public int getRis() {
	return ris;
}

}
