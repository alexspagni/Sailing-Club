package requestStaff;

import java.io.Serializable;
/**
 * The {@code RequestStaffPayments} class is a Request class and it used to send a request from the client to the server.
 * This Request class is used to send this kind of request:
 * 1)Get all Membership payment from the database.<br>
 * 2)Get all Storage payment from the database.<br>
 * 3)Get all Race payment from the database.
 * @author Spagni Alex Zuelli Arianna
 * 
 */
public class RequestStaffPayments implements Serializable{

	private static final long serialVersionUID = 1L;
	private int idmember;
	private int typeReq;
	/**
	 * Class constructor
	 * @param idmember id that identify univocally a member.
	 * @param typeReq an integer value that distinguish which request client has sent to the server.
	 */
	public RequestStaffPayments(int idmember,int typeReq) {
		this.idmember=idmember;
		this.typeReq=typeReq;
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
	 *  It returns an integer value
	 */
	public int getTypeReq() {
		return typeReq;
	}

	
}
