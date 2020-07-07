package hu.ak_akademia.book4you.entities.user;

import java.io.Serializable;

public class Cashier extends User implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private boolean isActive;
	
	public Cashier(String name, String identifier, String password) {
		super(name, identifier, password);
		isActive = true;
	}

	public Cashier(String name, String identifier, String password, boolean isActive) {
		super(name, identifier, password);
		this.isActive = isActive;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return  getName() + "(Cashier) ID=" + getIdentifier() + " Active=" + isActive;
	}
}
