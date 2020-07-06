package hu.ak_akademia.book4you.entities.user;

import java.io.Serializable;

public class Admin extends User implements Serializable{

	private static final long serialVersionUID = 1L;

	public Admin(String name, String identifier, String password) {
		super(name, identifier, password);
	}

	@Override
	public String toString() {
		return  getName() + "(Admin) ID=" + getIdentifier();
	}
}
