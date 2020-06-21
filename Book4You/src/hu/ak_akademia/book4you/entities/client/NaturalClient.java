package hu.ak_akademia.book4you.entities.client;

import hu.ak_akademia.book4you.entities.Address;
import hu.ak_akademia.book4you.entities.FullName;

public class NaturalClient extends Client{
	
	private FullName name;

	public NaturalClient(FullName name, Address address) {
		super(address);
		this.name = name;
	}

	public FullName getName() {
		return name;
	}

}
