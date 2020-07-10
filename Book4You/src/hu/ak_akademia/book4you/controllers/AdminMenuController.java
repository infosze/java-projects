package hu.ak_akademia.book4you.controllers;


import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class AdminMenuController {
	@FXML
	private BorderPane adminPage;

	public void loadCashiersManagementView(ActionEvent event) throws IOException {
		setContentView("Admin_cashiers");
	}
	
	public void loadClientsManagementView(ActionEvent event) throws IOException {
		setContentView("Admin_clients");
	}
	
	public void loadCompanyDataManagementView(ActionEvent event) throws IOException{
		setContentView("Admin_company");
	}
	
	public void logout(ActionEvent event) throws IOException {
		Session.setUser(null);
		System.out.println("session: " + Session.getUser());
		
		Parent MainRoot = FXMLLoader.load(getClass().getResource("../views/Login.fxml"));
		adminPage.getChildren().setAll(MainRoot);
		
	}

	private void setContentView(String view) throws IOException {
		Pane content = FXMLLoader.load(getClass().getResource("../views/" + view + ".fxml"));
		adminPage.setRight(content);
	}
	
}
