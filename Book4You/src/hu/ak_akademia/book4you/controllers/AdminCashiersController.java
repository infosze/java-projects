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

	NameFactory nameFactory = new NameFactory();
	IdentifierFactory identifierFactory = new IdentifierFactory();

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
		User cashier = users.getUser(selectedCashier.getID());
		User modified = new Cashier(selectedCashier.getName(), selectedCashier.getID(), passwordToModify.getText(), aktivCheckBox.isSelected());
		users.modify(cashier, modified);
		users.save();
		passwordToModify.setText("");
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
