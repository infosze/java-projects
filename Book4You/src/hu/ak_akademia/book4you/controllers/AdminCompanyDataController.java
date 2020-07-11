package hu.ak_akademia.book4you.controllers;


import java.io.IOException;

import hu.ak_akademia.book4you.SaveAndClearBottoms;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class AdminCompanyDataController implements SaveAndClearBottoms{
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
	private ComboBox publicSpaceTypeComboBox;
	
	@FXML
	private TextField houseNumberField;

	@Override
	public void saveAddedDatas(ActionEvent event) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void emptyTextField(ActionEvent event) throws IOException {
		// TODO Auto-generated method stub
		
	}
	
}
