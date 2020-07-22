package hu.ak_akademia.book4you.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import hu.ak_akademia.book4you.entities.Address;
import hu.ak_akademia.book4you.entities.PublicSpaceType;
import hu.ak_akademia.book4you.entities.client.Client;
import hu.ak_akademia.book4you.entities.client.Clients;
import hu.ak_akademia.book4you.entities.client.ClientsHandler;
import hu.ak_akademia.book4you.entities.client.EconomicClient;
import hu.ak_akademia.book4you.entities.client.NaturalClient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
	private TextField newClientPublicSpaceName;

	@FXML
	private TextField newClientHouseNumber;

	@FXML
	private TextField newClientCountry;

	@FXML
	private ComboBox<String> publicSpaceTypeComboBox;

	@FXML
	private CheckBox checkBox;

	@FXML
	private Label errorMessageLabel;

	private ObservableList<String> publicSpaceTypeOptions;
	private ClientsHandler clientHandler;
	NameFactory nameFactory = new NameFactory();
	private IdentifierFactory identifierFactory = new IdentifierFactory();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		clientHandler = new Clients("src/hu/ak_akademia/book4you/databases/clients.bin");
		setContentOfPublicSpaceTypeComboBoxes();
	}

	public void resetTextFields(ActionEvent event) throws IOException {
		resetFields();
	}

	public void addNewClient(ActionEvent event) throws IOException {
		if (isValid()) {
			String fullName = nameFactory.formatName(newClientFullName);
			String identifier = identifierFactory.generateIdentifier(fullName);
			Address address = setAddress();
			Client newClient = null;

			if (checkBox.isSelected()) {
				newClient = new EconomicClient(fullName, identifier, address);
			} else {
				newClient = new NaturalClient(fullName, identifier, address);
			}

			clientHandler.add(newClient);
			clientHandler.save();

			resetFields();
		}
	}

	private boolean isValid() {
		return Validation.validateName(newClientFullName) & Validation.validCountry(newClientCountry)
				& Validation.validPostalCode(newClientZipCode) & Validation.validateCity(newClientCityName)
				& Validation.validPubilcSpaceName(newClientPublicSpaceName)
				& Validation.validPublicSpaceType(publicSpaceTypeComboBox, errorMessageLabel)
				& Validation.validHouseNumber(newClientHouseNumber);
	}

	private void setContentOfPublicSpaceTypeComboBoxes() {
		publicSpaceTypeOptions = FXCollections.observableArrayList(PublicSpaceType.getAllValues());
		publicSpaceTypeComboBox.setItems(publicSpaceTypeOptions);
	}

	private Address setAddress() {
		String zipCode = newClientZipCode.getText().toUpperCase();
		var spaceType = getType(publicSpaceTypeComboBox.getValue());
		String number = newClientHouseNumber.getText();

		return new Address(nameFactory.formatName(newClientCountry), zipCode, nameFactory.formatName(newClientCityName),
				nameFactory.formatName(newClientPublicSpaceName), spaceType, number);
	}

	// Ez a metódus belekerült a PublicSpaceType enum-ba is...ki lehetne venni
	private PublicSpaceType getType(String enumName) {
		for (var pst : PublicSpaceType.values()) {
			if (pst.getValue().equals(enumName)) {
				return pst;
			}
		}
		return null;
	}

	private void resetFields() {
		newClientFullName.setText("");
		newClientCountry.setText("");
		newClientZipCode.setText("");
		newClientCityName.setText("");
		newClientPublicSpaceName.setText("");
		publicSpaceTypeComboBox.getSelectionModel().clearSelection();
		newClientHouseNumber.setText("");
	}
}
