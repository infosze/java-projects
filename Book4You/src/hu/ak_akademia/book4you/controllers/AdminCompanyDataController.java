package hu.ak_akademia.book4you.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import hu.ak_akademia.book4you.entities.Address;
import hu.ak_akademia.book4you.entities.PublicSpaceType;
import hu.ak_akademia.book4you.entities.bookstore.BookStore;
import hu.ak_akademia.book4you.entities.bookstore.Store;
import hu.ak_akademia.book4you.entities.bookstore.StoreHandler;
import hu.ak_akademia.book4you.entities.client.Client;
import hu.ak_akademia.book4you.entities.client.Clients;
import hu.ak_akademia.book4you.entities.client.ClientsHandler;
import hu.ak_akademia.book4you.entities.client.EconomicClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class AdminCompanyDataController implements Initializable {

	@FXML
	private BorderPane rootPane;

	@FXML
	private TextField companyNameField;

	@FXML
	private TextField countryField;

	@FXML
	private TextField postalCodeField;

	@FXML
	private TextField cityField;

	@FXML
	private TextField publicSpaceNameField;

	@FXML
	private ComboBox<String> publicSpaceTypeComboBox = new ComboBox<String>();

	@FXML
	private TextField houseNumberField;
	
	NameFactory nameFactory = new NameFactory();
	StoreHandler storeHandler = new BookStore("src/hu/ak_akademia/book4you/databases/owncompany.bin");
	
	public void addNewOwncompany(ActionEvent event) throws IOException {
		if (Validation.validateName(companyNameField) & Validation.validCountry(countryField)
				& Validation.validPostalCode(postalCodeField) & Validation.validateCity(cityField)
				& Validation.validPubilcSpaceName(publicSpaceNameField) & Validation.validHouseNumber(houseNumberField)) {
			String fullName = nameFactory.formatName(companyNameField);
//			String identifier = identifierFactory.generateIdentifier(fullName);
			Address address = setAdress();
			Store newOwnCompany = new Store(fullName, address);
//			storeHandler.add(newClient);
			storeHandler.modify(newOwnCompany);
//			companyNameField.setText("");
//			countryField.setText("");
//			postalCodeField.setText("");
//			cityField.setText("");
//			publicSpaceNameField.setText("");
//			publicSpaceTypeComboBox.getSelectionModel().clearSelection();
//			houseNumberField.setText("");
		}
	}
	
	public void resetTextFields(ActionEvent event) throws IOException {
		companyNameField.setText("");
		countryField.setText("");
		postalCodeField.setText("");
		cityField.setText("");
		publicSpaceNameField.setText("");
		publicSpaceTypeComboBox.getSelectionModel().clearSelection();
		houseNumberField.setText("");
	}
	
	private Address setAdress() {
		String zipCode = postalCodeField.getText().toUpperCase();
		var spaceType = getType(publicSpaceTypeComboBox.getValue());
		String number = houseNumberField.getText();
		return new Address(nameFactory.formatName(countryField), zipCode, nameFactory.formatName(cityField),
				nameFactory.formatName(publicSpaceNameField), spaceType, number);
	}
	
	private PublicSpaceType getType(String enumName) {
		for (var pst : PublicSpaceType.values()) {
			if (pst.getValue().equals(enumName)) {
				return pst;
			}
		}
		return null;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		StoreHandler storeHandler = new BookStore("src/hu/ak_akademia/book4you/databases/owncompany.bin");
		List<Store> storeList = storeHandler.load();
		Store ownCompany = storeList.get(0);
		companyNameField.setText(ownCompany.getName());
		Address ownCompanyAddress = ownCompany.getAddress();
		countryField.setText(ownCompanyAddress.getCountry());
		postalCodeField.setText(ownCompanyAddress.getPostalCode() + "");
		cityField.setText(ownCompanyAddress.getCity());
		publicSpaceNameField.setText(ownCompanyAddress.getPublicSpaceName());
		publicSpaceTypeComboBox.getSelectionModel().select(ownCompanyAddress.getPublicSpaceType().getValue());
		houseNumberField.setText(ownCompanyAddress.getNumber() + "");
		for (PublicSpaceType pst : PublicSpaceType.values()) {
			publicSpaceTypeComboBox.getItems().addAll(pst.getValue());
		}
	}

}
