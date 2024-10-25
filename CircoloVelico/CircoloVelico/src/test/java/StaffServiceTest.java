package test.java;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import org.junit.*;
import org.junit.jupiter.api.Disabled;

import enumClass.RaceStaffEnum;
import javaClass.Connessione;

import javaClass.FeePayment;
import javaClass.Race;
import staff.unipr.it.StaffService;

/**
 * The {@code StaffServiceTest } class is used to test some method of {@code StaffService } class.
 * 
 * @author Spagni Alex Zuelli Arianna
 *
 */

public class StaffServiceTest {
	private static StaffService staffService=new StaffService();
	//Testing adding a new race
	@Disabled
	/**
	 *  This test is used to test if a new race is inserted correctly.
	 */
	public void addARaceTest() {
		int typeReq=RaceStaffEnum.ADD_RACE.getTypeReq(),price=150;
		String raceName="quinta gara",racePlace="forte dei marmi";
		Date raceDate= java.sql.Date.valueOf(LocalDate.now());
		staffService.addARace(typeReq, raceName, raceDate, price, racePlace);
		int idGara=getIdLastRace();
		Race race=getLastRace(idGara);
		assertAll(() -> assertEquals(price, race.getPrice()),
				() -> assertEquals(raceName, race.getRaceName()),
				() -> assertEquals(racePlace,race.getPlace()),
				()->assertEquals(raceDate,race.getDate() ));
	}
	private int getIdLastRace() {
		try (Connection conn = Connessione.getConnection(); Statement stmt = conn.createStatement();) {
			String strSelect = "select max(idGara) as id from race";
			ResultSet rset = stmt.executeQuery(strSelect);

			int maxid = 0;
			while (rset.next()) {
				maxid = rset.getInt("id");

			}
			return maxid;
		} catch (SQLException e) {
			System.out.println("connessione non avvenuta sono qui");
			e.printStackTrace();
			return -1;
		}
	}

	private Race getLastRace(int id) {
		try (Connection conn = Connessione.getConnection(); Statement stmt = conn.createStatement();) {
			String strSelect = "select idGara,nomeGara,prezzo,luogo,data from race where idGara='"
					+ id + "'";
			ResultSet rset = stmt.executeQuery(strSelect);
			int idGara = -1, price=-1;
			String nomeGara="",place="";
			Race race = null;
			while (rset.next()) {
				idGara = rset.getInt("idGara");
				nomeGara = rset.getString("nomeGara");
				price = rset.getInt("prezzo");
				place = rset.getString("luogo");
				Date date=rset.getDate("data");
				race = new Race(idGara, nomeGara, price, place, date);
				return race;
			}
			return race;
		} catch (SQLException e) {
			System.out.println("connessione non avvenuta sono in questo punto");
			e.printStackTrace();
			Race race = new Race();
			return race;
		}
	}
	//Testing insert of a new fee
	@Test
	/**
	 *  This test is used to test id a membership fee is inserted correctly.
	 */
	public void addUserMustPayMembershipFee() {
		int typeRequest=1, idmember=0,price=100;
		String paied="no";
		Date date= java.sql.Date.valueOf(LocalDate.now());
		//RequeststaffAddPayments rq=new RequeststaffAddPayments(typeRequest, price, idmember, date);
		staffService.addUserMustPayMembershipFee(typeRequest, idmember, date, price, paied);
		int idFee=getIdLastFee();
		FeePayment fee=getLastFee(idFee);
		assertAll(() -> assertEquals(price,fee.getPrice() ),
				() -> assertEquals(idmember,fee.getIdmember()),
				() -> assertEquals(date,fee.getDate()));
	}
	private int getIdLastFee() {
		try (Connection conn = Connessione.getConnection(); Statement stmt = conn.createStatement();) {
			String strSelect = "select max(id) as id from membershippayment";
			ResultSet rset = stmt.executeQuery(strSelect);

			int maxid = 0;
			while (rset.next()) {
				maxid = rset.getInt("id");

			}
			return maxid;
		} catch (SQLException e) {
			System.out.println("connessione non avvenuta sono qui");
			e.printStackTrace();
			return -1;
		}
	}
	private FeePayment getLastFee(int id) {
		try (Connection conn = Connessione.getConnection(); Statement stmt = conn.createStatement();) {
			String strSelect = "select id,idmember,price,paied,data from membershippayment where id='"
					+ id + "'";
			ResultSet rset = stmt.executeQuery(strSelect);
			int idFee = -1, idmember = -1,  price=-1;
			String paied="";
			FeePayment fee = null;
			while (rset.next()) {
				idFee=rset.getInt("id");
				idmember = rset.getInt("idmember");
				price = rset.getInt("price");
				paied = rset.getString("paied");
				Date date=rset.getDate("data");
				fee = new FeePayment(idFee, idmember, price, date, paied);
				return fee;
			}
			return fee;
		} catch (SQLException e) {
			System.out.println("connessione non avvenuta sono in questo punto");
			e.printStackTrace();
			FeePayment fee = new FeePayment();
			return fee;
		}
	}
}