package hu.ak_akademia.book4you.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;


public class CashierTransactionsController {
	@FXML
	private ComboBox chooseTitle;
	
	@FXML
	private TextField incomeAmount;
	
	@FXML
	private TextField newClientFullName;
	
	@FXML
	private TextField newClientZipCode;
	
	@FXML
	private TextField newClientCityName;
	
	@FXML
	private TextField newClientAdressName;
	
	@FXML
	private TextField clientAdressType;
	
	@FXML
	private RadioButton income;
	
	@FXML
	private RadioButton outlay;
}