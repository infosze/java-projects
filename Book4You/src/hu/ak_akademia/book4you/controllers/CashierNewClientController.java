package hu.ak_akademia.book4you.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import hu.ak_akademia.book4you.entities.Address;
import hu.ak_akademia.book4you.entities.FullName;
import hu.ak_akademia.book4you.entities.PublicSpaceType;
import hu.ak_akademia.book4you.entities.client.Clients;
import hu.ak_akademia.book4you.entities.client.ClientsHandler;
import hu.ak_akademia.book4you.entities.client.NaturalClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class CashierNewClientController implements Initializable {

	@FXML
	private TextField newClientFullName;

	@FXML
	private TextField newClientZipCode;

	@FXML
	private TextField newClientCityName;

	@FXML
	private TextField newClientAdressName;
	
	@FXML
	private TextField newClientHouseNumber;
	
	@FXML
	private ComboBox<String> clientAdressType= new ComboBox<String>();

	private FullName fullName;
	private Address address;

	public void addNewClient(ActionEvent event) throws IOException {
		setName();
		setAdress();
		var newClient = new NaturalClient(fullName, address);
		newClientFullName.setText("");
		newClientZipCode.setText("");
		newClientCityName.setText("");
		newClientAdressName.setText("");
		clientAdressType.getSelectionModel().clearSelection();
		newClientHouseNumber.setText("");
	}
	
	private void setAdress() {
		int zipCode = Integer.parseInt(newClientZipCode.getText());
		var spaceType = getType(clientAdressType.getValue());
		int number = Integer.parseInt(newClientHouseNumber.getText());
		address = new Address(zipCode,newClientCityName.getText(),newClientAdressName.getText(),spaceType , number);
	}
	
	private PublicSpaceType getType(String enumName) {
		for(var pst : PublicSpaceType.values()) {
			if(pst.getName().equals(enumName)) {
				return pst;
			}
		}
		return null;
	}
	private void setName() {
		String[] name = newClientFullName.getText().split(" ");
		if(name.length <=2) {
			fullName = new FullName(name[0],"",name[1]);
		}else {
		 fullName = new FullName(name[0],name[1],name[2]);
		}
	}
	
	public void reset(ActionEvent event) throws IOException{
		newClientFullName.setText("");
		newClientZipCode.setText("");
		newClientCityName.setText("");
		newClientAdressName.setText("");
		clientAdressType.getSelectionModel().clearSelection();
		newClientHouseNumber.setText("");
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		for(PublicSpaceType pst : PublicSpaceType.values()) {
		clientAdressType.getItems().addAll(pst.getName());
		}
		
		

	}
}
