package hu.ak_akademia.book4you.entities.client;

import java.io.Serializable;

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
	
//	public String getNameAndID() {
//		StringBuilder text = new StringBuilder();
//		
//		text.append(name);
//		text.append(" ");
//		text.append("[");
//		text.append(ID);
//		text.append("]");
//		
//		System.out.println(text);
//		
//		return text.toString(); 
//	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		
		result.append(name);
		result.append(" ");
		result.append("(");
		result.append(ID);
		result.append(")");
		
		return  result.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ID == null) ? 0 : ID.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		if (ID == null) {
			if (other.ID != null)
				return false;
		} else if (!ID.equals(other.ID))
			return false;
		return true;
	}
	
}
