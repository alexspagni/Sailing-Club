package test.java;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.*;
import org.junit.jupiter.api.Disabled;

import CircoloVelico.unipr.it.MemberService;
import javaClass.Connessione;
import javaClass.RacePartecipants;
/**
 * The {@code MemberServiceTest } class is used to test some method of {@code MemberServiceTest } class.
 * 
 * @author Spagni Alex Zuelli Arianna
 *
 */
public class MemberServiceTest {
	private static MemberService memberService = new MemberService();
	@Disabled
	/**
	 *  This test is used to test if a fee is paied correctly.
	 */
	public void payFeememberTest() {
		int maxId;
		maxId = getMaxIdMembershipFee();
		int ris2 = memberService.payMembershipFee(maxId, 1, 1);
		maxId++;
		int ris = memberService.payMembershipFee(maxId, 1, 1);
		assertAll(() -> assertEquals(-1, ris), () -> assertEquals(1, ris2));
	}

	private int getMaxIdMembershipFee() {
		try (Connection conn = Connessione.getConnection(); Statement stmt = conn.createStatement();) {
			String strSelect = "select max(id) as id from membershippayment";
			// PreparedStatement pstmt = conn.prepareStatement(strSelect);
			ResultSet rset = stmt.executeQuery(strSelect);

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

	@Test
	/**
	 *  This test is used to test a user can join a race correctly.
	 */
	public void joinARaceTest() {
		int idmember = 0, idGara = 2, idImbarcazione = 3, reqType = 2;
		 memberService.joinARace(idmember, idGara, idImbarcazione, reqType);
		int maxId = getMaxIdRace();
		RacePartecipants racePartecipant = getLastPartecipant(maxId);
		assertAll(() -> assertEquals(idmember, racePartecipant.getIdmember()),
				() -> assertEquals(idGara, racePartecipant.getIdRace()),
				() -> assertEquals(idImbarcazione, racePartecipant.getIdBoat()));
		//assertEquals(maxId, ris);
	}

	private int getMaxIdRace() {
		try (Connection conn = Connessione.getConnection(); Statement stmt = conn.createStatement();) {
			String strSelect = "select max(idPartecipante) as id from racepartecipants";
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

	private RacePartecipants getLastPartecipant(int id) {
		try (Connection conn = Connessione.getConnection(); Statement stmt = conn.createStatement();) {
			String strSelect = "select idPartecipante,idGara,idmember,idImbarcazione from racepartecipants where idPartecipante='"
					+ id + "'";
			ResultSet rset = stmt.executeQuery(strSelect);
			int idGara = -1, idImbarcazione = -1, idmember = -1, idPartecipante = -1;
			RacePartecipants racePartecipants = null;
			while (rset.next()) {
				idPartecipante = rset.getInt("idPartecipante");
				idImbarcazione = rset.getInt("idImbarcazione");
				idGara = rset.getInt("idGara");
				idmember = rset.getInt("idmember");
				RacePartecipants race = new RacePartecipants(idGara, idImbarcazione, idmember, idPartecipante);
				return race;
			}
			return racePartecipants;
		} catch (SQLException e) {
			System.out.println("connessione non avvenuta sono in questo punto");
			e.printStackTrace();
			RacePartecipants racePartecipants = new RacePartecipants();
			return racePartecipants;
		}
	}
}
