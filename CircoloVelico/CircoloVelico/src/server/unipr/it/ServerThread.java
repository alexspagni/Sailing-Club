package server.unipr.it;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import java.util.ArrayList;

import CircoloVelico.unipr.it.Member;
import enumClass.BoatMemberEnum;
import enumClass.BoatStaffEnum;
import enumClass.FeeMemberEnum;
import enumClass.FeeStaffEnum;
import enumClass.MembersEnum;
import enumClass.NotificationMemberEnum;
import enumClass.NotificationStaffEnum;
import enumClass.RaceMemberEnum;
import enumClass.RaceStaffEnum;
import enumClass.StaffEnum;
//import javaClass.Constant;
import javaClass.FeePayment;
import javaClass.Boat;
import javaClass.Notification;
import javaClass.Race;
import requestMembers.RequestBoatMember;
import requestMembers.RequestNotificationMember;
import requestMembers.RequestPaymentFeeMember;
import requestMembers.RequestRaceMember;
import requestMembers.RequestSignMember;
import requestStaff.RequestStaffManageBoat;
import requestStaff.RequestStaffManageMember;
import requestStaff.RequestStaffFeeNotification;
import requestStaff.RequestStaffAddPayments;
import requestStaff.RequestStaffPayments;
import requestStaff.RequestStaffRace;
import requestStaff.RequestStaffSignUp;
import responseMembers.ResponseBoatMember;
import responseMembers.ResponseNotificationMember;
import responseMembers.ResponsePaymentFeeMember;
import responseMembers.ResponseRaceMember;
import responseMembers.ResponseSignMember;
import responseStaff.ResponseStaffSignUp;
import responseStaff.ResponseStaffFeeNotification;
import responseStaff.ResponseStaffManageBoat;
import responseStaff.ResponseStaffManageMember;
import responseStaff.ResponseStaffAddPayments;
import responseStaff.ResponseStaffPayments;
import responseStaff.ResponseStaffRace;

/**
 *
 * The class {@code ServerThread} manages the interaction with a client of the
 * server.
 *
 **/

public class ServerThread implements Runnable {
//	private static Constant constant = new Constant();
//	private final int reqSignIn = 1;// sign in del member
//	private final int reqSignUp = 0; // sign up del member
//	private final int reqAddBoat = 0;
//	private final int getBoatReq = 1;
//	private final int getBoatRaceReq = 3;
//	private final int reqRemoveABoat = 2;
//	private final int reqGetNotification = 0;
//	private final int reqGetFeeMembership = 0;
//	private final int reqGetFeeStorage = 3;
//	private final int reqPayFeeMembership = 1;
//	private final int reqPayFeeStorage = 2;
//	private final int reqPayFeeRace = 5;
//	private final int reqGetRace = 0;
//	private final int reqGetAllRace = 1;
//	private final int reqJoinARace = 2;
	// private Server server;
	private Socket socket;

	/**
	 * Class constructor.
	 *
	 * @param s the server.
	 * @param c the client socket.
	 *
	 **/
	public ServerThread(final Server s, final Socket c) {
		// this.server = s;
		this.socket = c;
	}

	@Override
	public void run() {
		ServerServiceMember serverServiceMembers = new ServerServiceMember();
		ServerServiceStaff serverServiceStaff = new ServerServiceStaff();
		ObjectInputStream is = null;
		ObjectOutputStream os = null;

		try {
			is = new ObjectInputStream(new BufferedInputStream(this.socket.getInputStream()));
		} catch (Exception e) {
			e.printStackTrace();

			return;
		}

		try {

			Object i = is.readObject();
			if (os == null) {
				os = new ObjectOutputStream(new BufferedOutputStream(this.socket.getOutputStream()));
			}
			if (i instanceof RequestSignMember) {
				RequestSignMember rq = (RequestSignMember) i;
				if (rq.getReq() == MembersEnum.SIGN_IN.getTypeReq()) {
					int ris = serverServiceMembers.signInMembers(rq);

					if (ris >= 0) {
						ResponseSignMember rs = new ResponseSignMember(rq.getUsername(), rq.getPassword(), 1, ris);
						os.writeObject(rs);
						os.flush();
					} else {
						ResponseSignMember rs = new ResponseSignMember(rq.getUsername(), rq.getPassword(), 0, ris);
						os.writeObject(rs);
						os.flush();
					}
				}
				if (rq.getReq() == MembersEnum.SIGN_UP.getTypeReq()) {
					int ris = serverServiceMembers.signUpMembers(rq);
					System.out.println(ris);
					if (ris >= 0) {
						ResponseSignMember rs = new ResponseSignMember(rq.getUsername(), rq.getPassword(), 1, ris);
						os.writeObject(rs);
						os.flush();
					} else {
						ResponseSignMember rs = new ResponseSignMember(rq.getUsername(), rq.getPassword(), 0, ris);
						os.writeObject(rs);
						os.flush();
					}
				}
			}
			if (i instanceof RequestBoatMember) {
				RequestBoatMember rq = (RequestBoatMember) i;
				if (rq.getTypeRequest() == BoatMemberEnum.ADD_BOAT.getTypeReq()) {
					int ris = serverServiceMembers.addABoat(rq);
					if (ris >= 0) {
						ResponseBoatMember rs = new ResponseBoatMember(1);
						os.writeObject(rs);
						os.flush();
					} else {
						ResponseBoatMember rs = new ResponseBoatMember(-1);
						os.writeObject(rs);
						os.flush();
					}
				}
				if (rq.getTypeRequest() == BoatMemberEnum.GET_BOAT.getTypeReq()) {
					ArrayList<Boat> ris = new ArrayList<Boat>();
					ris = serverServiceMembers.getBoat(rq.getIdmember(), ris, rq.getTypeRequest());
					if (ris != null) {
						ResponseBoatMember rs = new ResponseBoatMember(1, ris);
						os.writeObject(rs);
						os.flush();
					} else {
						ResponseBoatMember rs = new ResponseBoatMember(-1, ris);
						os.writeObject(rs);
						os.flush();
					}
				}
				if (rq.getTypeRequest() == BoatMemberEnum.GET_BOAT_RACE.getTypeReq()) {
					ArrayList<Boat> ris = new ArrayList<Boat>();
					ris = serverServiceMembers.getBoat(rq.getIdmember(), ris, rq.getTypeRequest());
					if (ris != null) {
						ResponseBoatMember rs = new ResponseBoatMember(1, ris);
						os.writeObject(rs);
						os.flush();
					} else {
						ResponseBoatMember rs = new ResponseBoatMember(-1, ris);
						os.writeObject(rs);
						os.flush();
						os.close();
					}
				}
				if (rq.getTypeRequest() == BoatMemberEnum.REMOVE_BOAT.getTypeReq()) {
					System.out.println("sono qui in eliminazione");
					int ris = serverServiceMembers.removeABoat(rq.getIdCircolo());
					System.out.println("ris " + ris);
					if (ris >= 0) {
						ResponseBoatMember rs = new ResponseBoatMember(ris);
						os.writeObject(rs);
						os.flush();
					} else {
						ResponseBoatMember rs = new ResponseBoatMember(ris);
						os.writeObject(rs);
						os.flush();
						os.close();
					}
				}
			}
			if (i instanceof RequestNotificationMember) {
				System.out.println("sono qui in notification ");
				RequestNotificationMember rq = (RequestNotificationMember) i;
				System.out.println("req notification " + rq.getTypeReq());
				if (rq.getTypeReq() == NotificationMemberEnum.GET_NOTIFICATION.getTypeReq()) {
					ArrayList<Notification> ris = new ArrayList<Notification>();
					ris = serverServiceMembers.getNotification(rq.getId(), ris);
					System.out.println(ris);
					if (ris != null) {
						ResponseNotificationMember rs = new ResponseNotificationMember(ris, 1);
						os.writeObject(rs);
						os.flush();
					} else {
						ResponseNotificationMember rs = new ResponseNotificationMember(ris, -1);
						os.writeObject(rs);
						os.flush();
					}
				}
				if (rq.getTypeReq() == NotificationMemberEnum.REMOVE_NOTIFICATION.getTypeReq()) {

					int ris = serverServiceMembers.removeNotification(rq.getId());
					System.out.println(ris);
					if (ris >= 0) {
						ResponseNotificationMember rs = new ResponseNotificationMember(1);
						os.writeObject(rs);
						os.flush();
					} else {
						ResponseNotificationMember rs = new ResponseNotificationMember(-1);
						os.writeObject(rs);
						os.flush();
					}
				}
			}
			if (i instanceof RequestPaymentFeeMember) {
				RequestPaymentFeeMember rq = (RequestPaymentFeeMember) i;
				System.out.println("req fee " + rq.getTypeReq());
				if (rq.getTypeReq() == FeeMemberEnum.GET_FEE_MEMBERSHIP.getTypeReq()) {
					System.out.println("sono qui in fee ");
					ArrayList<FeePayment> ris = new ArrayList<FeePayment>();
					ris = serverServiceMembers.getFeeMembership(rq.getIdmember(), ris);
					// System.out.println(ris);
					if (ris != null) {
						ResponsePaymentFeeMember rs = new ResponsePaymentFeeMember(1, ris);
						os.writeObject(rs);
						os.flush();
					} else {
						ResponsePaymentFeeMember rs = new ResponsePaymentFeeMember(-1, ris);
						os.writeObject(rs);
						os.flush();
					}
				}
				if (rq.getTypeReq() == FeeMemberEnum.GET_FEE_STORAGE.getTypeReq()) {
					System.out.println("sono qui in feeeee ");
					ArrayList<FeePayment> ris = new ArrayList<FeePayment>();
					ris = serverServiceMembers.getFeeStorage(rq.getIdmember(), ris);
					// System.out.println(ris);
					if (ris != null) {
						ResponsePaymentFeeMember rs = new ResponsePaymentFeeMember(1, ris);
						os.writeObject(rs);
						os.flush();
					} else {
						ResponsePaymentFeeMember rs = new ResponsePaymentFeeMember(-1, ris);
						os.writeObject(rs);
						os.flush();
					}
				}
				if (rq.getTypeReq() == FeeMemberEnum.PAY_FEE_MEMBERSHIP.getTypeReq()) {
					System.out.println("sono qui in fee membership pay ");
					int ris = serverServiceMembers.payFeeMember(rq.getIdFee(), rq.getTypeReq(), rq.getPaymentType());
					// System.out.println(ris);
					if (ris >= 0) {
						ResponsePaymentFeeMember rs = new ResponsePaymentFeeMember(1);
						os.writeObject(rs);
						os.flush();
					} else {
						ResponsePaymentFeeMember rs = new ResponsePaymentFeeMember(-1);
						os.writeObject(rs);
						os.flush();

					}
				}
				if (rq.getTypeReq() == FeeMemberEnum.PAY_FEE_STORAGE.getTypeReq()) {
					System.out.println(rq.getIdFee() + " " + rq.getTypeReq() + " " + rq.getPaymentType());
					int ris = serverServiceMembers.payFeeMember(rq.getIdFee(), rq.getTypeReq(), rq.getPaymentType());
					// System.out.println(ris);
					if (ris >= 0) {
						ResponsePaymentFeeMember rs = new ResponsePaymentFeeMember(1);
						os.writeObject(rs);
						os.flush();
					} else {
						ResponsePaymentFeeMember rs = new ResponsePaymentFeeMember(-1);
						os.writeObject(rs);
						os.flush();

					}
				}
				if (rq.getTypeReq() == FeeMemberEnum.PAY_FEE_RACE.getTypeReq()) {
					System.out.println("sono qui in fee race");
					int idFee = serverServiceMembers.insertRaceFee(rq);
					int ris = serverServiceMembers.payFeeMember(idFee, rq.getTypeReq(), rq.getPaymentType());
					// System.out.println(ris);
					if (ris >= 0) {
						ResponsePaymentFeeMember rs = new ResponsePaymentFeeMember(1);
						os.writeObject(rs);
						os.flush();
					} else {
						ResponsePaymentFeeMember rs = new ResponsePaymentFeeMember(-1);
						os.writeObject(rs);
						os.flush();

					}
				}
			}
			if (i instanceof RequestRaceMember) {
				RequestRaceMember rq = (RequestRaceMember) i;
				System.out.println("req race " + rq.getTypeReq());
				if (rq.getTypeReq() == RaceMemberEnum.GET_RACE.getTypeReq()
						|| rq.getTypeReq() == RaceMemberEnum.GET_RACE_WON.getTypeReq()) {
					System.out.println("sono qui in race ");
					ArrayList<Race> ris = new ArrayList<Race>();
					ris = serverServiceMembers.getRace(rq.getIdmember(), ris, rq.getTypeReq());
					// System.out.println(ris);
					if (ris != null) {
						ResponseRaceMember rs = new ResponseRaceMember(1, ris);
						os.writeObject(rs);
						os.flush();
					} else {
						ResponseRaceMember rs = new ResponseRaceMember(-1, ris);
						os.writeObject(rs);
						os.flush();

					}
				}
				if (rq.getTypeReq() == RaceMemberEnum.GET_ALL_RACE.getTypeReq()) {
					System.out.println("sono qui in race all ");
					ArrayList<Race> ris = new ArrayList<Race>();
					ris = serverServiceMembers.getRace(rq.getIdmember(), ris, rq.getTypeReq());
					// System.out.println(ris);
					if (ris != null) {
						ResponseRaceMember rs = new ResponseRaceMember(1, ris);
						os.writeObject(rs);
						os.flush();

					} else {
						ResponseRaceMember rs = new ResponseRaceMember(-1, ris);
						os.writeObject(rs);
						os.flush();

					}
				}
				if (rq.getTypeReq() == RaceMemberEnum.JOIN_A_RACE.getTypeReq()) {
					System.out.println("sono qui in join a race ");

					int ris = serverServiceMembers.joinARace(rq.getIdmember(), rq.getIdBoat(), rq.getIdRace());
					// System.out.println(ris);
					if (ris >= 0) {
						ResponseRaceMember rs = new ResponseRaceMember(ris, null);
						os.writeObject(rs);
						os.flush();
					} else {
						ResponseRaceMember rs = new ResponseRaceMember(-1, null);
						os.writeObject(rs);
						os.flush();
					}
				}
			}
			if (i instanceof RequestStaffSignUp) {
				RequestStaffSignUp rq = (RequestStaffSignUp) i;
				System.out.println("req staff " + rq.getTypeReq());
				if (rq.getTypeReq() == StaffEnum.SIGN_UP.getTypeReq()) {
					System.out.println("sono qui in staff sign up");
					int ris = serverServiceStaff.signUpStaff(rq);
					// System.out.println(ris);
					if (ris >= 0) {
						ResponseStaffSignUp rs = new ResponseStaffSignUp(rq.getUsername(), rq.getPassword(), 1, ris);
						os.writeObject(rs);
						os.flush();
					} else {
						ResponseStaffSignUp rs = new ResponseStaffSignUp(rq.getUsername(), rq.getPassword(), -1, ris);
						os.writeObject(rs);
						os.flush();

					}
				}
			}
			if (i instanceof RequestStaffPayments) {
				RequestStaffPayments rq = (RequestStaffPayments) i;
				System.out.println("req staff " + rq.getTypeReq());
				if (rq.getTypeReq() == FeeStaffEnum.GET_ALL_MEMBERSHIP_PAYMENT.getTypeReq()) {
					System.out.println("sono qui in staff payments membership");
					ArrayList<FeePayment> payments = new ArrayList<>();
					payments = serverServiceStaff.getAllPayments(rq.getTypeReq(), payments);
					// System.out.println(ris);
					if (payments != null) {
						ResponseStaffPayments rs = new ResponseStaffPayments(1, payments);
						os.writeObject(rs);
						os.flush();
					} else {
						ResponseStaffPayments rs = new ResponseStaffPayments(-1, payments);
						os.writeObject(rs);
						os.flush();

					}
				}
				if (rq.getTypeReq() == FeeStaffEnum.GET_ALL_STORAGE_PAYMENT.getTypeReq()) {
					System.out.println("sono qui in staff payments storage");
					ArrayList<FeePayment> payments = new ArrayList<>();
					payments = serverServiceStaff.getAllPayments(rq.getTypeReq(), payments);
					// System.out.println(ris);
					if (payments != null) {
						ResponseStaffPayments rs = new ResponseStaffPayments(1, payments);
						os.writeObject(rs);
						os.flush();
					} else {
						ResponseStaffPayments rs = new ResponseStaffPayments(-1, payments);
						os.writeObject(rs);
						os.flush();

					}
				}
				if (rq.getTypeReq() == FeeStaffEnum.GET_ALL_RACE_PAYMENT.getTypeReq()) {
					System.out.println("sono qui in staff payments race");
					ArrayList<FeePayment> payments = new ArrayList<>();
					payments = serverServiceStaff.getAllPayments(rq.getTypeReq(), payments);
					// System.out.println(ris);
					if (payments != null) {
						ResponseStaffPayments rs = new ResponseStaffPayments(1, payments);
						os.writeObject(rs);
						os.flush();
					} else {
						ResponseStaffPayments rs = new ResponseStaffPayments(-1, payments);
						os.writeObject(rs);
						os.flush();

					}
				}
			}
			if (i instanceof RequestStaffAddPayments) {
				// System.out.println("sono quiiiiiiiiiiiiiiiiii");
				RequestStaffAddPayments rq = (RequestStaffAddPayments) i;
				System.out.println("req staff " + rq.getTypeReq());
				if (rq.getTypeReq() == FeeStaffEnum.GET_MEMBERS_MUST_PAY_MEMBERSHIP_FEE.getTypeReq()) {
					System.out.println("sono qui in staff  membership");
					ArrayList<Notification> notifications = new ArrayList<>();
					notifications = serverServiceStaff.getUsersMustPayFee(rq.getTypeReq(), notifications);
					// System.out.println(ris);
					if (notifications != null) {
						ResponseStaffAddPayments rs = new ResponseStaffAddPayments(notifications, 1);
						os.writeObject(rs);
						os.flush();
					} else {
						ResponseStaffAddPayments rs = new ResponseStaffAddPayments(notifications, -1);
						os.writeObject(rs);
						os.flush();

					}
				}
				if (rq.getTypeReq() == FeeStaffEnum.GET_MEMBERS_MUST_PAY_STORAGE_FEE.getTypeReq()) {
					System.out.println("sono qui in staff storage get");
					ArrayList<Notification> notifications = new ArrayList<>();
					notifications = serverServiceStaff.getUsersMustPayFee(rq.getTypeReq(), notifications);
					// System.out.println(ris);
					if (notifications != null) {
						ResponseStaffAddPayments rs = new ResponseStaffAddPayments(notifications, 1);
						os.writeObject(rs);
						os.flush();
					} else {
						ResponseStaffAddPayments rs = new ResponseStaffAddPayments(notifications, -1);
						os.writeObject(rs);
						os.flush();

					}
				}
				if (rq.getTypeReq() == NotificationStaffEnum.GET_MEMBERS_TO_NOTIFY_MEMBERSHIP_FEE.getTypeReq()) {
					System.out.println("sono qui in staff notification  get");
					ArrayList<Notification> notifications = new ArrayList<>();
					notifications = serverServiceStaff.getUsersMustPayFee(rq.getTypeReq(), notifications);
					// System.out.println(ris);
					if (notifications != null) {
						ResponseStaffAddPayments rs = new ResponseStaffAddPayments(notifications, 1);
						os.writeObject(rs);
						os.flush();
					} else {
						ResponseStaffAddPayments rs = new ResponseStaffAddPayments(notifications, -1);
						os.writeObject(rs);
						os.flush();

					}
				}
				if (rq.getTypeReq() == NotificationStaffEnum.GET_MEMBERS_TO_NOTIFY_STORAGE_FEE.getTypeReq()) {
					System.out.println("sono qui in staff notification storage get");
					ArrayList<Notification> notifications = new ArrayList<>();
					notifications = serverServiceStaff.getUsersMustPayFee(rq.getTypeReq(), notifications);
					// System.out.println(ris);
					if (notifications != null) {
						ResponseStaffAddPayments rs = new ResponseStaffAddPayments(notifications, 1);
						os.writeObject(rs);
						os.flush();
					} else {
						ResponseStaffAddPayments rs = new ResponseStaffAddPayments(notifications, -1);
						os.writeObject(rs);
						os.flush();

					}
				}
				if (rq.getTypeReq() == FeeStaffEnum.ADD_MEMBERS_MUST_PAY_MEMBERSHIP_FEE.getTypeReq()) {
					System.out.println("sono qui in staff notification membership add");
					int ris = serverServiceStaff.addUsersMustPayFee(rq);
					// System.out.println(ris);
					if (ris >= 0) {
						ResponseStaffAddPayments rs = new ResponseStaffAddPayments(1);
						os.writeObject(rs);
						os.flush();
					} else {
						ResponseStaffAddPayments rs = new ResponseStaffAddPayments(-1);
						os.writeObject(rs);
						os.flush();

					}
				}
				if (rq.getTypeReq() == FeeStaffEnum.ADD_MEMBERS_MUST_PAY_STORAGE_FEE.getTypeReq()) {
					System.out.println("sono qui in staff notification storage add");
					int ris = serverServiceStaff.addUsersMustPayFee(rq);
					// System.out.println(ris);
					if (ris >= 0) {
						ResponseStaffAddPayments rs = new ResponseStaffAddPayments(1);
						os.writeObject(rs);
						os.flush();
					} else {
						ResponseStaffAddPayments rs = new ResponseStaffAddPayments(-1);
						os.writeObject(rs);
						os.flush();

					}
				}
			}
			if (i instanceof RequestStaffManageMember) {
				// System.out.println("sono quiiiiiiiiiiiiiiiiii");
				RequestStaffManageMember rq = (RequestStaffManageMember) i;
				System.out.println("req staff " + rq.getTypeReq());
				if (rq.getTypeReq() == StaffEnum.GET_MEMBERS.getTypeReq()) {
					ArrayList<Member> socii = new ArrayList<>();
					socii = serverServiceStaff.getMembers(socii);
					// System.out.println(ris);
					if (socii != null) {
						ResponseStaffManageMember rs = new ResponseStaffManageMember(1, socii);
						os.writeObject(rs);
						os.flush();
					} else {
						ResponseStaffManageMember rs = new ResponseStaffManageMember(-1, socii);
						os.writeObject(rs);
						os.flush();

					}
				}
				if (rq.getTypeReq() == StaffEnum.MODIFY_MEMBERS_CREDENTIAL.getTypeReq()) {
					int ris = serverServiceStaff.manageMembersCredential(rq);
					// System.out.println(ris);
					if (ris >= 0) {
						ResponseStaffManageMember rs = new ResponseStaffManageMember(1);
						os.writeObject(rs);
						os.flush();
					} else {
						ResponseStaffManageMember rs = new ResponseStaffManageMember(-1);
						os.writeObject(rs);
						os.flush();

					}
				}
				if (rq.getTypeReq() == StaffEnum.REMOVE_MEMBER.getTypeReq()) {
					int ris = serverServiceStaff.removeAMember(rq);
					// System.out.println(ris);
					if (ris >= 0) {
						ResponseStaffManageMember rs = new ResponseStaffManageMember(1);
						os.writeObject(rs);
						os.flush();
					} else {
						ResponseStaffManageMember rs = new ResponseStaffManageMember(-1);
						os.writeObject(rs);
						os.flush();

					}
				}
			}
			if (i instanceof RequestStaffManageBoat) {
				// System.out.println("sono quiiiiiiiiiiiiiiiiii");
				RequestStaffManageBoat rq = (RequestStaffManageBoat) i;
				System.out.println("req staff " + rq.getTypeReq());
				if (rq.getTypeReq() == BoatStaffEnum.GET_BOATS.getTypeReq()) {
					ArrayList<Boat> boats = new ArrayList<>();
					boats = serverServiceStaff.getBoats(boats);
					// System.out.println(ris);
					if (boats != null) {
						ResponseStaffManageBoat rs = new ResponseStaffManageBoat(1, boats);
						os.writeObject(rs);
						os.flush();
					} else {
						ResponseStaffManageBoat rs = new ResponseStaffManageBoat(-1, boats);
						os.writeObject(rs);
						os.flush();

					}
				}
				if (rq.getTypeReq() == BoatStaffEnum.MODIFY_BOAT.getTypeReq()) {

					int ris = serverServiceStaff.manageBoatData(rq);
					// System.out.println(ris);
					if (ris >= 0) {
						ResponseStaffManageBoat rs = new ResponseStaffManageBoat(1);
						os.writeObject(rs);
						os.flush();
					} else {
						ResponseStaffManageBoat rs = new ResponseStaffManageBoat(-1);
						os.writeObject(rs);
						os.flush();

					}
				}
			}
			if (i instanceof RequestStaffRace) {
				// System.out.println("sono quiiiiiiiiiiiiiiiiii");
				RequestStaffRace rq = (RequestStaffRace) i;
				System.out.println("req staff " + rq.getTypeReq());
				if (rq.getTypeReq() == RaceStaffEnum.ADD_RACE.getTypeReq()) {
					System.out.println("sono qui dentro ");
					int ris = serverServiceStaff.addARace(rq);
					// System.out.println(ris);
					if (ris >= 0) {
						ResponseStaffRace rs = new ResponseStaffRace(1);
						os.writeObject(rs);
						os.flush();
					} else {
						ResponseStaffRace rs = new ResponseStaffRace(-1);
						os.writeObject(rs);
						os.flush();

					}
				}
				if (rq.getTypeReq() == RaceStaffEnum.GET_RACE.getTypeReq()
						|| rq.getTypeReq() ==RaceStaffEnum.GET_RACE_TO_SIMULATE.getTypeReq()) {
					System.out.println("sono quiiiiiiiii in get race");
					ArrayList<Race> race = new ArrayList<>();
					race = serverServiceStaff.getRace(race, rq.getTypeReq());
					if (race != null) {
						ResponseStaffRace rs = new ResponseStaffRace(1, race);
						os.writeObject(rs);
						os.flush();
					} else {
						ResponseStaffRace rs = new ResponseStaffRace(-1, race);
						os.writeObject(rs);
						os.flush();

					}
				}
				if (rq.getTypeReq() ==RaceStaffEnum.MODIFY_RACE.getTypeReq()) {
					System.out.println("sono quiiiiiiiii");
					int ris = serverServiceStaff.modifyARace(rq);
					// System.out.println(ris);
					if (ris >= 0) {
						ResponseStaffRace rs = new ResponseStaffRace(1);
						os.writeObject(rs);
						os.flush();
					} else {
						ResponseStaffRace rs = new ResponseStaffRace(-1);
						os.writeObject(rs);
						os.flush();

					}
				}
				if (rq.getTypeReq() == RaceStaffEnum.SIMULATE_RACE.getTypeReq()) {
					System.out.println("sono qui in simulate a race");
					int ris = serverServiceStaff.simulateARace(rq);
					// System.out.println(ris);
					if (ris >= 0) {
						ResponseStaffRace rs = new ResponseStaffRace(ris);
						os.writeObject(rs);
						os.flush();
					} else {
						ResponseStaffRace rs = new ResponseStaffRace(-1);
						os.writeObject(rs);
						os.flush();

					}
				}
				if (rq.getTypeReq() == RaceStaffEnum.GET_ALL_RACE_AND_WINNERS.getTypeReq()) {
					System.out.println("sono qui in get all race and winners");
					ArrayList<Race> race = new ArrayList<>();
					race = serverServiceStaff.getRacesAndWinners(race, rq.getTypeReq());
					if (race != null) {
						ResponseStaffRace rs = new ResponseStaffRace(1, race);
						os.writeObject(rs);
						os.flush();
					} else {
						ResponseStaffRace rs = new ResponseStaffRace(-1, race);
						os.writeObject(rs);
						os.flush();

					}
				}
			}
			if (i instanceof RequestStaffFeeNotification) {
				// System.out.println("sono quiiiiiiiiiiiiiiiiii");
				RequestStaffFeeNotification rq = (RequestStaffFeeNotification) i;
				System.out.println("req staff " + rq.getTypeReq());
				if (rq.getTypeReq() == NotificationStaffEnum.SEND_MEMBERSHIP_NOTIFICATION.getTypeReq()) {
					System.out.println("sono qui dentro ");
					int ris = serverServiceStaff.sendFeeNotification(rq);
					// System.out.println(ris);
					if (ris >= 0) {
						ResponseStaffFeeNotification rs = new ResponseStaffFeeNotification(1);
						os.writeObject(rs);
						os.flush();
					} else {
						ResponseStaffFeeNotification rs = new ResponseStaffFeeNotification(-1);
						os.writeObject(rs);
						os.flush();

					}
				}
				if (rq.getTypeReq() == NotificationStaffEnum.SEND_STORAGE_NOTIFICATION.getTypeReq()) {
					System.out.println("sono qui dentro ");
					int ris = serverServiceStaff.sendFeeNotification(rq);
					// System.out.println(ris);
					if (ris >= 0) {
						ResponseStaffFeeNotification rs = new ResponseStaffFeeNotification(1);
						os.writeObject(rs);
						os.flush();
					} else {
						ResponseStaffFeeNotification rs = new ResponseStaffFeeNotification(-1);
						os.writeObject(rs);
						os.flush();

					}
				}

			}
			os.close();
			is.close();
			this.socket.close();
			return;
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
}
