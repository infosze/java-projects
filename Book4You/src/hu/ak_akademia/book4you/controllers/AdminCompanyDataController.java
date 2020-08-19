package hu.ak_akademia.book4you.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import hu.ak_akademia.book4you.entities.Address;
import hu.ak_akademia.book4you.entities.PublicSpaceType;
import hu.ak_akademia.book4you.entities.bookstore.BookStore;
import hu.ak_akademia.book4you.entities.bookstore.Store;
import hu.ak_akademia.book4you.entities.bookstore.StoreHandler;
import hu.ak_akademia.book4you.validation.CityNameValidator;
import hu.ak_akademia.book4you.validation.CompanyNameValidator;
import hu.ak_akademia.book4you.validation.CountryNameValidator;
import hu.ak_akademia.book4you.validation.HouseNumberValidator;
import hu.ak_akademia.book4you.validation.MyAlert;
import hu.ak_akademia.book4you.validation.MyException;
import hu.ak_akademia.book4you.validation.PostalCodeValidator;
import hu.ak_akademia.book4you.validation.PublicSpaceNameValidator;
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
		storeHandler = new BookStore("src/hu/ak_akademia/book4you/databases/owncompany.bin");

		setContentOfPublicSpaceTypeComboBox();
		fillFieldsWithStoreData();
	}

	public void saveStoreData(ActionEvent event) throws IOException {
		try {
			validateFields();

			String fullName = nameFactory.formatName(companyNameField);
			Address address = setAddress();
			Store modifiedStoreData = new Store(fullName, address);
			
			storeHandler.modify(modifiedStoreData);
			storeHandler.save();
			
			MyAlert.showInformation("Adatok mentése megtörtént");
		} catch (MyException e) {
			MyAlert.showError(e.getMessage());
		} catch (IllegalStateException e) {
			MyAlert.showError("Nem sikerült betölteni az adatokat, a mentés nem lehetséges!");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void resetTextFields(ActionEvent event) throws IOException {
		resetFieldsOnBookStorePage();
	}

	private void validateFields() throws Exception {
		new CompanyNameValidator(companyNameField.getText()).validate();
		new CountryNameValidator(countryField.getText()).validate();
		new PostalCodeValidator(postalCodeField.getText()).validate();
		new CityNameValidator(cityField.getText()).validate();
		new PublicSpaceNameValidator(publicSpaceNameField.getText()).validate();
		new HouseNumberValidator(houseNumberField.getText()).validate();
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
			if (pst.getLongName().equals(enumName)) {
				return pst;
			}
		}
		return null;
	}

	private Store loadFirstStoreData() {
		return storeHandler.load().get(0);
	}

	private void fillFieldsWithStoreData() {
		Store store = loadFirstStoreData();

		companyNameField.setText(store.getName());

		countryField.setText(store.getAddress().getCountry());
		postalCodeField.setText(store.getAddress().getPostalCode());
		cityField.setText(store.getAddress().getCity());
		publicSpaceNameField.setText(store.getAddress().getPublicSpaceName());
		publicSpaceTypeComboBox.getSelectionModel().select(store.getAddress().getPublicSpaceType().getLongName());
		houseNumberField.setText(store.getAddress().getNumber());
	}

	private void setContentOfPublicSpaceTypeComboBox() {
		publicSpaceTypeOptions = FXCollections.observableArrayList(PublicSpaceType.getAllValues());
		publicSpaceTypeComboBox.setItems(publicSpaceTypeOptions);
	}
}
