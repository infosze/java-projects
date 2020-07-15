package hu.ak_akademia.book4you.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import hu.ak_akademia.book4you.entities.Address;
import hu.ak_akademia.book4you.entities.PublicSpaceType;
import hu.ak_akademia.book4you.entities.client.Client;
import hu.ak_akademia.book4you.entities.client.Clients;
import hu.ak_akademia.book4you.entities.client.ClientsHandler;
import hu.ak_akademia.book4you.entities.client.EconomicClient;
import hu.ak_akademia.book4you.entities.client.NaturalClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;

import javafx.scene.control.TextField;

public class CashierNewClientController implements Initializable {

	@FXML
	private TextField newClientFullName;

	@FXML
	private TextField newClientZipCode;

	@FXML
	private TextField newClientCityName;

	@FXML
	private TextField newClientAdressName;

	@FXML
	private TextField newClientHouseNumber;

	@FXML
	private TextField newClientCountry;

	@FXML
	private ComboBox<String> clientAdressType = new ComboBox<String>();

	@FXML
	private CheckBox checkBox;

	private Random rnd = new Random(); // for testing

	public void addNewClient(ActionEvent event) throws IOException {
		Address address =setAdress();
		Client newClient = null;
		ClientsHandler clientHandler = new Clients("src/hu/ak_akademia/book4you/databases/clients.bin");
		if (checkBox.isSelected()) {
			newClient = new EconomicClient(newClientFullName.getText(), "Teszt" + rnd.nextInt(150), address);
		} else {
			newClient = new NaturalClient(newClientFullName.getText(), "Teszt" + rnd.nextInt(150), address);
		}
		clientHandler.add(newClient);
		clientHandler.save();
		newClientFullName.setText("");
		newClientCountry.setText("");
		newClientZipCode.setText("");
		newClientCityName.setText("");
		newClientAdressName.setText("");
		clientAdressType.getSelectionModel().clearSelection();
		newClientHouseNumber.setText("");
	}

	private Address setAdress() {
		int zipCode = Integer.parseInt(newClientZipCode.getText());
		var spaceType = getType(clientAdressType.getValue());
		int number = Integer.parseInt(newClientHouseNumber.getText());
		return  new Address(newClientCountry.getText(), zipCode, newClientCityName.getText(),
				newClientAdressName.getText(), spaceType, number);
	}

	private PublicSpaceType getType(String enumName) {
		for (var pst : PublicSpaceType.values()) {
			if (pst.getValue().equals(enumName)) {
				return pst;
			}
		}
		return null;
	}

	public void resetTextFields(ActionEvent event) throws IOException {
		newClientFullName.setText("");
		newClientZipCode.setText("");
		newClientCityName.setText("");
		newClientAdressName.setText("");
		clientAdressType.getSelectionModel().clearSelection();
		newClientHouseNumber.setText("");
		newClientCountry.setText("");

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		for (PublicSpaceType pst : PublicSpaceType.values()) {
			clientAdressType.getItems().addAll(pst.getValue());
		}

	}
}
