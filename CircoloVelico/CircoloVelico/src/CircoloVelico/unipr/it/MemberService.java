package CircoloVelico.unipr.it;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import javaClass.FeePayment;
import javaClass.Boat;
import javaClass.Notification;
import javaClass.Race;
import requestMembers.RequestBoatMember;
import requestMembers.RequestNotificationMember;
import requestMembers.RequestPaymentFeeMember;
import requestMembers.RequestRaceMember;
import requestMembers.RequestSignMember;
import responseMembers.ResponseBoatMember;
import responseMembers.ResponseNotificationMember;
import responseMembers.ResponsePaymentFeeMember;
import responseMembers.ResponseRaceMember;
import responseMembers.ResponseSignMember;

/**
 * The {@code MemberService} class is used to send to the server request objects.
 * Its methods receive all data which must be send to the server from different
 * form that user complete.
 * 
 * @author Spagni Alex Zuelli Arianna
 * 
 */
public class MemberService {
	private static final int SPORT = 4444;
	private static final String SHOST = "localhost";

	/**
	 * Class constructor
	 */
	public MemberService() {

	}

	/**
	 * This function is used from the {@code MemberSignInController} to SignUp a
	 * user. All the information that user enter into the form will be sent to the
	 * server into a request object. Request object that is used is
	 * {@code RequestSignMember}.
	 * 
	 * @param username is the username entered by the user.
	 * @param password is the passeword entered by the user.
	 * @param reqvalue this param is used to distinguish which request client is
	 *                 making to the server.
	 * @return It returns a response object that contain the answer send by the
	 *         server.
	 */
	public ResponseSignMember signUp(String username, String password, int reqvalue) {
		try {
			Socket client = new Socket(SHOST, SPORT);
			ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());
			ObjectInputStream is = null;
			while (true) {
				RequestSignMember rq = new RequestSignMember(username, password, reqvalue);

				System.out.format("Client sends:" + rq.getUsername() + " " + rq.getPassword() + " " + rq.getReq());
				os.writeObject(rq);
				os.flush();
				if (is == null) {
					is = new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
				}

				Object o = is.readObject();

				ResponseSignMember rs = (ResponseSignMember) o;
				System.out.println("Client receive :" + rs.getUsername() + " " + rs.getPassword() + " " + rs.getReq()
						+ " " + rs.getId());
				is.close();
				os.close();
				client.close();
				return rs;

			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			ResponseSignMember rs = new ResponseSignMember(username, password, 2, -1);
			return rs;

		}
	}

	/**
	 * * This function is used from the {@code MemberSignInController} to SignIn a
	 * user. All the information that user enter into the form will be sent to the
	 * server into a request object. Request object that is used is
	 * {@code RequestSignMember}.
	 * 
	 * @param username   is the username entered by the user.
	 * @param password   is the passeword entered by the user.
	 * @param reqvalue   this param is used to distinguish which request client is
	 *                   making to the server.
	 * @param address    is the address entered by the user.
	 * @param fiscalCode is the fiscal code entered by the user.
	 * @return It returns a response object that contain the answer send by the
	 *         server.
	 */
	public ResponseSignMember signIn(String username, String password, int reqvalue, String address,
			String fiscalCode) {
		try {
			Socket client = new Socket(SHOST, SPORT);
			ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());
			ObjectInputStream is = null;
			while (true) {
				RequestSignMember rq = new RequestSignMember(username, password, reqvalue, fiscalCode, address);

				System.out
						.format("Client sends:" + rq.getUsername() + " " + rq.getPassword() + " " + rq.getReq() + "%n");

				os.writeObject(rq);
				os.flush();
				if (is == null) {
					is = new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
				}

				Object o = is.readObject();

				ResponseSignMember rs = (ResponseSignMember) o;
				System.out.format("Client receive :" + rs.getUsername() + " " + rs.getPassword() + " " + rs.getReq()
						+ " " + rs.getId() + "\n");
				client.close();
				is.close();
				os.close();
				return rs;

			}

		} catch (IOException | ClassNotFoundException e) {
			ResponseSignMember rs = new ResponseSignMember(username, password, 2, -1);
			e.printStackTrace();
			return rs;
		}
	}

	/**
	 * This function is used from the {@code MemberAddABoatController} to store send boat
	 * information to the server. all the information that the user enter into the
	 * form will be sent to the server into a request object. Request object that is
	 * used is {@code RequestBoatMember}.
	 * 
	 * @param member     it's an object that contain user id.
	 * @param boatName   name of the boat .
	 * @param boatLength length of the boat.
	 * @param typeReq    an integer value that is used to distinguish user request.
	 * @return It returns an integer value that is the answer send by the server.
	 */
	public int addABoatmember(Member member, String boatName, int boatLength, int typeReq) {
		try {
			Socket client = new Socket(SHOST, SPORT);
			ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());
			ObjectInputStream is = null;
			while (true) {
				RequestBoatMember rq = new RequestBoatMember(boatName, boatLength, typeReq, member.getId());
				System.out.println("Client sends:" + rq.getBoatName() + " " + rq.getBoatLenght() + " "
						+ rq.getIdCircolo() + " " + rq.getTypeRequest() + " " + rq.getIdmember());

				os.writeObject(rq);
				os.flush();
				if (is == null) {
					is = new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
				}

				Object o = is.readObject();

				ResponseBoatMember rs = (ResponseBoatMember) o;
				System.out.println("client receive:" + rs.getResp());
				is.close();
				os.close();
				client.close();
				return rs.getResp();

			}

		} catch (IOException | ClassNotFoundException e) {

			e.printStackTrace();
			return -1;
		}
	}

	/**
	 * This function is used from the {@code MemberRemoveBoatController} to send a request
	 * to the sever, in order to get access to all boat related to a user.User id
	 * that identifies univocaly a boat is sent trough a request object. Request
	 * object that is used is {@code RequestBoatMember}.
	 * 
	 * @param id      user id.
	 * @param typeReq an integer value that is used to distinguish user request.
	 * @return It return an array list of Boat Object.
	 */
	public ArrayList<Boat> getBoat(int id, int typeReq) {
		try {
			Socket client = new Socket(SHOST, SPORT);
			ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());
			ObjectInputStream is = null;
			while (true) {
				RequestBoatMember rq = new RequestBoatMember(id, typeReq);
				System.out.println("Client sends:" + rq.getTypeRequest() + " " + rq.getIdmember());

				os.writeObject(rq);
				os.flush();
				if (is == null) {
					is = new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
				}

				Object o = is.readObject();

				ResponseBoatMember rs = (ResponseBoatMember) o;
				System.out.println("client receive:" + rs.getResp());
				is.close();
				os.close();
				client.close();
				return rs.getBoatArrayList();

			}

		}

		catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			ArrayList<Boat> boatArrayList = null;
			return boatArrayList;
		}
	}

	/**
	 * This function is used from the {@code MemberRemoveBoatController} to send a request
	 * to the sever, in order to remove to a boat.Boat id that identify univocaly a
	 * boat is sent trough a request object. Request object that is used is
	 * {@code RequestBoatMember}.
	 * 
	 * @param idBoat  boat id.
	 * @param typeReq an integer value that is used to distinguish user request.
	 * @return It returns an integer value that is the answer send by the server.
	 */
	public int removeABoat(int idBoat, int typeReq) {
		try {
			Socket client = new Socket(SHOST, SPORT);
			ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());
			ObjectInputStream is = null;

			while (true) {
				RequestBoatMember rq = new RequestBoatMember(idBoat, typeReq);
				System.out.println("Client sends:" + rq.getTypeRequest() + " " + rq.getIdCircolo());

				os.writeObject(rq);
				os.flush();
				if (is == null) {
					is = new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
				}

				Object o = is.readObject();

				ResponseBoatMember rs = (ResponseBoatMember) o;
				System.out.println("client receive:" + rs.getResp());
				is.close();
				os.close();
				client.close();
				return rs.getResp();

			}

		}

		catch (IOException | ClassNotFoundException e) {

			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * This function is used from the {@code MemberNotificationController} to send a
	 * request to the sever, in order get access to all notification related to a
	 * user.User id that identify univocaly a user is sent trough a request object.
	 * Request object that is used is {@code RequestNotificationMember}.
	 * 
	 * @param idMember member id.
	 * @param typeReq  an integer value that is used to distinguish user request.
	 * @return It return an array list of Notification object.
	 */
	public ArrayList<Notification> getNotifications(int idMember, int typeReq) {
		try {
			Socket client = new Socket(SHOST, SPORT);
			ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());
			ObjectInputStream is = null;
			while (true) {
				RequestNotificationMember rq = new RequestNotificationMember(idMember, typeReq);
				System.out.println("Client sends:" + rq.getTypeReq() + " " + rq.getId());

				os.writeObject(rq);
				os.flush();
				if (is == null) {
					is = new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
				}

				Object o = is.readObject();

				ResponseNotificationMember rs = (ResponseNotificationMember) o;
				System.out.println("client receive:" + rs.getRis());
				is.close();
				os.close();
				client.close();
				return rs.getNotifications();

			}

		}

		catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			ArrayList<Notification> notificationaArrayList = null;
			return notificationaArrayList;
		}
	}

	/**
	 * This function is used from the {@code MemberNotificationController} to send a
	 * request to the sever, in order get access to all notification related to a
	 * user.User id that identify univocaly a user is sent trough a request object.
	 * Request object that is used is {@code RequestNotificationMember}.
	 * 
	 * @param idNotification id that identify univocally a notification.
	 * @param typeReq an integer value that is used to distinguish user request.
	 * @return
	 * It returns an integer value that is the answer send by the server.
	 */
	public int removeNotification(int idNotification, int typeReq) {
		try {
			Socket client = new Socket(SHOST, SPORT);
			ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());
			ObjectInputStream is = null;
			while (true) {
				RequestNotificationMember rq = new RequestNotificationMember(idNotification, typeReq);
				System.out.println("Client sends:" + rq.getTypeReq() + " " + rq.getId());

				os.writeObject(rq);
				os.flush();
				if (is == null) {
					is = new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
				}

				Object o = is.readObject();

				ResponseNotificationMember rs = (ResponseNotificationMember) o;
				System.out.println("client receive:" + rs.getRis());
				is.close();
				os.close();
				client.close();
				return rs.getRis();

			}

		}

		catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			return -1;
		}
	}

	/**
	 * This function is used from the {@code MemberPayMembershipFeeController} to
	 * send a request to the sever, in order get access to all Membership Fee
	 * related to a user.User id that identify univocaly a user is sent trough a
	 * request object. Request object that is used is
	 * {@code RequestPaymentFeeMember}.
	 * 
	 * @param idMember Member id that identify him univocally.
	 * @param typeReq an integer value that is used to distinguish user request.
	 * @return It returns an array list of FeePayment object.
	 */
	public ArrayList<FeePayment> getMembershipFee(int idMember, int typeReq) {
		try {
			Socket client = new Socket(SHOST, SPORT);
			ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());
			ObjectInputStream is = null;

			RequestPaymentFeeMember rq = new RequestPaymentFeeMember(idMember, typeReq);
			System.out.println("Client sends:" + rq.getTypeReq() + " " + rq.getIdmember());

			os.writeObject(rq);
			os.flush();
			if (is == null) {
				is = new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
			}

			Object o = is.readObject();

			ResponsePaymentFeeMember rs = (ResponsePaymentFeeMember) o;
			System.out.println("client receive:" + rs.getRis());
			is.close();
			os.close();
			client.close();
			return rs.getPayment();
		}

		catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			ArrayList<FeePayment> paymentArrayList = null;
			return paymentArrayList;
		}
	}

	/**
	 * This function is used from the {@code MemberPayStorageFeeController} to send a
	 * request to the sever, in order get access to all Storage Fee related to a
	 * user.User id that identify univocaly a user is sent trough a request object.
	 * Request object that is used is {@code RequestPaymentFeeMember}.
	 * 
	 * @param idMember Member id
	 * @param typeReq an integer value that is used to distinguish user request.
	 * @return It returns an array list of FeePayment object.
	 */
	public ArrayList<FeePayment> getStorageFee(int idMember, int typeReq) {
		try {
			Socket client = new Socket(SHOST, SPORT);
			ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());
			ObjectInputStream is = null;

			RequestPaymentFeeMember rq = new RequestPaymentFeeMember(idMember, typeReq);
			System.out.println("Client sends:" + rq.getTypeReq() + " " + rq.getIdmember());

			os.writeObject(rq);
			os.flush();
			if (is == null) {
				is = new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
			}

			Object o = is.readObject();

			ResponsePaymentFeeMember rs = (ResponsePaymentFeeMember) o;
			System.out.println("client receive:" + rs.getRis());
			is.close();
			os.close();
			client.close();
			return rs.getPayment();
		}

		catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			ArrayList<FeePayment> paymentArrayList = null;
			return paymentArrayList;
		}
	}

	/**
	 * This function is used from the {@code MemberPayMembershipFeeController} to
	 * send a request to the sever, in order to pay a Membership Fee related to a
	 * user that send the request.Fee id that identify univocaly a fee is sent
	 * trough a request object. Request object that is used is
	 * {@code RequestPaymentFeeMember}
	 * 
	 * @param idFee       id of a fee that identify it automatically.
	 * @param typeReq     an integer value that is used to distinguish user request.
	 * @param paymentType type of payment that user decided to use
	 * @return It returns an integer value that is the answer sent by the server.
	 */
	public int payMembershipFee(int idFee, int typeReq, int paymentType) {
		try {
			Socket client = new Socket(SHOST, SPORT);
			ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());
			ObjectInputStream is = null;
			while (true) {
				RequestPaymentFeeMember rq = new RequestPaymentFeeMember(idFee, typeReq, paymentType);
				System.out.println("Client sends:" + rq.getTypeReq() + " " + rq.getIdFee() + " " + paymentType);

				os.writeObject(rq);
				os.flush();
				if (is == null) {
					is = new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
				}

				Object o = is.readObject();

				ResponsePaymentFeeMember rs = (ResponsePaymentFeeMember) o;
				System.out.println("client receive:" + rs.getRis());
				is.close();
				os.close();
				client.close();
				return rs.getRis();

			}

		}

		catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();

			return -1;
		}
	}

	/**
	 * This function is used from the {@code MemberJoinRaceController} to send a
	 * request to the sever, in order to pay a Race Fee related to a user that send
	 * the request.id Member that identify univocaly a user is sent trough a request
	 * object. Request object that is used is {@code RequestPaymentFeeMember}.
	 * 
	 * @param price       price of the fee.
	 * @param idMember     Member id.
	 * @param typeReq     an integer value that is used to distinguish user request.
	 * @param paymentType payment type that user wanted to use.
	 * @return It returns an integer value that is the answer sent by the server.
	 */
	public int payRaceFee(int price, int idMember, int typeReq, int paymentType) {
		try {
			Socket client = new Socket(SHOST, SPORT);
			ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());
			ObjectInputStream is = null;
			while (true) {
				RequestPaymentFeeMember rq = new RequestPaymentFeeMember(price, idMember, typeReq, paymentType);
				System.out.println("Client sends:" + rq.getTypeReq() + " " + rq.getPrice() + " " + paymentType + " "
						+ rq.getIdmember());

				os.writeObject(rq);
				os.flush();
				if (is == null) {
					is = new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
				}

				Object o = is.readObject();

				ResponsePaymentFeeMember rs = (ResponsePaymentFeeMember) o;
				System.out.println("client receive:" + rs.getRis());
				is.close();
				os.close();
				client.close();
				return rs.getRis();

			}

		}

		catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();

			return -1;
		}
	}

	/**
	 * This function is used from the
	 * {@code memberJoinRaceController},{@code MemberSeeRace} and
	 * {@code memberSeeRaceWon} to send a request to the sever, in order to get
	 * access to different kind of race.Id member that identify univocaly a user is
	 * sent trough a request object. Request object that is used is
	 * {@code RequestRaceMember}.
	 * 
	 * @param idMember Member id.
	 * @param typeReq an integer value that is used to distinguish user request.
	 * @return It returns an array list of Race objects.
	 */
	public ArrayList<Race> getRace(int idMember, int typeReq) {
		try {
			Socket client = new Socket(SHOST, SPORT);
			ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());
			ObjectInputStream is = null;

			RequestRaceMember rq = new RequestRaceMember(typeReq, idMember);
			System.out.println("Client sends:" + rq.getTypeReq() + " " + rq.getIdmember());

			os.writeObject(rq);
			os.flush();
			if (is == null) {
				is = new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
			}

			Object o = is.readObject();

			ResponseRaceMember rs = (ResponseRaceMember) o;
			System.out.println("client receive:" + rs.getRis());
			is.close();
			os.close();
			client.close();
			return rs.getRaces();
		}

		catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			ArrayList<Race> race = null;
			return race;
		}
	}

	/**
	 * This function is used from the {@code MemberJoinRaceController} to send a
	 * request to the sever, in order to join a race and connect a user boat to a
	 * specific race.Id Member that identify univocaly a user, idImbarcazione that
	 * identify Member a boat and idGara that identify univocally a race are sent
	 * trough a request object. Request object that is used is {@code RequestRaceMember}.
	 * 
	 * @param idMember        Member id.
	 * @param idRace         id of a specific race
	 * @param idBoat id of a user boat.
	 * @param typeReq        an integer value that is used to distinguish user
	 *                       request.
	 * @return It returns an integer value that is the answer sent by the server.
	 */
	public int joinARace(int idMember, int idRace, int idBoat, int typeReq) {
		try {
			Socket client = new Socket(SHOST, SPORT);
			ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());
			ObjectInputStream is = null;

			RequestRaceMember rq = new RequestRaceMember(typeReq, idMember, idRace, idBoat);
			System.out.println(
					"Client sends:" + rq.getTypeReq() + " " + rq.getIdmember() + " " + idRace + " " + idBoat);

			os.writeObject(rq);
			os.flush();
			if (is == null) {
				is = new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
			}

			Object o = is.readObject();

			ResponseRaceMember rs = (ResponseRaceMember) o;
			System.out.println("client receive:" + rs.getRis());
			is.close();
			os.close();
			client.close();
			return rs.getRis();
		}

		catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();

			return -1;
		}
	}
}
