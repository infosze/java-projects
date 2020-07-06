package hu.ak_akademia.book4you.entities.client;

import hu.ak_akademia.book4you.entities.Address;

public class NaturalClient extends Client{
	
	private String name;

	public NaturalClient(String name, Address address) {
		super(address);
		identifier = name.toString();
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
