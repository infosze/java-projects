package hu.ak_akademia.book4you.entities.client;

import java.io.Serializable;
import java.util.Random;

import hu.ak_akademia.book4you.entities.Address;

public abstract class Client implements Serializable{
	 
	private static final long serialVersionUID = 1L;
	
	private String name;
	private final String identifier; 
	private Address address;
	private boolean isVIP;
	
	public Client(String name, String identifier, Address address) {
		this.name = name;
		this.identifier = identifier;
		this.address = address;
		this.isVIP = false;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdentifier() {
		return identifier;
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
		return  name + " (client)  id=" + identifier + ", " + address + " isVIP=" + isVIP;
	}
}
