package requestStaff;

import java.io.Serializable;
import java.sql.Date;
/**
 * The {@code RequestStaffAddPayments} class is a Request class and it used to send a request from the client to the server.
 * This Request class is used to send this kind of request:
 * 1)Add a Membership fee to the database<br>
 * 2)Add a Storage fee to the database<br>
 * @author Spagni Alex Zuelli Arianna
 * 
 */
public class RequestStaffAddPayments implements Serializable{
	private static final long serialVersionUID = 1L;
	private int typeReq;
	private int idmember;
	private Date date;
	private int price;
	private String paied;
	private int idBoat;
	/**
	 * Class constructor
	 * @param typeRequest an integer value that distinguish which request client has sent to the server.
	 */
	public RequestStaffAddPayments(int typeRequest) {
		this.typeReq=typeRequest;
	}
	/**
	 * Class constructor
	 * @param typeRequest an integer value that distinguish which request client has sent to the server.
	 * @param price price of the fee to add.
	 * @param idmember id of the member who's must pay the fee.
	 * @param date local date of the fee.
	 */
	public RequestStaffAddPayments(int typeRequest,int price,int idmember,Date date) {
		this.typeReq=typeRequest;
		this.price=price;
		this.date=date;
		this.idmember=idmember;
		this.paied="no";
	}
	/**
	 * Class constructor
	 * @param typeRequest an integer value that distinguish which request client has sent to the server.
	 * @param price price of the fee to add.
	 * @param idmember id of the member who's must pay the fee.
	 * @param date local date of the fee.
	 * @param idBoat id of the boat which user must pay a storage fee.
	 */
	public RequestStaffAddPayments(int typeRequest,int price,int idmember,Date date,int idBoat) {
		this.typeReq=typeRequest;
		this.price=price;
		this.date=date;
		this.idmember=idmember;
		this.paied="no";
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
	 *  It returns the date of a payment
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * @return
	 *  It returns the price of a payment
	 */
	public int getPrice() {
		return price;
	}
	/**
	 * @return
	 *  It returns a string that show if a fee has been paied or not
	 */
	public String getPaied() {
		return paied;
	}
	/**
	 * @return
	 *  It returns the id of a boat
	 */
	public int getIdBoat() {
		return idBoat;
	}

}
