package requestMembers;

import java.io.Serializable;
/**
 * The {@code RequestPaymentFeeMember} class is a Request class and it used to send a request from the client to the server.
 * This Request class is used to send this kind of request:
 * 1)Drawn all Membership fee from the database based on the id of a Member.<br>
 * 2)Drawn all Storage fee from the database based on the id of a Member.<br>
 * 3)Pay a Membership fee.
 * 4)Pay a Storage fee.
 * @author Spagni Alex Zuelli Arianna
 * 
 */
public class RequestPaymentFeeMember implements Serializable{

private static final long serialVersionUID = 1L;
private int idmember;
private int typeReq;
private int idFee;
private int paymentType;
private int price;

public RequestPaymentFeeMember(int id,int typeReq,int paymentType) {
	if (typeReq==0) {
		this.idmember=id;
	}
	else {
		this.idFee=id;
	}
	this.typeReq=typeReq;
	this.paymentType=paymentType;
}

public RequestPaymentFeeMember(int id,int typeReq) {
	if (typeReq==0 || typeReq==3) {
		this.idmember=id;
	}
	
	else {
		this.idFee=id;
	}
	this.typeReq=typeReq;
}
//per pagare le Race fee
public RequestPaymentFeeMember(int price,int idmember,int typeReq,int paymentType) {
	this.price=price;
	this.idmember=idmember;
	this.typeReq=typeReq;
	this.paymentType=paymentType;
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
 *  It returns an integer value
 */
public int getTypeReq() {
	return typeReq;
}


/**
 * @return
 *  It returns an id of a fee
 */
public int getIdFee() {
	return idFee;
}

/**
 * @return
 *  It returns a payment type
 */
public int getPaymentType() {
	return paymentType;
}

/**
 * @return
 *  It returns the price of a fee
 */
public int getPrice() {
	return price;
}


}
