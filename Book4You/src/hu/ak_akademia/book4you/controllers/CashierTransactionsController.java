package hu.ak_akademia.book4you.controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;

import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;


import hu.ak_akademia.book4you.entities.certificate.Certificate;
import hu.ak_akademia.book4you.entities.certificate.Certificates;
import hu.ak_akademia.book4you.entities.certificate.CertificatesHandler;
import hu.ak_akademia.book4you.entities.certificate.Direction;
import hu.ak_akademia.book4you.entities.certificate.Title;
import hu.ak_akademia.book4you.entities.client.Client;
import hu.ak_akademia.book4you.entities.client.Clients;
import hu.ak_akademia.book4you.entities.client.ClientsHandler;
import hu.ak_akademia.book4you.entities.user.Cashier;
import hu.ak_akademia.book4you.login.Session;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class CashierTransactionsController implements Initializable {
	@FXML
	private ComboBox<String> chooseTitleComboBox;

	@FXML
	private ComboBox<Client> chooseClientComboBox;

	@FXML
	private TextField incomeAmount;

	@FXML
	private TextField comment;

	private ObservableList<String> titleOptions;
	private ObservableList<Client> clientOptions;
	private List<Certificate> certificates;
	private ClientsHandler clientsHandler;
	private String certificateURL = "src/hu/ak_akademia/book4you/databases/certificates.bin";
	private String clientsURL = "src/hu/ak_akademia/book4you/databases/clients.bin";
	private Alert alert = new Alert(AlertType.ERROR);

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// Ügyfél lenyíló lista feltöltése
		setContentOfChooseClientComboBox();

		// Jogcím lenyíló lista feltöltése
		setContentOfChooseTitleComboBox();

		certificates = getCertificates();

		// Tesztelés végett van csak bent!
		// Az aktuálisan belépett user-t így tudjátok elérni.
		System.out.println("session: " + Session.getUser());
	}

	// A 2 ActionEvent metódus hozzáadva az fxml filehoz -> az fxmlhez nem kell
	// hozzányúlni
	public void createTransaction(ActionEvent event) throws IOException {
		Certificate certificate;
		try {
			certificate = createCertificate();
		} catch (IllegalArgumentException ex) {
			return;
		}

		saveCertificate(certificate);
		resetText();
	}

	public void resetFields(ActionEvent event) throws IOException {
		resetText();
	}

	private void resetText() {
		chooseTitleComboBox.getSelectionModel().clearSelection();
		chooseClientComboBox.getSelectionModel().clearSelection();
		comment.setText("");
		incomeAmount.setText("");
	}

	private void saveCertificate(Certificate certificate) {
		CertificatesHandler handler = new Certificates(certificateURL);
		handler.add(certificate);
		handler.save();
	}

	private Certificate createCertificate() {
		Client client = chooseClientComboBox.getValue();
		Title title = Title.getTitle(chooseTitleComboBox.getValue());
		if (!(checkNullValue(client) && checkNullValue(title))) {
			throw new IllegalArgumentException();
		}

		int amount = 0;
		try {
			amount = parse(incomeAmount.getText());
		} catch (NumberFormatException e) {
			alert.setHeaderText("");
			alert.setTitle("Nem megfelelő paraméter");
			alert.setContentText("Kérlek pozitív egész számot adj meg!");
			alert.show();
			throw new IllegalArgumentException();
		}

		Cashier ch = (Cashier) Session.getUser();
		Direction dir = title.getDirection();
		long actualBalance = getActualBalance();
		long balance = dir == Direction.INCOME ? actualBalance + amount : actualBalance - amount;
		String certificateComment = comment.getText();

		Certificate certificate = new Certificate(getCertificateNumber(), LocalDate.now(), ch, dir, client, amount,
				title, certificateComment, balance);

		return certificate;
	}

	private int parse(String str) {
		int number = 0;
		number = Integer.parseInt(str);
		if (number <= 0) {
			throw new NumberFormatException();
		}
		return number;

	}

	private boolean checkNullValue(Object o) {
		if (Objects.equals(o, null)) {
			alert.setContentText("Kérlek válassz a listából!");
			alert.setHeaderText("");
			alert.setTitle("Nem megfelelő paraméter");
			alert.show();
			return false;
		}
		return true;
	}

	private int getCertificateNumber() {

		return certificates.size() + 1;
	}

	private long getActualBalance() {

		return certificates.get(certificates.size() - 1).getBalance();
	}

	private List<Certificate> getCertificates() {
		CertificatesHandler certificatesHandler = new Certificates(certificateURL);
		return certificatesHandler.get();
	}

	private void setContentOfChooseTitleComboBox() {
		titleOptions = FXCollections.observableArrayList(Title.getAllValues());
		chooseTitleComboBox.setItems(titleOptions);
	}

	private void setContentOfChooseClientComboBox() {
		clientsHandler = new Clients(clientsURL);
		clientOptions = FXCollections.observableArrayList(clientsHandler.load());
		chooseClientComboBox.setItems(clientOptions);
	}
}
