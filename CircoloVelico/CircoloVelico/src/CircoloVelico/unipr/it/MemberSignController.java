package CircoloVelico.unipr.it;

import java.io.IOException;

import enumClass.MembersEnum;
import javaClass.FiscalCode;
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
import responseMembers.ResponseSignMember;

/**
 * The {@code memberSignController} class is used to show to a member a Sign
 * In/Up GUI where he can enter his data and access to the system.
 * 
 * @author Spagni Alex Zuelli Arianna
 * 
 */
public class MemberSignController {
	private MembersEnum signIn = MembersEnum.SIGN_IN;
	private MembersEnum signUp = MembersEnum.SIGN_UP;
	private PrintMessageClass printMessageClass = new PrintMessageClass();
	@FXML
	private AnchorPane memberSignInAnchorPane;
	@FXML
	private PasswordField userPassword;
	@FXML
	private TextField codiceFiscaleTextField;
	@FXML
	private TextField indirizzoTextField;
	@FXML
	private TextField userUsername;
	String pwd, userName, indirizzo, codicefiscale;
	private Stage stage;
	private Scene scene;
	private Parent root;

//	private final int reqSignIn = 1;
//	private final int reqSignUp = 0;
	private String title, headerText, contentText;

	/**
	 * This is called by GUI, but logic to sign In is into the function
	 * {@code runSignIp}}
	 * 
	 * @param event Action Event parameter.
	 * @throws IOException            passes the generated exception to the calling
	 *                                method.
	 * @throws ClassNotFoundException passes the generated exception to the calling
	 *                                method.
	 */
	public void signIn(ActionEvent event) throws IOException, ClassNotFoundException {
		runSignIn(signIn.getTypeReq());
	}

	/**
	 * This is called by GUI, but logic to sign Up is into the function
	 * {@code runSignUp}}
	 * 
	 * @param event Action Event parameter.
	 * @throws IOException passes the generated exception to the calling method.
	 */
	public void signUp(ActionEvent event) throws IOException {
		runSignUp(signUp.getTypeReq());

	}

	/**
	 * This function is used to Sign up a user. It just takes username and password
	 * insert from the user and send it to the server that will check them.
	 * 
	 * @param reqvalue an integer value
	 */
	private void runSignUp(int reqvalue) {
		try {
			pwd = userPassword.getText();
			userName = userUsername.getText();
			// Socket client = new Socket(SHOST, SPORT);
			MemberService memberService = new MemberService();
			ResponseSignMember rs = memberService.signUp(userName, pwd, reqvalue);

			if (rs.getReq() == 1) {
				title = "Sign Up";
				headerText = "Your credential are correct";
				contentText = "You will now be redirected to the interface intended\n for Soci, click OK to continue  ";

				if (printMessageClass.displayOperationSuccess(title, headerText, contentText)) {

					Member member = new Member(userName, pwd, rs.getId());
					MemberHomeController.setData(member);
					root = FXMLLoader.load(getClass().getResource("/graficaMemberFXML/HomeMember.fxml"));
					stage = (Stage) memberSignInAnchorPane.getScene().getWindow();
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
	 * This function is used to Sign In a user. It just takes
	 * username,password,fiscal code and address insert from the user and send it to
	 * the server that will store them.
	 * 
	 * @param reqvalue an integer value
	 */
	private void runSignIn(int reqvalue) {
		try {
			pwd = userPassword.getText();
			userName = userUsername.getText();
			indirizzo = indirizzoTextField.getText();
			codicefiscale = codiceFiscaleTextField.getText();
			if (!codicefiscale.isBlank()) {
				FiscalCode fiscalCode = new FiscalCode();
				if (fiscalCode.checkFiscalCode(codicefiscale) == 1) {

				}
			}
			if (pwd.isBlank() || userName.isBlank() || indirizzo.isBlank() || codicefiscale.isBlank()) {
				title = "Sign In form";
				headerText = "Attention";
				contentText = "all field must be filled";
				printMessageClass.errorMessage(title, headerText, contentText);
			} else {
				System.out.println("sono qui");
				FiscalCode fiscalCode = new FiscalCode();
				if (!(fiscalCode.checkFiscalCode(codicefiscale) == 1)) {
					title = "Sign In form";
					headerText = "Attention";
					contentText = "you insert an invalid fiscal code";
					printMessageClass.errorMessage(title, headerText, contentText);
				} else {

					// Socket client = new Socket(SHOST, SPORT);
					MemberService memberService = new MemberService();
					ResponseSignMember rs = memberService.signIn(userName, pwd, reqvalue, indirizzo, codicefiscale);
					if (rs.getReq() == 1) {
						title = "Registrazion";
						headerText = "Registration Completed";
						contentText = "You will now be redirected to the interface intended\n for Soci, click OK to continue  ";
						if (printMessageClass.displayOperationSuccess(title, headerText, contentText)) {
							Member member = new Member(userName, pwd, rs.getId());
							MemberHomeController.setData(member);
							root = FXMLLoader.load(getClass().getResource("/graficaMemberFXML/HomeMember.fxml"));
							stage = (Stage) memberSignInAnchorPane.getScene().getWindow();
							scene = new Scene(root);
							stage.setScene(scene);
							stage.show();
						}
					} else {
						title = "Registration";
						headerText = "There is alredy a user with this username";
						contentText = "Try again, click OK to continue. ";
						printMessageClass.displayOperationFailure(title, headerText, contentText);
					}

					// client.close();
				}
			}
		} catch (IOException e) {
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
		stage = (Stage) memberSignInAnchorPane.getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

}
