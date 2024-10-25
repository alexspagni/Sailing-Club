package staff.unipr.it;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import enumClass.RaceStaffEnum;
//import javaClass.Constant;
import javaClass.PrintMessageClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * The {@code StaffAddARaceController} class is used to show to a Staff
 * member a from where he can enter race information and add a new race to the
 * database.
 * 
 * @author Spagni Alex Zuelli Arianna
 * 
 */
public class StaffAddARaceController {
	private static Staff staff;
	private PrintMessageClass printMessageClass = new PrintMessageClass();
	private String title, headerText, contentText;
//	private static final Constant constant = new Constant();
	private RaceStaffEnum addARace=RaceStaffEnum.ADD_RACE;
	private Stage stage;
	private Scene scene;
	private Parent root;
	@FXML
	private AnchorPane staffAnchorPaneAddARace;
	@FXML
	private TextField raceNameField;
	@FXML
	private TextField dateField;
	@FXML
	private TextField priceField;
	@FXML
	private TextField placeRaceField;
	private int price;
	int boatLength;

	/**
	 * This function is used from the GUI and allows the staff member to add a race
	 * to the database. The only requirement of this fee, is that user must select a
	 * race to add. The Request object that contains data, which must be send to the
	 * server, is into {@code StaffService}.
	 * 
	 * @param event Action Event parameter.
	 * @throws ParseException passes the generated exception to the calling method.
	 */
	public void addARace(ActionEvent event) throws ParseException {
		try {
			System.out.println(staff.getId());
			String raceName = raceNameField.getText();
			String dateString = dateField.getText();
			String priceString = priceField.getText();
			String racePlace = placeRaceField.getText();
			java.sql.Date dateSql = null;
			// controllo che il prezzo sia intero
			try {
				SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
				Date parsed = format.parse(dateField.getText());
				dateSql = new java.sql.Date(parsed.getTime());

				try {
					price = Integer.parseInt(priceField.getText());

					if (racePlace.isBlank() || raceName.isBlank() || priceString.isBlank() || dateString.isBlank()) {
						title = "Add a new race form";
						headerText = "Error";
						contentText = "All field must be filled";
						printMessageClass.errorMessage(title, headerText, contentText);
					} else {
						// Socket client = new Socket(SHOST, SPORT);
						StaffService staffService = new StaffService();
						int ris = staffService.addARace(addARace.getTypeReq(), raceName, dateSql, price,
								racePlace);
						if (ris >= 0) {
							title = "Add a new race form";
							headerText = "New race";
							contentText = "Race store correctly into the database, click OK to continue  ";

							if (printMessageClass.displayOperationSuccess(title, headerText, contentText)) {
								backToPrincipalMenu(event);
							}
						} else {
							title = "Add a new race form";
							headerText = "New race";
							contentText = "Error in storing new race, click OK to continue.";
							printMessageClass.displayOperationFailure(title, headerText, contentText);
						}

					}
				} catch (NumberFormatException e) {
					title = "Add a new raceform";
					headerText = "Error";
					contentText = "In order to add a new race you must insert an integer price and not a String one";
					printMessageClass.errorMessage(title, headerText, contentText);
				}
			} catch (Exception e) {
				title = "Add a new raceform";
				headerText = "Error";
				contentText = "In order to add a new race you must insert a date in this format: 'yyyyMMdd'";
				printMessageClass.errorMessage(title, headerText, contentText);
			}
		} catch (NumberFormatException p) {
			System.out.println("errore");

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
		stage = (Stage) staffAnchorPaneAddARace.getScene().getWindow();
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