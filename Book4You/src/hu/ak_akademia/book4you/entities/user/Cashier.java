package hu.ak_akademia.book4you.entities.user;

import hu.ak_akademia.book4you.entities.FullName;

public class Cashier extends User{
	private boolean isActive;
	
	public Cashier(FullName name, String identifier, String password) {
		super(name, identifier, password);
		isActive = true;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "Cashier [isActive=" + isActive + ", toString()=" + super.toString() + "]";
	}
	
	
}
