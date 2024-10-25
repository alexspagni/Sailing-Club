package CircoloVelico.unipr.it;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * The {@code EntryStageClient} class contains methods that allow the Client to
 * Sign up/in if he is a Member or just Sign up if he is staff of the club,
 * and the recall of the various interfaces that will allow the various users of
 * the system to interact with the program.
 * 
 * @author Spagni Alex Zuelli Arianna
 * 
 */
public class EntryStageClient extends Application {

	private Stage stage;
	private Scene scene;
	private Parent root;

	@FXML
	private AnchorPane myAnchorPane;

	@Override
	public void start(Stage primaryStage) throws Exception {
//		FiscalCode codiceFiscale=new FiscalCode();
//		codiceFiscale.checkFiscalCode("SPGLXC11T11DC54C");
		Parent root = FXMLLoader.load(getClass().getResource("/graficaMemberFXML/Entry.fxml"));
		primaryStage.setResizable(false);
		primaryStage.setTitle("Client");
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}

	/**
	 * This method is used to allow the Client-->Member to Sign In
	 * 
	 * @param event Action Event parameter.
	 * 
	 * @throws IOException passes the generated exception to the calling method.
	 */
	public void signIn(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("/graficaMemberFXML/MemberSignIn.fxml"));
		stage = (Stage) myAnchorPane.getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * This method is used to allow the Client--> Staff, to Sign In
	 * 
	 * @param event Action Event parameter.
	 * 
	 * @throws IOException passes the generated exception to the calling method.
	 */
	public void staffSignIn(ActionEvent event) throws IOException {

		root = FXMLLoader.load(getClass().getResource("/graficaStaffFXML/StaffSignUp.fxml"));
		stage = (Stage) myAnchorPane.getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * This method is used to allow the client-->Member, to Sign Up
	 * 
	 * @param event Action Event parameter.
	 * 
	 * @throws IOException passes the generated exception to the calling method.
	 */
	public void signUp(ActionEvent event) throws IOException {

		root = FXMLLoader.load(getClass().getResource("/graficaMemberFXML/MemberSignUp.fxml"));
		stage = (Stage) myAnchorPane.getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * Main method
	 * 
	 * @param args main
	 */
	public static void main(String[] args) {

		launch(args);
	}

}
