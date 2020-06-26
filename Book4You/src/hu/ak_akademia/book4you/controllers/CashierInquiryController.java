package hu.ak_akademia.book4you.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class CashierInquiryController {
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

}
