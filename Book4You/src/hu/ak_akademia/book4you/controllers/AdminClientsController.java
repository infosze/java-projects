package hu.ak_akademia.book4you.controllers;


import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class AdminClientsController {
	@FXML
	private BorderPane rootPane;

	@FXML
	private TextField clientNameFieldToAdd;
	
	@FXML
	private TextField clientNameFieldToModify;
	
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
	private ComboBox publicSpaceTypeComboBoxToAdd;
	
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
	
	
	
	
}
