package requestStaff;

import java.io.Serializable;
import java.sql.Date;
/**
 * The {@code RequestStaffRace} class is a Request class and it used to send a request from the client to the server.
 * This Request class is used to send this kind of request:
 * 1)Get all race payment from the database.<br>
 * 2)Modify a race.<br>
 * 3)Simulate a race.<br>
 * 4)Add a race.
 * @author Spagni Alex Zuelli Arianna
 * 
 */
public class RequestStaffRace implements Serializable{
	private static final long serialVersionUID = 1L;
	private String raceName;
	private Date raceDate;
	private int price;
	private String racePlace;
	private int typeReq;
	private int idRace;
	/**
	 * Class constructor
	 * @param typeReq an integer value that distinguish which request client has sent to the server.
	 * @param raceName name of the new race.
	 * @param raceDate date of the new race.
	 * @param price price of the new race.
	 * @param racePlace place of the new race.
	 */
	public RequestStaffRace(int typeReq,String raceName,Date raceDate,int price,String racePlace) {
		this.typeReq=typeReq;
		this.raceName=raceName;
		this.raceDate=raceDate;
		this.price=price;
		this.racePlace=racePlace;
		
	}
	/**
	 * Class constructor
	 * @param typeReq an integer value that distinguish which request client has sent to the server.
	 * @param idGara id that identify univocally a race.
	 * @param raceName name of the race.
	 * @param racePlace place of the race.
	 */
	public RequestStaffRace(int typeReq,int idGara,String raceName,String racePlace) {
		this.typeReq=typeReq;
		this.idRace=idGara;
		this.raceName=raceName;
		this.racePlace=racePlace;
		
	}
	/**
	 * Class constructor
	 * @param typeReq an integer value that distinguish which request client has sent to the server.
	 */
	public RequestStaffRace(int typeReq) {
		this.typeReq=typeReq;
		
	}
	/**
	 * Class constructor
	 * @param typeReq an integer value that distinguish which request client has sent to the server.
	 * @param idGara id that identify univocally a race.
	 * @param raceDate date of a race.
	 * @param racePlace where a race will took place.
	 */
	public RequestStaffRace(int typeReq,int idGara, Date raceDate,String racePlace) {
		this.typeReq=typeReq;
		this.idRace=idGara;
		this.raceDate=raceDate;
		this.racePlace=racePlace;
		
	}
	/**
	 * Class constructor
	 * @param typeReq an integer value that distinguish which request client has sent to the server.
	 * @param idGara id that identify univocally a race.
	 */
	public RequestStaffRace(int typeReq,int idGara) {
		this.typeReq=typeReq;
		this.idRace=idGara;
		
	}
	/**
	 * @return
	 *  It returns the name of a race
	 */
	public String getRaceName() {
		return raceName;
	}

	/**
	 * @return
	 *  It returns the date of a race
	 */
	public Date getRaceDate() {
		return raceDate;
	}

	/**
	 * @return
	 *  It returns the price of a race
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * @return
	 *  It returns the place of a race
	 */
	public String getRacePlace() {
		return racePlace;
	}

	/**
	 * @return
	 *  It returns an integer value
	 */
	public int getTypeReq() {
		return typeReq;
	}

	/**
	 * @return
	 *  It returns the id of a race
	 */
	public int getIdRace() {
		return idRace;
	}

	
}
