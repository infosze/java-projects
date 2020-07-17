package hu.ak_akademia.book4you.entities.bookstore;

import java.io.Serializable;
import hu.ak_akademia.book4you.entities.Address;

public class Store implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String name;
	private Address address;

	public Store(String name, Address address) {
		this.name = name;
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public Address getAddress() {
		return address;
	}
	
	@Override
	public String toString() {
		return "OwnCompany: " + name + " " + address.toString();
	}
}
