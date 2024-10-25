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

import enumClass.BoatMemberEnum;
import enumClass.FeeMemberEnum;
import enumClass.RaceMemberEnum;
import javaClass.Connessione;

import javaClass.FeePayment;
import javaClass.Boat;
import javaClass.Notification;
import javaClass.Race;
import requestMembers.RequestBoatMember;
import requestMembers.RequestPaymentFeeMember;
import requestMembers.RequestSignMember;

/**
 * The {@code ServerServiceMember} class contains all method that a member needs
 * to use to get access to database. For example here there's a method that user
 * user to store his information into the database when he sign in, contains a
 * method that user needs to excract all payment that he has to make.
 * 
 * @author Spagni Alex Zuelli Arianna
 * 
 */
public class ServerServiceMember {
	private static final String DBURL = "jdbc:mysql://localhost:3306/circolovelico";
	private static final String LOGIN = "root";
	private static final String PASSWORD = "";
//	private final Constant constant = new Constant();

	/**
	 * Class constructor.
	 */
	public ServerServiceMember() {

	}

	/**
	 * This method is used to check if username and password insert from the user
	 * are correct or not.
	 * 
	 * @param rq an object that contains username and password to check.
	 * @return It return an integer value:<br>
	 *         1)positive=Username and password correct.<br>
	 *         2)negative=Username and password not correct.
	 */
	public int signUpMembers(RequestSignMember rq) {
		try (Connection conn = Connessione.getConnection(); Statement stmt = conn.createStatement();) {
			String strSelect = "select id,username,password FROM `members`";
			ResultSet rset = stmt.executeQuery(strSelect);
			System.out.println(rq.getUsername() + " " + rq.getPassword());
			while (rset.next()) {
				String username = rset.getString("username");
				String password = rset.getString("password");
				int id = rset.getInt("id");
				// System.out.println(username+" "+password);
				if (rq.getUsername().equals(username) && rq.getPassword().equals(password)) {
					// System.out.println("sono qui");
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
	 * This method is used to store username,password,fiscal code and address of a
	 * new user.
	 * 
	 * @param rq an object that contains username,password,address and fiscal code.
	 * @return It return an integer value:<br>
	 *         1)positive=All data stored correctly.<br>
	 *         2)negative=Data not stored.
	 */
	public int signInMembers(RequestSignMember rq) {
		try (Connection conn = Connessione.getConnection(); Statement stmt = conn.createStatement();) {
			String strSelect = "select id,username FROM `members`";
			ResultSet rset2 = stmt.executeQuery(strSelect);
			while (rset2.next()) {
				String username = rset2.getString("username");
				if (rq.getUsername().equals(username)) {
					// System.out.println("sono qui");
					conn.close();
					return -1;
				}
			}
			strSelect = "select max(id) as id from members";

			ResultSet rset = stmt.executeQuery(strSelect);
			int maxid = 0;
			while (rset.next()) {
				maxid = rset.getInt("id");
				maxid++;

			}
			System.out.println("max id è " + maxid);
			String insertSql = "insert into members values (?,?,?,?,?)";

			PreparedStatement pstmt = conn.prepareStatement(insertSql);
			pstmt.setString(1, Integer.toString(maxid));
			pstmt.setString(2, rq.getUsername());
			pstmt.setString(3, rq.getPassword());
			pstmt.setString(4, rq.getAddress());
			pstmt.setString(5, rq.getFiscalCode());
			pstmt.addBatch();

			pstmt.executeBatch();
			conn.close();
			return maxid;
		} catch (SQLException e) {
			System.out.println("connessione non avvenuta");
			e.printStackTrace();
			return -1;

		}
	}

	/**
	 * This method is used to store a new boat.
	 * 
	 * @param rq object that contains lenght, id of the boat, id of member who wants
	 *           to add a boat and name of the boat.
	 * @return It return an integer value:<br>
	 *         1)positive=New boat stored correctly.<br>
	 *         2)negative=New boat not stored.
	 */
	public int addABoat(RequestBoatMember rq) {
		try (Connection conn = Connessione.getConnection(); Statement stmt = conn.createStatement();) {
			String strSelect = "select name FROM boat";
			ResultSet rset2 = stmt.executeQuery(strSelect);
			while (rset2.next()) {
				String nome = rset2.getString("name");
				// System.out.println(rq.getBoatName());
				if (rq.getBoatName().equals(nome)) {
					// System.out.println("sono qui dentro");
					return -1;
				}

			}
			strSelect = "select max(idBoat) as id from boat";

			ResultSet rset = stmt.executeQuery(strSelect);
			int maxid = 0;
			while (rset.next()) {
				maxid = rset.getInt("id");
				maxid++;

			}
			String insertSql = "insert into boat values (?,?,?,?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(insertSql);
			pstmt.setString(1, Integer.toString(maxid));
			pstmt.setString(2, rq.getBoatName());
			pstmt.setString(3, Integer.toString(rq.getBoatLenght()));
			pstmt.setString(4, Integer.toString(rq.getIdmember()));
			pstmt.setString(5, "no");
			pstmt.setString(6, "si");
			pstmt.addBatch();
			pstmt.executeBatch();
			conn.close();
			return 1;
		} catch (SQLException e) {
			System.out.println("connessione non avvenuta");
			e.printStackTrace();
			return -1;
		}
	}

	/**
	 * This method is used to fetch all boat related a specific user.
	 * 
	 * @param idMember      id of the Member who boats we want to extract.
	 * @param boatArrayList list where we will go to add all boat extracted.
	 * @param typeReq       an integer value that distinguish which request client
	 *                      has sent to the server.
	 * @return It return an array list with all boats fetched.
	 */
	public ArrayList<Boat> getBoat(int idMember, ArrayList<Boat> boatArrayList, int typeReq) {
		try (Connection conn = Connessione.getConnection(); Statement stmt = conn.createStatement();) {
			ResultSet rset = null;
			if (typeReq == BoatMemberEnum.GET_BOAT.getTypeReq()) {
				System.out.println("id member: " + idMember);
				String strSelect = "select idBoat,name,length,idMember FROM `boat` where idMember = '" + idMember + "'";
				rset = stmt.executeQuery(strSelect);
			} else {
				String strSelect = "select idBoat,name,length,idMember FROM `boat` where idMember = '" + idMember
						+ "'and assigned='" + "no" + "'";
				rset = stmt.executeQuery(strSelect);
			}
			while (rset.next()) {
				int idBoat = rset.getInt("idBoat");
				String name = rset.getString("name");
				int length = rset.getInt("length");
				int id = rset.getInt("idMember");

				boatArrayList.add(new Boat(name, idBoat, id, length));
			}
			conn.close();
			return boatArrayList;
		} catch (SQLException e) {
			System.out.println("connessione non avvenuta");
			e.printStackTrace();
			return boatArrayList;
		}
	}

	/**
	 * This method is used to remove a specific boat from the database.
	 * 
	 * @param idBoat id of the boat we want to remove.
	 * @return It return an integer value:<br>
	 *         1)positive=Boat removed.<br>
	 *         2)negative=Boat not removed.
	 */
	public int removeABoat(int idBoat) {
		try (Connection conn = Connessione.getConnection(); Statement stmt = conn.createStatement();) {
			// System.out.println("id member: " + idBoat);
			String strSelect = "DELETE FROM boat WHERE boat.idBoat=?";
			PreparedStatement pstmt = conn.prepareStatement(strSelect);
			pstmt.setInt(1, idBoat);
			pstmt.execute();
			strSelect = "DELETE FROM racepartecipants WHERE idBoat=?";
			PreparedStatement pstmt2 = conn.prepareStatement(strSelect);
			pstmt2.setInt(1, idBoat);
			boolean ris = pstmt2.execute();
			conn.close();
			return 1;
		} catch (SQLException e) {
			System.out.println("connessione non avvenuta");
			e.printStackTrace();
			return -1;
		}
	}

	/**
	 * This method is used to fetch all Notification related to a user.
	 * 
	 * @param idMember               id of the Member, we want to extract
	 *                               notification.
	 * @param notificationsArrayList list where we are going to add all notification
	 *                               extracted.
	 * @return It return an array list of that contains all notification extracted.
	 */
	public ArrayList<Notification> getNotification(int idMember, ArrayList<Notification> notificationsArrayList) {
		try (Connection conn = Connessione.getConnection(); Statement stmt = conn.createStatement();) {

			System.out.println("id member: " + idMember);
			String strSelect = "select id,feeType,price,date FROM notification where idMember = '" + idMember + "'";
			ResultSet rset = stmt.executeQuery(strSelect);
			while (rset.next()) {
				int id = rset.getInt("id");
				String feeType = rset.getString("feeType");
				int price = rset.getInt("price");
				Date date = rset.getDate("date");
				notificationsArrayList.add(new Notification(id, feeType, price, idMember, date));
			}
			conn.close();
			return notificationsArrayList;
		} catch (SQLException e) {
			System.out.println("connessione non avvenuta");
			e.printStackTrace();
			return notificationsArrayList;
		}
	}

	/**
	 * This method is used to remove a notification related to a user.
	 * 
	 * @param idNotification id that identify univocally a notification
	 * @return It return an integer value:<br>
	 *         1)positive=notification removed.<br>
	 *         2)negative=Notification not removed.
	 */
	public int removeNotification(int idNotification) {
		try (Connection conn = Connessione.getConnection(); Statement stmt = conn.createStatement();) {
			String strSelect = "DELETE FROM notification WHERE notification.id=?";
			PreparedStatement pstmt = conn.prepareStatement(strSelect);
			pstmt.setInt(1, idNotification);
			pstmt.execute();
			conn.close();
			return 1;

		} catch (SQLException e) {
			System.out.println("connessione non avvenuta");
			e.printStackTrace();
			return -1;
		}
	}

	/**
	 * This method is used to fetch all membershipFee related to a user.
	 * 
	 * @param idMember         id of a member that identifity a member univocally
	 * @param paymentArrayList list where we are going to add all membership fee
	 *                         extracted.s
	 * @return It return an array list of that contains all membership fee
	 *         extracted.
	 */
	public ArrayList<FeePayment> getFeeMembership(int idMember, ArrayList<FeePayment> paymentArrayList) {
		try (Connection conn = Connessione.getConnection(); Statement stmt = conn.createStatement();) {

			System.out.println("id member: " + idMember);
			String strSelect = "select id,date,price,idMember FROM membershippayment where idMember = '" + idMember
					+ "' and paied = '" + "no" + "'";
			ResultSet rset = stmt.executeQuery(strSelect);
			while (rset.next()) {

				int idFee = rset.getInt("id");
				Date date = rset.getDate("date");
				int price = rset.getInt("price");
				System.out.println(idFee + " " + date + " " + price);

				paymentArrayList.add(new FeePayment(idFee, idMember, price, date));
			}
			conn.close();
			return paymentArrayList;
		} catch (SQLException e) {
			System.out.println("connessione non avvenuta");
			e.printStackTrace();
			return paymentArrayList;
		}
	}

	/**
	 * This method is used to fetch all storage fee related to a user.
	 * 
	 * @param idMember         id of a member that identifity a member univocally
	 * @param paymentArrayList list where we are going to add all storage fee
	 *                         extracted.
	 * @return It return an array list of that contains all storage fee extracted.
	 */
	public ArrayList<FeePayment> getFeeStorage(int idMember, ArrayList<FeePayment> paymentArrayList) {
		try (Connection conn = Connessione.getConnection(); Statement stmt = conn.createStatement();) {

			System.out.println("id member: " + idMember);
			String strSelect = "select id,date,price,idMember FROM storagepayment where idMember = '" + idMember
					+ "' and paied = '" + "no" + "'";
			ResultSet rset = stmt.executeQuery(strSelect);
			while (rset.next()) {

				int idFee = rset.getInt("id");
				Date date = rset.getDate("date");
				int price = rset.getInt("price");
				System.out.println(idFee + " " + date + " " + price);

				paymentArrayList.add(new FeePayment(idFee, idMember, price, date));
			}
			conn.close();
			return paymentArrayList;
		} catch (SQLException e) {
			System.out.println("connessione non avvenuta");
			e.printStackTrace();
			return paymentArrayList;
		}
	}

	/**
	 * This method is used to add a race fee into the database.This fee will be
	 * related to a user.
	 * 
	 * @param rq contains all data about the fee that we are going to store.
	 * @return It return an integer value: <br>
	 *         1)positive=Fee added.<br>
	 *         2)negative=Fee not added.
	 */
	public int insertRaceFee(RequestPaymentFeeMember rq) {
		try (Connection conn = Connessione.getConnection(); Statement stmt = conn.createStatement();) {
			int maxId = 0;
			String strSelect2 = "select max(id) as id from racefee";
			ResultSet rset = stmt.executeQuery(strSelect2);
			while (rset.next()) {
				maxId = rset.getInt("id");
				maxId++;

			}
			String insertSql = "insert into racefee values (?,?,?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(insertSql);
			pstmt.setString(1, Integer.toString(maxId));
			LocalDate data = LocalDate.now();
			pstmt.setDate(2, java.sql.Date.valueOf(data));
			pstmt.setString(3, Integer.toString(rq.getPrice()));
			pstmt.setString(4, Integer.toString(rq.getIdmember()));
			pstmt.setString(5, "si");
			pstmt.addBatch();
			pstmt.executeBatch();
			conn.close();
			return maxId;
		} catch (SQLException e) {
			System.out.println("connessione non avvenuta");
			e.printStackTrace();
			return -1;
		}
	}

	/**
	 * This method is used from a member to pay membership or storage fee.
	 * 
	 * @param id          this is the id of the fee that a member wants to pay
	 * @param typeReq     an integer value that distinguish which request client has
	 *                    sent to the server.
	 * @param paymentType a string value that say which payment method a member
	 *                    used.
	 * @return It return an integer value:<br>
	 *         1)positive=Fee paied.<br>
	 *         2)negative=Fee not paied.
	 */
	public int payFeeMember(int id, int typeReq, int paymentType) {
		try (Connection conn = Connessione.getConnection(); Statement stmt = conn.createStatement();) {
			// System.out.println("id : " + id);
			if (typeReq == FeeMemberEnum.PAY_FEE_MEMBERSHIP.getTypeReq()) {

				String strUpdate = "update membershippayment set paied = '" + "si" + "' where id = '" + id + "'";
				int check = stmt.executeUpdate(strUpdate);
				if (check == 0) {
					return -1;
				}
//				 String strSelect="DELETE FROM notification WHERE feeType=? and idFee=?";
//				 PreparedStatement pstmt = conn.prepareStatement(strSelect);
//				 pstmt.setString(1, "membership");
//				 pstmt.setInt(2, id);
//				 pstmt.execute();

				if (paymentType == 0) {// allora bonifico
					addFeeToBonifico(id, typeReq);
				} else {// credit card
					addFeeToCreditCard(id, typeReq);
				}
				conn.close();
				return 1;
			} else if (typeReq == FeeMemberEnum.PAY_FEE_STORAGE.getTypeReq()) {
				String strUpdate = "update storagepayment set paied = '" + "si" + "' where id = '" + id + "'";
				stmt.executeUpdate(strUpdate);
//				 String strSelect="DELETE FROM notification WHERE feeType=? and idFee=?";
//				 PreparedStatement pstmt = conn.prepareStatement(strSelect);
//				 pstmt.setString(1, "storage");
//				 pstmt.setInt(2, id);
//				 pstmt.execute();

				if (paymentType == 0) {// allora bonifico
					addFeeToBonifico(id, typeReq);
				} else {
					addFeeToCreditCard(id, typeReq);
				}

			} else {

				if (paymentType == 0) {// allora bonifico
					addFeeToBonifico(id, typeReq);
				} else {
					addFeeToCreditCard(id, typeReq);
				}
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
	 * This method is used to add the fee paied by the user (Membership,Storage or
	 * Race) to bonifico table in the database.
	 * 
	 * @param id      id of the fee paied.
	 * @param typeReq an integer value that distinguish which request client has
	 *                sent to the server.
	 */
	public void addFeeToBonifico(int id, int typeReq) {
		try (Connection conn = Connessione.getConnection(); Statement stmt = conn.createStatement();) {
			Date date = null;
			int idFee = -1, price = -1, idmember = -1, maxId = 0;
			String feeType = "", paied = "";
			ResultSet rset;
			if (typeReq == FeeMemberEnum.PAY_FEE_MEMBERSHIP.getTypeReq()) {
				String strSelect2 = "select max(id) as id from transfer";

				rset = stmt.executeQuery(strSelect2);
				while (rset.next()) {
					maxId = rset.getInt("id");
					maxId++;

				}
				String strSelect = "select id,paied,date,price,idMember FROM membershippayment where id = '" + id + "'";
				rset = stmt.executeQuery(strSelect);
				feeType = "membership";
			} else if (typeReq == FeeMemberEnum.PAY_FEE_STORAGE.getTypeReq()) {
				String strSelect2 = "select max(id) as id from transfer";

				rset = stmt.executeQuery(strSelect2);
				while (rset.next()) {
					maxId = rset.getInt("id");
					maxId++;

				}
				String strSelect = "select id,paied,date,price,idMember FROM storagepayment where id = '" + id + "'";
				rset = stmt.executeQuery(strSelect);
				feeType = "storage";
			} else {
				String strSelect2 = "select max(id) as id from transfer";

				rset = stmt.executeQuery(strSelect2);
				while (rset.next()) {
					maxId = rset.getInt("id");
					maxId++;

				}
				String strSelect = "select id,paied,date,price,idMember FROM racefee where id = '" + id + "'";
				rset = stmt.executeQuery(strSelect);
				feeType = "feeRace";
			}

			while (rset.next()) {

				idFee = rset.getInt("id");
				date = rset.getDate("date");
				price = rset.getInt("price");
				paied = rset.getString("paied");
				idmember = rset.getInt("idMember");

				System.out.println(idFee + " " + date + " " + price);

			}

			String insertSql = "insert into transfer values (?,?,?,?,?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(insertSql);
			pstmt.setString(1, Integer.toString(maxId));
			pstmt.setString(2, Integer.toString(idFee));
			pstmt.setDate(3, date);
			pstmt.setString(4, Integer.toString(price));
			pstmt.setString(5, Integer.toString(idmember));
			pstmt.setString(6, paied);
			pstmt.setString(7, feeType);
			pstmt.addBatch();
			pstmt.executeBatch();

		} catch (SQLException e) {
			System.out.println("connessione non avvenuta");
			e.printStackTrace();

		}
	}

	/**
	 * This method is used to add the fee paied by the user (Membership,Storage or
	 * Race) to creditCard table in the database.
	 * 
	 * @param id      id of the fee paied.
	 * @param typeReq an integer value that distinguish which request client has
	 *                sent to the server.
	 */
	public void addFeeToCreditCard(int id, int typeReq) {
		try (Connection conn = Connessione.getConnection(); Statement stmt = conn.createStatement();) {
			Date date = null;
			int idFee = -1, price = -1, idmember = -1, maxId = 0;
			String feeType = "", paied = "";
			ResultSet rset;
			if (typeReq == FeeMemberEnum.PAY_FEE_MEMBERSHIP.getTypeReq()) {
				String strSelect2 = "select max(id) as id from creditcard";
				rset = stmt.executeQuery(strSelect2);
				while (rset.next()) {
					maxId = rset.getInt("id");
					maxId++;

				}

				String strSelect = "select id,paied,date,price,idMember FROM membershippayment where id = '" + id + "'";
				rset = stmt.executeQuery(strSelect);
				feeType = "membership";
			} else if (typeReq == FeeMemberEnum.PAY_FEE_STORAGE.getTypeReq()) {
				String strSelect2 = "select max(id) as id from creditcard";
				rset = stmt.executeQuery(strSelect2);
				while (rset.next()) {
					maxId = rset.getInt("id");
					maxId++;

				}

				String strSelect = "select id,paied,date,price,idMember FROM storagepayment where id = '" + id + "'";
				rset = stmt.executeQuery(strSelect);
				feeType = "storage";
			}

			else {
				String strSelect2 = "select max(id) as id from creditcard";
				rset = stmt.executeQuery(strSelect2);
				while (rset.next()) {
					maxId = rset.getInt("id");
					maxId++;

				}

				String strSelect = "select id,paied,date,price,idMember FROM racefee where id = '" + id + "'";
				rset = stmt.executeQuery(strSelect);
				feeType = "feeRace";
			}
			while (rset.next()) {

				idFee = rset.getInt("id");
				date = rset.getDate("date");
				price = rset.getInt("price");
				paied = rset.getString("paied");
				idmember = rset.getInt("idmember");
				System.out.println(idFee + " " + date + " " + price);

			}
			String insertSql = "insert into creditCard values (?,?,?,?,?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(insertSql);
			pstmt.setString(1, Integer.toString(maxId));
			pstmt.setString(2, Integer.toString(idFee));
			pstmt.setDate(3, date);
			pstmt.setString(4, Integer.toString(price));
			pstmt.setString(5, Integer.toString(idmember));
			pstmt.setString(6, paied);
			pstmt.setString(7, feeType);
			pstmt.addBatch();
			pstmt.executeBatch();
		} catch (SQLException e) {
			System.out.println("connessione non avvenuta");
			e.printStackTrace();

		}
	}

	/**
	 * This method is used to fetch race from the database. Different races will be
	 * drawn based on the type of request.
	 * 
	 * @param idMember      id of the member we want to drawn races.
	 * @param raceArrayList list where we are going to add race that we drawn from
	 *                      the database.
	 * @param typeReq       an integer value that distinguish which request client
	 *                      has sent to the server.
	 * @return It return an array that contain all the race drawn from the database.
	 */
	public ArrayList<Race> getRace(int idMember, ArrayList<Race> raceArrayList, int typeReq) {
		try (Connection conn = Connessione.getConnection(); Statement stmt = conn.createStatement();) {
			ResultSet rset = null;
			System.out.println("id member: " + idMember);
			if (typeReq == RaceMemberEnum.GET_RACE.getTypeReq()) {
				String strSelect = "select race.idRace,raceName,price,place,winner,date,idMember,ended FROM race,racepartecipants where racepartecipants.idRace=race.idRace and idMember ='"
						+ idMember + "'";
				rset = stmt.executeQuery(strSelect);
			}
			if (typeReq == RaceMemberEnum.GET_ALL_RACE.getTypeReq()) {
				String strSelect = "select race.idRace,raceName,price,place,winner,date,ended FROM race where date > '"
						+ java.sql.Date.valueOf(LocalDate.now()) + "' and ended='" + "no"
						+ "'and idRace <> all (select race.idRace from race,racepartecipants where race.idRace=racepartecipants.idRace and racepartecipants.idMember='"
						+ idMember + "')";

				rset = stmt.executeQuery(strSelect);
			}
			if (typeReq == RaceMemberEnum.GET_RACE_WON.getTypeReq()) {
				String strSelect = "select race.idRace,raceName,price,place,winner,date,ended from race where winner= '"
						+ idMember + "'";
				rset = stmt.executeQuery(strSelect);
			}
			while (rset.next()) {

				int idGara = rset.getInt("idRace");
				String raceName = rset.getString("raceName");
				int price = rset.getInt("price");
				String place = rset.getString("place");
				int winner = rset.getInt("winner");
				Date date = rset.getDate("date");
				// Date localDate=java.sql.Date.valueOf( LocalDate.now());
				// int ris=date.compareTo(localDate);
				String ended = rset.getString("ended");
//				 if (ris<0) {
//					ended="yes";
//				 }
//				 else {
//					ended="no";
//				}
				System.out.println(idGara + " " + raceName + " " + place);

				raceArrayList.add(new Race(idGara, raceName, price, place, winner, date, ended));
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
	 * This method is used to add a user to a race.
	 * 
	 * @param idMember id that identify the member who wants to join the race.
	 * @param idBoat   id of the boat a member will use for that race.
	 * @param idRace   id of the race, member wants to join.
	 * @return It return an integer value:<br>
	 *         1)positive=member added to the race.<br>
	 *         2)negative=member not added to the race.
	 */
	public int joinARace(int idMember, int idBoat, int idRace) {
		try (Connection conn = Connessione.getConnection(); Statement stmt = conn.createStatement();) {
			ResultSet rset = null;
			int maxId = 0;
			System.out.println("id member: " + idMember);
			String strSelect = "select max(idPartecipant) as id from racepartecipants";
			rset = stmt.executeQuery(strSelect);
			while (rset.next()) {
				maxId = rset.getInt("id");
				maxId++;

			}
			String insertSql = "insert into racepartecipants values (?,?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(insertSql);
			pstmt.setString(1, Integer.toString(maxId));
			pstmt.setString(2, Integer.toString(idRace));
			pstmt.setString(3, Integer.toString(idMember));
			pstmt.setString(4, Integer.toString(idBoat));
			pstmt.addBatch();
			pstmt.executeBatch();
			String strUpdate = "update boat set assigned = '" + "si" + "' where idBoat = '" + idBoat + "'";
			stmt.executeUpdate(strUpdate);
			conn.close();
			return maxId;
		} catch (SQLException e) {
			System.out.println("connessione non avvenuta");
			e.printStackTrace();
			return -1;
		}
	}
}
