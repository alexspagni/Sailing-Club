package CircoloVelico.unipr.it;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * The {@code MemberHomeController} class is used to show to a member a Form a
 * menu bar where he can choose which action he wants to perform.
 * 
 * @author Spagni Alex Zuelli Arianna
 * 
 */
public class MemberHomeController {
	private static Member member;
	@FXML
	private AnchorPane memberHomeAnchorPane;
	private Stage stage;
	private Scene scene;
	private Parent root;

	/**
	 * This function is used by GUI and send user to a form where he can add a new
	 * boat
	 * 
	 * @param e Action Event parameter.
	 * 
	 * @throws IOException passes the generated exception to the calling method.
	 */
	public void addABoat(ActionEvent e) throws IOException {
		MemberAddABoatController.setData(member);
		root = FXMLLoader.load(getClass().getResource("/graficaMemberFXML/MemberAddABoat.fxml"));
		stage = (Stage) memberHomeAnchorPane.getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * This function is used by GUI and send user to a form where he can remove a
	 * boat
	 * 
	 * @param e Action Event parameter.
	 * 
	 * @throws IOException passes the generated exception to the calling method.
	 */
	public void removeABoat(ActionEvent e) throws IOException {
		MemberRemoveABoatController.setData(member);
		root = FXMLLoader.load(getClass().getResource("/graficaMemberFXML/MemberRemoveABoat.fxml"));
		stage = (Stage) memberHomeAnchorPane.getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * This function is used by GUI and send user to a form where he can see if he
	 * has some notification about his fees
	 * 
	 * @param e Action Event parameter.
	 * 
	 * @throws IOException passes the generated exception to the calling method.
	 */
	public void seeYourNotification(ActionEvent e) throws IOException {
		MemberNotificationController.setData(member);
		root = FXMLLoader.load(getClass().getResource("/graficaMemberFXML/MemberNotification.fxml"));
		stage = (Stage) memberHomeAnchorPane.getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * This function is used by GUI and send user to a form where he can pay its
	 * membership fee
	 * 
	 * @param e Action Event parameter.
	 * 
	 * @throws IOException passes the generated exception to the calling method.
	 */
	public void payMemberShipRent(ActionEvent e) throws IOException {
		MemberPayMembershipFeeController.setData(member);
		root = FXMLLoader.load(getClass().getResource("/graficaMemberFXML/MemberPayMembershipFee.fxml"));
		stage = (Stage) memberHomeAnchorPane.getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * This function is used by GUI and send user to a form where he can pay storage
	 * fees for each boat he has
	 * 
	 * @param e Action Event parameter.
	 * 
	 * @throws IOException passes the generated exception to the calling method.
	 */
	public void payStorageRent(ActionEvent e) throws IOException {
		MemberPayStorageFeeController.setData(member);
		root = FXMLLoader.load(getClass().getResource("/graficaMemberFXML/MemberPayStorageFee.fxml"));
		stage = (Stage) memberHomeAnchorPane.getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * This function is used by GUI and send user to a form where he can join a race
	 * with one of his boat avaible
	 * 
	 * @param e Action Event parameter.
	 * 
	 * @throws IOException passes the generated exception to the calling method.
	 */
	public void joinARace(ActionEvent e) throws IOException {
		MemberJoinRaceController.setData(member);
		root = FXMLLoader.load(getClass().getResource("/graficaMemberFXML/MemberJoinRace.fxml"));
		stage = (Stage) memberHomeAnchorPane.getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * This function is used by GUI and send user to a form where he can see all
	 * race he joined
	 * 
	 * @param e Action Event parameter.
	 * 
	 * @throws IOException passes the generated exception to the calling method.
	 */
	public void seeYourRace(ActionEvent e) throws IOException {
		MemberSeeRaceController.setData(member);
		root = FXMLLoader.load(getClass().getResource("/graficaMemberFXML/MemberSeeRace.fxml"));
		stage = (Stage) memberHomeAnchorPane.getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * This function is used by GUI and send user to a form where he can see all
	 * race that he won
	 * 
	 * @param e Action Event parameter.
	 * 
	 * @throws IOException passes the generated exception to the calling method.
	 */
	public void seeRaceWon(ActionEvent e) throws IOException {
		MemberSeeRaceWonController.setData(member);
		root = FXMLLoader.load(getClass().getResource("/graficaMemberFXML/MemberSeeRaceWon.fxml"));
		stage = (Stage) memberHomeAnchorPane.getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * This function is used by GUI and send user to previous form
	 * 
	 * @param event Action Event parameter.
	 * 
	 * @throws IOException passes the generated exception to the calling method.
	 */
	public void backToPrincipalMenu(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("/graficaMemberFXML/Entry.fxml"));
		stage = (Stage) memberHomeAnchorPane.getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * used to keep the id of the member who signed up/in
	 * 
	 * @param s member object
	 */
	public static void setData(Member s) {
		member = s;
	}
}
