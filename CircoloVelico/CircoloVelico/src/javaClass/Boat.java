package javaClass;

import java.io.Serializable;

/**
 * The {@code Boat} class is used to store information about boats.
 * 
 * @author Spagni Alex Zuelli Arianna
 * 
 */
public class Boat implements Serializable{
	private static final long serialVersionUID = 1L;
	private String name;
	private int idBoat;
	private int idMember;
	private int length;
	private String storage = "si";

	/**
	 * Class constructor
	 * 
	 * @param name      name of a boat
	 * @param id        id of a boat
	 * @param idMember   id of a member
	 * @param length length of a boat
	 */
	public Boat(String name, int id, int idMember, int length) {
		this.name = name;
		this.idBoat = id;
		this.idMember = idMember;
		this.length = length;
	}

	/**
	 * Class constructor
	 * 
	 * @param name        name of a boat
	 * @param id          id of a boat
	 * @param idMember     id of a member
	 * @param length   length of a boat
	 * @param storage string that say if a boat is in rimessaggio or not
	 */
	public Boat(String name, int id, int idMember, int length, String storage) {
		this.name = name;
		this.idBoat = id;
		this.idMember = idMember;
		this.length = length;
		this.storage = storage;
	}

	/**
	 * Class constructor
	 */
	public Boat() {

	}

	/**
	 * @return It return a boat name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return It return a boat id
	 */
	public int getId() {
		return idBoat;
	}

	/**
	 * @return It return a id member
	 */
	public int getIdMember() {
		return idMember;
	}

	/**
	 * @return It return a boat length
	 */
	public int getLength() {
		return length;
	}

/**
 * @return
 * It return a boat status
 */
public String getStorage() {
	return storage;
}
//	@Override
//	public int compareTo(Boat o) {
//		if (this.getId() == o.getId()) {
//			return 1;
//		}
//		return -1;
//
//	}
//@Override
//public String toString() {
//	return ""+this.idCircolo;
//}
}
