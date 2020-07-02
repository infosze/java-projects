package hu.ak_akademia.book4you.controllers;

import java.awt.event.ActionEvent;
import java.io.IOException;

import hu.ak_akademia.book4you.entities.FullName;
import hu.ak_akademia.book4you.entities.user.Cashier;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
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

	@FXML
	private Label id;

	@FXML
	private Label messageLabelToAdd;

	@FXML
	private Label messageLabelToModify;

	private FullName fullName;
	private String identifier;
	private String password;

	public void addNewCashier(ActionEvent event) throws IOException {
		setName();
		generateIdentifier();
		writePassword();
		Cashier newChashier = new Cashier(fullName, identifier, password);
		fullNameFieldToAdd.setText("");
		passwordFieldToAdd.setText("");
		System.out.println(newChashier);
	}

	private void setName() {
		String[] name = fullNameFieldToAdd.getText().split(" ");
		if (name.length <= 2) {
			fullName = new FullName(name[0], "", name[1]);
		} else {
			fullName = new FullName(name[0], name[1], name[2]);
		}
	}

	private void generateIdentifier() {
		identifier = fullName.getLastName().substring(0, 1);
		identifier += fullName.getFirstName().substring(0, 1);
		int i = (int) (Math.random() * 100_000) + 0;
		String s = String.format("%05d", i);
		identifier += s;
	}

	private void writePassword() {
		password = passwordFieldToAdd.getText();
	}

//	private boolean checkPassword() {
//		return password.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$");
//	}

	public void reset(ActionEvent event) throws IOException {
		fullNameFieldToAdd.setText("");
		passwordFieldToAdd.setText("");
	}

}
