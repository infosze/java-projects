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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
	private ComboBox<String> publicSpaceTypeComboBox;

	@FXML
	private TextField houseNumberField;
	
	NameFactory nameFactory = new NameFactory();
	
	private ObservableList<String> publicSpaceTypeOptions;
	private StoreHandler storeHandler;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setContentOfPublicSpaceTypeComboBox();
		fillFieldsWithStoreData(getStoreData());
	}
	
	public void modifyStoreData(ActionEvent event) throws IOException {
		if (isValid()) {
			String fullName = nameFactory.formatName(companyNameField);
			Address address = setAddress();
			Store modifiedStoreData = new Store(fullName, address);
			
			storeHandler.modify(modifiedStoreData);

		}
	}

	private boolean isValid() {
		return Validation.validateName(companyNameField) & Validation.validCountry(countryField)
				& Validation.validPostalCode(postalCodeField) & Validation.validateCity(cityField)
				& Validation.validPubilcSpaceName(publicSpaceNameField) & Validation.validHouseNumber(houseNumberField);
	}
	
	public void resetTextFields(ActionEvent event) throws IOException {
		resetFieldsOnBookStorePage();
	}

	private void resetFieldsOnBookStorePage() {
		companyNameField.setText("");
		countryField.setText("");
		postalCodeField.setText("");
		cityField.setText("");
		publicSpaceNameField.setText("");
		publicSpaceTypeComboBox.getSelectionModel().clearSelection();
		houseNumberField.setText("");
	}
	
	private Address setAddress() {
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

	private Store getStoreData() {
		storeHandler = new BookStore("src/hu/ak_akademia/book4you/databases/owncompany.bin");
		List<Store> storeList = storeHandler.load();
		Store book4UStore = storeList.get(0);
		return book4UStore;
	}

	private void fillFieldsWithStoreData(Store store) {
		companyNameField.setText(store.getName());
		
		countryField.setText(store.getAddress().getCountry());
		postalCodeField.setText(store.getAddress().getPostalCode());
		cityField.setText(store.getAddress().getCity());
		publicSpaceNameField.setText(store.getAddress().getPublicSpaceName());
		publicSpaceTypeComboBox.getSelectionModel().select(store.getAddress().getPublicSpaceType().getValue());
		houseNumberField.setText(store.getAddress().getNumber());
	}

	private void setContentOfPublicSpaceTypeComboBox() {
		publicSpaceTypeOptions = FXCollections.observableArrayList(PublicSpaceType.getAllValues());
		publicSpaceTypeComboBox.setItems(publicSpaceTypeOptions);
	}
}
