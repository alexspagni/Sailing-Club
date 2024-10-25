package test.java;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.junit.*;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import javaClass.Boat;
import javaClass.Connessione;
import requestMembers.RequestBoatMember;
import requestMembers.RequestPaymentFeeMember;
import requestMembers.RequestSignMember;
import server.unipr.it.ServerServiceMember;
import static org.junit.jupiter.api.Assertions.*;
/**
 * The {@code ServerServiceMemberTest } class is used to test some method of {@code ServerServiceMember } class.
 * 
 * @author Spagni Alex Zuelli Arianna
 *
 */

public class ServerServiceMemberTest {

	private static ServerServiceMember serverServicemember = new ServerServiceMember();
	@DisplayName("testing user signed Up")
	@ParameterizedTest
	@CsvSource({ "Alex,pass,0", "paola,pass,11", "francesco,pass,12" })
	@Disabled
	/**
	 * This test is used to test user login.
	 * @param username username of a user.
	 * @param password password of a user.
	 * @param id id of a user.
	 */
	public void testSignUpmember(String username, String password, int id) {
		RequestSignMember rq = new RequestSignMember(username, password, id);
		// It returns the id of a member
		int ris = serverServicemember.signUpMembers(rq);
		assertEquals(id, ris);
	}

	@DisplayName("testing user signed In")
	@ParameterizedTest
	@CsvSource({ "Arianna,pass,via verdi,12121212,14", "Daniele,password,via franchi,13131313,15",
			"Riccardo,Ricarburami,via giannini,114141414,16" })
	@Disabled
	/**
	 * This test is used to test user registration.
	 * @param username username of a user.
	 * @param password password of a user.
	 * @param address address of a user.
	 * @param fiscalCode fiscalCode of a user.
	 * @param id id of a user.
	 */
	public void testSignInmember(String username, String password, String address, String fiscalCode, int id) {
		RequestSignMember rq = new RequestSignMember(username, password, 1, address, fiscalCode);
		// It returns the id of a member
		int ris = serverServicemember.signInMembers(rq);
		assertEquals(id, ris);
	}

	@DisplayName("testing add a boat method")
	@ParameterizedTest
	@CsvSource({ "giulia,23,0,0", "anna,12,0,1" })
	@Disabled
	public void testAddABoat(String boatName, int boatLength, int typeReq, int idmember) {
		RequestBoatMember rq = new RequestBoatMember(boatName, boatLength, typeReq, idmember);
		int ris = serverServicemember.addABoat(rq);
		assertEquals(1, ris);
	}

//	@Test
	@Disabled
	/**
	 * This test is used to test is some boat are fetch correctly.
	 */
	public void testGetBoat() {
		ArrayList<Boat> list2 = new ArrayList<>();
		list2.add(new Boat("gialuca", 4, 0, 233));
		list2.add(new Boat("paola", 5, 0, 2300));
		ArrayList<Boat> list1 = new ArrayList<Boat>();
		list1 = serverServicemember.getBoat(0, list1, 1);
		int correct = 0, cont = list1.size();

		for (Boat b : list1) {
			for (Boat b2 : list2) {
				if (b2.getId() == b.getId()) {
					correct = 1;
				}
			}
			if (correct == 0) {
				break;
			}
		}
		int ris = correct;
		assertAll(() -> assertEquals(cont, list2.size()), () -> assertEquals(1, ris));
	}

	@Disabled
	/**
	 *  This test is used to test is some boat is removed correctly.
	 */
	public void testRemoveABoat() {
		int boatRemoved=serverServicemember.removeABoat(1);
		assertEquals(1, boatRemoved);
	}
	@Test
	/**
	 *  This test is used to test if a RaceFee is stored correctly.
	 */
	public void insertFeeTest() {
		RequestPaymentFeeMember rq=new RequestPaymentFeeMember(200, 0, 3, 1);
		int feeId=serverServicemember.insertRaceFee(rq);
		int maxId=getMaxIdFee();
		assertEquals(maxId, feeId);
	}
	private int getMaxIdFee() {
		try (Connection conn = Connessione.getConnection(); Statement stmt = conn.createStatement();) {
			String strSelect = "select max(id) as id from racefee";
		//	PreparedStatement pstmt = conn.prepareStatement(strSelect);
			ResultSet rset=stmt.executeQuery(strSelect);
			
			int maxid = 0;
			while (rset.next()) {
				maxid = rset.getInt("id");

			}
			return maxid;
		} catch (SQLException e) {
			System.out.println("connessione non avvenuta");
			e.printStackTrace();
			return -1;
		}
	}
	
	
}
