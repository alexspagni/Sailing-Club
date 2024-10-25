package javaClass;

import java.io.Serializable;
import java.sql.Date;
/**
 * The {@code Boat} class is used to store information about notifications.
 * 
 * @author Spagni Alex Zuelli Arianna
 * 
 */
public class Notification implements Serializable{
/**
	 * 
	 */
private static final long serialVersionUID = 1L;
private int idNotification;
private String typeOfFee;
private int price;
private int idmember;
private Date date;
private int idBoat;
private String boatName;
/**
 * Class constructor
 * @param typeOfFee type of a fee
 * @param price price of a fee
 * @param idmember id of a member
 * @param date date of a fee
 */
public Notification(String typeOfFee,int price,int idmember,Date date) {
	this.typeOfFee=typeOfFee;
	this.price=price;
	this.idmember=idmember;
	this.date=date;
}
/**
 * Class constructor
 * @param idNotification id of a notification.
 * @param typeOfFee type of a fee.
 * @param price price of a fee.
 * @param idmember id of a member.
 * @param date date of a fee.
 */
public Notification(int idNotification, String typeOfFee,int price,int idmember,Date date) {
	this.idNotification=idNotification;
	this.typeOfFee=typeOfFee;
	this.price=price;
	this.idmember=idmember;
	this.date=date;
}
/**
 * Class constructor
 * @param price price of a fee.
 * @param idmember id of a member.
 * @param date date of a fee.
 * @param idBoat id of a boat.
 */
public Notification(int price,int idmember,Date date,int idBoat) {
	this.idBoat=idBoat;
	this.price=price;
	this.idmember=idmember;
	this.date=date;
}
/**
 * Class constructor
 * @param price price of a fee.
 * @param idmember id of a member.
 * @param date date of a fee.
 * @param boatName name of a boat.
 * @param idBoat id of a boat.
 */
public Notification(int price,int idmember,Date date,String boatName,int idBoat) {
	this.boatName=boatName;
	this.price=price;
	this.idmember=idmember;
	this.date=date;
	this.idBoat=idBoat;
}
/**
 * Class constructor
 * @param price price of a fee.
 * @param idmember id of a member.
 * @param date date of a fee.
 */
public Notification(int price,int idmember,Date date) {
	this.price=price;
	this.idmember=idmember;
	this.date=date;
}
/**
 * Class constructor
 */
public Notification() {
	
}
/**
 * @return
 * It returns the type of a fee
 */
public String getTypeOfFee() {
	return typeOfFee;
}

/**
 * @return
 *  * It returns the price of a fee
 */
public int getPrice() {
	return price;
}

/**
 * @return
 *  * It returns the id of a member
 */
public int getIdmember() {
	return idmember;
}


/**
 * @return
 *  * It returns the year of a fee
 */
public Date getDate() {
	return date;
}

/**
 * @return
 *  * It returns the id of a boat
 */
public int getIdBoat() {
	return idBoat;
}
/**
 * @return
 *  * It returns the id of a notification
 */
public int getIdNotification() {
	return idNotification;
}
/**
 * @return
 * It returns the id of a name of a boat
 */
public String getBoatName() {
	return boatName;
}


}
