package staff.unipr.it;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;

import enumClass.RaceStaffEnum;

import javaClass.PrintMessageClass;
import javaClass.Race;
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
 * The {@code StaffSimulateARaceController} class is used to show to a Staff
 * member a table where he can see all race that sholud take place today.He can
 * select one of them to simulate the race and proclame a winner.
 * 
 * @author Spagni Alex Zuelli Arianna
 * 
 */
public class StaffSimulateARaceController implements Initializable {
	private RaceStaffEnum getRace=RaceStaffEnum.GET_RACE_TO_SIMULATE;
	private RaceStaffEnum simulateRace=RaceStaffEnum.SIMULATE_RACE;
//	private static final Constant constant = new Constant();
	private static Staff staff;
	private String title, headerText, contentText;
	private PrintMessageClass printMessageClass = new PrintMessageClass();
	private Stage stage;
	private Scene scene;
	private Parent root;
	@FXML
	private AnchorPane staffAnchorPaneSimulateRace;
	@FXML
	private TableView<Race> staffSimulateRacePrintTableView;
	@FXML
	private TableColumn<Race, String> raceNameColumn;
	@FXML
	private TableColumn<Race, String> racePlaceColumn;
	@FXML
	private TableColumn<Race, Integer> racePriceColumn;
	@FXML
	private TableColumn<Race, Date> raceDateColumn;
	@FXML
	private TextField raceSelectedTextField, racePlaceTextField, raceDateTextField;
	private ObservableList<Race> values = FXCollections.observableArrayList();
	private Race raceTemp = new Race();

	/**
	 * This function is used from the GUI and allows the staff member to simulate a
	 * race. The only requirement of this fee, is that user must select a race to
	 * simulate. The Request object that contains data, which must be send to the
	 * server, is into {@code StaffService}.
	 * 
	 * @param event Action Event parameter.
	 */
	public void simulateARace(ActionEvent event) {
		try {
			String raceSelected = raceSelectedTextField.getText();
			if (raceSelected.isBlank()) {
				title = "Simulate a race form";
				headerText = "Error";
				contentText = "You must select a race to simulate it";
				printMessageClass.errorMessage(title, headerText, contentText);
			} else {
				// Socket client = new Socket(SHOST, SPORT);
				StaffService staffService = new StaffService();
				int ris = staffService.simulateARace(simulateRace.getTypeReq(), raceTemp.getIdRace(),
						raceTemp.getPlace(), raceTemp.getRaceName());
				if (ris >= 0) {
					title = "Simulate a race form";
					headerText = "Simulate race";
					contentText = "Run simulated, click OK to continue";
					if (printMessageClass.displayOperationSuccess(title, headerText, contentText)) {
						backToPrincipalMenu(event);
					}
				} else {
					title = "Simulate a race form";
					headerText = "Simulate race";
					contentText = "Error during simulation, click OK to continue  ";
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
			ArrayList<Race> race = staffService.getRace(getRace.getTypeReq());
			for (Race i : race) {
				values.add(i);
			}
//			for (Notification i: values) {
//				System.out.println(i.getYear()+" "+i.getIdmember()+" "+i.getPrice());
//			}
			raceNameColumn.setCellValueFactory(new PropertyValueFactory<Race, String>("raceName"));
			racePlaceColumn.setCellValueFactory(new PropertyValueFactory<Race, String>("place"));
			racePriceColumn.setCellValueFactory(new PropertyValueFactory<Race, Integer>("price"));
			raceDateColumn.setCellValueFactory(new PropertyValueFactory<Race, Date>("date"));
			staffSimulateRacePrintTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
			staffSimulateRacePrintTableView.setItems(values);
			staffSimulateRacePrintTableView.getSelectionModel().selectedItemProperty()
					.addListener(new ChangeListener<Race>() {

						@Override
						public void changed(ObservableValue<? extends Race> arg0, Race arg1, Race arg2) {
							raceTemp = staffSimulateRacePrintTableView.getSelectionModel().getSelectedItem();
							raceSelectedTextField.setText(raceTemp.getRaceName());
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
		stage = (Stage) staffAnchorPaneSimulateRace.getScene().getWindow();
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