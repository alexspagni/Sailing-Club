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
 * The {@code memberPayFeeStorageController} class is used to show to a member a
 * table where he can See all his Storage fee he must pay.
 * 
 * @author Spagni Alex Zuelli Arianna
 * 
 */
public class MemberPayStorageFeeController implements Initializable {
	private FeeMemberEnum getFeeStorage=FeeMemberEnum.GET_FEE_STORAGE;
	private FeeMemberEnum payFeeStorage=FeeMemberEnum.PAY_FEE_STORAGE;
//	private final int getFeeStorage = 3;
//	private final int payFeeStorage = 2;
	private final int bonifico = 0;
	private final int creditCard = 1;
	private static Member member;
	private PrintMessageClass printMessageClass = new PrintMessageClass();
	private String title, headerText, contentText;
	private Stage stage;
	private Scene scene;
	private Parent root;
	@FXML
	private AnchorPane memberAnchorPanePayStorageFee;
	@FXML
	private TableView<FeePayment> memberPayStorageFeePrintTableView;
	@FXML
	private TableColumn<FeePayment, Date> dateColumnStorage;
	@FXML
	private TableColumn<FeePayment, Integer> priceFeeColumnStorage;
	@FXML
	private TextField feeSelectedTextField;
	@FXML
	private RadioButton bonificoRadioButtonStorageFee, cartaDiCreditoRadioButtonStorageFee;
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
	public void payFeeStorage(ActionEvent event) {
		try {
			MemberService memberService = new MemberService();
			// System.out.println("sono qui");
			String feeSelected = feeSelectedTextField.getText();
			if (feeSelected.isBlank()) {
				title = "Storage fee payment form";
				headerText = "Error";
				contentText = "In order to pay a fee, you must select one";
				printMessageClass.errorMessage(title, headerText, contentText);
			} else {
				int ris = -1;
				if (bonificoRadioButtonStorageFee.isSelected()) {
					ris = memberService.payMembershipFee(feeTemp.getIdFee(), payFeeStorage.getTypeReq(), bonifico);
				} else {
					ris = memberService.payMembershipFee(feeTemp.getIdFee(), payFeeStorage.getTypeReq(), creditCard);

				}
				if (ris >= 0) {
					title = "Stoage fee payment form";
					headerText = "Fee Storage payment";
					contentText = "Payment done, click OK to continue  ";

					if (printMessageClass.displayOperationSuccess(title, headerText, contentText)) {
						backToPrincipalMenu(event);
					}

				} else {
					title = "Stoage fee payment form";
					headerText = "Fee Storage payment";
					contentText = "error in fee payment, click OK to continue  ";
					printMessageClass.displayOperationFailure(title, headerText, contentText);
					backToPrincipalMenu(event);
				}
			}
		} catch (Exception e) {

		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		System.out.println(member.getId());
		try {
			// Socket client = new Socket(SHOST, SPORT);
			MemberService memberServices = new MemberService();
			ArrayList<FeePayment> payments = memberServices.getStorageFee(member.getId(), getFeeStorage.getTypeReq());
			for (FeePayment i : payments) {
				values.add(i);
			}
			for (FeePayment i : values) {
				System.out.println(i.getIdFee() + " " + i.getIdmember());
			}
			dateColumnStorage.setCellValueFactory(new PropertyValueFactory<FeePayment, Date>("date"));
			priceFeeColumnStorage.setCellValueFactory(new PropertyValueFactory<FeePayment, Integer>("price"));
			memberPayStorageFeePrintTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
			memberPayStorageFeePrintTableView.setItems(values);
			memberPayStorageFeePrintTableView.getSelectionModel().selectedItemProperty()
					.addListener(new ChangeListener<FeePayment>() {

						@Override
						public void changed(ObservableValue<? extends FeePayment> arg0, FeePayment arg1,
								FeePayment arg2) {
							feeTemp = memberPayStorageFeePrintTableView.getSelectionModel().getSelectedItem();
							feeSelectedTextField.setText(String.format("%1$tY-%1$tm-%1$td", feeTemp.getDate()));
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
		stage = (Stage) memberAnchorPanePayStorageFee.getScene().getWindow();
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