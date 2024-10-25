package requestStaff;

import java.io.Serializable;
/**
 * The {@code RequestStaffManagemember} class is a Request class and it used to send a request from the client to the server.
 * This Request class is used to send this kind of request:
 * 1)Get all Members from the database.<br>
 * 2)Send new credential about a Member to the server.<br>
 * @author Spagni Alex Zuelli Arianna
 * 
 */

public class RequestStaffManageMember implements Serializable{

	private static final long serialVersionUID = 1L;
	private int typeReq;
	private String fiscalCode;
	private String address;
	private int idmember;
	/**
	 * Class constructor
	 * @param typeReq an integer value that distinguish which request client has sent to the server.
	 * @param fiscalCode new fiscal code.
	 * @param address new addres of a member.
	 * @param idmember id that identify unically a member.
	 */
	public RequestStaffManageMember(int typeReq,String fiscalCode,String address,int idmember) {
		this.typeReq=typeReq;
		this.fiscalCode=fiscalCode;
		this.address=address;
		this.idmember=idmember;
	}
	/**
	 * Class constructor
	 * @param typeReq an integer value that distinguish which request client has sent to the server.
	 */
	public RequestStaffManageMember(int typeReq) {
		this.typeReq=typeReq;
	}
	/**
	 * Class constructor
	 * @param typeReq an integer value that distinguish which request client has sent to the server.
	 * @param idmember id that identify unically a member.
	 */
	public RequestStaffManageMember(int typeReq,int idmember) {
		this.typeReq=typeReq;
		this.idmember=idmember;
	}
	/**
	 * 
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
	 *  It returns the fiscal code of a member
	 */
	public String getFiscalCode() {
		return fiscalCode;
	}
	/**
	 * @return
	 *  It returns the address of a member
	 */
	public String getAddress() {
		return address;
	}
}
