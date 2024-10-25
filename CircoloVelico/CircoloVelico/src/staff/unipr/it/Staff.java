package staff.unipr.it;

import java.net.Socket;
/**
 * The {@code Staff} class is used to store information about staff members.
 * 
 * @author Spagni Alex Zuelli Arianna
 * 
 */
public class Staff {
private Socket client;
private String username;
private String password;
private int id;
/**
 * Class constructor
 * @param client Strem socket used to communicate with the server
 * @param username username of a member staff
 * @param password password of a member staff
 * @param id id of a member staff
 */
public Staff(Socket client,String username,String password,int id) {
	this.client=client;
	this.username=username;
	this.password=password;
	this.id=id;
}
/**
 * Class constructor
 * @param username username of a member staff
 * @param password password of a member staff
 * @param id id of a member staff
 */
public Staff(String username,String password,int id) {
	
	this.username=username;
	this.password=password;
	this.id=id;
}
/**
 * @return
 *  It return a username
 */
public String getUsername() {
	return username;
}

/**
 * @return
 *  It return a password
 */
public String getPassword() {
	return password;
}

/**
 * @return
 *  It return an id
 */
public int getId() {
	return id;
}

/**
 * @return
 *  It return a stream Socket
 */
public Socket getClient() {
	return client;
}
}
