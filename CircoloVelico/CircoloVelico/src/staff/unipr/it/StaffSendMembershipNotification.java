package staff.unipr.it;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;

import enumClass.NotificationStaffEnum;
//import javaClass.Constant;

import javaClass.Notification;
import javaClass.PrintMessageClass;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * The {@code StaffSendMembershipNotification} class is used to show to a Staff
 * member a table where he can see all membership user fee haven't paied yet. He
 * can select one of them to send a notification to the related user in order to
 * remember tìhim to pay that fee.
 * 
 * @author Spagni Alex Zuelli Arianna
 * 
 */
public class StaffSendMembershipNotification implements Initializable {
	private NotificationStaffEnum membershipNotification=NotificationStaffEnum.GET_MEMBERS_TO_NOTIFY_MEMBERSHIP_FEE;
	private NotificationStaffEnum sendMembershipNotification=NotificationStaffEnum.SEND_MEMBERSHIP_NOTIFICATION;
//	private static final Constant constant = new Constant();
	private static Staff staff;
	private PrintMessageClass printMessageClass = new PrintMessageClass();
	private String title, headerText, contentText;
	private Stage stage;
	private Scene scene;
	private Parent root;
	@FXML
	private AnchorPane staffAnchorPaneSendNotification;
	@FXML
	private TableView<Notification> staffSendNotificationPrintTableView;
	@FXML
	private TableColumn<Notification, Integer> diColumnmember;
	@FXML
	private TableColumn<Notification, Date> dateColumnstaff;
	@FXML
	private TableColumn<Notification, Integer> priceColumnstaff;
	@FXML
	private TextField userSelectedTextField;
	private ObservableList<Notification> values = FXCollections.observableArrayList();
	private Notification notificationTemp = new Notification();

	/**
	 * This function is used from the GUI and allows the staff member to send some a
	 * notification to a user. The only requirement of this fee, is that user must
	 * select a fee hasn't paied yet. The Request object that contains data, which
	 * must be send to the server, is into {@code StaffService}.
	 * 
	 * @param event Action Event parameter.
	 */
	public void sendNotification(ActionEvent event) {
		try {
			String userSelected = userSelectedTextField.getText();
			if (userSelected.isBlank()) {
				title = "Send notification form";
				headerText = "Error";
				contentText = "You must select a user to send him a notification";
				printMessageClass.errorMessage(title, headerText, contentText);
			} else {
				// Socket client = new Socket(SHOST, SPORT);
				StaffService staffService = new StaffService();
				int ris = staffService.sendMembershipFeeNotification(sendMembershipNotification.getTypeReq(),
						notificationTemp.getIdmember(), notificationTemp.getDate(), notificationTemp.getPrice(), "no");
				if (ris >= 0) {
					title = "Send notification form";
					headerText = "Notification";
					contentText = "Notification sent to the user'selected, click OK to continue  ";
					if (printMessageClass.displayOperationSuccess(title, headerText, contentText)) {
						backToPrincipalMenu(event);
					}
				} else {
					title = "Join a race form";
					headerText = "Join a race";
					contentText = "Error in sending notification, click OK to continue  ";
					printMessageClass.displayOperationFailure(title, headerText, contentText);
					backToPrincipalMenu(event);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		System.out.println(staff.getId());
		try {
			// Socket client = new Socket(SHOST, SPORT);
			StaffService staffService = new StaffService();
			ArrayList<Notification> notifications = staffService
					.getUserMustPayFee(membershipNotification.getTypeReq());
			for (Notification i : notifications) {
				values.add(i);
			}
			diColumnmember.setCellValueFactory(new PropertyValueFactory<Notification, Integer>("idmember"));
			dateColumnstaff.setCellValueFactory(new PropertyValueFactory<Notification, Date>("date"));
			priceColumnstaff.setCellValueFactory(new PropertyValueFactory<Notification, Integer>("price"));
			staffSendNotificationPrintTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
			staffSendNotificationPrintTableView.setItems(values);
			staffSendNotificationPrintTableView.getSelectionModel().selectedItemProperty()
					.addListener(new ChangeListener<Notification>() {

						@Override
						public void changed(ObservableValue<? extends Notification> arg0, Notification arg1,
								Notification arg2) {
							notificationTemp = staffSendNotificationPrintTableView.getSelectionModel()
									.getSelectedItem();
							userSelectedTextField.setText(Integer.toString(notificationTemp.getIdmember()));
						}

					});
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * This method is used to came back to the previous stage.
	 * 
	 * @param event Action Event parameter.
	 * 
	 * @throws IOException passes the generated exception to the calling method.
	 */
	public void backToPrincipalMenu(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("/graficaStaffFXML/HomeStaff.fxml"));
		stage = (Stage) staffAnchorPaneSendNotification.getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * used to keep the id of the staff member who signed in
	 * 
	 * @param p staff object
	 */
	public static void setData(Staff p) {
		staff = p;
	}

}