package server.unipr.it;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

import CircoloVelico.unipr.it.Member;
import enumClass.FeeStaffEnum;
import enumClass.NotificationStaffEnum;
import enumClass.RaceStaffEnum;
import javaClass.Connessione;

import javaClass.FeePayment;
import javaClass.Boat;
import javaClass.Notification;
import javaClass.Race;
import requestStaff.RequestStaffManageBoat;
import requestStaff.RequestStaffManageMember;
import requestStaff.RequestStaffFeeNotification;
import requestStaff.RequestStaffAddPayments;
import requestStaff.RequestStaffRace;
import requestStaff.RequestStaffSignUp;

/**
 * The {@code ServerServiceStaff} class contains all method that a staff member
 * needs to use to get access to database. For example here there's a method
 * that a staff member use to store access to the software, contains a method
 * that a staff member needs to drawn all payment that user made.
 * 
 * @author Spagni Alex Zuelli Arianna
 * 
 */
public class ServerServiceStaff {
	private static final String DBURL = "jdbc:mysql://localhost:3306/circolovelico";
	private static final String LOGIN = "root";
	private static final String PASSWORD = "";
//	private static final Constant costant = new Constant();

	/**
	 * Class constructor
	 */
	public ServerServiceStaff() {

	}

	/**
	 * This method is used to check if username and password insert from a staff
	 * member are correct or not.
	 * 
	 * @param rq an object that contains username and password to check.
	 * @return It return an integer value:<br>
	 *         1)positive=Username and password correct.<br>
	 *         2)negative=Username and password not correct.
	 */
	public int signUpStaff(RequestStaffSignUp rq) {
		try (Connection conn = Connessione.getConnection(); Statement stmt = conn.createStatement();) {
			String strSelect = "select id,username,password FROM staff";
			ResultSet rset = stmt.executeQuery(strSelect);
			System.out.println(rq.getUsername() + " " + rq.getPassword());
			while (rset.next()) {
				String username = rset.getString("username");
				String password = rset.getString("password");
				int id = rset.getInt("id");
				// System.out.println(username+" "+password);
				if (rq.getUsername().equals(username) && rq.getPassword().equals(password)) {
					System.out.println("sono qui");
					conn.close();
					return id;
				}

			}
			conn.close();
			return -1;
		} catch (SQLException e) {
			System.out.println("connessione non avvenuta");
			e.printStackTrace();
			return -1;
		}
	}

	/**
	 * This method is used to fetch payments that user made. Different payment will
	 * be fetched based on the type of request that a staff member made.
	 * 
	 * @param typeReq     an integer value that distinguish which request client has
	 *                    sent to the server.
	 * @param allPayments list where we are going to add all payment drawn.
	 * @return It return an array list that contains all payment made.
	 */
	public ArrayList<FeePayment> getAllPayments(int typeReq, ArrayList<FeePayment> allPayments) {
		try (Connection conn = Connessione.getConnection(); Statement stmt = conn.createStatement();) {
			Date date = null;
			int price = -1, idFee = -1, idmember = -1;
			String paymentMethod = "bonifico", paied = "";
			ResultSet rset2 = null, rSet3 = null;
			if (typeReq == FeeStaffEnum.GET_ALL_MEMBERSHIP_PAYMENT.getTypeReq()) {

				String strSelect2 = "select idFee,date,price,paied,idMember FROM transfer where feeType='"
						+ "membership" + "' ";
				rset2 = stmt.executeQuery(strSelect2);

				while (rset2.next()) {
					idFee = rset2.getInt("idFee");
					date = rset2.getDate("date");
					price = rset2.getInt("price");
					paied = rset2.getString("paied");
					idmember = rset2.getInt("idMember");
					allPayments.add(new FeePayment(idFee, date, price, paied, idmember, paymentMethod));
				}
				strSelect2 = "select idFee,date,price,paied,idMember FROM creditcard where feeType='" + "membership"
						+ "'";
				rSet3 = stmt.executeQuery(strSelect2);
				paymentMethod = "creditCard";
				while (rSet3.next()) {
					idFee = rSet3.getInt("idFee");
					date = rSet3.getDate("date");
					price = rSet3.getInt("price");
					paied = rSet3.getString("paied");
					idmember = rSet3.getInt("idMember");
					allPayments.add(new FeePayment(idFee, date, price, paied, idmember, paymentMethod));
				}
			} else if (typeReq == FeeStaffEnum.GET_ALL_STORAGE_PAYMENT.getTypeReq()) {
				String strSelect2 = "select idFee,date,price,paied,idMember FROM transfer where feeType='" + "storage"
						+ "' ";
				rset2 = stmt.executeQuery(strSelect2);

				while (rset2.next()) {
					idFee = rset2.getInt("idFee");
					date = rset2.getDate("date");
					price = rset2.getInt("price");
					paied = rset2.getString("paied");
					idmember = rset2.getInt("idmember");
					allPayments.add(new FeePayment(idFee, date, price, paied, idmember, paymentMethod));
				}
				strSelect2 = "select idFee,date,price,paied,idMember FROM creditcard where feeType='" + "storage" + "'";
				rSet3 = stmt.executeQuery(strSelect2);
				paymentMethod = "creditCard";
				while (rSet3.next()) {
					idFee = rSet3.getInt("idFee");
					date = rSet3.getDate("date");
					price = rSet3.getInt("price");
					paied = rSet3.getString("paied");
					idmember = rSet3.getInt("idMember");
					allPayments.add(new FeePayment(idFee, date, price, paied, idmember, paymentMethod));
				}
			} else {
				String strSelect2 = "select idFee,date,price,paied,idMember FROM transfer where feeType='" + "feeRace"
						+ "' ";
				rset2 = stmt.executeQuery(strSelect2);

				while (rset2.next()) {
					idFee = rset2.getInt("idFee");
					date = rset2.getDate("date");
					price = rset2.getInt("price");
					paied = rset2.getString("paied");
					idmember = rset2.getInt("idMember");
					allPayments.add(new FeePayment(idFee, date, price, paied, idmember, paymentMethod));
				}
				strSelect2 = "select idFee,date,price,paied,idMember FROM creditcard where feeType='" + "feeRace" + "'";
				rSet3 = stmt.executeQuery(strSelect2);
				paymentMethod = "creditCard";
				while (rSet3.next()) {
					idFee = rSet3.getInt("idFee");
					date = rSet3.getDate("date");
					price = rSet3.getInt("price");
					paied = rSet3.getString("paied");
					idmember = rSet3.getInt("idMember");
					allPayments.add(new FeePayment(idFee, date, price, paied, idmember, paymentMethod));
				}
			}
			conn.close();
			return allPayments;
		} catch (SQLException e) {
			System.out.println("connessione non avvenuta");
			e.printStackTrace();
			return allPayments;
		}
	}

	// per il form di send notification
	/**
	 * This method is used to fetch all membership/storage fee that must be add into
	 * the database.
	 * 
	 * @param typeReq       an integer value that distinguish which request client
	 *                      has sent to the server.
	 * @param notifications list where we are going to add all payment drawn.
	 * @return It return an array list that contains all membership/storage fee that
	 *         have been fetched.
	 */
	public ArrayList<Notification> getUsersMustPayFee(int typeReq, ArrayList<Notification> notifications) {
		try (Connection conn = Connessione.getConnection(); Statement stmt = conn.createStatement();) {
			Date date = java.sql.Date.valueOf(LocalDate.now());
			int price = -1, idmember = -1, idBoat = -1;
			int idFee = -1;
			String boatName = "";
			ResultSet rset = null, rset2 = null;
			if (typeReq == FeeStaffEnum.GET_MEMBERS_MUST_PAY_MEMBERSHIP_FEE.getTypeReq()) {

				String strSelect = "SELECT members.id from members where members.id <> all (SELECT membershippayment.idMember from membershippayment where year(membershippayment.date)='"
						+ LocalDate.now().getYear() + "') ";
				rset = stmt.executeQuery(strSelect);
				while (rset.next()) {
					idmember = rset.getInt("id");
					price = (int) (Math.random() * 500);

					notifications.add(new Notification(price, idmember, date));

				}

			}
			// per selezionare tutte le quote di rimessaggio da inserire nel database
			if (typeReq == FeeStaffEnum.GET_MEMBERS_MUST_PAY_STORAGE_FEE.getTypeReq()) {
				System.out.println("sono quiiiiii");
				String strSelect = "SELECT length,members.id,boat.name,boat.idBoat from members, boat where boat.idMember=members.id and boat.storage='"
						+ "si" + "' and boat.idBoat  <> "
						+ "all (SELECT storagepayment.idBoat FROM storagepayment where year(storagepayment.date)='"
						+ LocalDate.now().getYear() + "') ";
				rset = stmt.executeQuery(strSelect);
				while (rset.next()) {

					idmember = rset.getInt("id");
					price = rset.getInt("length");
					idBoat = rset.getInt("idBoat");
					boatName = rset.getString("name");
					System.out.println(boatName+" "+idBoat);
					notifications.add(new Notification(price, idmember, date, boatName, idBoat));
				}
			}
			if (typeReq == NotificationStaffEnum.GET_MEMBERS_TO_NOTIFY_MEMBERSHIP_FEE.getTypeReq()) {
				String strSelect = "SELECT idMember,price,id from membershippayment where paied='" + "no"
						+ "' and year(date)='" + LocalDate.now().getYear() + "'";
				rset = stmt.executeQuery(strSelect);
				while (rset.next()) {
					idFee = rset.getInt("id");
					idmember = rset.getInt("idMember");
					price = rset.getInt("price");
					notifications.add(new Notification(price, idmember, date));
				}
			}
			if (typeReq == NotificationStaffEnum.GET_MEMBERS_TO_NOTIFY_STORAGE_FEE.getTypeReq()) {
				// System.out.println("sono quiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");
				String strSelect = "SELECT storagepayment.idMember,price,boat.idBoat,id,boat.name from storagepayment,boat where boat.idBoat=storagepayment.idBoat and paied='"
						+ "no" + "' and year(date)='" + LocalDate.now().getYear() + "'";
				rset = stmt.executeQuery(strSelect);
				while (rset.next()) {
					// idFee = rset.getInt("id");
					idmember = rset.getInt("idMember");
					price = rset.getInt("price");
					idBoat = rset.getInt("idBoat");
					// boatName = rset.getString("name");
					boatName = rset.getString("name");
					notifications.add(new Notification(price, idmember, date, boatName, idBoat));
				}
//				String strSelect2 = "SELECT name from boat where idBoat='" + idBoat
//						+ "'";
//				rset2 = stmt.executeQuery(strSelect2);
//				while (rset2.next()) {
//					boatName = rset2.getString("name");
//					
//			}

			}
			conn.close();
			return notifications;
		} catch (SQLException e) {
			System.out.println("connessione non avvenuta");
			e.printStackTrace();
			return notifications;
		}
	}

	/**
	 * This method is used to add membership/storage fee to the database.
	 * 
	 * @param rq an object that contains all data of the fee that we have to store
	 *           on the database.
	 * @return It return an integer value: <br>
	 *         1)positive=Membership/Storage fee stored on the database.<br>
	 *         2)negative=Membership/storage fee not stored on the database.
	 */
	public int addUsersMustPayFee(RequestStaffAddPayments rq) {
		try (Connection conn = Connessione.getConnection(); Statement stmt = conn.createStatement();) {
			int maxIdMembership_storage = 0, maxIdNotification = 0;
			ResultSet rset = null;
			if (rq.getTypeReq() == FeeStaffEnum.ADD_MEMBERS_MUST_PAY_MEMBERSHIP_FEE.getTypeReq()) {
				String strSelect = "select max(id) as id from membershippayment";

				rset = stmt.executeQuery(strSelect);

				while (rset.next()) {
					maxIdMembership_storage = rset.getInt("id");
					maxIdMembership_storage++;

				}

				String insertSql = "insert into membershippayment values (?,?,?,?,?)";
				PreparedStatement pstmt = conn.prepareStatement(insertSql);
				pstmt.setString(1, Integer.toString(maxIdMembership_storage));
				pstmt.setDate(2, rq.getDate());
				pstmt.setString(3, Integer.toString(rq.getPrice()));
				pstmt.setString(4, Integer.toString(rq.getIdmember()));
				pstmt.setString(5, "no");
				pstmt.addBatch();
				pstmt.executeBatch();
				strSelect = "select max(id) as id from notification";

				rset = stmt.executeQuery(strSelect);

				while (rset.next()) {
					maxIdNotification = rset.getInt("id");
					maxIdNotification++;
				}
				insertSql = "insert into notification values (?,?,?,?,?,?)";
				PreparedStatement pstmt2 = conn.prepareStatement(insertSql);
				pstmt2.setString(1, Integer.toString(maxIdNotification));
				pstmt2.setString(2, "membership");
				pstmt2.setString(3, Integer.toString(rq.getPrice()));
				pstmt2.setString(4, Integer.toString(rq.getIdmember()));
				pstmt2.setDate(5, rq.getDate());
				// pstmt2.setString(6, Integer.toString(maxIdMembership_storage));
				pstmt2.setString(6, "pay membership fee");
				pstmt2.addBatch();
				pstmt2.executeBatch();

			} else {
				String strSelect = "select max(id) as id from storagepayment";

				rset = stmt.executeQuery(strSelect);

				while (rset.next()) {
					maxIdMembership_storage = rset.getInt("id");
					maxIdMembership_storage++;

				}

				String insertSql = "insert into storagepayment values (?,?,?,?,?,?)";
				PreparedStatement pstmt = conn.prepareStatement(insertSql);
				pstmt.setString(1, Integer.toString(maxIdMembership_storage));
				pstmt.setDate(2, rq.getDate());
				pstmt.setString(3, Integer.toString(rq.getPrice()));
				pstmt.setString(4, Integer.toString(rq.getIdmember()));
				pstmt.setString(5, "no");
				pstmt.setString(6, Integer.toString(rq.getIdBoat()));
				pstmt.addBatch();
				pstmt.executeBatch();
				strSelect = "select max(id) as id from notification";

				rset = stmt.executeQuery(strSelect);

				while (rset.next()) {
					maxIdNotification = rset.getInt("id");
					maxIdNotification++;
				}
				insertSql = "insert into notification values (?,?,?,?,?,?)";
				PreparedStatement pstmt2 = conn.prepareStatement(insertSql);
				pstmt2.setString(1, Integer.toString(maxIdNotification));
				pstmt2.setString(2, "storage");
				pstmt2.setString(3, Integer.toString(rq.getPrice()));
				pstmt2.setString(4, Integer.toString(rq.getIdmember()));
				pstmt2.setDate(5, rq.getDate());
				// pstmt2.setString(6, Integer.toString(maxIdMembership_storage));
				pstmt2.setString(6, "pay storage fee");
				pstmt2.addBatch();
				pstmt2.executeBatch();

			}
			conn.close();
			return 1;
		} catch (SQLException e) {
			System.out.println("connessione non avvenuta");
			e.printStackTrace();
			return -1;
		}
	}

	/**
	 * This method is used to drawn all user from the database.
	 * 
	 * @param members list where we are going to add all users drawn.
	 * @return It returns an array list that containt all user drawn.
	 */
	public ArrayList<Member> getMembers(ArrayList<Member> members) {
		try (Connection conn = Connessione.getConnection(); Statement stmt = conn.createStatement();) {

			String strSelect = "select username,address,fiscalCode,id FROM members";
			ResultSet rset = stmt.executeQuery(strSelect);
			while (rset.next()) {
				String username = rset.getString("username");
				String address = rset.getString("address");
				int id = rset.getInt("id");
				String fiscalCode = rset.getString("fiscalCode");
				// System.out.println(username+" "+password);
				members.add(new Member(username, address, id, fiscalCode));
			}
			for (Member i : members) {
				System.out.println(i.getUsername() + "" + i.getId());
			}
			return members;
		} catch (SQLException e) {
			System.out.println("connessione non avvenuta");
			e.printStackTrace();
			return members;
		}
	}

	/**
	 * This method is used to modify some information about a user in the database.
	 * 
	 * @param rq object that contains new data, that we have to insert in the
	 *           database and the id of the member, who's credential have to be
	 *           changed.
	 * @return It return an integer value:<br>
	 *         1)positive=Data changed correctly.<br>
	 *         2)negative=Data not changed correctly.
	 */
	public int manageMembersCredential(RequestStaffManageMember rq) {
		try (Connection conn = Connessione.getConnection(); Statement stmt = conn.createStatement();) {
			if (!rq.getAddress().isBlank() && !rq.getFiscalCode().isBlank()) {
				String strUpdate = "update members set fiscalCode = '" + rq.getFiscalCode() + "'where id = '"
						+ rq.getIdmember() + "'";
				stmt.executeUpdate(strUpdate);
				strUpdate = "update members set address = '" + rq.getAddress() + "'where id = '" + rq.getIdmember()
						+ "'";
				stmt.executeUpdate(strUpdate);
				return 1;
			}
			if (!rq.getAddress().isBlank()) {
				String strUpdate = "update members set address = '" + rq.getAddress() + "' where id = '"
						+ rq.getIdmember() + "'";
				stmt.executeUpdate(strUpdate);
				return 1;
			}
			if (!rq.getFiscalCode().isBlank()) {
				String strUpdate = "update members set fiscalCode = '" + rq.getFiscalCode() + "' where id = '"
						+ rq.getIdmember() + "'";
				stmt.executeUpdate(strUpdate);
				return 1;
			}
			conn.close();
			return 1;
		} catch (SQLException e) {
			System.out.println("connessione non avvenuta");
			e.printStackTrace();
			return -1;
		}
	}

	/**
	 * This method is used to remove a member from the database.
	 * 
	 * @param rq object that contains the id of member we want to remove.
	 * @return It return an integer value:<br>
	 *         1)positive=Member removed correctly.<br>
	 *         2)negative=Member not removed correctly.
	 */
	public int removeAMember(RequestStaffManageMember rq) {
		try (Connection conn = Connessione.getConnection(); Statement stmt = conn.createStatement();) {
			String strSelect = "DELETE FROM members WHERE id=?";
			PreparedStatement pstmt = conn.prepareStatement(strSelect);
			pstmt.setInt(1, rq.getIdmember());
			pstmt.execute();
			int ris = removeMemberConnections(rq.getIdmember());
			if (ris >= 0) {
				conn.close();
				return 1;
			} else {
				conn.close();
				return -1;
			}
		} catch (SQLException e) {
			System.out.println("connessione non avvenuta");
			e.printStackTrace();
			return -1;
		}
	}

	private int removeMemberConnections(int idMember) {
		try (Connection conn = Connessione.getConnection(); Statement stmt = conn.createStatement();) {
			// System.out.println("id member: " + idBoat);
			String strSelect = "DELETE FROM boat WHERE boat.idMember=?";
			PreparedStatement pstmt = conn.prepareStatement(strSelect);
			pstmt.setInt(1, idMember);
			pstmt.execute();
			strSelect = "DELETE FROM racepartecipants WHERE idMember=?";
			PreparedStatement pstmt2 = conn.prepareStatement(strSelect);
			pstmt2.setInt(1, idMember);
			pstmt2.execute();
			strSelect = "DELETE FROM notification WHERE idMember=?";
			PreparedStatement pstmt3 = conn.prepareStatement(strSelect);
			pstmt3.setInt(1, idMember);
			pstmt3.execute();
			final int constant = -1;
			String strUpdate = "update race set winner = '" + constant + "' where winner = '" + idMember + "'";
			stmt.executeUpdate(strUpdate);
			conn.close();
			return 1;
		} catch (SQLException e) {
			System.out.println("connessione non avvenuta");
			e.printStackTrace();
			return -1;
		}
	}

	/**
	 * This method is used to drawn all boats from the database.
	 * 
	 * @param boats list where we are going to add all boats drawn.
	 * @return It return an array list that contains all boats drawn.
	 */
	public ArrayList<Boat> getBoats(ArrayList<Boat> boats) {
		try (Connection conn = Connessione.getConnection(); Statement stmt = conn.createStatement();) {

			String strSelect = "select idBoat,name,length,idMember,storage FROM boat";
			ResultSet rset = stmt.executeQuery(strSelect);
			while (rset.next()) {
				String name = rset.getString("name");
				String storage = rset.getString("storage");
				int idBoat = rset.getInt("idBoat");
				int idmember = rset.getInt("idMember");
				int length = rset.getInt("length");
				// System.out.println(username+" "+password);
				boats.add(new Boat(name, idBoat, idmember, length, storage));
			}
			conn.close();
			return boats;
		} catch (SQLException e) {
			System.out.println("connessione non avvenuta");
			e.printStackTrace();
			return boats;
		}
	}

	/**
	 * This method is used to modify some data about a boat in the database.
	 * 
	 * @param rq object that contains new data, that we have to insert in the
	 *           database and the id of the boat, who's data have to be changed.
	 * @return It return an integer value:<br>
	 *         1)positive=Data changed correctly.<br>
	 *         2)negative=Data not changed correctly.
	 */
	public int manageBoatData(RequestStaffManageBoat rq) {
		try (Connection conn = Connessione.getConnection(); Statement stmt = conn.createStatement();) {
			if (rq.getLength() != 0 && !rq.getStorage().isBlank()) {
				String strUpdate = "update boat set length = '" + rq.getLength() + "'where idBoat = '" + rq.getIdBoat()
						+ "'";
				stmt.executeUpdate(strUpdate);
				strUpdate = "update boat set storage = '" + rq.getStorage() + "'where idBoat = '" + rq.getIdBoat()
						+ "'";
				stmt.executeUpdate(strUpdate);
				return 1;
			}
			if (rq.getLength() != 0) {
				String strUpdate = "update boat set length = '" + rq.getLength() + "'where idBoat = '" + rq.getIdBoat()
						+ "'";
				stmt.executeUpdate(strUpdate);
				return 1;
			}
			if (!rq.getStorage().isBlank()) {
				String strUpdate = "update boat set storage = '" + rq.getStorage() + "'where idBoat = '"
						+ rq.getIdBoat() + "'";
				stmt.executeUpdate(strUpdate);
				return 1;
			}
			conn.close();
			return 1;
		} catch (SQLException e) {
			System.out.println("connessione non avvenuta");
			e.printStackTrace();
			return -1;
		}

	}

	/**
	 * This method is used to add a new race in the database.
	 * 
	 * @param rq object that contains new data, that we have to insert in the
	 *           database.
	 * @return It return an integer value:<br>
	 *         1)positive=New race added.<br>
	 *         2)negative=New race not added.
	 */
	public int addARace(RequestStaffRace rq) {
		try (Connection conn = Connessione.getConnection(); Statement stmt = conn.createStatement();) {
			String strSelect = "select max(idRace) as id from race";
			int maxId = 0;
			ResultSet rset = stmt.executeQuery(strSelect);

			while (rset.next()) {
				maxId = rset.getInt("id");
				maxId++;
			}
			String insertSql = "insert into race values (?,?,?,?,?,?,?)";
			PreparedStatement pstmt2 = conn.prepareStatement(insertSql);
			pstmt2.setString(1, Integer.toString(maxId));
			pstmt2.setString(2, rq.getRaceName());
			pstmt2.setString(3, Integer.toString(rq.getPrice()));
			pstmt2.setString(4, rq.getRacePlace());
			pstmt2.setString(5, Integer.toString(-1));
			pstmt2.setDate(6, rq.getRaceDate());
			pstmt2.setString(7, "no");
			pstmt2.addBatch();
			pstmt2.executeBatch();
			conn.close();
			return 1;
		} catch (SQLException e) {
			System.out.println("connessione non avvenuta");
			e.printStackTrace();
			return -1;
		}

	}

	/**
	 * This method is used to drawn some type of race from the database.
	 * 
	 * @param raceArrayList list where we are going to add all race drawn.
	 * @param typeReq       an integer value that distinguish which request client
	 *                      has sent to the server.
	 * @return It return an array list that contains all race drawn.
	 */
	public ArrayList<Race> getRace(ArrayList<Race> raceArrayList, int typeReq) {
		try (Connection conn = Connessione.getConnection(); Statement stmt = conn.createStatement();) {
			ResultSet rset = null;
			if (typeReq == RaceStaffEnum.GET_RACE.getTypeReq()) {
				String strSelect = "select race.idRace,raceName,price,place,date from race";
				rset = stmt.executeQuery(strSelect);
			} else {
				int constant = -1;
				String strSelect = "select race.idRace,raceName,price,place,date from race where date='"
						+ java.sql.Date.valueOf(LocalDate.now()) + "' and winner='" + constant + "'and race.idRace = any (select idRace from racepartecipants)";
				rset = stmt.executeQuery(strSelect);
			}
			while (rset.next()) {

				int idRace = rset.getInt("idRace");
				String raceName = rset.getString("raceName");
				int price = rset.getInt("price");
				String place = rset.getString("place");
				Date date = rset.getDate("date");
				System.out.println(idRace + " " + raceName + " " + place);

				raceArrayList.add(new Race(idRace, raceName, price, place, date));
			}
			conn.close();
			return raceArrayList;
		} catch (SQLException e) {
			System.out.println("connessione non avvenuta");
			e.printStackTrace();
			return raceArrayList;
		}
	}

	/**
	 * This method is used to drawn some type of race from the database.
	 * 
	 * @param raceArrayList list where we are going to add all race drawn.
	 * @param typeReq       an integer value that distinguish which request client
	 *                      has sent to the server.
	 * @return It return an array list that contains all race drawn.
	 */
	public ArrayList<Race> getRacesAndWinners(ArrayList<Race> raceArrayList, int typeReq) {
		try (Connection conn = Connessione.getConnection(); Statement stmt = conn.createStatement();) {
			ResultSet rset = null;
			String winnerName = "";
			int idWinner = -1;
			ResultSet rSet2 = null;
			String strSelect = "select race.idRace,raceName,price,place,date,winner from race";
			rset = stmt.executeQuery(strSelect);

			while (rset.next()) {

				int idRace = rset.getInt("idRace");
				String raceName = rset.getString("raceName");
				int price = rset.getInt("price");
				String place = rset.getString("place");
				int winner = rset.getInt("winner");
				Date date = rset.getDate("date");
				if (winner >= 0) {

					winnerName = getWinnerName(winner);
					idWinner = getIdWinner(winner);
				} else {
					idWinner = -1;
					winnerName = "No winner anymore ";
				}

				System.out.println(idRace + " " + raceName + " " + place);

				raceArrayList.add(new Race(idRace, raceName, price, place, date, idWinner, winnerName));
			}
			conn.close();
			return raceArrayList;
		} catch (

		SQLException e) {
			System.out.println("connessione non avvenuta");
			e.printStackTrace();
			return raceArrayList;
		}
	}

	private int getIdWinner(int winner) {
		try (Connection conn = Connessione.getConnection(); Statement stmt = conn.createStatement();) {
			// ResultSet rSet=null;
			String strSelect = "select id from members where id='" + winner + "'";
			ResultSet rSet2 = stmt.executeQuery(strSelect);
			while (rSet2.next()) {
//				String winnerName = rSet2.getString("username");
				int idWinner = rSet2.getInt("id");
				return idWinner;
			}
			return -1;
		} catch (SQLException e) {
			System.out.println("connessione non avvenuta");
			e.printStackTrace();
			return -1;
		}
	}

	private String getWinnerName(int winner) {
		try (Connection conn = Connessione.getConnection(); Statement stmt = conn.createStatement();) {
			// ResultSet rSet=null;
			String strSelect = "select username from members where id='" + winner + "'";
			ResultSet rSet2 = stmt.executeQuery(strSelect);
			while (rSet2.next()) {
				String winnerName = rSet2.getString("username");
//				int idWinner = rSet2.getInt("id");
				return winnerName;
			}
			return "";
		} catch (SQLException e) {
			System.out.println("connessione non avvenuta");
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * This method is used to modify some data about a race in the database.
	 * 
	 * @param rq object that contains new data, that we have to insert in the
	 *           database and the id of the race, who's data have to be changed.
	 * @return It return an integer value:<br>
	 *         1)positive=Data changed correctly.<br>
	 *         2)negative=Data not changed correctly.
	 */
	public int modifyARace(RequestStaffRace rq) {
		try (Connection conn = Connessione.getConnection(); Statement stmt = conn.createStatement();) {
			String strUpdate = "update race set place = '" + rq.getRacePlace() + "'where idRace = '" + rq.getIdRace()
					+ "'";
			stmt.executeUpdate(strUpdate);
			strUpdate = "update race set date = '" + rq.getRaceDate() + "'where idRace = '" + rq.getIdRace() + "'";
			stmt.executeUpdate(strUpdate);
			conn.close();
			return 1;
		} catch (SQLException e) {
			System.out.println("connessione non avvenuta");
			e.printStackTrace();
			return -1;
		}
	}

	/**
	 * This method is used to simulated a race and proclame a winner.
	 * 
	 * @param rq object that contains data of the race we have to simulate.
	 * @return It return an integer value:<br>
	 *         1)positive=Race simulated correctly.<br>
	 *         2)negative=Race not simulated correctly.
	 */
	public int simulateARace(RequestStaffRace rq) {
		try (Connection conn = Connessione.getConnection(); Statement stmt = conn.createStatement();) {
			System.out.println("sono nella funzione");
			ArrayList<Integer> players = new ArrayList<>();
			String strSelect = "select racepartecipants.idMember from racepartecipants where racepartecipants.idRace ='"
					+ rq.getIdRace() + "' ";
			ResultSet rset = stmt.executeQuery(strSelect);

			while (rset.next()) {
				int player = rset.getInt("idMember");
				players.add(player);
				System.out.println("players: " + player);
			}

			Random rand = new Random();
			System.out.println("players size:" + players.size());
			int casuale = rand.nextInt(players.size());
			System.out.println(casuale);
			int winner = players.get(casuale);
			String strUpdate = "update race set winner = '" + winner + "'where idRace = '" + rq.getIdRace() + "'";
			stmt.executeUpdate(strUpdate);
			strSelect = "select racepartecipants.idBoat from racepartecipants where racepartecipants.idRace ='"
					+ rq.getIdRace() + "' ";
			ResultSet rset3 = stmt.executeQuery(strSelect);
			int idImbarcazione = 0;
			while (rset3.next()) {

				idImbarcazione = rset3.getInt("idBoat");
				System.out.println(idImbarcazione);
				refresh(idImbarcazione);

			}
			strUpdate = "update race set ended = '" + "si" + "'where idRace = '" + rq.getIdRace() + "'";
			stmt.executeUpdate(strUpdate);

//				 strSelect = "select max(id) as id from othernotification";
//			  	int maxId=0;
//		        ResultSet rset2 = stmt.executeQuery(strSelect);
//		   
//		        while (rset2.next())
//		        {
//		        	maxId= rset2.getInt("id");
//		        	maxId++;
//		        }
//				 String insertSql = "insert into othernotification values (?,?,?)";
//				 PreparedStatement pstmt2 = conn.prepareStatement(insertSql);
//			        pstmt2.setString(1, Integer.toString(maxId));
//			        pstmt2.setString(2,Integer.toString(winner));
//			        String text="complimenti hai vinto la gara"+rq.getRaceName()+" che si svolgeva a"+rq.getRacePlace();
//			        pstmt2.setString(3,text);
//			        pstmt2.addBatch();
//			        pstmt2.executeBatch();
			conn.close();
			return winner;

		} catch (SQLException e) {
			System.out.println("connessione non avvenuta");
			e.printStackTrace();
			return -1;
		}

	}

	private int refresh(int idImbarcazione) {
		try (Connection conn = Connessione.getConnection(); Statement stmt = conn.createStatement();) {
			// ResultSet rSet=null;
			String strUpdate2 = "update boat set assigned = '" + "no" + "'where idBoat = '" + idImbarcazione + "'";
			stmt.executeUpdate(strUpdate2);

			return 1;
		} catch (SQLException e) {
			System.out.println("connessione non avvenuta");
			e.printStackTrace();
			return -1;
		}
	}

	/**
	 * This method is used send a notification to users that haven't paied their
	 * fees yet.
	 * 
	 * @param rq object that contains data of the fee and the id of the member who's
	 *           notification is directed to.
	 * @return It return an integer value:<br>
	 *         1)positive=Notification sent correctly.<br>
	 *         2)negative=Notification not sent correctly.
	 */
	public int sendFeeNotification(RequestStaffFeeNotification rq) {
		try (Connection conn = Connessione.getConnection(); Statement stmt = conn.createStatement();) {
			int maxIdNotification = 0;
			String strSelect = "select max(id) as id from notification";

			ResultSet rset = stmt.executeQuery(strSelect);

			while (rset.next()) {
				maxIdNotification = rset.getInt("id");
				maxIdNotification++;
			}
			if (rq.getTypeReq() == NotificationStaffEnum.SEND_MEMBERSHIP_NOTIFICATION.getTypeReq()) {

				System.out.println("sono nella funzione");
				String insertSql = "insert into notification values (?,?,?,?,?,?)";
				PreparedStatement pstmt2 = conn.prepareStatement(insertSql);
				pstmt2.setString(1, Integer.toString(maxIdNotification));
				pstmt2.setString(2, "membership");
				pstmt2.setString(3, Integer.toString(rq.getPrice()));
				pstmt2.setString(4, Integer.toString(rq.getIdmember()));
				pstmt2.setDate(5, rq.getDate());
				pstmt2.setString(6, "pay membership fee");
				pstmt2.addBatch();
				pstmt2.executeBatch();

			} else {
				System.out.println("sono nella funzione");
				String insertSql = "insert into notification values (?,?,?,?,?,?)";
				PreparedStatement pstmt2 = conn.prepareStatement(insertSql);
				pstmt2.setString(1, Integer.toString(maxIdNotification));
				pstmt2.setString(2, "storage");
				pstmt2.setString(3, Integer.toString(rq.getPrice()));
				pstmt2.setString(4, Integer.toString(rq.getIdmember()));
				pstmt2.setDate(5, rq.getDate());
				pstmt2.setString(6, "pay storage fee");
				pstmt2.addBatch();
				pstmt2.executeBatch();
			}
			conn.close();
			return 1;
		} catch (SQLException e) {
			System.out.println("connessione non avvenuta");
			e.printStackTrace();
			return -1;
		}
	}
}
