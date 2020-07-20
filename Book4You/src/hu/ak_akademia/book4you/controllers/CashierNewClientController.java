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
import javafx.scene.control.Label;
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

	@FXML
	private Label errorMessageLabel;

	private Random rnd = new Random(); // for testing
	private ClientsHandler clientHandler = new Clients("src/hu/ak_akademia/book4you/databases/clients.bin");
	NameFactory nameFactory = new NameFactory();
	private IdentifierFactory identifierFactory = new IdentifierFactory();

	public void addNewClient(ActionEvent event) throws IOException {
		if (Validation.validateName(newClientFullName) & Validation.validCountry(newClientCountry) & Validation.validPostalCode(newClientZipCode)
				& Validation.validateCity(newClientCityName) & Validation.validPubilcSpaceName(newClientAdressName)
				& Validation.validPublicSpaceType(clientAdressType, errorMessageLabel) & Validation.validHouseNumber(newClientHouseNumber)) {
			String fullName = nameFactory.formatName(newClientFullName);
			String identifier = identifierFactory.generateIdentifier(fullName);
			Address address = setAdress();
			Client newClient = null;
//		ClientsHandler clientHandler = new Clients("src/hu/ak_akademia/book4you/databases/clients.bin");
			if (checkBox.isSelected()) {
				newClient = new EconomicClient(fullName, identifier, address);
			} else {
				newClient = new NaturalClient(fullName, identifier, address);
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
	}

	private Address setAdress() {
		String zipCode = newClientZipCode.getText().toUpperCase();
		var spaceType = getType(clientAdressType.getValue());
		String number = newClientHouseNumber.getText();
		return new Address(nameFactory.formatName(newClientCountry), zipCode, nameFactory.formatName(newClientCityName),
				nameFactory.formatName(newClientAdressName), spaceType, number);
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
