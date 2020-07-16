package hu.ak_akademia.book4you.controllers;

import javafx.scene.control.TextField;

class NameFactory {

	String addName(TextField textField) {
		return changeFirstLetterToUpperCase(textField);
	}

	private String changeFirstLetterToUpperCase(TextField textField) {
		StringBuilder fullName = new StringBuilder(textField.getText().toLowerCase());
		fullName.setCharAt(0, Character.toUpperCase(fullName.charAt(0)));
		for (int i = 1; i < fullName.length(); i++) {
			if (fullName.charAt(i) == ' ' || fullName.charAt(i) == '-') {
				fullName.setCharAt(i + 1, Character.toUpperCase(fullName.charAt(i + 1)));
			}
		}
		return fullName.toString();
	}

}
