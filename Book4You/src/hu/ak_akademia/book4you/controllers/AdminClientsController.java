package hu.ak_akademia.book4you.controllers;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import hu.ak_akademia.book4you.entities.Address;
import hu.ak_akademia.book4you.entities.FullName;
import hu.ak_akademia.book4you.entities.PublicSpaceType;
import hu.ak_akademia.book4you.entities.client.EconomicClient;
import hu.ak_akademia.book4you.entities.client.NaturalClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class AdminClientsController implements Initializable{
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
	
	private FullName fullName;
	private Address address;
	
	public void addNewClient(ActionEvent event) throws IOException {
		setAdress();
		if(checkBox.isSelected()) {
			var ecoClient = new EconomicClient(companyNameFieldToAdd.getText(), address);
		}else {
			setName();
			var newClient = new NaturalClient(fullName, address);	
			System.out.println(newClient.toString());
		}
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
		address = new Address(countryFieldToAdd.getText(),zipCode,cityFieldToAdd.getText(),publicSpaceNameFieldToAdd.getText(),spaceType , number);
	}
	
	private PublicSpaceType getType(String enumName) {
		for(var pst : PublicSpaceType.values()) {
			if(pst.getName().equals(enumName)) {
				return pst;
			}
		}
		return null;
	}
	private void setName() {
		String[] name = companyNameFieldToAdd.getText().split(" ");
		if(name.length <=2) {
			fullName = new FullName(name[0],"",name[1]);
		}else {
		 fullName = new FullName(name[0],name[1],name[2]);
		}
	}
	
	public void resetTextFields(ActionEvent event) throws IOException{
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
		for(PublicSpaceType pst : PublicSpaceType.values()) {
		publicSpaceTypeComboBoxToAdd.getItems().addAll(pst.getName());
		}
		
		

	}
	
	
}
