package requestMembers;

import java.io.Serializable;
/**
 * The {@code RequestRaceMember} class is a Request class and it used to send a request from the client to the server.
 * This Request class is used to send this kind of request:
 * 1)Drawn all race from the database based on the id of a Member.<br>
 * 2)Join a race<br>
 * @author Spagni Alex Zuelli Arianna
 * 
 */
public class RequestRaceMember implements Serializable{

private static final long serialVersionUID = 1L;
private int typeReq;
private int idmember;
private int idRace;
private int idBoat;
/**
 * Class constructor.
 * @param typeReq an integer value that distinguish which request client has sent to the server.
 * @param idmember id of the member who is making the request.
 */
public RequestRaceMember (int typeReq,int idmember) {
	this.typeReq=typeReq;
	this.idmember=idmember;
}
/**
 * Class constructor.
 * @param typeReq an integer value that distinguish which request client has sent to the server.
 * @param idmember id of the member who is making the request.
 * @param idRace  id of the race.
 * @param idBoat id of the boat.
 */
public RequestRaceMember (int typeReq,int idmember,int idRace,int idBoat) {
	this.typeReq=typeReq;
	this.idmember=idmember;
	this.idRace=idRace;
	this.idBoat=idBoat;
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
 *  It returns the id of a member
 */
public int getIdmember() {
	return idmember;
}

/**
 * @return
 *  It returns the id of a race
 */
public int getIdRace() {
	return idRace;
}

/**
 * @return
 *  It returns the id of a boat
 */
public int getIdBoat() {
	return idBoat;
}


}
