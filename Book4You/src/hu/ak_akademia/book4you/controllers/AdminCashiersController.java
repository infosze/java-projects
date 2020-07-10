package hu.ak_akademia.book4you.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import hu.ak_akademia.book4you.entities.user.Cashier;
import hu.ak_akademia.book4you.entities.user.User;
import hu.ak_akademia.book4you.entities.user.Users;
import hu.ak_akademia.book4you.entities.user.UsersHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class AdminCashiersController implements Initializable {

	@FXML
	private BorderPane rootPane;

	@FXML
	private TextField fullNameFieldToAdd;

	@FXML
	private TextField passwordFieldToAdd;

	@FXML
	private TextField passwordToModify;

	@FXML
	private ComboBox<Cashier> cashierChooser = new ComboBox<>();

	@FXML
	private CheckBox aktivCheckBox;

	@FXML
	private Label id;

	@FXML
	private Label messageLabelToAdd;

	@FXML
	private Label messageLabelToModify;

	private String errorMessage1 = String.format("Kérem töltse ki a mezőt!");
	private String errorMessage2 = String.format("Kérem megfelelő formátumot adjon meg!");

	private Cashier selectedCashier;

	UsersHandler users = new Users("src/hu/ak_akademia/book4you/databases/users.bin");
	List<User> usersList = users.load();
	List<Cashier> cashiers = new ArrayList<>();

	public void chooseCashier(ActionEvent event) {
		cashierChooser.getItems().addAll(cashiers);
		selectedCashier = cashierChooser.getValue();
		passwordToModify.setDisable(false);
		passwordToModify.setText(selectedCashier.getPassword());
		aktivCheckBox.setDisable(false);
		aktivCheckBox.setSelected(selectedCashier.isActive());
		cashierChooser.getItems().removeAll(cashiers);
	}

	public void editCashier(ActionEvent event) throws IOException {
		User cashier = users.getUser(selectedCashier.getIdentifier());
		User modified = //
				new Cashier(selectedCashier.getName(), selectedCashier.getIdentifier(), passwordToModify.getText(), aktivCheckBox.isSelected());
		users.modify(cashier, modified);
		users.save();
		passwordToModify.setText("");
	}

	public void addNewCashier(ActionEvent event) throws IOException {
		if (validateName(fullNameFieldToAdd) & validatePassword(passwordFieldToAdd)) {
			String fullName = addCashier(fullNameFieldToAdd);
			String password = addPassword(passwordFieldToAdd);
			String[] letters = cutLettersFromName(fullName);
			String identifier = generateIdentifier(letters);
			messageLabelToAdd.setText(identifier);
			UsersHandler users = new Users("src/hu/ak_akademia/book4you/databases/users.bin");
			Cashier newChashier = new Cashier(fullName, identifier, password);
			users.add(newChashier);
			users.save();
			fullNameFieldToAdd.setText("");
			passwordFieldToAdd.setText("");
			System.out.print(newChashier);
			System.out.printf(" Password= %s%n", password);
		}
	}

	private String addCashier(TextField textField) {
		return changeFirstLetterToUpperCase(textField);
	}

	private String addPassword(TextField textField) {
		return textField.getText();
	}

	private String generateIdentifier(String[] letters) {
		String identifier;
		identifier = letters[0];
		identifier += letters[1];
		int identityNumber = (int) (Math.random() * 10_000) + 0;
		String formattedIdentityNumber = String.format("%04d", identityNumber);
		identifier += formattedIdentityNumber;
		return identifier;
	}

	private boolean validateName(TextField textFieldName) {
		String rawName = textFieldName.getText();
		if (rawName.isEmpty()) {
			textFieldName.setText(errorMessage1);
			return false;
		} else if (!rawName
				.matches("([a-záéíóúöüőűA-ZÁÉÍÓÚÖÜŐŰ]{2,})(?: |\\-)([a-záéíóúöüőűA-ZÁÉÍÓÚÖÜŐŰ]*\\b\\.?)(| ([a-záéíóúöüőűA-ZÁÉÍÓÚÖÜŐŰ]*))\\ *")) {
			textFieldName.setText(errorMessage2);
			return false;
		}
		return true;
	}

	private boolean validatePassword(TextField textField) {
		String rawPassword = textField.getText();
		if (rawPassword.isEmpty()) {
			textField.setText(errorMessage1);
			return false;
		} else if (!rawPassword.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$")) {
			textField.setText(errorMessage2);
			return false;
		} else
			return true;
	}

	private static String changeFirstLetterToUpperCase(TextField textField) {
		StringBuilder fullName = new StringBuilder(textField.getText().toLowerCase());
		fullName.setCharAt(0, Character.toUpperCase(fullName.charAt(0)));
		for (int i = 1; i < fullName.length(); i++) {
			if (fullName.charAt(i) == ' ' || fullName.charAt(i) == '-') {
				fullName.setCharAt(i + 1, Character.toUpperCase(fullName.charAt(i + 1)));
			}
		}
		return fullName.toString();
	}

	private String[] cutLettersFromName(String textField) {
		String name1;
		String name2;
		String[] name = textField.split(" ");
		if (name.length == 2) {
			name1 = name[0];
			name2 = name[1];
		} else if (name[1].lastIndexOf(".") > -1) {
			name1 = name[0];
			name2 = name[2];
		} else {
			name1 = name[0];
			name2 = name[1];
		}
		String[] letters = { name1.substring(0, 2).toUpperCase(), name2.substring(0, 1) };
		return letters;
	}

	public void emptyTextField(ActionEvent event) throws IOException {
		boolean deleteAll = true;
		if (fullNameFieldToAdd.getText().equals(errorMessage1) || fullNameFieldToAdd.getText().equals(errorMessage2)) {
			fullNameFieldToAdd.setText("");
			deleteAll = false;
		}
		if (passwordFieldToAdd.getText().equals(errorMessage1) || passwordFieldToAdd.getText().equals(errorMessage2)) {
			passwordFieldToAdd.setText("");
			deleteAll = false;
		}
		if (deleteAll) {
			fullNameFieldToAdd.setText("");
			passwordFieldToAdd.setText("");
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		List<Cashier> cashiers = new ArrayList<>();
		{
			List<User> usersList = users.load();
			for (User user : usersList) {
				if (user instanceof Cashier) {
					cashiers.add((Cashier) user);
				}
			}
		}
		cashierChooser.getItems().addAll(cashiers);
	}

}
