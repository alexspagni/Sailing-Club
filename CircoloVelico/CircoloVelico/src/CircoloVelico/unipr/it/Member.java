package CircoloVelico.unipr.it;

import java.io.Serializable;
import java.net.Socket;

/**
 * The {@code Member} class is used to store information about Members
 * 
 * @author Spagni Alex Zuelli Arianna
 * 
 */
public class Member implements Serializable {

	private static final long serialVersionUID = 1L;
	private Socket client;
	private String username;
	private String password;
	private String fiscalCode;
	private String address;
	private int id;

	/**
	 * Class constructor
	 * 
	 * @param client   stream used to communicate to the server.
	 * @param username username of a Member
	 * @param password password of a Member
	 * @param id       id of a Member
	 */
	public Member(Socket client, String username, String password, int id) {
		this.client = client;
		this.username = username;
		this.password = password;
		this.id = id;
	}

	/**
	 * Class constructor
	 * 
	 * @param username username of a Member
	 * @param password password of a Member
	 * @param id       id of a Member
	 */
	public Member(String username, String password, int id) {
		this.username = username;
		this.password = password;
		this.id = id;
	}

	/**
	 * Class constructor
	 */
	public Member() {
	}

	/**
	 * Class constructor
	 * 
	 * @param username   username of a Member
	 * @param address    address of a Member
	 * @param id         id of a Member
	 * @param fiscalCode fiscal code of a Member
	 */
	public Member(String username, String address, int id, String fiscalCode) {

		this.username = username;
		this.address = address;
		this.id = id;
		this.fiscalCode = fiscalCode;
	}

	/**
	 * 
	 * @return It return a stream socket
	 */
	public Socket getClient() {
		return client;
	}

	/**
	 * 
	 * @return It return a username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * 
	 * @return It return a password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 
	 * @return It return an id
	 */
	public int getId() {
		return id;
	}

	/**
	 * 
	 * @return It return a fiscal code
	 */

	public String getFiscalCode() {
		return fiscalCode;
	}

	/**
	 * 
	 * @return It return an address
	 */
	public String getAddress() {
		return address;
	}

}
