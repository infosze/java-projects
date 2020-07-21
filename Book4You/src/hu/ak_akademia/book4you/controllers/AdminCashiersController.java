package hu.ak_akademia.book4you.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import hu.ak_akademia.book4you.entities.client.Client;
import hu.ak_akademia.book4you.entities.client.ClientsHandler;
import hu.ak_akademia.book4you.entities.user.Cashier;
import hu.ak_akademia.book4you.entities.user.User;
import hu.ak_akademia.book4you.entities.user.Users;
import hu.ak_akademia.book4you.entities.user.UsersHandler;
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

	public void chooseCashier(ActionEvent event) {
		if(cashierChooserComboBox.getValue() != null) {
			setDisableStateOfTextFields(false);
			fillFieldsWithChoosenCashierData();
		}else {
			setDisableStateOfTextFields(true);
		}
	}

	private void fillFieldsWithChoosenCashierData() {
		passwordToModify.setText(cashierChooserComboBox.getValue().getPassword());
		aktivCheckBox.setSelected(cashierChooserComboBox.getValue().isActive());
	}

	private void setDisableStateOfTextFields(boolean value) {
		passwordToModify.setDisable(value);
		aktivCheckBox.setDisable(value);
	}

	public void saveEditedCashier(ActionEvent event) throws IOException {
		User choosen = cashierChooserComboBox.getValue();
		User modified = new Cashier(choosen.getName(), choosen.getID(), passwordToModify.getText(),	aktivCheckBox.isSelected());
		
		usersHandler.modify(choosen, modified);
		usersHandler.save();
	}

	public void addNewCashier(ActionEvent event) throws IOException {
		if (Validation.validateName(fullNameFieldToAdd) & Validation.validatePassword(passwordFieldToAdd)) {
			String fullName = nameFactory.formatName(fullNameFieldToAdd);
			String password = passwordFieldToAdd.getText();
			String identifier = identifierFactory.generateIdentifier(fullName);
			messageLabelToAdd.setText(identifier);
			UsersHandler users = new Users("src/hu/ak_akademia/book4you/databases/users.bin");
			Cashier newChashier = new Cashier(fullName, identifier, password);
			users.add(newChashier);
			users.save();
			fullNameFieldToAdd.setText("");
			passwordFieldToAdd.setText("");
		}
	}

	public void emptyTextField(ActionEvent event) throws IOException {
		boolean deleteAll = true;
		if (fullNameFieldToAdd.getText().equals(Messages.getErrorMessageEmpty())
				|| fullNameFieldToAdd.getText().equals(Messages.getErrorMessageWrongFormat())) {
			fullNameFieldToAdd.setText("");
			deleteAll = false;
		}
		if (passwordFieldToAdd.getText().equals(Messages.getErrorMessageEmpty())
				|| passwordFieldToAdd.getText().equals(Messages.getErrorMessageWrongFormat())) {
			passwordFieldToAdd.setText("");
			deleteAll = false;
		}
		if (deleteAll) {
			fullNameFieldToAdd.setText("");
			passwordFieldToAdd.setText("");
		}
	}
}
