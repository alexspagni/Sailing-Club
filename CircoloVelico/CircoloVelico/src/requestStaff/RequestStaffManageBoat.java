package requestStaff;

import java.io.Serializable;
/**
 * The {@code RequestStaffManageBoat} class is a Request class and it used to send a request from the client to the server.
 * This Request class is used to send this kind of request:
 * 1)Get all boat from the database.<br>
 * 2)Send new data about a boat to the server.<br>
 * @author Spagni Alex Zuelli Arianna
 * 
 */

public class RequestStaffManageBoat implements Serializable{

private static final long serialVersionUID = 1L;
private int idBoat;
private int length;
private int typeReq;
private String storage;
/**
 * Class constructor
 * @param idBoat id of the boat which a member of the staff managed.
 * @param lunghezza new length of the boat.
 * @param typeReq an integer value that distinguish which request client has sent to the server.
 * @param storage string that say if a boat is in rimessaggio or not.
 */
public RequestStaffManageBoat(int idBoat,int lunghezza,int typeReq,String storage) {
	this.idBoat=idBoat;
	this.length=lunghezza;
	this.typeReq=typeReq;
	this.storage=storage;
}
/**
 * Class constructor
 * @param req an integer value that distinguish which request client has sent to the server.
 */
public RequestStaffManageBoat(int req) {
	this.typeReq=req;
}
/**
 * @return
 *  It returns the id of a boat
 */
public int getIdBoat() {
	return idBoat;
}
/**
 * @return
 *  It returns the length of a boat
 */
public int getLength() {
	return length;
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
 *  It returns a state of the boat
 */
public String getStorage() {
	return storage;
}

}
