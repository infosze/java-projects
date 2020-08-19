package hu.ak_akademia.book4you.controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import hu.ak_akademia.book4you.calculations.Calculation;
import hu.ak_akademia.book4you.calculations.ClosingBalanceCalculation;
import hu.ak_akademia.book4you.calculations.IncomeCalculation;
import hu.ak_akademia.book4you.calculations.OpeningBalanceCalculation;
import hu.ak_akademia.book4you.calculations.OutcomeCalculation;
import hu.ak_akademia.book4you.entities.certificate.Certificate;
import hu.ak_akademia.book4you.entities.certificate.Certificates;
import hu.ak_akademia.book4you.entities.certificate.CertificatesHandler;
import hu.ak_akademia.book4you.entities.certificate.Direction;
import hu.ak_akademia.book4you.entities.client.Client;
import hu.ak_akademia.book4you.entities.client.Clients;
import hu.ak_akademia.book4you.entities.client.ClientsHandler;
import hu.ak_akademia.book4you.entities.user.Cashier;
import hu.ak_akademia.book4you.validation.DateValidator;
import hu.ak_akademia.book4you.validation.ExistenceValidator;
import hu.ak_akademia.book4you.validation.MyAlert;
import hu.ak_akademia.book4you.validation.MyException;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class CashierInquiryController implements Initializable {
	@FXML
	private Label openingBalance;

	@FXML
	private Label closingBalance;

	@FXML
	private Label totalIncome;

	@FXML
	private Label totalOutcome;

	@FXML
	private TableView<Certificate> table;

	@FXML
	private DatePicker dateFrom;

	@FXML
	private DatePicker dateTo;

	@FXML
	private ComboBox<Client> clientChooserComboBox;

	private ObservableList<Certificate> certificatesObservableList;
	private CertificatesHandler certificatesHandler;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setContentOfClientChooserComboBox();

		loadCertificates();
		setCertificatesObservableList(certificatesHandler.get());
		setTableHeaders();
		setTableContent();
		setTableContentAppearance();
		setDatePickers();
		setInfoBar();
	}

	private void setInfoBar() {
		Calculation income = new IncomeCalculation(certificatesObservableList);
		totalIncome.setText(income.format(income.calculate()));

		Calculation outcome = new OutcomeCalculation(certificatesObservableList);
		totalOutcome.setText(outcome.format(outcome.calculate()));

		Calculation opening = new OpeningBalanceCalculation(certificatesObservableList);
		openingBalance.setText(opening.format(opening.calculate()));

		Calculation closing = new ClosingBalanceCalculation(certificatesObservableList);
		closingBalance.setText(closing.format(closing.calculate()));

	}

	public void selectByAllButton(ActionEvent event) throws IOException {
		setCertificatesObservableList(certificatesHandler.get());
		setTableContent();
		resetComboBox();
		resetDatePickers();
		setInfoBar();
	}

	public void selectByClientButton(ActionEvent event) throws IOException {
		List<Certificate> selection;
		try {
			new ExistenceValidator(clientChooserComboBox.getValue(), "Kérem válasszon az ügyfél listából!").validate();

			selection = certificatesHandler.getByClient(clientChooserComboBox.getValue());
			setCertificatesObservableList(selection);
			setTableContent();
			resetDatePickers();
			setInfoBar();
		} catch (MyException e) {
			MyAlert.showError(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void selectByDateButton(ActionEvent event) throws IOException {
		List<Certificate> selection;
		try {
			new DateValidator(dateFrom.getValue(), dateTo.getValue()).validate();

			selection = certificatesHandler.getByDate(dateFrom.getValue(), dateTo.getValue());
			setCertificatesObservableList(selection);
			setTableContent();
			resetComboBox();
			setInfoBar();
		} catch (MyException e) {
			MyAlert.showError(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private void resetComboBox() {
		clientChooserComboBox.getSelectionModel().clearSelection();
	}

	private void setDatePickers() {
		dateFrom.setEditable(false);
		dateTo.setEditable(false);
	}

	private void resetDatePickers() {
		dateFrom.setValue(null);
		dateTo.setValue(null);
	}

	private void setTableContent() {
		table.setItems(certificatesObservableList);
	}

	private void setCertificatesObservableList(List<Certificate> list) {
		certificatesObservableList = FXCollections.observableArrayList(list);
	}

	private void loadCertificates() {
		certificatesHandler = new Certificates("src/hu/ak_akademia/book4you/databases/certificates.bin");
	}

	@SuppressWarnings("unchecked")
	private void setTableHeaders() {
		TableColumn<Certificate, LocalDate> dateColumn = new TableColumn<>("Dátum");
		dateColumn.setMinWidth(60);
		dateColumn.setStyle("-fx-alignment: CENTER;");
		dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

		TableColumn<Certificate, Integer> certificateIDColumn = new TableColumn<>("B.szám");
		certificateIDColumn.setMinWidth(40);
		certificateIDColumn.setStyle("-fx-alignment: CENTER;");
		certificateIDColumn.setCellValueFactory(new PropertyValueFactory<>("number"));

		TableColumn<Certificate, Client> clientColumn = new TableColumn<>("Ügyfél");
		clientColumn.setMinWidth(150);
		clientColumn.setCellValueFactory(new PropertyValueFactory<>("client"));

		TableColumn<Certificate, Cashier> cashierColumn = new TableColumn<>("Pénztáros");
		cashierColumn.setMinWidth(150);
		cashierColumn.setCellValueFactory(new PropertyValueFactory<>("cashier"));

		TableColumn<Certificate, String> titleColumn = new TableColumn<>("Jogcím");
		titleColumn.setMinWidth(80);
		titleColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getTitle().getLongName()));

		TableColumn<Certificate, Integer> balanceColumn = new TableColumn<>("Egyenleg");
		balanceColumn.setMinWidth(80);
		balanceColumn.setStyle("-fx-alignment: CENTER-RIGHT;");
		balanceColumn.setCellValueFactory(new PropertyValueFactory<>("balance"));

		TableColumn<Certificate, Integer> amountColumn = new TableColumn<>("Összeg");
		amountColumn.setMinWidth(80);
		amountColumn.setStyle("-fx-alignment: CENTER-RIGHT;");
		amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));

		table.getColumns().addAll(dateColumn, certificateIDColumn, clientColumn, cashierColumn, titleColumn,
				amountColumn, balanceColumn);
	}

	private void setTableContentAppearance() {
		table.setRowFactory(e -> new TableRow<Certificate>() {
			@Override
			protected void updateItem(Certificate item, boolean empty) {
				super.updateItem(item, empty);
				if (item == null || empty)
					setStyle("");
				else if (item.getDirection() == Direction.INCOME)
					setStyle("-fx-background-color: #baffba;");
				else if (item.getDirection() == Direction.OUTCOME)
					setStyle("-fx-background-color: #ffd7d1;");
				else
					setStyle("");
			}
		});
	}

	private void setContentOfClientChooserComboBox() {
		ClientsHandler clientsHandler = new Clients("src/hu/ak_akademia/book4you/databases/clients.bin");
		ObservableList<Client> clientOptions = FXCollections.observableList(clientsHandler.load());
		clientChooserComboBox.setItems(clientOptions);
	}
}
