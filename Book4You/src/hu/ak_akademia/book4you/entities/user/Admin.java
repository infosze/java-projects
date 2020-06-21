package hu.ak_akademia.book4you.entities.user;

import hu.ak_akademia.book4you.entities.FullName;

public class Admin extends User{

	public Admin(FullName name, String identifier, String password) {
		super(name, identifier, password);
	}

	@Override
	public String toString() {
		return "Admin [getName()=" + getName() + ", getIdentifier()=" + getIdentifier() + "]";
	}
	
	
	
}
