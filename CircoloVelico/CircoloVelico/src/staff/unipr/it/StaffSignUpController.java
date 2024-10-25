package staff.unipr.it;

import java.io.IOException;

import enumClass.StaffEnum;
import javaClass.PrintMessageClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * The {@code StaffSignInController} class is used to show to a Staff member a
 * form where he can enter his username and password and try to access to his
 * account.
 * 
 * @author Spagni Alex Zuelli Arianna
 * 
 */
public class StaffSignUpController {
	@FXML
	private AnchorPane staffSignInAnchorPane;
	@FXML
	private PasswordField staffPassword;
	@FXML
	private TextField staffUsername;
	private PrintMessageClass printMessageClass = new PrintMessageClass();
	private String title, headerText, contentText;
	String pwd, userName;
	private Stage stage;
	private Scene scene;
	private Parent root;
	private StaffEnum signUp=StaffEnum.SIGN_UP;
//	private final int reqSignIn = 2;

	/**
	 * This is called by GUI, but logic to sign In is into the function
	 * {@code runSignUp}}
	 * 
	 * @param event Action Event parameter.
	 * @throws IOException            passes the generated exception to the calling
	 *                                method.
	 * @throws ClassNotFoundException passes the generated exception to the calling
	 *                                method.
	 */
	public void signUp(ActionEvent event) throws IOException, ClassNotFoundException {
		runSignUp(signUp.getTypeReq());
	}

	/**
	 * This function is used to Sign up a staff member. It just takes username and
	 * password insert from the staff member and send it to the server that will
	 * store them.
	 * 
	 * @param reqvalue integer value
	 */
	public void runSignUp(int reqvalue) {
		try {
			pwd = staffPassword.getText();
			userName = staffUsername.getText();
			// Socket client = new Socket(SHOST, SPORT);
			StaffService staffService = new StaffService();
			int ris = staffService.signUp(userName, pwd, reqvalue);

			if (ris >= 0) {
				title = "Sign Up";
				headerText = "Your credential are correct";
				contentText = "You will now be redirected to the interface intended\n for staff, click OK to continue  ";
				if (printMessageClass.displayOperationSuccess(title, headerText, contentText)) {

					Staff staff = new Staff(userName, pwd, ris);
					HomeStaffController.setData(staff);
					root = FXMLLoader.load(getClass().getResource("/graficaStaffFXML/HomeStaff.fxml"));
					stage = (Stage) staffSignInAnchorPane.getScene().getWindow();
					scene = new Scene(root);
					stage.setScene(scene);
					stage.show();
				}
			} else {
				title = "Sign up";
				headerText = "Username or password not correct.";
				contentText = "Try again, click OK to continue.";
				printMessageClass.displayOperationFailure(title, headerText, contentText);
			}
		}

		catch (IOException e) {
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
		root = FXMLLoader.load(getClass().getResource("/graficaMemberFXML/Entry.fxml"));
		stage = (Stage) staffSignInAnchorPane.getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
}
