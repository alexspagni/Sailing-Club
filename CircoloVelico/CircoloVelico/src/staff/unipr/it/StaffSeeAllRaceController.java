package staff.unipr.it;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;

import enumClass.RaceStaffEnum;
//import javaClass.Constant;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * The {@code StaffSeeAllRaceController} class is used to show to a Staff member
 * a table where he can see all race and who won them.
 * 
 * @author Spagni Alex Zuelli Arianna
 * 
 */
public class StaffSeeAllRaceController implements Initializable {
	private RaceStaffEnum getAllRace=RaceStaffEnum.GET_ALL_RACE_AND_WINNERS;
//	private static final Constant constant = new Constant();
	private static Staff staff;

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
	private TableColumn<Race, String> raceWinnerIdColumn;
	@FXML
	private TableColumn<Race, String> raceWinnerNameColumn;
	private ObservableList<Race> values = FXCollections.observableArrayList();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		System.out.println(staff.getId());
		try {
			// Socket client = new Socket(SHOST, SPORT);
			StaffService staffService = new StaffService();
			ArrayList<Race> race = staffService.getRace(getAllRace.getTypeReq());
			for (Race i : race) {
				values.add(i);
			}
			raceNameColumn.setCellValueFactory(new PropertyValueFactory<Race, String>("raceName"));
			racePlaceColumn.setCellValueFactory(new PropertyValueFactory<Race, String>("place"));
			racePriceColumn.setCellValueFactory(new PropertyValueFactory<Race, Integer>("price"));
			raceDateColumn.setCellValueFactory(new PropertyValueFactory<Race, Date>("date"));
			raceWinnerIdColumn.setCellValueFactory(new PropertyValueFactory<Race, String>("idWinner"));
			raceWinnerNameColumn.setCellValueFactory(new PropertyValueFactory<Race, String>("winnerName"));
			staffSimulateRacePrintTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
			staffSimulateRacePrintTableView.setItems(values);
			staffSimulateRacePrintTableView.getSelectionModel().selectedItemProperty()
					.addListener(new ChangeListener<Race>() {

						@Override
						public void changed(ObservableValue<? extends Race> arg0, Race arg1, Race arg2) {

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