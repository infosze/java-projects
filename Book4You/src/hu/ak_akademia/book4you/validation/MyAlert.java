package hu.ak_akademia.book4you.validation;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class MyAlert {
	public static void showError(String message) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setContentText(message);
		alert.setTitle("Hiba üzenet");
		alert.setHeaderText("");
		alert.show();
	}
	
	public static void showInformation(String message) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setContentText(message);
		alert.setTitle("Információ");
		alert.setHeaderText("");
		alert.show();
	}
}
