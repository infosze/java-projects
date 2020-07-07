package hu.ak_akademia.book4you.entities.owncompany;

import java.io.Serializable;
import hu.ak_akademia.book4you.entities.Address;

public class OwnCompany implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String name;
	private Address address;
	
	public OwnCompany(String name, Address address) {
		this.name = name;
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "OwnCompany: " + name + " " + address.toString();
	}
}
