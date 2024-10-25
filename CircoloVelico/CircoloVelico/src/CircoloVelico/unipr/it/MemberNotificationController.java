package CircoloVelico.unipr.it;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import enumClass.NotificationMemberEnum;

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
 * The {@code memberNotificationController} class is used to show to a member a
 * table where he can See all his notification about his fee. Into this table
 * there are both storage and membership fee.
 * 
 * @author Spagni Alex Zuelli Arianna
 * 
 */
public class MemberNotificationController implements Initializable {
	private NotificationMemberEnum getNotification=NotificationMemberEnum.GET_NOTIFICATION;
	private NotificationMemberEnum removeNotification=NotificationMemberEnum.REMOVE_NOTIFICATION;
	//private final int getNotification = 0;
//	private static final Constant constant = new Constant();
	private static Member member;
	private Stage stage;
	private Scene scene;
	private Parent root;
	private String title, headerText, contentText;
	private PrintMessageClass printMessageClass = new PrintMessageClass();
	@FXML
	private AnchorPane memberAnchorPaneNotification;
	@FXML
	private TableView<Notification> memberNotificationPrintTableView;
	@FXML
	private TableColumn<Notification, String> feeTypeColumn;
	@FXML
	private TableColumn<Notification, Integer> feePriceColumn;
	@FXML
	private TextField notificationSelectedTextField;
	ObservableList<Notification> values = FXCollections.observableArrayList();
	private Notification notificationTemp = new Notification();

	/**
	 * This function is used from the GUI and allows the user to remove his
	 * notification. The only requirement of this fee, is that user must select a
	 * notification to remove. The Request object that contains data, which must be
	 * send to the server, is into {@code MemberService}.
	 * 
	 * @param event Action Event parameter.
	 */
	public void removeNotification(ActionEvent event) {
		try {
			// System.out.println("sono qui");
			// Socket client = new Socket(SHOST, SPORT);
			MemberService memberService = new MemberService();
			String feeTypeSelected = notificationSelectedTextField.getText();
			if (feeTypeSelected.isBlank()) {
				title = "member Notification form";
				headerText = "Error";
				contentText = "In order to remove a notification, you must select one";
				printMessageClass.errorMessage(title, headerText, contentText);
			} else {
				int ris = -1;
				ris = memberService.removeNotification(notificationTemp.getIdNotification(),
						removeNotification.getTypeReq());
				if (ris >= 0) {
					title = "member Notification form";
					headerText = "member Notification";
					contentText = "Notification removed, click OK to continue  ";

					if (printMessageClass.displayOperationSuccess(title, headerText, contentText)) {
						backToPrincipalMenu(event);
					}

				} else {
					title = "member Notification form";
					headerText = "member Notification";
					contentText = "Error in Removing notification.  ";
					printMessageClass.displayOperationFailure(title, headerText, contentText);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		try {

			// Socket client = new Socket(SHOST, SPORT);
			MemberService memberService = new MemberService();
			ArrayList<Notification> notifications = memberService.getNotifications(member.getId(), getNotification.getTypeReq());
			for (Notification i : notifications) {
				values.add(i);
			}
			feeTypeColumn.setCellValueFactory(new PropertyValueFactory<Notification, String>("typeOfFee"));
			feePriceColumn.setCellValueFactory(new PropertyValueFactory<Notification, Integer>("price"));
			memberNotificationPrintTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
			memberNotificationPrintTableView.setItems(values);
			memberNotificationPrintTableView.getSelectionModel().selectedItemProperty()
					.addListener(new ChangeListener<Notification>() {

						@Override
						public void changed(ObservableValue<? extends Notification> arg0, Notification arg1,
								Notification arg2) {
							notificationTemp = memberNotificationPrintTableView.getSelectionModel().getSelectedItem();
							notificationSelectedTextField.setText(notificationTemp.getTypeOfFee());
						}

					});
		} catch (Exception e) {

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
		root = FXMLLoader.load(getClass().getResource("/graficaMemberFXML/HomeMember.fxml"));
		stage = (Stage) memberAnchorPaneNotification.getScene().getWindow();
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
