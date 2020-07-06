package hu.ak_akademia.book4you.entities.client;

import hu.ak_akademia.book4you.entities.Address;

public abstract class Client {
	protected String identifier; 
	private Address address;
	private boolean isVIP;
	
	public Client(Address address) {
		this.address = address;
		this.isVIP = false;
	}
}
