package hu.ak_akademia.book4you.controllers;

import javafx.scene.control.TextField;

class NameFactory {
	
	Messages message = new Messages();
	
	String addName(TextField textField) {
		return changeFirstLetterToUpperCase(textField);
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

	boolean validateName(TextField textFieldName) {
		String rawName = textFieldName.getText();
		if (rawName.isEmpty()) {
			textFieldName.setText(message.getErrorMessageEmpty());
			return false;
		} else if (!rawName
				.matches("([a-záéíóúöüőűA-ZÁÉÍÓÚÖÜŐŰ]{2,})(?: |\\-)([a-záéíóúöüőűA-ZÁÉÍÓÚÖÜŐŰ]*\\b\\.?)(| ([a-záéíóúöüőűA-ZÁÉÍÓÚÖÜŐŰ]*))\\ *")) {
			textFieldName.setText(message.getErrorMessageWrongFormat());
			return false;
		}
		return true;
	}
	
}
