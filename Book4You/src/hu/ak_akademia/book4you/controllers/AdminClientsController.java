package hu.ak_akademia.book4you.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Random;
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
	private ComboBox<String> publicSpaceTypeComboBoxToAdd = new ComboBox<String>();

	@FXML
	private ComboBox<String> publicSpaceTypeComboBoxToModify = new ComboBox<String>();

	@FXML
	private ComboBox<String> clientChooser = new ComboBox<>();
//  Meglátom a végén melyik lesz a befutó, egyelnőre String a lista az frissítések miatt	
//	@FXML   
//	private ComboBox<Client> clientChooser = new ComboBox<>();

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
	private Random rnd = new Random(); // for testing
	private ClientsHandler clientHandler = new Clients("src/hu/ak_akademia/book4you/databases/clients.bin");
	private Client selectedClient;
	private IdentifierFactory identifier;

	public void editClient(ActionEvent event) throws IOException {
		setTextFieldsDisableFalse();
		selectedClient = identifyClient();
		companyNameFieldToModify.setText(selectedClient.getName());
		Address clientAddress = selectedClient.getAddress();
		countryFieldToModify.setText(clientAddress.getCountry());
		postalCodeFieldToModify.setText(clientAddress.getPostalCode() + "");
		cityFieldToModify.setText(clientAddress.getCity());
		publicSpaceNameFieldToModify.setText(clientAddress.getPublicSpaceName());
		houseNumberFieldToModify.setText(clientAddress.getNumber() + "");
		publicSpaceTypeComboBoxToModify.getSelectionModel().select(clientAddress.getPublicSpaceType().getValue());
	}

	private void setTextFieldsDisableFalse() {
		companyNameFieldToModify.setDisable(false);
		countryFieldToModify.setDisable(false);
		postalCodeFieldToModify.setDisable(false);
		cityFieldToModify.setDisable(false);
		publicSpaceNameFieldToModify.setDisable(false);
		houseNumberFieldToModify.setDisable(false);
		publicSpaceTypeComboBoxToModify.setDisable(false);
	}

	public void saveEditedClient(ActionEvent event) throws IOException {
		if (!companyNameFieldToModify.getText().equals("")) { // Ezt javítani kell -gyors megoldás- Valudáció? ha nem
																// akkor manuálisan megoldom -Üres textfield mentése ->
																// Hibaüzenet
			Address modifiedAddress = setModifiedAdress();
			Client modified = null;
			if (selectedClient instanceof NaturalClient) {
				modified = new NaturalClient(companyNameFieldToModify.getText(), selectedClient.getID(),
						modifiedAddress);
			} else if (selectedClient instanceof EconomicClient) {
				modified = new EconomicClient(companyNameFieldToModify.getText(), selectedClient.getID(),
						modifiedAddress);
			}
			clientHandler.modify(selectedClient, modified);
			clientHandler.save();
		}
	}

	public void addNewClient(ActionEvent event) throws IOException {
		Address address = setAdress();
		Client newClient = null;
		if (checkBox.isSelected()) {
			newClient = new EconomicClient(companyNameFieldToAdd.getText(),
					identifier.generateIdentifier(companyNameFieldToAdd.getText()), address);
		} else {
			newClient = new NaturalClient(companyNameFieldToAdd.getText(), "Teszt" + rnd.nextInt(150), address);
		}
		clientHandler.add(newClient);
		clientHandler.save();
		companyNameFieldToAdd.setText("");
		countryFieldToAdd.setText("");
		postalCodeFieldToAdd.setText("");
		cityFieldToAdd.setText("");
		publicSpaceNameFieldToAdd.setText("");
		publicSpaceTypeComboBoxToAdd.getSelectionModel().clearSelection();
		houseNumberFieldToAdd.setText("");
	}

	private Address setAdress() {
		int zipCode = Integer.parseInt(postalCodeFieldToAdd.getText());
		var spaceType = getType(publicSpaceTypeComboBoxToAdd.getValue());
		int number = Integer.parseInt(houseNumberFieldToAdd.getText());
		return new Address(countryFieldToAdd.getText(), zipCode, cityFieldToAdd.getText(),
				publicSpaceNameFieldToAdd.getText(), spaceType, number);
	}

	private Address setModifiedAdress() {
		int zipCode = Integer.parseInt(postalCodeFieldToModify.getText());
		var spaceType = getType(publicSpaceTypeComboBoxToModify.getValue());
		int number = Integer.parseInt(houseNumberFieldToModify.getText());
		return new Address(countryFieldToModify.getText(), zipCode, cityFieldToModify.getText(),
				publicSpaceNameFieldToModify.getText(), spaceType, number);
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
		companyNameFieldToAdd.setText("");
		companyNameFieldToModify.setText("");
		postalCodeFieldToAdd.setText("");
		postalCodeFieldToModify.setText("");
		cityFieldToAdd.setText("");
		cityFieldToModify.setText("");
		publicSpaceNameFieldToAdd.setText("");
		publicSpaceNameFieldToModify.setText("");
		publicSpaceTypeComboBoxToAdd.getSelectionModel().clearSelection();
		publicSpaceTypeComboBoxToModify.getSelectionModel().clearSelection();
		houseNumberFieldToAdd.setText("");
		houseNumberFieldToModify.setText("");
		countryFieldToAdd.setText("");
		countryFieldToModify.setText("");

	}

	private Client identifyClient() {
		String[] valueOfComboBox = clientChooser.getValue().split(" ");
		return switch (valueOfComboBox.length) {
		case 3 -> clientHandler.getClient(valueOfComboBox[2]);
		case 4 -> clientHandler.getClient(valueOfComboBox[3]);
		default -> null;
		};
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		for (PublicSpaceType pst : PublicSpaceType.values()) {
			publicSpaceTypeComboBoxToAdd.getItems().addAll(pst.getValue());
			publicSpaceTypeComboBoxToModify.getItems().addAll(pst.getValue());
		}

		ObservableList<String> ls = FXCollections.observableArrayList();
		// ObservableList<Client> ls2 = FXCollections.observableArrayList();
//		for (var cl : clientHandler.load()) {
//			ls2.add(cl);
//		}
		//clientChooser.setItems(ls2);
		for (var cl : clientHandler.load()) {
			ls.add(cl.getName() + " " + cl.getID());
		}
		clientChooser.setItems(ls);

	}

}
