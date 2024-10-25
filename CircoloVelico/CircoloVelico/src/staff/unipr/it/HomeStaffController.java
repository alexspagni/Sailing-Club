package staff.unipr.it;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
/**
 * The {@code HomeStaffController} class  is used to show to a Staff member a menuBar where he can choose what he wants to do
 * 
 * @author Spagni Alex Zuelli Arianna
 * 
 */

public class HomeStaffController {
	private static Staff staff;
	@FXML
	private AnchorPane homestaffAnchorPane;
	private Stage stage;
	private Scene scene;
	private Parent root;
	/**
	 * This function is used by GUI and send the staff member to a form where he can see all membership payment made with credit card or bonifico.
	 * @param event Action Event parameter.
	 * 
	 * @throws IOException
	 * passes the generated exception to the calling method.
	 */
	public void seeMembershipPayment (ActionEvent event)  throws IOException{
		 StaffSeeMembershipPaymentController.setData(staff);
		 root = FXMLLoader.load(getClass().getResource("/graficaStaffFXML/StaffSeeMembershipPayment.fxml"));
		  stage = (Stage) homestaffAnchorPane.getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
	}
	/**
	 * This function is used by GUI and send the staff member to a form where he can see all storage payment made with credit card or bonifico.
	 * @param event Action Event parameter.
	 * 
	 * @throws IOException
	 * passes the generated exception to the calling method.
	 */
	public void seeStoragePayment (ActionEvent event)  throws IOException{
		StaffSeeStoragePaymentController.setData(staff);
		 root = FXMLLoader.load(getClass().getResource("/graficaStaffFXML/StaffSeeStoragePayments.fxml"));
		  stage = (Stage) homestaffAnchorPane.getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
	}
	/**
	 * This function is used by GUI and send the staff member to a form where he can see all Race payment made with credit card or bonifico.
	 * @param event Action Event parameter.
	 * 
	 * @throws IOException
	 * passes the generated exception to the calling method.
	 */
	public void seeRacePayment (ActionEvent event)  throws IOException{
		StaffSeeRacePaymentController.setData(staff);
		 root = FXMLLoader.load(getClass().getResource("/graficaStaffFXML/StaffSeeRacePayments.fxml"));
		  stage = (Stage) homestaffAnchorPane.getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
	}
	/**
	 * This function is used by GUI and send the staff member to a form where he can send notification to users who haven't paied Membership fee yet.
	 * @param event Action Event parameter.
	 * @throws IOException
	 * passes the generated exception to the calling method.
	 */
	public void seeNotificationMembership (ActionEvent event)  throws IOException{
		StaffSendMembershipNotification.setData(staff);
		 root = FXMLLoader.load(getClass().getResource("/graficaStaffFXML/StaffSendMembershipNotification.fxml"));
		  stage = (Stage) homestaffAnchorPane.getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
	}
	/**
	 * This function is used by GUI and send the staff member to a form where he can send notification to users who haven't paied Storage fees yet.
	 * @param event Action Event parameter.
	 * 
	 * @throws IOException
	 * passes the generated exception to the calling method.
	 */
	public void seeNotificationStorage(ActionEvent event)  throws IOException{
		StaffSendStorageNotification.setData(staff);
		 root = FXMLLoader.load(getClass().getResource("/graficaStaffFXML/StaffSendStorageNotification.fxml"));
		  stage = (Stage) homestaffAnchorPane.getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
	}
	/**
	 * This function is used by GUI and send the staff member to a form where he can modify some credential of a member choose from him.
	 * @param event Action Event parameter.
	 * @throws IOException
	 * passes the generated exception to the calling method.
	 */
	public void modifyMemberCredential(ActionEvent event)  throws IOException{
		//StaffModifyMemberCredential.setData(staff);
		 root = FXMLLoader.load(getClass().getResource("/graficaStaffFXML/StaffModifyMemberCredential.fxml"));
		  stage = (Stage) homestaffAnchorPane.getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
	}
	/**
	 * This function is used by GUI and send the staff member to a form where he can remove a member.
	 * @param event Action Event parameter.
	 * @throws IOException
	 * passes the generated exception to the calling method.
	 */
	public void removeAMember(ActionEvent event)  throws IOException{
		//StaffModifyMemberCredential.setData(staff);
		 root = FXMLLoader.load(getClass().getResource("/graficaStaffFXML/StaffRemoveAMember.fxml"));
		  stage = (Stage) homestaffAnchorPane.getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
	}
	/**
	 * This function is used by GUI and send the staff member to a form where he can modify some data of a boat choose from him.
	 * @param event Action Event parameter.
	 * 
	 * @throws IOException
	 * passes the generated exception to the calling method.
	 */
	public void modifyBoatData(ActionEvent event)  throws IOException{
		StaffModifyBoatData.setData(staff);
		 root = FXMLLoader.load(getClass().getResource("/graficaStaffFXML/StaffModifyBoatData.fxml"));
		  stage = (Stage) homestaffAnchorPane.getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
	}
	/**
	 * This function is used by GUI and send the staff member to a form where he can add a new race.
	 *  @param event Action Event parameter.
	 * 
	 * @throws IOException
	 * passes the generated exception to the calling method.
	 */
	public void addNewRace(ActionEvent event)  throws IOException{
		StaffAddARaceController.setData(staff);
		 root = FXMLLoader.load(getClass().getResource("/graficaStaffFXML/StaffAddARace.fxml"));
		  stage = (Stage) homestaffAnchorPane.getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
	}
	/**
	 * This function is used by GUI and send the staff member to a form where he can modify some data of a race choose from him.
	 *  @param event Action Event parameter.
	 * 
	 * @throws IOException
	 * passes the generated exception to the calling method.
	 */
	public void modifyARace(ActionEvent event)  throws IOException{
		StafflModifyRaceController.setData(staff);
		 root = FXMLLoader.load(getClass().getResource("/graficaStaffFXML/StaffModifyRace.fxml"));
		  stage = (Stage) homestaffAnchorPane.getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
	}
	/**
	 * This function is used by GUI and send the staff member to a form where he can simulate a race and so proclame a winner.
	 * @param event Action Event parameter.
	 * 
	 * @throws IOException
	 * passes the generated exception to the calling method.
	 */
	public void simulateARace(ActionEvent event)  throws IOException{
		StaffSimulateARaceController.setData(staff);
		 root = FXMLLoader.load(getClass().getResource("/graficaStaffFXML/StaffSimulateARace.fxml"));
		  stage = (Stage) homestaffAnchorPane.getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
	}
	/**
	 * This function is used by GUI and send the staff member to a form where he can add membership fee to the database.
	 * @param event Action Event parameter.
	 * 
	 * @throws IOException
	 * passes the generated exception to the calling method.
	 */
	public void addMembershipPayment(ActionEvent event)  throws IOException{
		StaffAddMembershipFeeController.setData(staff);
		 root = FXMLLoader.load(getClass().getResource("/graficaStaffFXML/StaffAddMembershipFee.fxml"));
		  stage = (Stage) homestaffAnchorPane.getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
	}
	/**
	 * This function is used by GUI and send the staff member to a form where he can add membership fee to the database.
	 * @param event Action Event parameter.
	 * 
	 * @throws IOException
	 * passes the generated exception to the calling method.
	 */
	public void addStoragePayment(ActionEvent event)  throws IOException{
		StaffAddStorageFeeController.setData(staff);
		 root = FXMLLoader.load(getClass().getResource("/graficaStaffFXML/StaffAddStorageFee.fxml"));
		  stage = (Stage) homestaffAnchorPane.getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
	}
	/**
	 * This function is used by GUI and send the staff member to a form where he can see all different race and the winner of those race.
	 * @param event Action Event parameter.
	 * 
	 * @throws IOException
	 * passes the generated exception to the calling method.
	 */
	public void seeAllRace(ActionEvent event)  throws IOException{
		StaffSeeAllRaceController.setData(staff);
		 root = FXMLLoader.load(getClass().getResource("/graficaStaffFXML/StaffSeeAllRace.fxml"));
		  stage = (Stage) homestaffAnchorPane.getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
	}
	/**
	 * This method is used to came back to the previous stage.
	 * @param event Action Event parameter.
	 * 
	 * @throws IOException
	 * passes the generated exception to the calling method.
	 */
	public void backToPrincipalMenu(ActionEvent event) throws IOException
	 {
			
		  root = FXMLLoader.load(getClass().getResource("/graficaMemberFXML/Entry.fxml"));
		  stage = (Stage) homestaffAnchorPane.getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
	 }
	/**
	 * used to keep the id of the staff member who signed in
	 * @param p staff object
	 */
	public static void setData(Staff p) {
		staff=p;
	}
}
