package javaClass;
/**
 * The {@code RacePartecipants} class is used to store information about race partecipants.
 * 
 * @author Spagni Alex Zuelli Arianna
 * 
 */
public class RacePartecipants {
	private int idRace;
	private int idBoat;
	private int idmember;
	private int idPartecipants;
/**
 * Constructor Class
 */
	public RacePartecipants() {
		
	}
	/**
	 * Constructor Class
	 * @param idRace id of a race
	 * @param idBoat id of a boat
	 * @param idmember id of a member
	 * @param idPartecipants id of a partecipant of a race
	 */
	public RacePartecipants(int idRace, int idBoat, int idmember, int idPartecipants) {
		this.idRace = idRace;
		this.idBoat = idBoat;
		this.idmember = idmember;
		this.idPartecipants = idPartecipants;
	}
/**
 * 
 * @return
 * It returns the id of a race.
 */
	public int getIdRace() {
		return idRace;
	}

	/**
	 * 
	 * @return
	 * It returns the id of a boat.
	 */
	public int getIdBoat() {
		return idBoat;
	}
	/**
	 * 
	 * @return
	 * It returns the id of a member.
	 */
	public int getIdmember() {
		return idmember;
	}
	/**
	 * 
	 * @return
	 * It returns the id of a partecipants of a race.
	 */
	public int getIdPartecipants() {
		return idPartecipants;
	}

}
