package hu.ak_akademia.book4you.entities.client;

import java.io.Serializable;
import java.util.Random;

import hu.ak_akademia.book4you.entities.Address;

public abstract class Client implements Serializable{
	 
	private static final long serialVersionUID = 1L;
	
	private String name;
	private final String ID; 
	private Address address;
	private boolean isVIP;
	
	public Client(String name, String ID, Address address) {
		this.name = name;
		this.ID = ID;
		this.address = address;
		this.isVIP = false;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getID() {
		return ID;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public boolean isVIP() {
		return isVIP;
	}

	public void setVIP(boolean isVIP) {
		this.isVIP = isVIP;
	}

	@Override
	public String toString() {
		return  name + " (client)  id=" + ID + ", " + address + " isVIP=" + isVIP;
	}
}
