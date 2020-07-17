package hu.ak_akademia.book4you.controllers;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

class Validation {

	static boolean validateName(TextField textField) {
		String rawName = textField.getText();
		if (rawName.isEmpty()) {
			textField.setText(Messages.getErrorMessageEmpty());
			return false;
		} else if (!rawName
				.matches("([a-záéíóúöüőűA-ZÁÉÍÓÚÖÜŐŰ]{2,})(?: |\\-)([a-záéíóúöüőűA-ZÁÉÍÓÚÖÜŐŰ]*\\b\\.?)(| ([a-záéíóúöüőűA-ZÁÉÍÓÚÖÜŐŰ]*))\\ *")) {
			textField.setText(Messages.getErrorMessageWrongFormat());
			return false;
		}
		return true;
	}

	static boolean validatePassword(TextField textField) {
		String rawPassword = textField.getText();
		if (rawPassword.isEmpty()) {
			textField.setText(Messages.getErrorMessageEmpty());
			return false;
		} else if (!rawPassword.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$")) {
			textField.setText(Messages.getErrorMessageWrongFormat());
			return false;
		} else
			return true;
	}

	static boolean validHouseNumber(TextField number) {
		String rawNumber = number.getText();
		if (rawNumber.isEmpty()) {
			number.setText(Messages.getErrorMessageEmpty());
			return false;
		} else if (!rawNumber.matches("(\\d{1,})")) {
			number.setText(Messages.getErrorMessageWrongFormat());
			return false;
		}
		return true;
	}

	static boolean validPublicSpaceType(ComboBox publicSpaceType) {
//		String rawPublicSpaceType = publicSpaceType.getSelectionModel().isEmpty();
		try {
			if (publicSpaceType.getValue() == null) {
				return false;
			}
		} catch (NullPointerException e) {

		}
		return true;
	}

	static boolean validPubilcSpaceName(TextField publicSpaceName) {
		String rawPublicSpaceName = publicSpaceName.getText();
		if (rawPublicSpaceName.isEmpty()) {
			publicSpaceName.setText(Messages.getErrorMessageEmpty());
			return false;
		} else if (!rawPublicSpaceName
				.matches("([a-záéíóúöüőűA-ZÁÉÍÓÚÖÜŐŰ\\d]{2,})(( |\\-)([a-záéíóúöüőűA-ZÁÉÍÓÚÖÜŐŰ]*))?(| ([a-záéíóúöüőűA-ZÁÉÍÓÚÖÜŐŰ]\\d*))\\ *")) {
			publicSpaceName.setText(Messages.getErrorMessageWrongFormat());
			return false;
		}
		return true;
	}

	static boolean validateCity(TextField city) {
		String rawCity = city.getText();
		if (rawCity.isEmpty()) {
			city.setText(Messages.getErrorMessageEmpty());
			return false;
		} else if (!rawCity.matches("([a-záéíóúöüőűA-ZÁÉÍÓÚÖÜŐŰ]{2,})(( |\\-)([a-záéíóúöüőűA-ZÁÉÍÓÚÖÜŐŰ]*))?(| ([a-záéíóúöüőűA-ZÁÉÍÓÚÖÜŐŰ]*))\\ *")) {
			city.setText(Messages.getErrorMessageWrongFormat());
			return false;
		}
		return true;
	}

	static boolean validPostalCode(TextField postalCode) {
		String rawPostalCode = postalCode.getText();
		if (rawPostalCode.isEmpty()) {
			postalCode.setText(Messages.getErrorMessageEmpty());
			return false;
		} else if (!rawPostalCode.matches("(\\d{4,})")) {
			postalCode.setText(Messages.getErrorMessageWrongFormat());
			return false;
		}
		return true;
	}

	static boolean validCountry(TextField country) {
		String rawCountry = country.getText();
		if (rawCountry.isEmpty()) {
			country.setText(Messages.getErrorMessageEmpty());
			return false;
		} else if (!rawCountry
				.matches("([a-záéíóúöüőűA-ZÁÉÍÓÚÖÜŐŰ]{2,})(( |\\-)([a-záéíóúöüőűA-ZÁÉÍÓÚÖÜŐŰ]*))?(| ([a-záéíóúöüőűA-ZÁÉÍÓÚÖÜŐŰ]*))\\ *")) {
			country.setText(Messages.getErrorMessageWrongFormat());
			return false;
		}
		return true;
	}

}
