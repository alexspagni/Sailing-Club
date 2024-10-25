package CircoloVelico.unipr.it;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;

import enumClass.FeeMemberEnum;
import javaClass.FeePayment;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * The {@code memberPayMembershipFeeController} class is used to show to a
 * member a table where he can See all membership fee he must pay.
 * 
 * @author Spagni Alex Zuelli Arianna
 * 
 */
public class MemberPayMembershipFeeController implements Initializable {
	private FeeMemberEnum getFeeMembership=FeeMemberEnum.GET_FEE_MEMBERSHIP;
	private FeeMemberEnum payFeeMembership=FeeMemberEnum.PAY_FEE_MEMBERSHIP;
//	private final int getFeeMembership = 0;
//	private final int payFeeMembership = 1;
	private final int bonifico = 0;
	private final int creditCard = 1;
	private static Member member;
	private PrintMessageClass printMessageClass = new PrintMessageClass();
	private Stage stage;
	private Scene scene;
	private Parent root;
	@FXML
	private AnchorPane memberAnchorPanePayMembershipFee;
	@FXML
	private TableView<FeePayment> memberPayMembershipFeePrintTableView;
	@FXML
	private TableColumn<FeePayment, Date> dateColumn;
	@FXML
	private TableColumn<FeePayment, Integer> priceFeeColumn;
	@FXML
	private TextField feeSelectedTextField;
	@FXML
	private RadioButton bonificoRadioButton, cartaDiCreditoRadioButton;
	private String title, headerText, contentText;
	private ObservableList<FeePayment> values = FXCollections.observableArrayList();
	private FeePayment feeTemp = new FeePayment();

	/**
	 * This function is used from the GUI and allows the user to pay his fees. The
	 * only requirement of this fee, is that user must select a fee to pay. The
	 * Request object that contains data, which must be send to the server, is into
	 * {@code MemberService}.
	 * 
	 * @param event Action Event parameter.
	 */
	public void payFee(ActionEvent event) {
		try {
			// System.out.println("sono qui");
			// Socket client = new Socket(SHOST, SPORT);
			MemberService memberService = new MemberService();
			String feeSelected = feeSelectedTextField.getText();
			if (feeSelected.isBlank()) {
				title = "Membership fee payment form";
				headerText = "Error";
				contentText = "In order to pay a fee, you must select one";
				printMessageClass.errorMessage(title, headerText, contentText);
			} else {
				int ris = -1;
				if (bonificoRadioButton.isSelected()) {
					ris = memberService.payMembershipFee(feeTemp.getIdFee(), payFeeMembership.getTypeReq(), bonifico);
				} else {
					ris = memberService.payMembershipFee(feeTemp.getIdFee(), payFeeMembership.getTypeReq(), creditCard);

				}
				if (ris >= 0) {
					title = "Membership fee payment form";
					headerText = "Fee membership payment";
					contentText = "Payment done, click OK to continue  ";

					if (printMessageClass.displayOperationSuccess(title, headerText, contentText)) {
						backToPrincipalMenu(event);
					}
				} else {
					title = "Membership fee payment form";
					headerText = "Fee membership payment";
					contentText = "error in fee payment, click OK to continue  ";
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

		try {
			// System.out.println("sono qui");
			// Socket client = new Socket(SHOST, SPORT);
			MemberService memberService = new MemberService();
			ArrayList<FeePayment> payment = memberService.getMembershipFee(member.getId(), getFeeMembership.getTypeReq());

			for (FeePayment i : payment) {
				values.add(i);
			}
			dateColumn.setCellValueFactory(new PropertyValueFactory<FeePayment, Date>("date"));
			priceFeeColumn.setCellValueFactory(new PropertyValueFactory<FeePayment, Integer>("price"));
			memberPayMembershipFeePrintTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
			memberPayMembershipFeePrintTableView.setItems(values);
			memberPayMembershipFeePrintTableView.getSelectionModel().selectedItemProperty()
					.addListener(new ChangeListener<FeePayment>() {

						@Override
						public void changed(ObservableValue<? extends FeePayment> arg0, FeePayment arg1,
								FeePayment arg2) {
							feeTemp = memberPayMembershipFeePrintTableView.getSelectionModel().getSelectedItem();
							feeSelectedTextField.setText(String.format("%1$tY-%1$tm-%1$td", feeTemp.getDate()));
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
		stage = (Stage) memberAnchorPanePayMembershipFee.getScene().getWindow();
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