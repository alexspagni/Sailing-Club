package requestStaff;

import java.io.Serializable;
import java.sql.Date;
/**
 * The {@code RequestStaffFeeNotification} class is a Request class and it used to send a request from the client to the server.
 * This Request class is used to send this kind of request:
 * 1)Send a notification to a user who has to pay a membership fee.<br>
 * 2)Send a notification to a user who has to pay a storage fee.
 * @author Spagni Alex Zuelli Arianna
 * 
 */
public class RequestStaffFeeNotification implements Serializable{

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
	 * @param price price of the fee to pay.
	 * @param idmember id that identify univocally a member.
	 * @param date date of the notification.
	 */
	public RequestStaffFeeNotification(int typeRequest,int price,int idmember,Date date) {
		this.typeReq=typeRequest;
		this.price=price;
		this.date=date;
		this.idmember=idmember;
		this.paied="no";
	}
	/**
	 * Class constructor
	 * @param typeRequest an integer value that distinguish which request client has sent to the server.
	 * @param price price of the fee to pay.
	 * @param idmember id that identify univocally a member.
	 * @param date date of the notification.
	 * @param idBoat id that identify univocally a boat.
	 */
	public RequestStaffFeeNotification(int typeRequest,int price,int idmember,Date date,int idBoat) {
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
	 * It returns the date of a fee
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @return
	 * It returns the price of a fee
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * @return
	 * It returns a string that say if a fee has been paied or not
	 */
	public String getPaied() {
		return paied;
	}

	/**
	 * @return
	 * It returns the id of a boat
	 */
	public int getIdImbarcazione() {
		return idBoat;
	}



}
