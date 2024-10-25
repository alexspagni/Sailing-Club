package staff.unipr.it;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import CircoloVelico.unipr.it.Member;
import enumClass.StaffEnum;
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
 * The {@code StaffModifyBoatData} class is used to show to a Staff member a
 * table where he can choose a boat and modify some of its information.
 * 
 * @author Spagni Alex Zuelli Arianna
 * 
 */
public class StaffRemoveAMember implements Initializable {
	private StaffEnum removeMember = StaffEnum.REMOVE_MEMBER;
	private StaffEnum getMembers = StaffEnum.GET_MEMBERS;
//	private static final Constant constant = new Constant();
	private String title, headerText, contentText;
	private PrintMessageClass printMessageClass = new PrintMessageClass();
	private Stage stage;
	private Scene scene;
	private Parent root;
	@FXML
	private AnchorPane staffAnchorPaneRemoveAMember;
	@FXML
	private TableView<Member> staffRemoveAMemberPrintTableView;
	@FXML
	private TableColumn<Member, String> memberNameColumn;
	@FXML
	private TableColumn<Member, Integer> memberIdColumn;
	@FXML
	private TableColumn<Member, String> memberFiscalCodeColumnstaff;
	@FXML
	private TextField memberSelectedTextField;

	private ObservableList<Member> values = FXCollections.observableArrayList();
	private Member memberTemp = new Member();

	/**
	 * This function is used from the GUI and allows the staff member to modify some
	 * information about a boat. The only requirement of this fee, is that user must
	 * select a boat to modify. The Request object that contains data, which must be
	 * send to the server, is into {@code StaffService}.
	 * 
	 * @param event Action Event parameter.
	 */
	public void removeAMember(ActionEvent event) {
		try {
			String memberSelected=memberSelectedTextField.getText();
			if (memberSelected.isBlank()) {
				title = "Remove member form";
				headerText = "Error";
				contentText = "You must select a member to remove him.";
				printMessageClass.errorMessage(title, headerText, contentText);
			}
			else {
				StaffService staffService = new StaffService();
				int ris = staffService.removeAMember(removeMember.getTypeReq(),
						memberTemp.getId());
				if (ris >= 0) {
					title = "Remove member form";
					headerText = "Remove member";
					contentText = "Member remove, click OK to continue";
					if (printMessageClass.displayOperationSuccess(title, headerText, contentText)) {
						backToPrincipalMenu(event);
					}
				} else {
					title = "Remove member form";
					headerText = "Remove member";
					contentText = "Error during member removing, click OK to continue  ";
					printMessageClass.displayOperationFailure(title, headerText, contentText);
					backToPrincipalMenu(event);
				}
			}

		} catch (Exception p) {
			title = "Remove a member form.";
			headerText = "Error.";
			contentText = "Plese try again.";
			printMessageClass.errorMessage(title, headerText, contentText);

		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			// Socket client = new Socket(SHOST, SPORT);
			StaffService staffService = new StaffService();
			ArrayList<Member> members = staffService.getMembers(getMembers.getTypeReq());
			for (Member i : members) {
				values.add(i);
			}
//			for (Notification i: values) {
//				System.out.println(i.getYear()+" "+i.getIdmember()+" "+i.getPrice());
//			}
			memberIdColumn.setCellValueFactory(new PropertyValueFactory<Member, Integer>("id"));
			memberNameColumn.setCellValueFactory(new PropertyValueFactory<Member, String>("username"));
			memberFiscalCodeColumnstaff.setCellValueFactory(new PropertyValueFactory<Member, String>("fiscalCode"));
			staffRemoveAMemberPrintTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
			staffRemoveAMemberPrintTableView.setItems(values);
			staffRemoveAMemberPrintTableView.getSelectionModel().selectedItemProperty()
					.addListener(new ChangeListener<Member>() {

						@Override
						public void changed(ObservableValue<? extends Member> arg0, Member arg1, Member arg2) {
							memberTemp = staffRemoveAMemberPrintTableView.getSelectionModel().getSelectedItem();
							memberSelectedTextField.setText(memberTemp.getUsername());
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
		stage = (Stage) staffAnchorPaneRemoveAMember.getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

}