package hu.ak_akademia.book4you.controllers;

import java.io.IOException;
import hu.ak_akademia.book4you.entities.user.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class LoginController implements Authentication {
	@FXML
	private BorderPane rootPane;

	@FXML
	private Label alertMessage;

	@FXML
	private TextField userNameField;

	@FXML
	private TextField passwordField;

	public void login(ActionEvent event) throws IOException {
		loadMain(); //tesztelés céljából
	}
	
	//ez ki lesz szedve
	private void loadMain() throws IOException {
		Parent MainRoot = FXMLLoader.load(getClass().getResource("../views/Cashier.fxml"));
		rootPane.getChildren().setAll(MainRoot);
	}
	//lehet refaktorálni kell...file adatstruktúra függőség
	private void loadCashierView() throws IOException {
		Parent MainRoot = FXMLLoader.load(getClass().getResource("../views/Cashier.fxml"));
		rootPane.getChildren().setAll(MainRoot); //redundáns sor
	}
	//lehet refaktorálni kell...file adatstruktúra függőség
	private void loadAdminView() throws IOException {
		Parent MainRoot = FXMLLoader.load(getClass().getResource("../views/Admin.fxml"));
		rootPane.getChildren().setAll(MainRoot); //redundáns sor
	}

	private void reset() {
		alertMessage.setVisible(true);
		passwordField.setText("");
	}

	@Override
	public boolean isAccessGranted() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getUserType(User user) {
		// TODO Auto-generated method stub
		return null;
	}
}
