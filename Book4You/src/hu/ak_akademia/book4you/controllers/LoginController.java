package hu.ak_akademia.book4you.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import hu.ak_akademia.book4you.entities.user.Admin;
import hu.ak_akademia.book4you.entities.user.Cashier;
import hu.ak_akademia.book4you.entities.user.User;
import hu.ak_akademia.book4you.entities.user.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class LoginController implements Authentication, Initializable {
	@FXML
	private BorderPane rootPane;

	@FXML
	private Label alertMessage;

	@FXML
	private TextField userIDField;

	@FXML
	private TextField passwordField;

	private Users users;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		users = new Users("src/hu/ak_akademia/book4you/databases/users.bin");
	}
	
	public void login(ActionEvent event) throws IOException {
		setAlertMessage("");
		if (!isOneFieldEmpty()) {
			if (isAccessGranted(userIDField.getText(), passwordField.getText())) {
				LoginAsAdminOrCashier();
			} else {
				setAlertMessage("Belépés megtagadva!");
			}
		} else {
			setAlertMessage("Üres mező(k)!");
		}
	}

	public void resetFields(ActionEvent event) {
		userIDField.setText("");
		passwordField.setText("");
		setAlertMessage("");
	}
	
	private boolean isOneFieldEmpty() {
		return userIDField.getText().isEmpty() || passwordField.getText().isEmpty();
	}

	private void setAlertMessage(String message) {
		alertMessage.setText(message);
		alertMessage.setVisible(true);
	}

	private void loadView(String url) throws IOException {
		Parent MainRoot = FXMLLoader.load(getClass().getResource(url));
		rootPane.getChildren().setAll(MainRoot);
	}

	@Override
	public boolean isAccessGranted(String ID, String password) {
		User user = users.getUser(ID);

		if (user != null) {
			return user.isPasswordMatch(password);
		}

		return false;
	}

	@Override
	public String getUserType(User user) {
		String result = null;
		if (user instanceof Admin) {
			result = "Admin";
		} else if (user instanceof Cashier) {
			result = "Cashier";
		}

		return result;
	}


	private void LoginAsAdminOrCashier() throws IOException {
		User user = users.getUser(userIDField.getText());

		switch (getUserType(user)) {
		case "Admin":
			loadView("../views/Admin.fxml");
			break;
		case "Cashier":
			loadView("../views/Cashier.fxml");
			break;
		default:
			System.out.println("Nincs jogosultsága még!");
		}
	}
}
