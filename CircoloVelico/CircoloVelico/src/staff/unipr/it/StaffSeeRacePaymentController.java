package staff.unipr.it;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;

import enumClass.FeeStaffEnum;
//import javaClass.Constant;
import javaClass.FeePayment;
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
 * The {@code StaffSeeRacePaymentController} class is used to show to a Staff
 * member a table where he see all race payment made with credit card or
 * bonifico.
 * 
 * @author Spagni Alex Zuelli Arianna
 * 
 */
public class StaffSeeRacePaymentController implements Initializable {
	private FeeStaffEnum storagePayment=FeeStaffEnum.GET_ALL_RACE_PAYMENT;
//	private static final Constant constant = new Constant();
	private static Staff staff;
	private Stage stage;
	private Scene scene;
	private Parent root;
	@FXML
	private AnchorPane staffAnchorPaneSeePayment;
	@FXML
	private TableView<FeePayment> staffSeePaymentPrintTableView;
	@FXML
	private TableColumn<FeePayment, Integer> diColumnmember;
	@FXML
	private TableColumn<FeePayment, Date> dateColumnstaff;
	@FXML
	private TableColumn<FeePayment, Integer> priceColumnstaff;
	@FXML
	private TableColumn<FeePayment, String> feeColumnPaied;
//	@FXML
//	private TableColumn<FeePayment, Integer> feeColumnFee;
	@FXML
	private TableColumn<FeePayment, String> paymentTypeColumn;
	private ObservableList<FeePayment> values = FXCollections.observableArrayList();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		System.out.println(staff.getId());
		try {
			// Socket client = new Socket(SHOST, SPORT);
			StaffService staffService = new StaffService();
			ArrayList<FeePayment> feePayments = staffService.getPayments(staff.getId(),
					storagePayment.getTypeReq());
			for (FeePayment i : feePayments) {
				values.add(i);
			}
			for (FeePayment i : values) {
				System.out.println(i.getIdFee() + " " + i.getIdmember());
			}
			diColumnmember.setCellValueFactory(new PropertyValueFactory<FeePayment, Integer>("idmember"));
			dateColumnstaff.setCellValueFactory(new PropertyValueFactory<FeePayment, Date>("date"));
			priceColumnstaff.setCellValueFactory(new PropertyValueFactory<FeePayment, Integer>("price"));
			feeColumnPaied.setCellValueFactory(new PropertyValueFactory<FeePayment, String>("paied"));
//			feeColumnFee.setCellValueFactory(new PropertyValueFactory<FeePayment, Integer>("idFee"));
			paymentTypeColumn.setCellValueFactory(new PropertyValueFactory<FeePayment, String>("paymentType"));
			staffSeePaymentPrintTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
			staffSeePaymentPrintTableView.setItems(values);
			staffSeePaymentPrintTableView.getSelectionModel().selectedItemProperty()
					.addListener(new ChangeListener<FeePayment>() {

						@Override
						public void changed(ObservableValue<? extends FeePayment> arg0, FeePayment arg1,
								FeePayment arg2) {

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
		root = FXMLLoader.load(getClass().getResource("/graficaStaffFXML/HomeStaff.fxml"));
		stage = (Stage) staffAnchorPaneSeePayment.getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * used to keep the id of the staff member who signed in
	 * 
	 * @param p staff object
	 */
	public static void setData(Staff p) {
		staff = p;
	}
}
