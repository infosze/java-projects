package hu.ak_akademia.book4you.entities.user;

import hu.ak_akademia.book4you.entities.FullName;

public abstract class User {
	private FullName name;
	private String identifier;
	private String password;
	
	public User(FullName name, String identifier, String password) {
		this.name = name;
		this.identifier = identifier;
		this.password = password;
	}

	public FullName getName() {
		return name;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean isPasswordMatch(String givenPassword) {
		return password.equals(givenPassword);
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", identifier=" + identifier + "]";
	}
	
	
}
