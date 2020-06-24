package hu.ak_akademia.book4you.controllers;


import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class AdminMenuController {
	@FXML
	private BorderPane adminPage;

	public void loadCashiersManagementView(ActionEvent event) {
		System.out.println("loadCashiersManagementView");
	}
	
	public void loadClientsManagementView(ActionEvent event) {
		System.out.println("loadClientsManagementView");
	}
	
	public void loadCompanyDataManagementView(ActionEvent event){
		Pane content = null;
		try {
			content = FXMLLoader.load(getClass().getResource("../views/Admin_company.fxml"));
		} catch (IOException e) {
			System.out.println("Hiba az fxml betöltésekor!");
		}
		adminPage.setRight(content);
	}
	
	public void logout(ActionEvent event) {
		System.out.println("logout");
	}
}
