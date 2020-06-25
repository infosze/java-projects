package hu.ak_akademia.book4you.controllers;


import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class AdminCashiersController {
	@FXML
	private BorderPane rootPane;

	@FXML
	private TextField fullNameFieldToAdd;
	
	@FXML
	private TextField passwordFieldToAdd;
	
	@FXML
	private TextField passwordToModify;
	
	@FXML
	private ComboBox cashierChooser;
	
	@FXML
	private CheckBox aktivCheckBox;
}
