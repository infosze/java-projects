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

	public void chooseClient(ActionEvent event) throws IOException {
		if(clientChooserComboBox.getValue() != null) {
			setDisableStateOfTextFields(false);
			fillFieldsWithChoosenClientData();
		}else {
			setDisableStateOfTextFields(true);
		}
	}
	
	public void resetFields(ActionEvent event) throws IOException {
		resetFieldsOnClientModificationPage();
		resetFieldsOnClientAdditionPage();
	}
	
	public void saveEditedClient(ActionEvent event) throws IOException {
		if (isValidToModification()) {
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
		}
	}

	public void saveNewClient(ActionEvent event) throws IOException {
		if (isValidToAddition()) {
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
		}
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
		publicSpaceTypeComboBoxToModify.getSelectionModel().select(clientChooserComboBox.getValue().getAddress().getPublicSpaceType().getValue());
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
	
	private boolean isValidToAddition() {
		return Validation.validateName(companyNameFieldToAdd) & Validation.validCountry(countryFieldToAdd)
				& Validation.validPostalCode(postalCodeFieldToAdd) & Validation.validateCity(cityFieldToAdd)
				& Validation.validPubilcSpaceName(publicSpaceNameFieldToAdd)
				& Validation.validPublicSpaceType(publicSpaceTypeComboBoxToAdd, errorMessageLabel)
				& Validation.validHouseNumber(houseNumberFieldToAdd);
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
			if (pst.getValue().equals(enumName)) {
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
