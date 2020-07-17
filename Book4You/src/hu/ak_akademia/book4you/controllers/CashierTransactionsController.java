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
	private ComboBox<String> chooseTitle = new ComboBox<>();

	@FXML
	private ComboBox<Client> chooseClient = new ComboBox<>();

	@FXML
	private TextField incomeAmount;

	@FXML
	private RadioButton income;

	@FXML
	private RadioButton outlay;

	private ClientsHandler clientHandler = new Clients("src/hu/ak_akademia/book4you/databases/clients.bin");
	//A 2 ActionEvent metódus hozzáadva az fxml filehoz -> az fxmlhez nem kell hozzányúlni 
	public void createTransaction(ActionEvent event) throws IOException {
		// TODO
		chooseClient.getValue();
		chooseTitle.getValue(); // Ez csak String ebből kell majd csinálti egy Title típust...
		System.out.println("Tesztüzenet");
	}

	public void resetFields(ActionEvent event) throws IOException {
		// TODO
		// Mégse gomb -> alapállapotba teszi a textfieldeket
		System.out.println("Törlés tesztüzenet");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//Ügyfél lenyíló lista feltöltése
		ObservableList<Client> clients = FXCollections.observableArrayList();
		for (var cl : clientHandler.load()) {
			clients.add(cl);
		}
		chooseClient.setItems(clients);
		//Jogcím lenyíló lista feltöltése
		ObservableList<String> titles = FXCollections.observableArrayList();
		for (var t : Title.values()) {
			titles.add(t.getValue());
		}
		chooseTitle.setItems(titles);

		// Tesztelés végett van csak bent!
		// Az aktuálisan belépett user-t így tudjátok elérni.
		System.out.println("session: " + Session.getUser());
	}

}
