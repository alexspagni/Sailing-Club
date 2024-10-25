package staff.unipr.it;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

import enumClass.RaceStaffEnum;
//import javaClass.Constant;
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
 * The {@code StafflModifyRaceController} class is used to show to a Staff
 * member a table where he can choose a race and modify some of its information.
 * 
 * @author Spagni Alex Zuelli Arianna
 * 
 */
public class StafflModifyRaceController implements Initializable {
	private RaceStaffEnum modifyRace = RaceStaffEnum.MODIFY_RACE;
	private RaceStaffEnum getRace = RaceStaffEnum.GET_RACE;
//	private static final Constant constant = new Constant();
	private String title, headerText, contentText;
	private PrintMessageClass printMessageClass = new PrintMessageClass();
	private static Staff staff;
	private Stage stage;
	private Scene scene;
	private Parent root;
	@FXML
	private AnchorPane staffAnchorPaneModifyRaceData;
	@FXML
	private TableView<Race> staffModifyRaceDataPrintTableView;
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
	 * This function is used from the GUI and allows the staff member to modify some
	 * information about a race. The only requirement of this fee, is that user must
	 * select a race to modify. The Request object that contains data, which must be
	 * send to the server, is into {@code StaffService}.
	 * 
	 * @param event Action Event parameter.
	 */
	public void changeARace(ActionEvent event) {
		try {
			Date dateSql = null;
			String raceSelected = raceSelectedTextField.getText();
			try {
				String dateString = raceDateTextField.getText();
				if (!dateString.isBlank()) {
					SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
					java.util.Date parsed = format.parse(raceDateTextField.getText());
					dateSql = new java.sql.Date(parsed.getTime());
				} else {
					dateSql = raceTemp.getDate();
				}
				if (raceSelected.isBlank()) {
					title = "Modify race data form";
					headerText = "Modify race";
					contentText = "You must select a race to modify its own data";
					printMessageClass.errorMessage(title, headerText, contentText);
				} else {
					// Socket client = new Socket(SHOST, SPORT);
					StaffService staffService = new StaffService();
					String racePlace = racePlaceTextField.getText();
					if (racePlace.isBlank()) {
						racePlace = raceTemp.getPlace();
					}
					int ris = staffService.modifyARace(modifyRace.getTypeReq(), raceTemp.getIdRace(), racePlace,
							dateSql);
					if (ris >= 0) {
						title = "Modify a race form";
						headerText = "Modify race";
						contentText = "Race modified correctly, click OK to continue  ";
						if (printMessageClass.displayOperationSuccess(title, headerText, contentText)) {
							backToPrincipalMenu(event);
						}
					} else {
						title = "Modify a race form";
						headerText = "Modify race";
						contentText = "Error in storing new race infortion, click OK to continue.";
						printMessageClass.displayOperationFailure(title, headerText, contentText);
					}
				}
			} catch (Exception e) {
				title = "Add a new raceform";
				headerText = "Error";
				contentText = "In order to add a new race you must insert a date in this format: 'yyyyMMdd'";
				printMessageClass.errorMessage(title, headerText, contentText);
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
			raceNameColumn.setCellValueFactory(new PropertyValueFactory<Race, String>("raceName"));
			racePlaceColumn.setCellValueFactory(new PropertyValueFactory<Race, String>("place"));
			racePriceColumn.setCellValueFactory(new PropertyValueFactory<Race, Integer>("price"));
			raceDateColumn.setCellValueFactory(new PropertyValueFactory<Race, Date>("date"));
			staffModifyRaceDataPrintTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
			staffModifyRaceDataPrintTableView.setItems(values);
			staffModifyRaceDataPrintTableView.getSelectionModel().selectedItemProperty()
					.addListener(new ChangeListener<Race>() {

						@Override
						public void changed(ObservableValue<? extends Race> arg0, Race arg1, Race arg2) {
							raceTemp = staffModifyRaceDataPrintTableView.getSelectionModel().getSelectedItem();
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
		stage = (Stage) staffAnchorPaneModifyRaceData.getScene().getWindow();
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