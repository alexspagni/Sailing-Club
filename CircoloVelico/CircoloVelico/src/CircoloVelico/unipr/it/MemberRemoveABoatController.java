package CircoloVelico.unipr.it;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import enumClass.BoatMemberEnum;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * The {@code addABoatController} class is used to show to a member a table
 * where he can choose one of its boat and remove it. When a member remove a
 * boat it will be removed also from all raced which has been signed up
 * 
 * @author Spagni Alex Zuelli Arianna
 * 
 */

public class MemberRemoveABoatController implements Initializable {
	private BoatMemberEnum getBoat=BoatMemberEnum.GET_BOAT;
	private BoatMemberEnum removeBoat=BoatMemberEnum.REMOVE_BOAT;
//	private final int getboatReq = 1;
//	private final int removeAboatreq = 2;
	private PrintMessageClass printMessageClass = new PrintMessageClass();
	private static Member member;
	private Stage stage;
	private Scene scene;
	private Parent root;
	@FXML
	private AnchorPane memberAnchorPaneRemoveBoat;
	@FXML
	private TableView<Boat> memberPrintTableView;
	@FXML
	private TableColumn<Boat, String> boatNameColumn;
	@FXML
	private TableColumn<Boat, Integer> boatIdColumn;
	@FXML
	private TextField boatSelectedTextField;
	private String title, headerText, contentText;
	int boatSelected;
	ObservableList<Boat> values = FXCollections.observableArrayList();
	Boat imbarcazioneTemp = new Boat();

	/**
	 * This function is used from the GUI and allows the user to send data about
	 * boat, he wants to remove, to the server. The Request object that contains
	 * data, which must be send to the server, is into {@code MemberService}.
	 * 
	 * @param event Action Event parameter.
	 */
	public void removeABoat(ActionEvent event) {
		try {
			System.out.println("sono qui");

			String boatSelected = boatSelectedTextField.getText();
			if (!boatSelected.isBlank()) {

				MemberService memberService = new MemberService();
				int ris = memberService.removeABoat(imbarcazioneTemp.getId(), removeBoat.getTypeReq());
				if (ris >= 0) {

					title = "Remove a boat Form";
					headerText = "Boat Removing";
					contentText = "Boat selected removed, click OK to continue  ";

					if (printMessageClass.displayOperationSuccess(title, headerText, contentText)) {
						backToPrincipalMenu(event);
					}
				} else {
					title = "Remove a boat Form";
					headerText = "Boat Removing";
					contentText = "error in boat removing, click OK to continue  ";
					printMessageClass.displayOperationFailure(title, headerText, contentText);
					backToPrincipalMenu(event);
				}
			} else {
				title = "Remove a boat form";
				headerText = "Error";
				contentText = "In order to remove a boat you must select one";
				printMessageClass.errorMessage(title, headerText, contentText);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			System.out.println("sono qui");
			MemberService memberService = new MemberService();
			ArrayList<Boat> boat = memberService.getBoat(member.getId(), getBoat.getTypeReq());
			for (Boat i : boat) {
				values.add(i);
			}
			boatNameColumn.setCellValueFactory(new PropertyValueFactory<Boat, String>("name"));
			boatIdColumn.setCellValueFactory(new PropertyValueFactory<Boat, Integer>("length"));
			memberPrintTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
			memberPrintTableView.setItems(values);
			memberPrintTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Boat>() {

				@Override
				public void changed(ObservableValue<? extends Boat> arg0, Boat arg1, Boat arg2) {
					imbarcazioneTemp = memberPrintTableView.getSelectionModel().getSelectedItem();
					boatSelectedTextField.setText(imbarcazioneTemp.getName());
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
		stage = (Stage) memberAnchorPaneRemoveBoat.getScene().getWindow();
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
