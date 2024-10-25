package javaClass;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
/**
 * The {@code PrintMessageClass} class is used to print alert messages.
 * 
 * @author Spagni Alex Zuelli Arianna
 * 
 */
public class PrintMessageClass {
	/**
	 * This method is used to print an Alert message
	 * @param title title of the message
	 * @param headerText header of the message
	 * @param contentText content of the messagge
	 * @return
	 * It returns a boolean value.
	 */
	public boolean displayOperationSuccess(String title,String headerText,String contentText) {
		Alert alert = new Alert(AlertType.CONFIRMATION);// per fare una finestra di aler dove l'utente può decidere se chiudere oppure no la finestra
	 	alert.setTitle(title);
	 	alert.setHeaderText(headerText);
	 	alert.setContentText(contentText);
	 	if(alert.showAndWait().get()== ButtonType.OK)
	 	{
	 		return true;
	 	}
	 	return false;
	}
	 /**
	 * This method is used to print an Alert message
	 * @param title title of the message
	 * @param headerText header of the message
	 * @param contentText content of the messagge
	 * @return
	 * It returns a boolean value.
	 */
	public boolean displayOperationFailure(String title,String headerText,String contentText)
	 {
		 Alert alert = new Alert(AlertType.CONFIRMATION);// per fare una finestra di aler dove l'utente può decidere se chiudere oppure no la finestra
		 	alert.setTitle(title);
		 	alert.setHeaderText(headerText);
		 	alert.setContentText(contentText);
		 	if(alert.showAndWait().get()== ButtonType.OK)
		 	{
		 		return true;
		 	}
		 	return false;
	 }
	 /**
	 * This method is used to print an Alert message
	 * @param title title of the message
	 * @param headerText header of the message
	 * @param contentText content of the messagge
	 * @return
	 * It returns a boolean value.
	 */
	public boolean errorMessage(String title,String headerText,String contentText)
		{
			Alert alert = new Alert(AlertType.ERROR);// per fare una finestra di aler dove l'utente può decidere se chiudere oppure no la finestra
			alert.setTitle(title);
			alert.setHeaderText(headerText);
			alert.setContentText(contentText);
			if(alert.showAndWait().get()== ButtonType.OK)
			{
				return true;
			}
			return false;
		}
}
