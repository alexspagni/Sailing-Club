package staff.unipr.it;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import enumClass.BoatStaffEnum;
import javaClass.Boat;
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
import javafx.scene.control.RadioButton;
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
public class StaffModifyBoatData implements Initializable {
	private BoatStaffEnum getBoat = BoatStaffEnum.GET_BOATS;
	private BoatStaffEnum modifyBoat = BoatStaffEnum.MODIFY_BOAT;
//	private static final Constant constant = new Constant();
	private static Staff staff;
	private String title, headerText, contentText;
	private int lunghezza;
	private PrintMessageClass printMessageClass = new PrintMessageClass();
	private Stage stage;
	private Scene scene;
	private Parent root;
	@FXML
	private AnchorPane staffAnchorPaneModifyBoatData;
	@FXML
	private TableView<Boat> staffModifyBoatDataPrintTableView;
	@FXML
	private TableColumn<Boat, String> boatNameColumn;
	@FXML
	private TableColumn<Boat, Integer> memberIdColumnmember;
	@FXML
	private TableColumn<Boat, Integer> boatLengthColumnstaff;
	@FXML
	private TableColumn<Boat, String> boatRimessaggioColumnstaff;
	@FXML
	private TextField boatSelectedTextField, boatLengthTextField;
	@FXML
	private RadioButton mettiInRimessaggioRadioButton, togliDalRimessaggioRadioButton;
	private ObservableList<Boat> values = FXCollections.observableArrayList();
	private Boat boatTemp = new Boat();

	/**
	 * This function is used from the GUI and allows the staff member to modify some
	 * information about a boat. The only requirement of this fee, is that user must
	 * select a boat to modify. The Request object that contains data, which must be
	 * send to the server, is into {@code StaffService}.
	 * 
	 * @param event Action Event parameter.
	 */
	public void changeBoatData(ActionEvent event) {
		try {
			String lenght = boatLengthTextField.getText();
			if (lenght.isBlank()) {
				lunghezza = boatTemp.getLength();
			}
			lunghezza = Integer.parseInt(boatLengthTextField.getText());
			String boatSelected = boatSelectedTextField.getText();
			if (boatSelected.isBlank()) {
				title = "Modify boat data form";
				headerText = "Error";
				contentText = "You must select a boat to modify its data";
				printMessageClass.errorMessage(title, headerText, contentText);
			}

			else {

				// Socket client = new Socket(SHOST, SPORT);
				StaffService staffService = new StaffService();
				String rimessaggio = " ";
				if (mettiInRimessaggioRadioButton.isSelected()) {
					rimessaggio = "si";
				}
				if (togliDalRimessaggioRadioButton.isSelected()) {
					rimessaggio = "no";
				}

				int ris = staffService.modifyBoatData(modifyBoat.getTypeReq(), boatTemp.getId(), rimessaggio,
						lunghezza);

				if (ris >= 0) {
					title = "Modify boat data form";
					headerText = "Modify boat";
					contentText = "Data modified, click OK to continue";
					if (printMessageClass.displayOperationSuccess(title, headerText, contentText)) {
						backToPrincipalMenu(event);

					}
				} else {
					title = "Modify boat data form";
					headerText = "Modify data";
					contentText = "Error during data management, click OK to continue  ";
					printMessageClass.displayOperationFailure(title, headerText, contentText);
					backToPrincipalMenu(event);
				}
			}
		} catch (NumberFormatException | IOException p) {
			title = "modify boat data form";
			headerText = "Error";
			contentText = "In order to modify a boat you must insert an integer lenght and not a String one";
			printMessageClass.errorMessage(title, headerText, contentText);

		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		System.out.println(staff.getId());
		try {
			// Socket client = new Socket(SHOST, SPORT);
			StaffService staffService = new StaffService();
			ArrayList<Boat> boats = staffService.getBoat(getBoat.getTypeReq());
			for (Boat i : boats) {
				values.add(i);
			}
//			for (Notification i: values) {
//				System.out.println(i.getYear()+" "+i.getIdmember()+" "+i.getPrice());
//			}
			memberIdColumnmember.setCellValueFactory(new PropertyValueFactory<Boat, Integer>("idMember"));
			boatNameColumn.setCellValueFactory(new PropertyValueFactory<Boat, String>("name"));
			boatLengthColumnstaff.setCellValueFactory(new PropertyValueFactory<Boat, Integer>("length"));
			boatRimessaggioColumnstaff.setCellValueFactory(new PropertyValueFactory<Boat, String>("storage"));
			staffModifyBoatDataPrintTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
			staffModifyBoatDataPrintTableView.setItems(values);
			staffModifyBoatDataPrintTableView.getSelectionModel().selectedItemProperty()
					.addListener(new ChangeListener<Boat>() {

						@Override
						public void changed(ObservableValue<? extends Boat> arg0, Boat arg1, Boat arg2) {
							boatTemp = staffModifyBoatDataPrintTableView.getSelectionModel().getSelectedItem();
							boatSelectedTextField.setText(boatTemp.getName());
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
		stage = (Stage) staffAnchorPaneModifyBoatData.getScene().getWindow();
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