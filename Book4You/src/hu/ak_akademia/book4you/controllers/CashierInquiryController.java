package hu.ak_akademia.book4you.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import hu.ak_akademia.book4you.entities.client.Client;
import hu.ak_akademia.book4you.entities.client.Clients;
import hu.ak_akademia.book4you.entities.client.ClientsHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class CashierInquiryController implements Initializable{
	@FXML
	private Label openingBalance;
	
	@FXML
	private Label closedBalance;
	
	@FXML
	private Label totalRevenue;
	
	@FXML
	private Label totalOutlay;
	
	@FXML
	private TableView tableView;
	
	@FXML
	private TableColumn tableViewDateColumn;
	
	@FXML
	private TableColumn tableViewVoucherNum;
	
	@FXML
	private TableColumn tableViewClient;
	
	@FXML
	private TableColumn tableViewBalance;
	
	@FXML
	private TableColumn tableViewAmount;
	
	@FXML
	private ComboBox<Client> clientChooserComboBox;

	private ClientsHandler clientsHandler;
	private ObservableList<Client> clientOptions;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setContentOfClientChooserComboBox();
	}

	private void setContentOfClientChooserComboBox() {
		clientsHandler = new Clients("src/hu/ak_akademia/book4you/databases/clients.bin");
		clientOptions = FXCollections.observableList(clientsHandler.load());
		clientChooserComboBox.setItems(clientOptions);
	}
}
