package CircoloVelico.unipr.it;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;

import enumClass.BoatMemberEnum;
import enumClass.FeeMemberEnum;
import enumClass.RaceMemberEnum;
import javaClass.Boat;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * The {@code MemberJoinRaceController} class is used to show to a member a
 * table where he can choose one of its boat available and a race that hasn't
 * finish yet and join that race. Joining a race means also pay a fee.
 * 
 * @author Spagni Alex Zuelli Arianna
 * 
 */

public class MemberJoinRaceController implements Initializable {
	private RaceMemberEnum allRace=RaceMemberEnum.GET_ALL_RACE;
	private RaceMemberEnum joinARace=RaceMemberEnum.JOIN_A_RACE;
	private FeeMemberEnum payFeeRace=FeeMemberEnum.PAY_FEE_RACE;
	private BoatMemberEnum getBoat=BoatMemberEnum.GET_BOAT_RACE;
//	private final int getAllRace = 1;
//	private final int getboatReq = 3;
//	private final int joinARaceReq = 2;
//	private final int payFeeRace = 5;
	private final int bonifico = 0;
	private final int creditCard = 1;
	private static Member member;
	private PrintMessageClass printMessageClass = new PrintMessageClass();
	private String title, headerText, contentText;
	private Stage stage;
	private Scene scene;
	private Parent root;
	@FXML
	private AnchorPane memberAnchorPaneJoinRace;
	@FXML
	private TableView<Race> memberSeeRacePrintTableView;
	@FXML
	private TableView<Boat> memberSelecBoatPrintTableView;
	@FXML
	private TableColumn<Race, String> nameColumnRace;
	@FXML
	private TableColumn<Race, String> luogoColumnRace;
	@FXML
	private TableColumn<Race, Date> dateColumnRace;
	@FXML
	private TableColumn<Race, Integer> priceColumnRace;
	@FXML
	private TableColumn<Boat, String> boatNameColumnRace;
	@FXML
	private TableColumn<Boat, Integer> lunghezzaColumnRace;
	@FXML
	private TextField raceSelectedTextField, boatSelectedTextField;
	@FXML
	private RadioButton bonificoRadioButton, cartaDiCreditoRadioButton;
	private ObservableList<Race> races = FXCollections.observableArrayList();
	private ObservableList<Boat> boats = FXCollections.observableArrayList();
	private Race raceTemp = new Race();
	private Boat imbarcazioneTemp = new Boat();

	/**
	 * This function is used from the GUI and allows the user to send data about
	 * race and boat used , to the server. The Request object that contains data,
	 * which must be send to the server, is into {@code MemberService}.
	 * 
	 * @param event Action Event parameter.
	 */
	public void joinARace(ActionEvent event) {
		try {
			// System.out.println(member.getId());
			String boatSelected = boatSelectedTextField.getText();
			String raceSelected = raceSelectedTextField.getText();
			if (raceSelected.isBlank() || boatSelected.isBlank()) {
				title = "Join a race form";
				headerText = "Error";
				contentText = "You must choose, both race and boat to join a race";
				printMessageClass.errorMessage(title, headerText, contentText);
			} else {
				// Socket client = new Socket(SHOST, SPORT);
				MemberService memberService = new MemberService();
				int ris = memberService.joinARace(member.getId(), raceTemp.getIdRace(), imbarcazioneTemp.getId(),
						joinARace.getTypeReq());
				if (bonificoRadioButton.isSelected()) {
					ris = memberService.payRaceFee(raceTemp.getPrice(), member.getId(), payFeeRace.getTypeReq(), bonifico);
				}

				else {
					ris = memberService.payRaceFee(raceTemp.getPrice(), member.getId(), payFeeRace.getTypeReq(), creditCard);

				}
				if (ris >= 0) {
					title = "Join a race form";
					headerText = "Join a race";
					contentText = "Registration completed, click OK to continue  ";
					if (printMessageClass.displayOperationSuccess(title, headerText, contentText)) {
						backToPrincipalMenu(event);
					}

				} else {
					title = "Join a race form";
					headerText = "Join a race";
					contentText = "Error in registration, click OK to continue  ";
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
		System.out.println(member.getId());
		try {
			// System.out.println(member.getId());
			MemberService memberServices = new MemberService();
			ArrayList<Race> race = memberServices.getRace(member.getId(),allRace.getTypeReq());
			for (Race i : race) {
				races.add(i);
			}
			for (Race i : races) {
				System.out.println(i.getIdRace() + " " + i.getRaceName());
			}
			nameColumnRace.setCellValueFactory(new PropertyValueFactory<Race, String>("raceName"));
			luogoColumnRace.setCellValueFactory(new PropertyValueFactory<Race, String>("place"));
			dateColumnRace.setCellValueFactory(new PropertyValueFactory<Race, Date>("date"));
			priceColumnRace.setCellValueFactory(new PropertyValueFactory<Race, Integer>("price"));
			memberSeeRacePrintTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
			memberSeeRacePrintTableView.setItems(races);
			memberSeeRacePrintTableView.getSelectionModel().selectedItemProperty()
					.addListener(new ChangeListener<Race>() {

						@Override
						public void changed(ObservableValue<? extends Race> arg0, Race arg1, Race arg2) {
							raceTemp = memberSeeRacePrintTableView.getSelectionModel().getSelectedItem();
							raceSelectedTextField.setText(raceTemp.getRaceName());
						}

					});
			// Socket client2 = new Socket(SHOST, SPORT);
			// System.out.println("id: "+member.getId());
			ArrayList<Boat> boat = memberServices.getBoat(member.getId(), getBoat.getTypeReq());
			System.out.println("sono quiiiiiiiiiiiiii");
			for (Boat i : boat) {
				boats.add(i);
			}
//			for (Imbarcazione i: boats) {
//				System.out.println("sono qui"+i.getId()+" "+i.getNome()+" "+i.getLunghezza()+" "+i.getIdmember());
//			}
			boatNameColumnRace.setCellValueFactory(new PropertyValueFactory<Boat, String>("name"));
			lunghezzaColumnRace.setCellValueFactory(new PropertyValueFactory<Boat, Integer>("length"));
			memberSelecBoatPrintTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
			memberSelecBoatPrintTableView.setItems(boats);
			memberSelecBoatPrintTableView.getSelectionModel().selectedItemProperty()
					.addListener(new ChangeListener<Boat>() {

						@Override
						public void changed(ObservableValue<? extends Boat> arg0, Boat arg1, Boat arg2) {
							imbarcazioneTemp = memberSelecBoatPrintTableView.getSelectionModel().getSelectedItem();
							boatSelectedTextField.setText(imbarcazioneTemp.getName());
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
		root = FXMLLoader.load(getClass().getResource("/graficaMemberFXML/HomeMember.fxml"));
		stage = (Stage) memberAnchorPaneJoinRace.getScene().getWindow();
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