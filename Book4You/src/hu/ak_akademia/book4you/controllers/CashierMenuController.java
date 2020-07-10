package hu.ak_akademia.book4you.controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class CashierMenuController {
	@FXML
	private BorderPane cashierPage;
	
	public void loadIncomeManagementView(ActionEvent event) throws IOException {
		setContentView("Cashier_transactions");
	}
	
	public void loadNewClientManagementView(ActionEvent event) throws IOException {
		setContentView("Cashier_new_client");
	}
	
	public void loadinquiryManagementView(ActionEvent event) throws IOException{
		setContentView("Cashier_inquiry");
	}
	
	public void logout(ActionEvent event) throws IOException {
		Session.setUser(null);
		System.out.println("session: " + Session.getUser());
		
		Parent MainRoot = FXMLLoader.load(getClass().getResource("../views/Login.fxml"));
		cashierPage.getChildren().setAll(MainRoot);
		
	}

	private void setContentView(String view) throws IOException {
		Pane content = FXMLLoader.load(getClass().getResource("../views/" + view + ".fxml"));
		cashierPage.setCenter(content);
	}

}
