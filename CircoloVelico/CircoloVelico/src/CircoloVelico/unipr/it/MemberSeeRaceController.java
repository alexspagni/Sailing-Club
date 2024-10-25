package CircoloVelico.unipr.it;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;
import enumClass.RaceMemberEnum;
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
 * The {@code memberSeeRaceController} class is used to show to a member a table
 * where he can see all race he joined and he can also see if these race ended
 * or not.
 * 
 * @author Spagni Alex Zuelli Arianna
 * 
 */
public class MemberSeeRaceController implements Initializable {
	private RaceMemberEnum getRace=RaceMemberEnum.GET_RACE;
//	private final int getRace = 0;

	private static Member member;
	private Stage stage;
	private Scene scene;
	private Parent root;
	@FXML
	private AnchorPane memberAnchorPaneSeeRace;
	@FXML
	private TableView<Race> memberSeeRacePrintTableView;
	@FXML
	private TableColumn<Race, String> nameColumnRace;
	@FXML
	private TableColumn<Race, String> luogoColumnRace;
	@FXML
	private TableColumn<Race, Date> dateColumnRace;
	@FXML
	private TableColumn<Race, Integer> priceColumnRace;
	@FXML
	private TableColumn<Race, String> endedColumnRace;
	private ObservableList<Race> values = FXCollections.observableArrayList();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		System.out.println(member.getId());
		try {
			System.out.println(member.getId());
			// Socket client = new Socket(SHOST, SPORT);
			MemberService memberService = new MemberService();
			ArrayList<Race> race = memberService.getRace(member.getId(), getRace.getTypeReq());
			for (Race i : race) {
				values.add(i);
			}
			for (Race i : values) {
				System.out.println(i.getIdRace() + " " + i.getRaceName());
			}
			nameColumnRace.setCellValueFactory(new PropertyValueFactory<Race, String>("raceName"));
			luogoColumnRace.setCellValueFactory(new PropertyValueFactory<Race, String>("place"));
			dateColumnRace.setCellValueFactory(new PropertyValueFactory<Race, Date>("date"));
			priceColumnRace.setCellValueFactory(new PropertyValueFactory<Race, Integer>("price"));
			endedColumnRace.setCellValueFactory(new PropertyValueFactory<Race, String>("ended"));
			memberSeeRacePrintTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
			memberSeeRacePrintTableView.setItems(values);
			memberSeeRacePrintTableView.getSelectionModel().selectedItemProperty()
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
		root = FXMLLoader.load(getClass().getResource("/graficaMemberFXML/HomeMember.fxml"));
		stage = (Stage) memberAnchorPaneSeeRace.getScene().getWindow();
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