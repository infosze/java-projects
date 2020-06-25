package hu.ak_akademia.book4you.controllers;

import java.io.IOException;
import java.util.List;

import hu.ak_akademia.book4you.entities.user.User;
import hu.ak_akademia.book4you.entities.user.UsersHandler;
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
//		if (isAccessGranted()) {
//			loadMain();
//		} else {
//			System.out.println("Belépés megtagadva!");
//			reset();
//		}
		
		loadMain(); //tesztelés céljából mindíg átdob az Adminra.
	}

	private void loadMain() throws IOException {
		Parent MainRoot = FXMLLoader.load(getClass().getResource("../views/Admin.fxml"));
		rootPane.getChildren().setAll(MainRoot);

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
