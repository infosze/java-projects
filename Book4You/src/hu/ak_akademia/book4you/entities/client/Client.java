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
		return "Client [address=" + address + ", isVIP=" + isVIP + "]";
	}
	
	
}
