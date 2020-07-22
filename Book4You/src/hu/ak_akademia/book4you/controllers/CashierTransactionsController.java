package hu.ak_akademia.book4you.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import hu.ak_akademia.book4you.entities.certificate.Title;
import hu.ak_akademia.book4you.entities.client.Client;
import hu.ak_akademia.book4you.entities.client.Clients;
import hu.ak_akademia.book4you.entities.client.ClientsHandler;
import hu.ak_akademia.book4you.login.Session;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class CashierTransactionsController implements Initializable {
	@FXML
	private ComboBox<String> chooseTitleComboBox;

	@FXML
	private ComboBox<Client> chooseClientComboBox;

	@FXML
	private TextField incomeAmount;

	@FXML
	private RadioButton income;

	@FXML
	private RadioButton outlay;

	private ObservableList<String> titleOptions;
	private ObservableList<Client> clientOptions;
	private ClientsHandler clientsHandler;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// Ügyfél lenyíló lista feltöltése
		setContentOfChooseClientComboBox();
		
		// Jogcím lenyíló lista feltöltése
		setContentOfChooseTitleComboBox();

		// Tesztelés végett van csak bent!
		// Az aktuálisan belépett user-t így tudjátok elérni.
		System.out.println("session: " + Session.getUser());
	}

	// A 2 ActionEvent metódus hozzáadva az fxml filehoz -> az fxmlhez nem kell
	// hozzányúlni
	public void createTransaction(ActionEvent event) throws IOException {
		// TODO
		chooseClientComboBox.getValue(); //Ez Client
		chooseTitleComboBox.getValue(); // Ez String
		System.out.println("Tesztüzenet");
	}

	public void resetFields(ActionEvent event) throws IOException {
		// TODO
		// Mégse gomb -> alapállapotba teszi a textfieldeket
		System.out.println("Törlés tesztüzenet");
	}

	private void setContentOfChooseTitleComboBox() {
		titleOptions = FXCollections.observableArrayList(Title.getAllValues());
		chooseTitleComboBox.setItems(titleOptions);
	}

	private void setContentOfChooseClientComboBox() {
		clientsHandler = new Clients("src/hu/ak_akademia/book4you/databases/clients.bin");
		clientOptions = FXCollections.observableArrayList(clientsHandler.load());
		chooseClientComboBox.setItems(clientOptions);
	}
}
