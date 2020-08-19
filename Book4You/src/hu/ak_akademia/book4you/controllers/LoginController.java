package hu.ak_akademia.book4you.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import hu.ak_akademia.book4you.entities.user.Admin;
import hu.ak_akademia.book4you.entities.user.Cashier;
import hu.ak_akademia.book4you.entities.user.Users;
import hu.ak_akademia.book4you.login.Login;
import hu.ak_akademia.book4you.login.LoginSession;
import hu.ak_akademia.book4you.login.Session;
import hu.ak_akademia.book4you.validation.MyAlert;
import hu.ak_akademia.book4you.validation.MyException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class LoginController implements Initializable {
	@FXML
	private BorderPane rootPane;

	@FXML
	private Label alertMessage;

	@FXML
	private TextField userIDField;

	@FXML
	private TextField passwordField;

	private Login userLogin;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.userLogin = new LoginSession(new Users("src/hu/ak_akademia/book4you/databases/users.bin"));
	}

	public void login(ActionEvent event) throws IOException {
		try {
			userLogin.authenticate(userIDField.getText(), passwordField.getText());
			userLogin.storeSession();
			redirect();
		} catch (MyException e) {
			MyAlert.show(e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void resetFields(ActionEvent event) {
		userIDField.setText("");
		passwordField.setText("");
	}

	private void loadView(String url) throws IOException {
		Parent MainRoot = FXMLLoader.load(getClass().getResource(url));
		rootPane.getChildren().setAll(MainRoot);
	}

	private void redirect() throws IOException, ClassNotFoundException {
		if (Session.getUser() instanceof Admin) {
			loadView("../views/Admin.fxml");
		} else if (Session.getUser() instanceof Cashier) {
			loadView("../views/Cashier.fxml");
		} else
			throw new ClassNotFoundException();
	}
}
