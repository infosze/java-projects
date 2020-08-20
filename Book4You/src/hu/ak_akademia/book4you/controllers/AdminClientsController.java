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
import hu.ak_akademia.book4you.validation.CityNameValidator;
import hu.ak_akademia.book4you.validation.CompanyNameValidator;
import hu.ak_akademia.book4you.validation.CountryNameValidator;
import hu.ak_akademia.book4you.validation.ExistenceValidator;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class AdminClientsController implements Initializable {

	@FXML
	private BorderPane rootPane;

	@FXML
	private TextField companyNameFieldToAdd;

	@FXML
	private TextField clientNameFieldToModify;

	@FXML
	private TextField countryFieldToAdd;

	@FXML
	private TextField countryFieldToModify;

	@FXML
	private TextField postalCodeFieldToAdd;

	@FXML
	private TextField postalCodeFieldToModify;

	@FXML
	private TextField cityFieldToAdd;

	@FXML
	private TextField cityFieldToModify;

	@FXML
	private TextField publicSpaceNameFieldToAdd;

	@FXML
	private TextField publicSpaceNameFieldToModify;

	@FXML
	private ComboBox<String> publicSpaceTypeComboBoxToAdd;

	@FXML
	private ComboBox<String> publicSpaceTypeComboBoxToModify;

	@FXML
	private ComboBox<Client> clientChooserComboBox;

	@FXML
	private TextField houseNumberFieldToAdd;

	@FXML
	private TextField houseNumberFieldToModify;

	@FXML
	private TextField companyNameFieldToModify;

	@FXML
	private CheckBox aktivCheckBox;

	@FXML
	private CheckBox checkBox;

	@FXML
	private Label errorMessageLabel;

	@FXML
	private Label errorMessageLabel1;

	private ClientsHandler clientsHandler;
	private ObservableList<String> publicSpaceTypeOptions;
	private ObservableList<Client> clientOptions;

	NameFactory nameFactory = new NameFactory();
	private IdentifierFactory IdentifierFactory;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setContentOfClientChooserComboBox();
		setContentOfPublicSpaceTypeComboBoxes();

		IdentifierFactory = new IdentifierFactory();
	}

	public void chooseClient(ActionEvent event) throws IOException {
		if (clientChooserComboBox.getValue() != null) {
			setDisableStateOfTextFields(false);
			fillFieldsWithChoosenClientData();
		} else {
			setDisableStateOfTextFields(true);
		}
	}

	public void resetFields(ActionEvent event) throws IOException {
		resetFieldsOnClientModificationPage();
		resetFieldsOnClientAdditionPage();
	}

	public void saveEditedClient(ActionEvent event) throws IOException {
			try {
				validateModifyClientFields();
				
				String fullName = nameFactory.formatName(companyNameFieldToModify);
				Address modifiedAddress = setModifiedAdress();
				Client modified = null;
				Client choosen = clientChooserComboBox.getValue();
				
				if (choosen instanceof NaturalClient) {
					modified = new NaturalClient(fullName, choosen.getID(), modifiedAddress);
				} else if (choosen instanceof EconomicClient) {
					modified = new EconomicClient(fullName, choosen.getID(), modifiedAddress);
				}
				
				clientsHandler.modify(choosen, modified);
				clientsHandler.save();
				
				MyAlert.showInformation("Adatok mentése megtörtént");
			} catch (MyException e) {
				MyAlert.showError(e.getMessage());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
	}

	public void saveNewClient(ActionEvent event) throws IOException {
		try {
			validateAddClientFields();

			String fullName = nameFactory.formatName(companyNameFieldToAdd);
			String identifier = IdentifierFactory.generateIdentifier(fullName);
			Address address = setAdress();
			Client newClient = null;

			if (checkBox.isSelected()) {
				newClient = new EconomicClient(fullName, identifier, address);
			} else {
				newClient = new NaturalClient(fullName, identifier, address);
			}
			clientsHandler.add(newClient);
			clientsHandler.save();

			setContentOfClientChooserComboBox();
			resetFieldsOnClientAdditionPage();

			MyAlert.showInformation("Adatok mentése megtörtént");
		} catch (MyException e) {
			MyAlert.showError(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	private void validateAddClientFields() throws Exception {
		new CompanyNameValidator(companyNameFieldToAdd.getText()).validate();
		new CountryNameValidator(countryFieldToAdd.getText()).validate();
		new PostalCodeValidator(postalCodeFieldToAdd.getText()).validate();
		new CityNameValidator(cityFieldToAdd.getText()).validate();
		new PublicSpaceNameValidator(publicSpaceNameFieldToAdd.getText()).validate();
		new ExistenceValidator(publicSpaceTypeComboBoxToAdd.getValue(),
				"Kérem válaszon a közterület jellege listából!").validate();
		new HouseNumberValidator(houseNumberFieldToAdd.getText()).validate();
	}
	
	private void validateModifyClientFields() throws Exception {
		new ExistenceValidator(clientChooserComboBox.getValue(),"Kérem válasszon az ügyfél listából!").validate();;
		new CompanyNameValidator(companyNameFieldToModify.getText()).validate();
		new CountryNameValidator(countryFieldToModify.getText()).validate();
		new PostalCodeValidator(postalCodeFieldToModify.getText()).validate();
		new CityNameValidator(cityFieldToModify.getText()).validate();
		new PublicSpaceNameValidator(publicSpaceNameFieldToModify.getText()).validate();
		new ExistenceValidator(publicSpaceTypeComboBoxToModify.getValue(),
				"Kérem válaszon a közterület jellege listából!").validate();
		new HouseNumberValidator(houseNumberFieldToModify.getText()).validate();
	}

	private void setContentOfPublicSpaceTypeComboBoxes() {
		publicSpaceTypeOptions = FXCollections.observableArrayList(PublicSpaceType.getAllValues());
		publicSpaceTypeComboBoxToAdd.setItems(publicSpaceTypeOptions);
		publicSpaceTypeComboBoxToModify.setItems(publicSpaceTypeOptions);
	}

	private void setContentOfClientChooserComboBox() {
		clientsHandler = new Clients("src/hu/ak_akademia/book4you/databases/clients.bin");

		clientOptions = FXCollections.observableList(clientsHandler.load());
		clientChooserComboBox.setItems(clientOptions);
	}

	private void resetFieldsOnClientModificationPage() {
		clientChooserComboBox.getSelectionModel().clearSelection();
		publicSpaceTypeComboBoxToAdd.getSelectionModel().clearSelection();
		publicSpaceTypeComboBoxToModify.getSelectionModel().clearSelection();

		companyNameFieldToAdd.setText("");
		companyNameFieldToModify.setText("");

		postalCodeFieldToAdd.setText("");
		postalCodeFieldToModify.setText("");

		cityFieldToAdd.setText("");
		cityFieldToModify.setText("");

		publicSpaceNameFieldToAdd.setText("");
		publicSpaceNameFieldToModify.setText("");

		houseNumberFieldToAdd.setText("");
		houseNumberFieldToModify.setText("");

		countryFieldToAdd.setText("");
		countryFieldToModify.setText("");
	}

	private void resetFieldsOnClientAdditionPage() {
		companyNameFieldToAdd.setText("");
		countryFieldToAdd.setText("");
		postalCodeFieldToAdd.setText("");
		cityFieldToAdd.setText("");
		publicSpaceNameFieldToAdd.setText("");
		publicSpaceTypeComboBoxToAdd.getSelectionModel().clearSelection();
		houseNumberFieldToAdd.setText("");
	}

	private void fillFieldsWithChoosenClientData() {
		companyNameFieldToModify.setText(clientChooserComboBox.getValue().getName());
		countryFieldToModify.setText(clientChooserComboBox.getValue().getAddress().getCountry());
		postalCodeFieldToModify.setText(clientChooserComboBox.getValue().getAddress().getPostalCode());
		cityFieldToModify.setText(clientChooserComboBox.getValue().getAddress().getCity());
		publicSpaceNameFieldToModify.setText(clientChooserComboBox.getValue().getAddress().getPublicSpaceName());
		houseNumberFieldToModify.setText(clientChooserComboBox.getValue().getAddress().getNumber());
		publicSpaceTypeComboBoxToModify.getSelectionModel()
				.select(clientChooserComboBox.getValue().getAddress().getPublicSpaceType().getLongName());
	}

	private void setDisableStateOfTextFields(boolean value) {
		companyNameFieldToModify.setDisable(value);
		countryFieldToModify.setDisable(value);
		postalCodeFieldToModify.setDisable(value);
		cityFieldToModify.setDisable(value);
		publicSpaceNameFieldToModify.setDisable(value);
		houseNumberFieldToModify.setDisable(value);
		publicSpaceTypeComboBoxToModify.setDisable(value);
	}

	private boolean isValidToModification() {
		return Validation.validateName(companyNameFieldToModify) & Validation.validCountry(countryFieldToModify)
				& Validation.validPostalCode(postalCodeFieldToModify) & Validation.validateCity(cityFieldToModify)
				& Validation.validPubilcSpaceName(publicSpaceNameFieldToModify)
				& Validation.validPublicSpaceType(publicSpaceTypeComboBoxToModify, errorMessageLabel1)
				& Validation.validHouseNumber(houseNumberFieldToModify);
	}

	private Address setAdress() {
		String zipCode = postalCodeFieldToAdd.getText().toUpperCase();
		var spaceType = getType(publicSpaceTypeComboBoxToAdd.getValue());
		String number = houseNumberFieldToAdd.getText();
		return new Address(nameFactory.formatName(countryFieldToAdd), zipCode, nameFactory.formatName(cityFieldToAdd),
				nameFactory.formatName(publicSpaceNameFieldToAdd), spaceType, number);
	}

	private Address setModifiedAdress() {
		String zipCode = postalCodeFieldToModify.getText().toUpperCase();
		var spaceType = getType(publicSpaceTypeComboBoxToModify.getValue());
		String number = houseNumberFieldToModify.getText();
		return new Address(nameFactory.formatName(countryFieldToModify), zipCode,
				nameFactory.formatName(cityFieldToModify), nameFactory.formatName(publicSpaceNameFieldToModify),
				spaceType, number);
	}

	private PublicSpaceType getType(String enumName) {
		for (var pst : PublicSpaceType.values()) {
			if (pst.getLongName().equals(enumName)) {
				return pst;
			}
		}
		return null;
	}

	public void emptyTextField(ActionEvent event) throws IOException {
		boolean deleteAll = true;
		if (companyNameFieldToAdd.getText().equals(Messages.getErrorMessageEmpty())
				|| companyNameFieldToAdd.getText().equals(Messages.getErrorMessageWrongFormat())) {
			companyNameFieldToAdd.setText("");
			deleteAll = false;
		}
		if (postalCodeFieldToAdd.getText().equals(Messages.getErrorMessageEmpty())
				|| postalCodeFieldToAdd.getText().equals(Messages.getErrorMessageWrongFormat())) {
			postalCodeFieldToAdd.setText("");
			deleteAll = false;
		}
		if (cityFieldToAdd.getText().equals(Messages.getErrorMessageEmpty())
				|| cityFieldToAdd.getText().equals(Messages.getErrorMessageWrongFormat())) {
			cityFieldToAdd.setText("");
			deleteAll = false;
		}
		if (publicSpaceNameFieldToAdd.getText().equals(Messages.getErrorMessageEmpty())
				|| publicSpaceNameFieldToAdd.getText().equals(Messages.getErrorMessageWrongFormat())) {
			publicSpaceNameFieldToAdd.setText("");
			deleteAll = false;
		}
		if (houseNumberFieldToAdd.getText().equals(Messages.getErrorMessageEmpty())
				|| houseNumberFieldToAdd.getText().equals(Messages.getErrorMessageWrongFormat())) {
			houseNumberFieldToAdd.setText("");
			deleteAll = false;
		}
		if (errorMessageLabel.getText().equals(Messages.getErrorMessageNotChosen())) {
			errorMessageLabel.setText("");
			deleteAll = false;
		}
		if (countryFieldToAdd.getText().equals(Messages.getErrorMessageEmpty())
				|| countryFieldToAdd.getText().equals(Messages.getErrorMessageWrongFormat())) {
			countryFieldToAdd.setText("");
			deleteAll = false;
		}
		if (deleteAll) {
			companyNameFieldToAdd.setText("");
			postalCodeFieldToAdd.setText("");
			cityFieldToAdd.setText("");
			publicSpaceNameFieldToAdd.setText("");
			houseNumberFieldToAdd.setText("");
			countryFieldToAdd.setText("");
		}
	}
}
