package requestMembers;

import java.io.Serializable;
/**
 * The {@code RequestBoatMember} class is a Request class and it used to send a request from the client to the server.
 * This Request class is used to send this kind of request:
 * 1)Drawn all boat from the database based on the id of a Member.<br>
 * 2)Remove a boat of a Member.<br>
 * 3)Store a new boat on the server.
 * @author Spagni Alex Zuelli Arianna
 * 
 */
public class RequestBoatMember implements Serializable {
private static final long serialVersionUID = 1L;
private String boatName;
private int boatLenght;
private int idBoat;
private int typeRequest;
private int idmember;
/**
 * Costruct class
 * @param boatName name of the boat.
 * @param boatLength length of the boat.
 * @param typeReq an integer value that distinguish which request client has sent to the server.
 * @param idmember id of the member who is making the request.
 */
public RequestBoatMember(String boatName,int boatLength,int typeReq,int idmember) {
	this.boatName=boatName;
	this.boatLenght=boatLength;
	this.idBoat=(int) (Math.random() * 1000000);
	this.typeRequest=typeReq;
	this.idmember=idmember;
}
/**
 * Constructor class.
 * @param id it could be the id of a member that is making the request or the the id of the boat that a user is tring to store on the database.
 * @param typeReq an integer value that distinguish which request client has sent to the server.
 */
public RequestBoatMember(int id,int typeReq) {
	if(typeReq==1|| typeReq==3) {
		this.idmember=id;
	}
	else {
		this.idBoat=id;
	}
	this.typeRequest=typeReq;
}

/**
 * @return
 * It returns an integer value 
 */
public int getTypeRequest() {
	return typeRequest;
}

/**
 * @return
 *  It returns an id of a member
 */
public int getIdmember() {
	return idmember;
}

/**
 * @return
 *  It returns a boat name
 */
public String getBoatName() {
	return boatName;
}

/**
 * @return
 *  It returns a boat lenghth
 */
public int getBoatLenght() {
	return boatLenght;
}

/**
 * @return
 *  It returns an id of a boat
 */
public int getIdCircolo() {
	return idBoat;
}

}
