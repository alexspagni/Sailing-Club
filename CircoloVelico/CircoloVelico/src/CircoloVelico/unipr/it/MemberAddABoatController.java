package CircoloVelico.unipr.it;

import java.io.IOException;

import enumClass.BoatMemberEnum;
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
 * The {@code MemberAddABoatController} class is used to show to a member a Form
 * where he can enter data about its boat and click the button "add a boat", its
 * data will be stored in the system.
 * 
 * @author Spagni Alex Zuelli Arianna
 * 
 */
public class MemberAddABoatController {
	private PrintMessageClass printMessageClass = new PrintMessageClass();
//	private final int addAboatreq = 0;
	private BoatMemberEnum boatMemberEnum=BoatMemberEnum.ADD_BOAT;
	private static Member member;
	private Stage stage;
	private Scene scene;
	private Parent root;
	@FXML
	private AnchorPane memberAnchorPaneAddBoat;
	@FXML
	private TextField boatLengthField;
	@FXML
	private TextField boatNameField;
	private String boatName;
	private String title, headerText, contentText;

	/**
	 * This method is used from the GUI to allow user to send data to the server.
	 * The Request object that contains data, which must be send to the server, is
	 * into {@code MemberService}.
	 * 
	 * @param event Action Event parameter.
	 *
	 * @see MemberService
	 */
	public void addBoat(ActionEvent event) {
		int boatLength;
		String boatLen;
		try {
			boatName = boatNameField.getText();
			boatLen = boatLengthField.getText();

			if (boatName.isBlank() || boatLen.isBlank()) {
				title = "Add a boat form";
				headerText = "Error";
				contentText = "In order to add a boat boat field must be filled";
				printMessageClass.errorMessage(title, headerText, contentText);
			} else {
				try {

					boatLength = Integer.parseInt(boatLengthField.getText());
					// Socket client = new Socket(SHOST, SPORT);
					System.out.println(member.getId() + " " + member.getUsername());
					MemberService memberService = new MemberService();
					int ris = memberService.addABoatmember(member, boatName, boatLength,boatMemberEnum.getTypeReq());
					if (ris >= 0) {
						title = "Add a boat Form";
						headerText = "Boat registration";
						contentText = "Your boat has been registered, click OK to continue  ";

						if (printMessageClass.displayOperationSuccess(title, headerText, contentText)) {
							backToPrincipalMenu(event);
						}
					} else {
						title = "Add a boat Form";
						headerText = "Error in registration";
						contentText = "You already have a boat with this name, use another name  ";
						if (printMessageClass.displayOperationFailure(title, headerText, contentText)) {
							backToPrincipalMenu(event);
						}
					}
				} catch (NumberFormatException e) {
					title = "Add a boat form";
					headerText = "Error";
					contentText = "In order to add a boat you must insert an integer lenght and not a String one";
					printMessageClass.errorMessage(title, headerText, contentText);
				}
			}
		} catch (Exception p) {
			p.printStackTrace();

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
		stage = (Stage) memberAnchorPaneAddBoat.getScene().getWindow();
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
