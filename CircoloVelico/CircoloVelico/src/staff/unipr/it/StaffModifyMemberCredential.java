package staff.unipr.it;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import CircoloVelico.unipr.it.Member;
import enumClass.StaffEnum;
import javaClass.FiscalCode;
//import javaClass.Constant;
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
 * The {@code StaffModifymemberCredential} class is used to show to a Staff
 * member a table where he can choose a user and modify some of its information.
 * 
 * @author Spagni Alex Zuelli Arianna
 * 
 */
public class StaffModifyMemberCredential implements Initializable {
	private StaffEnum modifyMember = StaffEnum.MODIFY_MEMBERS_CREDENTIAL;
	private StaffEnum getMembers = StaffEnum.GET_MEMBERS;
//	private static final Constant constant = new Constant();
	private String title, headerText, contentText;
	private PrintMessageClass printMessageClass = new PrintMessageClass();
	private Stage stage;
	private Scene scene;
	private Parent root;
	@FXML
	private AnchorPane staffAnchorPaneModifymemberCredential;
	@FXML
	private TableView<Member> staffModifymemberCredentialPrintTableView;
	@FXML
	private TableColumn<Member, String> addressmemberColumnstaff;
	@FXML
	private TableColumn<Member, String> memberCodiceFiscaleColumnstaff;
	@FXML
	private TableColumn<Member, String> memberNameColumnmember;
	@FXML
	private TableColumn<Member, Integer> memberIdColumnmember;
	@FXML
	private TextField userSelectedTextField, memberAddressTextField, memberCodiceFiscaleTextField;
	private ObservableList<Member> values = FXCollections.observableArrayList();
	private Member memberTemp = new Member();

	/**
	 * This function is used from the GUI and allows the staff member to modify some
	 * information about a user. The only requirement of this fee, is that user must
	 * select a user to modify. The Request object that contains data, which must be
	 * send to the server, is into {@code StaffService}.
	 * 
	 * @param event Action Event parameter.
	 */
	public void changeMemberCredential(ActionEvent event) {
		try {
			String userSelected = userSelectedTextField.getText();
			if (userSelected.isBlank()) {
				title = "Modify credential form";
				headerText = "Error";
				contentText = "You must select a user to modify his data";
				printMessageClass.errorMessage(title, headerText, contentText);
			} else {
				String address = memberAddressTextField.getText();
				String fiscalCode = memberCodiceFiscaleTextField.getText();
				if (address.isBlank()) {
					address = memberTemp.getAddress();
				}
				if (fiscalCode.isBlank()) {
					fiscalCode = memberTemp.getFiscalCode();
				}
				// Socket client = new Socket(SHOST, SPORT);

				FiscalCode fiscalCodeObject = new FiscalCode();
				if (!(fiscalCodeObject.checkFiscalCode(fiscalCode) == 1)) {
					title = "Sign In form";
					headerText = "Attention";
					contentText = "you insert an invalid fiscal code";
					printMessageClass.errorMessage(title, headerText, contentText);
				} else {
					StaffService staffService = new StaffService();
					int ris = staffService.manageMemberCredential(modifyMember.getTypeReq(), memberTemp.getId(),
							fiscalCode, address);

					if (ris >= 0) {
						title = "Modify credential form";
						headerText = "Modify credential";
						contentText = "Credential modified, click OK to continue";
						if (printMessageClass.displayOperationSuccess(title, headerText, contentText)) {
							backToPrincipalMenu(event);
						}
					} else {
						title = "Modify credential form";
						headerText = "Modify credential";
						contentText = "Error during credential management, click OK to continue  ";
						printMessageClass.displayOperationFailure(title, headerText, contentText);
						backToPrincipalMenu(event);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// System.out.println(staff.getId());
		try {
			// Socket client = new Socket(SHOST, SPORT);
			StaffService staffService = new StaffService();
			ArrayList<Member> socii = staffService.getMembers(getMembers.getTypeReq());
			for (Member i : socii) {
				values.add(i);
			}
//			for (Notification i: values) {
//				System.out.println(i.getYear()+" "+i.getIdmember()+" "+i.getPrice());
//			}
			memberIdColumnmember.setCellValueFactory(new PropertyValueFactory<Member, Integer>("id"));
			memberNameColumnmember.setCellValueFactory(new PropertyValueFactory<Member, String>("username"));
			addressmemberColumnstaff.setCellValueFactory(new PropertyValueFactory<Member, String>("address"));
			memberCodiceFiscaleColumnstaff.setCellValueFactory(new PropertyValueFactory<Member, String>("fiscalCode"));
			staffModifymemberCredentialPrintTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
			staffModifymemberCredentialPrintTableView.setItems(values);
			staffModifymemberCredentialPrintTableView.getSelectionModel().selectedItemProperty()
					.addListener(new ChangeListener<Member>() {

						@Override
						public void changed(ObservableValue<? extends Member> arg0, Member arg1, Member arg2) {
							memberTemp = staffModifymemberCredentialPrintTableView.getSelectionModel()
									.getSelectedItem();
							userSelectedTextField.setText(Integer.toString(memberTemp.getId()));
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
		stage = (Stage) staffAnchorPaneModifymemberCredential.getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

}