package hu.ak_akademia.book4you.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import hu.ak_akademia.book4you.login.Session;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;


public class CashierTransactionsController implements Initializable{
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
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//Tesztelés végett van csak bent!
		//Az aktuálisan belépett user-t így tudjátok elérni.
		System.out.println("session: " + Session.getUser());
	}
	
	
}
