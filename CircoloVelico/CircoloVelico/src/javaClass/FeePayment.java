package javaClass;

import java.io.Serializable;
import java.sql.Date;

/**
 * The {@code FeePayment} class is used to store information about Fees.
 * 
 * @author Spagni Alex Zuelli Arianna
 * 
 */
public class FeePayment implements Serializable {

	private static final long serialVersionUID = 1L;
	private int idFee;
	private int idmember;
	private int price;
	private Date date;
	private String paied;
	private String paymentType;

	/**
	 * Class constructor
	 * 
	 * @param idFee    id of a fee.
	 * @param idmember id of a member.
	 * @param price    price of a fee.
	 * @param date     date of a fee.
	 */
	public FeePayment(int idFee, int idmember, int price, Date date) {
		this.idFee = idFee;
		this.idmember = idmember;
		this.price = price;
		this.date = date;
	}

	/**
	 * Class constructor
	 * 
	 * @param idFee    id of a fee.
	 * @param idmember id of a member.
	 * @param price    price of a fee.
	 * @param date     date of a fee.
	 * @param paied    string that denotes if a payement has been done or not.
	 */
	public FeePayment(int idFee, int idmember, int price, Date date, String paied) {
		this.idFee = idFee;
		this.idmember = idmember;
		this.price = price;
		this.date = date;
		this.paied = paied;
	}

	/**
	 * Class constructor
	 * 
	 * @param idFee       id of a fee.
	 * @param price       price of a fee.
	 * @param date        date of a fee.
	 * @param paied       string that say if a fee has been paied or not.
	 * @param idmember    id of a member.
	 * @param paymentType string that show a payement method of a fee.
	 */
	public FeePayment(int idFee, Date date, int price, String paied, int idmember, String paymentType) {
		this.idFee = idFee;
		this.date = date;
		this.price = price;
		this.paied = paied;
		this.idmember = idmember;
		this.paymentType = paymentType;
	}

	/**
	 * Class constructor
	 */
	public FeePayment() {

	}

	/**
	 * @return It return am id of a fee.
	 */
	public int getIdFee() {
		return idFee;
	}

	/**
	 * @return It return an id of a member.
	 */
	public int getIdmember() {
		return idmember;
	}

	/**
	 * @return It return the price of a fee.
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * @return It return the date of a fee.
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @return It return a fee status.
	 */
	public String getPaied() {
		return paied;
	}

	/**
	 * @return It return a payment method.
	 */
	public String getPaymentType() {
		return paymentType;
	}

}
