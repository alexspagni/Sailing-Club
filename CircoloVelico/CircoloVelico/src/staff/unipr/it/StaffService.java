package staff.unipr.it;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Date;
import java.util.ArrayList;
import CircoloVelico.unipr.it.Member;
import javaClass.FeePayment;
import javaClass.Boat;
import javaClass.Notification;
import javaClass.Race;
import requestStaff.RequestStaffManageBoat;
import requestStaff.RequestStaffManageMember;
import requestStaff.RequestStaffFeeNotification;
import requestStaff.RequestStaffAddPayments;
import requestStaff.RequestStaffPayments;
import requestStaff.RequestStaffRace;
import requestStaff.RequestStaffSignUp;
import responseStaff.ResponseStaffSignUp;
import responseStaff.ResponseStaffFeeNotification;
import responseStaff.ResponseStaffManageBoat;
import responseStaff.ResponseStaffManageMember;
import responseStaff.ResponseStaffAddPayments;
import responseStaff.ResponseStaffPayments;
import responseStaff.ResponseStaffRace;

/**
 * The {@code StaffService} class contains all request that a staff member
 * could do to the server. It is used to send to the server request objects. Its
 * methods receive all data which must be send to the server from different form
 * that a staff member complete.
 * 
 * @author Spagni Alex Zuelli Arianna
 * 
 */
public class StaffService {
	private static final int SPORT = 4444;
	private static final String SHOST = "localhost";

	/**
	 * Class constructor
	 */
	public StaffService() {

	}

	/**
	 * This function is used from the {@code StaffSignInController} to SignIn a
	 * user. All the information that a staff member enter into the form will be
	 * sent to the server into a request object. Request object that is used is
	 * {@code RequestStaffSignUp}.
	 * 
	 * @param username is the username entered by the user.
	 * @param password is the passeword entered by the user.
	 * @param reqvalue this param is used to distinguish which request client is
	 *                 making to the server.
	 * @return It returns an integer value that is the answer send by the server.
	 */
	public int signUp(String username, String password, int reqvalue) {
		try {
			Socket client = new Socket(SHOST, SPORT);
			ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());
			ObjectInputStream is = null;
			while (true) {
				RequestStaffSignUp rq = new RequestStaffSignUp(username, password, reqvalue);

				System.out.format("Client sends:" + rq.getUsername() + " " + rq.getPassword() + " " + rq.getTypeReq());
				os.writeObject(rq);
				os.flush();
				if (is == null) {
					is = new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
				}

				Object o = is.readObject();

				ResponseStaffSignUp rs = (ResponseStaffSignUp) o;
				System.out.println("Client receive :" + rs.getUsername() + " " + rs.getPassword() + " " + rs.getReq()
						+ " " + rs.getId());
				is.close();
				os.close();
				client.close();
				return rs.getId();

			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();

			return -1;

		}
	}

	/**
	 * This function is used from the
	 * {@code StaffSeeMembershipPayment},{@code StaffSeeRacePayment} and
	 * {@code staffSeeStoragePayment} in order to get get access to all payment
	 * made by users with credit card or by bonifico.This kind of request is sent to
	 * the server with a request object. The request object that is used is
	 * {@code RequestStaffPayments}.
	 * 
	 * @param idmember id that identify univocally a member
	 * @param typeReq this param is used to distinguish which request client is
	 *                making to the server.
	 * @return it returns an array list of FeePayment object.
	 */
	public ArrayList<FeePayment> getPayments(int idmember, int typeReq) {
		try {
			Socket client = new Socket(SHOST, SPORT);
			ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());
			ObjectInputStream is = null;
			while (true) {
				RequestStaffPayments rq = new RequestStaffPayments(idmember, typeReq);
				System.out.println("Client sends:" + rq.getTypeReq() + " " + rq.getIdmember());

				os.writeObject(rq);
				os.flush();
				if (is == null) {
					is = new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
				}

				Object o = is.readObject();

				ResponseStaffPayments rs = (ResponseStaffPayments) o;
				System.out.println("client receive:" + rs.getRis());
				is.close();
				os.close();
				client.close();
				return rs.getPayment();

			}

		}

		catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			ArrayList<FeePayment> paymentsArrayList = null;
			return paymentsArrayList;
		}
	}

	/**
	 * This function is used from the
	 * {@code StaffAddMembershipFeeController},{@code StaffAddStorageFeeController},{@code StaffSendMembershipNotification}
	 * and {@code StaffSendStorageNotification} in order to get get access to all
	 * Membership/Storage fee that hasn't stored in the database yet. This kind of
	 * request is sent to the server with a request object. The request object that
	 * is used is {@code RequestStaffAddPayments}.
	 * 
	 * @param typeReq this param is used to distinguish which request client is
	 *                making to the server.
	 * @return it returns an array list of Notification object.
	 */
	public ArrayList<Notification> getUserMustPayFee(int typeReq) {
		try {
			Socket client = new Socket(SHOST, SPORT);
			ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());
			ObjectInputStream is = null;
			while (true) {
				RequestStaffAddPayments rq = new RequestStaffAddPayments(typeReq);
				System.out.println("Client sends:" + rq.getTypeReq());

				os.writeObject(rq);
				os.flush();
				if (is == null) {
					is = new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
				}

				Object o = is.readObject();

				ResponseStaffAddPayments rs = (ResponseStaffAddPayments) o;
				System.out.println("client receive:" + rs.getRis());
				is.close();
				os.close();
				client.close();
				return rs.getNotifications();

			}

		}

		catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			ArrayList<Notification> notificationsArrayList = null;
			return notificationsArrayList;
		}
	}

	/**
	 * This function is used from the {@code StaffAddMembershipFeeController} to
	 * add a new membership fee to the database, so a member will pay it. This kind
	 * of request is sent to the server with a request object. The request object
	 * that is used is {@code RequestStaffAddPayments}.
	 * 
	 * @param typeReq this param is used to distinguish which request client is
	 *                making to the server.
	 * @param idmember id that identify univocally a member.
	 * @param data    local date.
	 * @param price   price of the fee.
	 * @param paied   string that say if a fee has been paied or not.
	 * @return It returns an integer value that is the answer send by the server.
	 */
	public int addUserMustPayMembershipFee( int typeReq, int idmember, Date data, int price,
			String paied) {
		try {
			Socket client = new Socket(SHOST, SPORT); 
			ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());
			ObjectInputStream is = null;
			while (true) {

				RequestStaffAddPayments rq = new RequestStaffAddPayments(typeReq, price, idmember, data);
				System.out.println("Client sends:" + rq.getTypeReq());

				os.writeObject(rq);
				os.flush();
				if (is == null) {
					is = new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
				}

				Object o = is.readObject();

				ResponseStaffAddPayments rs = (ResponseStaffAddPayments) o;
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
	 * This function is used from the {@code StaffAddStorageFeeController} to add
	 * a new storage fee to the database, so a member will pay it. This kind of
	 * request is sent to the server with a request object. The request object that
	 * is used is {@code RequestStaffAddPayments}.
	 * 
	 * @param typeReq this param is used to distinguish which request client is
	 *                making to the server.
	 * @param idmember id that identify univocally a member.
	 * @param data    local date.
	 * @param price   price of the fee.
	 * @param paied   string that say if a fee has been paied or not.
	 * @param idBoat  id of the boat on which a member must pay a fee
	 * @return It returns an integer value that is the answer send by the server.
	 */

	public int addUserMustPayStorageFee( int typeReq, int idmember, Date data, int price, String paied,
			int idBoat) {
		try {
			Socket client = new Socket(SHOST, SPORT); 
			ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());
			ObjectInputStream is = null;
			while (true) {

				RequestStaffAddPayments rq = new RequestStaffAddPayments(typeReq, price, idmember, data, idBoat);
				System.out.println("Client sends:" + rq.getTypeReq());

				os.writeObject(rq);
				os.flush();
				if (is == null) {
					is = new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
				}

				Object o = is.readObject();

				ResponseStaffAddPayments rs = (ResponseStaffAddPayments) o;
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
	 * This function is used from the {@code StaffModifymemberCredential} to
	 * modify some credential of a user. This kind of request is sent to the server
	 * with a request object. The request object that is used is
	 * {@code RequestStaffManagemember}.
	 * 
	 * @param typeReq    this param is used to distinguish which request client is
	 *                   making to the server.
	 * @param idMember    id that identify univocally a Member.
	 * @param fiscalCode new fiscal code of the user selected.
	 * @param address    new address of the user selected.
	 * @return It returns an integer value that is the answer send by the server.
	 */

	public int manageMemberCredential( int typeReq, int idMember, String fiscalCode, String address) {
		try {
			Socket client = new Socket(SHOST, SPORT); 
			ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());
			ObjectInputStream is = null;
			while (true) {

				RequestStaffManageMember rq = new RequestStaffManageMember(typeReq, fiscalCode, address, idMember);
				System.out.println("Client sends:" + rq.getTypeReq());

				os.writeObject(rq);
				os.flush();
				if (is == null) {
					is = new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
				}

				Object o = is.readObject();

				ResponseStaffManageMember rs = (ResponseStaffManageMember) o;
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
	 * This function is used from the {@code StaffRemoveMember} to
	 * remove a member. This kind of request is sent to the server
	 * with a request object. The request object that is used is
	 * {@code RequestStaffManagemember}.
	 * 
	 * @param typeReq    this param is used to distinguish which request client is
	 *                   making to the server.
	 * @param idMember    id that identify univocally a Member.
	 * 
	 * @return It returns an integer value that is the answer send by the server.
	 */
	public int removeAMember(int typeReq, int idMember) {
		try {
			Socket client = new Socket(SHOST, SPORT); 
			ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());
			ObjectInputStream is = null;
			while (true) {

				RequestStaffManageMember rq = new RequestStaffManageMember(typeReq,idMember);
				System.out.println("Client sends:" + rq.getTypeReq());

				os.writeObject(rq);
				os.flush();
				if (is == null) {
					is = new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
				}

				Object o = is.readObject();

				ResponseStaffManageMember rs = (ResponseStaffManageMember) o;
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
	 * This function is used from the {@code StaffModifymemberCredential} to get
	 * access to all user stored into the database. This kind of request is sent to
	 * the server with a request object. The request object that is used is
	 * {@code RequestStaffManagemember}.
	 * 
	 * @param typeReq this param is used to distinguish which request client is
	 *                making to the server.
	 * @return It returns an array list of member objects.
	 */
	public ArrayList<Member> getMembers(int typeReq) {
		try {
			Socket client = new Socket(SHOST, SPORT); 
			ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());
			ObjectInputStream is = null;
			while (true) {

				RequestStaffManageMember rq = new RequestStaffManageMember(typeReq);
				System.out.println("Client sends:" + rq.getTypeReq());

				os.writeObject(rq);
				os.flush();
				if (is == null) {
					is = new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
				}

				Object o = is.readObject();

				ResponseStaffManageMember rs = (ResponseStaffManageMember) o;
				System.out.println("client receive:" + rs.getRis());
				is.close();
				os.close();
				client.close();
				return rs.getSocii();

			}

		}

		catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			ArrayList<Member> sociiArrayList = null;
			return sociiArrayList;
		}
	}

	/**
	 * This function is used from the {@code StaffModifyBoatDatal} to get access
	 * to all boat stored into the database. This kind of request is sent to the
	 * server with a request object. The request object that is used is
	 * {@code RequestStaffManageBoat}.
	 * 
	 * @param typeReq this param is used to distinguish which request client is
	 *                making to the server.
	 * @return It returns an array list of boat objects.
	 */
	public ArrayList<Boat> getBoat( int typeReq) {
		try {
			Socket client = new Socket(SHOST, SPORT); 
			ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());
			ObjectInputStream is = null;
			while (true) {

				RequestStaffManageBoat rq = new RequestStaffManageBoat(typeReq);
				System.out.println("Client sends:" + rq.getTypeReq());

				os.writeObject(rq);
				os.flush();
				if (is == null) {
					is = new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
				}

				Object o = is.readObject();

				ResponseStaffManageBoat rs = (ResponseStaffManageBoat) o;
				System.out.println("client receive:" + rs.getRis());
				is.close();
				os.close();
				client.close();
				return rs.getBoats();

			}

		}

		catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			ArrayList<Boat> boatsArrayList = null;
			return boatsArrayList;
		}
	}

	/**
	 * This function is used from the {@code StaffModifyBoatData} to modify boat
	 * lenght and boat "state". This kind of request is sent to the server with a
	 * request object. The request object that is used is
	 * {@code RequestStaffManageBoat}.
	 * 
	 * @param typeReq this param is used to distinguish which request client is
	 *                making to the server.
	 * @param idBoat  id that identify univocally a boat.
	 * @param storage string that say if a boat is into a state of storage or not.
	 * @param length  new length of the boat.
	 * @return It returns an integer value that is the answer send by the server.
	 */
	public int modifyBoatData( int typeReq, int idBoat, String storage, int length) {
		try {
			Socket client = new Socket(SHOST, SPORT); 
			ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());
			ObjectInputStream is = null;
			while (true) {
				System.out.println(idBoat + " " + length + " " + typeReq + " " + storage);
				RequestStaffManageBoat rq = new RequestStaffManageBoat(idBoat, length, typeReq, storage);
				System.out.println("Client sends:" + rq.getTypeReq());

				os.writeObject(rq);
				os.flush();
				if (is == null) {
					is = new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
				}

				Object o = is.readObject();

				ResponseStaffManageBoat rs = (ResponseStaffManageBoat) o;
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
	 * This function is used from the {@code staffAddARace} to add a new race.
	 * Race information will be stored on the database. This kind of request is sent
	 * to the server with a request object. The request object that is used is
	 * {@code RequestStaffRace}.
	 * 
	 * @param typeReq   this param is used to distinguish which request client is
	 *                  making to the server.
	 * @param raceName  name of the race that has been added
	 * @param raceDate  when the race will take place
	 * @param price     price to join the race
	 * @param racePlace where the race will take place
	 * @return It returns an integer value that is the answer send by the server.
	 */
	public int addARace( int typeReq, String raceName, Date raceDate, int price, String racePlace) {
		try {
			Socket client = new Socket(SHOST, SPORT); 
			ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());
			ObjectInputStream is = null;
			while (true) {
				System.out.println(raceName + " " + price + " " + typeReq + " " + racePlace);
				RequestStaffRace rq = new RequestStaffRace(typeReq, raceName, raceDate, price, racePlace);
				System.out.println("Client sends:" + rq.getTypeReq());

				os.writeObject(rq);
				os.flush();
				if (is == null) {
					is = new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
				}

				Object o = is.readObject();

				ResponseStaffRace rs = (ResponseStaffRace) o;
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
	 * This function is used from the {@code StaffModifyRaceController},
	 * {@code StaffSeeAllRaceController} and
	 * {@code StaffSimulateARaceController}to get access to all race stored into
	 * the database. This kind of request is sent to the server with a request
	 * object. The request object that is used is {@code RequestStaffRace}.
	 * 
	 * @param typeReq this param is used to distinguish which request client is
	 *                making to the server.
	 * @return it return an array list of Race objects.
	 */
	public ArrayList<Race> getRace( int typeReq) {
		try {
			Socket client = new Socket(SHOST, SPORT); 
			ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());
			ObjectInputStream is = null;

			RequestStaffRace rq = new RequestStaffRace(typeReq);
			System.out.println("Client sends:" + rq.getTypeReq());

			os.writeObject(rq);
			os.flush();
			if (is == null) {
				is = new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
			}

			Object o = is.readObject();

			ResponseStaffRace rs = (ResponseStaffRace) o;
			System.out.println("client receive:" + rs.getRis());
			is.close();
			os.close();
			client.close();
			return rs.getRace();
		}

		catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			ArrayList<Race> race = null;
			return race;
		}
	}

	/**
	 * This function is used from the {@code StaffModifyRaceController} to modify
	 * where and when a race will take place. This kind of request is sent to the
	 * server with a request object. The request object that is used is
	 * {@code RequestStaffManageBoat}.
	 * 
	 * @param typeReq this param is used to distinguish which request client is
	 *                making to the server.
	 * @param idGara  id that identifies univocally a race.
	 * @param place   new place where a race will take place.
	 * @param date    new date when a race will take place.
	 * @return It returns an integer value that is the answer send by the server.
	 */
	public int modifyARace( int typeReq, int idGara, String place, Date date) {
		try {
			Socket client = new Socket(SHOST, SPORT); 
			ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());
			ObjectInputStream is = null;

			RequestStaffRace rq = new RequestStaffRace(typeReq, idGara, date, place);
			System.out.println("Client sends:" + rq.getTypeReq());

			os.writeObject(rq);
			os.flush();
			if (is == null) {
				is = new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
			}

			Object o = is.readObject();

			ResponseStaffRace rs = (ResponseStaffRace) o;
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
	

	/**
	 * This function is used from the {@code StaffSimulateARaceController} to
	 * simulate a race. This kind of request is sent to the server with a request
	 * object. The request object that is used is
	 * {@code RequestStaffManageBoat}.
	 * 
	 * @param typeReq   this param is used to distinguish which request client is
	 *                  making to the server.
	 * @param idGara    id that identifies univocally a race.
	 * @param racePlace place where a race will take place.
	 * @param raceName  name of the race
	 * @return It returns an integer value that is the answer send by the server.
	 */
	public int simulateARace( int typeReq, int idGara, String racePlace, String raceName) {
		try {
			Socket client = new Socket(SHOST, SPORT); 
			ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());
			ObjectInputStream is = null;

			RequestStaffRace rq = new RequestStaffRace(typeReq, idGara, raceName, racePlace);
			System.out.println("Client sends:" + rq.getTypeReq() + " " + idGara + " " + racePlace + " " + raceName);

			os.writeObject(rq);
			os.flush();
			if (is == null) {
				is = new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
			}

			Object o = is.readObject();

			ResponseStaffRace rs = (ResponseStaffRace) o;
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

	/**
	 * This function is used from the {@code StaffSendMembershipNotification} to
	 * send a notification to a user to remember him to pay the fee This kind of
	 * request is sent to the server with a request object. The request object that
	 * is used is {@code RequestStaffFeeNotification}.
	 * @param typeReq this param is used to distinguish which request client is
	 *                making to the server.
	 * @param idMember id that identify univocally a Member.
	 * @param data    local date of the fee user must pay.
	 * @param price   price of the fee user must pay.
	 * @param paied   string that say if a fee has been paied or not.
	 * @return It returns an integer value that is the answer send by the server.
	 */
	public int sendMembershipFeeNotification( int typeReq, int idMember, Date data, int price,
			String paied) {
		try {
			Socket client = new Socket(SHOST, SPORT); 
			ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());
			ObjectInputStream is = null;
			while (true) {

				RequestStaffFeeNotification rq = new RequestStaffFeeNotification(typeReq, price, idMember, data);
				System.out.println("Client sends:" + rq.getTypeReq());

				os.writeObject(rq);
				os.flush();
				if (is == null) {
					is = new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
				}

				Object o = is.readObject();

				ResponseStaffFeeNotification rs = (ResponseStaffFeeNotification) o;
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
	 * This function is used from the {@code StaffSendMembershipNotification} to
	 * send a notification to a user to remember him to pay the fee. This kind of
	 * request is sent to the server with a request object. The request object that
	 * is used is {@code RequestStaffFeeNotification}.
	 * 
	 * @param typeReq this param is used to distinguish which request client is
	 *                making to the server.
	 * @param idMember id that identify univocally a Member.
	 * @param data    local date of the fee user must pay.
	 * @param price   price of the fee user must pay.
	 * @param paied   string that say if a fee has been paied or not.
	 * @param idBoat  id taht identify univocally a boat.
	 * @return It returns an integer value that is the answer send by the server.
	 */
	public int sendStorageFeeNotification( int typeReq, int idMember, Date data, int price, String paied,
			int idBoat) {
		try {
			Socket client = new Socket(SHOST, SPORT); 
			ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());
			ObjectInputStream is = null;
			while (true) {

				RequestStaffFeeNotification rq = new RequestStaffFeeNotification(typeReq, price, idMember, data,
						idBoat);
				System.out.println("Client sends:" + rq.getTypeReq());

				os.writeObject(rq);
				os.flush();
				if (is == null) {
					is = new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
				}

				Object o = is.readObject();

				ResponseStaffFeeNotification rs = (ResponseStaffFeeNotification) o;
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

}