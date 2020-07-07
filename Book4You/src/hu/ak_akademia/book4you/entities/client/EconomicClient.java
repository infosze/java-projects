package hu.ak_akademia.book4you.entities.client;

import java.io.Serializable;

import hu.ak_akademia.book4you.entities.Address;

public class EconomicClient extends Client implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public EconomicClient(String name, String identifier, Address address) {
		super(name, identifier, address);
	}
}
