package hu.ak_akademia.book4you.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import hu.ak_akademia.book4you.entities.Address;
import hu.ak_akademia.book4you.entities.PublicSpaceType;
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
	private ComboBox<String> publicSpaceTypeComboBoxToAdd = new ComboBox<String>();;

	@FXML
	private ComboBox publicSpaceTypeComboBoxToModify;

	@FXML
	private ComboBox clientChooser;

	@FXML
	private TextField houseNumberFieldToAdd;

	@FXML
	private TextField houseNumberFieldToModify;

	@FXML
	private CheckBox aktivCheckBox;

	@FXML
	private CheckBox checkBox;
	private Random rnd = new Random(); // for testing
	private Address address;

	public void addNewClient(ActionEvent event) throws IOException {
		setAdress();
		EconomicClient newEClient = null;
		NaturalClient newNClient = null;
		ClientsHandler clientHandler = new Clients("src/hu/ak_akademia/book4you/databases/clients.bin");
		if (checkBox.isSelected()) {
			newEClient = new EconomicClient(companyNameFieldToAdd.getText(), "Teszt" + rnd.nextInt(150), address);
			clientHandler.add(newEClient);
			clientHandler.save();
		} else {
			newNClient = new NaturalClient(companyNameFieldToAdd.getText(), "Teszt" + rnd.nextInt(150), address);
			clientHandler.add(newNClient);
			clientHandler.save();
		}
		System.out.println(newNClient); // for testing
		System.out.println(newEClient); // for testing
		companyNameFieldToAdd.setText("");
		countryFieldToAdd.setText("");
		postalCodeFieldToAdd.setText("");
		cityFieldToAdd.setText("");
		publicSpaceNameFieldToAdd.setText("");
		publicSpaceTypeComboBoxToAdd.getSelectionModel().clearSelection();
		houseNumberFieldToAdd.setText("");
	}

	private void setAdress() {
		int zipCode = Integer.parseInt(postalCodeFieldToAdd.getText());
		var spaceType = getType(publicSpaceTypeComboBoxToAdd.getValue());
		int number = Integer.parseInt(houseNumberFieldToAdd.getText());
		address = new Address(countryFieldToAdd.getText(), zipCode, cityFieldToAdd.getText(),
				publicSpaceNameFieldToAdd.getText(), spaceType, number);
	}

	private PublicSpaceType getType(String enumName) {
		for (var pst : PublicSpaceType.values()) {
			if (pst.getName().equals(enumName)) {
				return pst;
			}
		}
		return null;
	}

	public void resetTextFields(ActionEvent event) throws IOException {
		companyNameFieldToAdd.setText("");
		postalCodeFieldToAdd.setText("");
		cityFieldToAdd.setText("");
		publicSpaceNameFieldToAdd.setText("");
		publicSpaceTypeComboBoxToAdd.getSelectionModel().clearSelection();
		houseNumberFieldToAdd.setText("");
		countryFieldToAdd.setText("");

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		for (PublicSpaceType pst : PublicSpaceType.values()) {
			publicSpaceTypeComboBoxToAdd.getItems().addAll(pst.getName());
		}

	}

}
