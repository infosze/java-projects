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
import hu.ak_akademia.book4you.validation.CashierNameValidator;
import hu.ak_akademia.book4you.validation.ExistenceValidator;
import hu.ak_akademia.book4you.validation.MyAlert;
import hu.ak_akademia.book4you.validation.MyException;
import hu.ak_akademia.book4you.validation.PasswordValidator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
	private ComboBox<Cashier> cashierChooserComboBox;

	@FXML
	private CheckBox aktivCheckBox;

	@FXML
	private Label id;

	@FXML
	private Label messageLabelToAdd;

	@FXML
	private Label messageLabelToModify;

	NameFactory nameFactory = new NameFactory();
	IdentifierFactory identifierFactory = new IdentifierFactory();

	private UsersHandler usersHandler;
	private ObservableList<Cashier> cashierOptions;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setContentOfCashierChooserComboBox();
	}

	public void saveEditedCashier(ActionEvent event) throws IOException {
		try {
			new ExistenceValidator(cashierChooserComboBox.getValue(), "Kérem válasszon a pénztáros listáról!")
					.validate();
			new PasswordValidator(passwordToModify.getText()).validate();

			User choosen = cashierChooserComboBox.getValue();
			User modified = new Cashier(choosen.getName(), choosen.getID(), passwordToModify.getText(),
					aktivCheckBox.isSelected());

			usersHandler.modify(choosen, modified);
			usersHandler.save();

			setContentOfCashierChooserComboBox();
			resetFieldsOnCashierModificatuinPage();

			MyAlert.showInformation("Adatok mentése megtörtént");
		} catch (MyException e) {
			MyAlert.showError(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void chooseCashier(ActionEvent event) {
		if (cashierChooserComboBox.getValue() != null) {
			setDisableStateOfTextFields(false);
			fillFieldsWithChoosenCashierData();
		} else {
			setDisableStateOfTextFields(true);
		}
	}

	public void emptyTextField(ActionEvent event) throws IOException {
		resetFieldsOnNewCashierAdditionPage();
		resetFieldsOnCashierModificatuinPage();
	}

	public void addNewCashier(ActionEvent event) throws IOException {
		try {
			new CashierNameValidator(fullNameFieldToAdd.getText()).validate();
			new PasswordValidator(passwordFieldToAdd.getText()).validate();

			String fullName = nameFactory.formatName(fullNameFieldToAdd);
			String password = passwordFieldToAdd.getText();
			String identifier = identifierFactory.generateIdentifier(fullName);
			messageLabelToAdd.setText(identifier);
			UsersHandler users = new Users("src/hu/ak_akademia/book4you/databases/users.bin");
			Cashier newChashier = new Cashier(fullName, identifier, password);
			users.add(newChashier);
			users.save();

			resetFieldsOnNewCashierAdditionPage();

			MyAlert.showInformation("Adatok mentése megtörtént");

		} catch (MyException e) {
			MyAlert.showError(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private void resetFieldsOnCashierModificatuinPage() {
		cashierChooserComboBox.getSelectionModel().clearSelection();
		passwordToModify.setText("");
		aktivCheckBox.setSelected(false);
		aktivCheckBox.setDisable(true);
	}
	
	private void resetFieldsOnNewCashierAdditionPage() {
		fullNameFieldToAdd.setText("");
		passwordFieldToAdd.setText("");
	}

	private void setContentOfCashierChooserComboBox() {
		usersHandler = new Users("src/hu/ak_akademia/book4you/databases/users.bin");

		cashierOptions = FXCollections.observableList(getCashiers(usersHandler.load()));
		cashierChooserComboBox.setItems(cashierOptions);
	}

	private List<Cashier> getCashiers(List<User> users) {
		List<Cashier> result = new ArrayList<>();
		for (User user : users) {
			if (user instanceof Cashier) {
				result.add((Cashier) user);
			}
		}

		return result;
	}

	private void fillFieldsWithChoosenCashierData() {
		passwordToModify.setText(cashierChooserComboBox.getValue().getPassword());
		aktivCheckBox.setSelected(cashierChooserComboBox.getValue().isActive());
	}

	private void setDisableStateOfTextFields(boolean value) {
		passwordToModify.setDisable(value);
		aktivCheckBox.setDisable(value);
	}
}
