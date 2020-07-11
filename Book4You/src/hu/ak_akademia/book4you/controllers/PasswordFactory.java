package hu.ak_akademia.book4you.controllers;

import javafx.scene.control.TextField;

class PasswordFactory {

	Messages message = new Messages();

	String addPassword(TextField textField) {
		return textField.getText();
	}

	boolean validatePassword(TextField textField) {
		String rawPassword = textField.getText();
		if (rawPassword.isEmpty()) {
			textField.setText(message.getErrorMessageEmpty());
			return false;
		} else if (!rawPassword.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$")) {
			textField.setText(message.getErrorMessageWrongFormat());
			return false;
		} else
			return true;
	}

}
