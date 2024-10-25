package requestMembers;

import java.io.Serializable;
/**
 * The {@code RequestNotificationMember} class is a Request class and it used to send a request from the client to the server.
 * This Request class is used to send this kind of request:
 * 1)Drawn all Notification from the database based on the id of a Member.<br>
 * @author Spagni Alex Zuelli Arianna
 * 
 */
public class RequestNotificationMember implements Serializable{

private static final long serialVersionUID = 1L;
private int typeReq;
private int id;
/**
 * Constructore class.
 * @param id id of the Member we are going to drawn notification
 * @param typeRequest an integer value that distinguish which request client has sent to the server.
 */
public RequestNotificationMember(int id,int typeRequest) {
	this.typeReq=typeRequest;
	this.id=id;
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
 *  It returns an id of a notification or a Member
 */
public int getId() {
	return id;
}


}
