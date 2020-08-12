package hu.ak_akademia.book4you.controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import hu.ak_akademia.book4you.entities.certificate.Certificate;
import hu.ak_akademia.book4you.entities.certificate.Certificates;
import hu.ak_akademia.book4you.entities.certificate.CertificatesHandler;
import hu.ak_akademia.book4you.entities.client.Client;
import hu.ak_akademia.book4you.entities.client.Clients;
import hu.ak_akademia.book4you.entities.client.ClientsHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class CashierInquiryController implements Initializable{
	@FXML
	private Label openingBalance;
	
	@FXML
	private Label closedBalance;
	
	@FXML
	private Label totalRevenue;
	
	@FXML
	private Label totalOutlay;
	
	@FXML
	private TableView<Certificate> table;
	
	@FXML
	private ComboBox<Client> clientChooserComboBox;

	private ClientsHandler clientsHandler;
	private ObservableList<Client> clientOptions;
	
	private CertificatesHandler certificatesHandler;
	private ObservableList<Certificate> certificatesData;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setContentOfClientChooserComboBox();
		setTableContent();
	}

	@SuppressWarnings("unchecked")
	private void setTableContent() {
		certificatesHandler = new Certificates("src/hu/ak_akademia/book4you/databases/certificates.bin");
		certificatesData = FXCollections.observableArrayList(certificatesHandler.load());
		
		TableColumn<Certificate, LocalDate> dateColumn = new TableColumn<>("Dátum");
		dateColumn.setMinWidth(60);
		dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
		
		TableColumn<Certificate, LocalDate> certificateIDColumn = new TableColumn<>("B.szám");
		certificateIDColumn.setMinWidth(60);
		certificateIDColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
		
		TableColumn<Certificate, LocalDate> clientColumn = new TableColumn<>("Ügyfél");
		clientColumn.setMinWidth(200);
		clientColumn.setCellValueFactory(new PropertyValueFactory<>("client"));
		
		TableColumn<Certificate, LocalDate> cashierColumn = new TableColumn<>("Pénztáros");
		cashierColumn.setMinWidth(150);
		cashierColumn.setCellValueFactory(new PropertyValueFactory<>("cashier"));
		
		TableColumn<Certificate, LocalDate> titleColumn = new TableColumn<>("Jogcím");
		titleColumn.setMinWidth(80);
		titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
		
		TableColumn<Certificate, LocalDate> balanceColumn = new TableColumn<>("Egyenleg");
		balanceColumn.setMinWidth(80);
		balanceColumn.setCellValueFactory(new PropertyValueFactory<>(""));
		
		TableColumn<Certificate, LocalDate> amountColumn = new TableColumn<>("Összeg");
		amountColumn.setMinWidth(80);
		amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
		
		table.getColumns().addAll(dateColumn, certificateIDColumn, clientColumn, cashierColumn, titleColumn, amountColumn, balanceColumn);
		table.setItems(certificatesData);
	}

	private void setContentOfClientChooserComboBox() {
		clientsHandler = new Clients("src/hu/ak_akademia/book4you/databases/clients.bin");
		clientOptions = FXCollections.observableList(clientsHandler.load());
		clientChooserComboBox.setItems(clientOptions);
	}
}
