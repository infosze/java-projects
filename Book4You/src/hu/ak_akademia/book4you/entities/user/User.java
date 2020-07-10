package hu.ak_akademia.book4you.entities.user;

import java.io.Serializable;

public abstract class User implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;
	private final String identifier;
	private String password;

	public User(String name, String identifier, String password) {
		this.name = name;
		this.identifier = identifier;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public boolean isPasswordMatch(String givenPassword) {
		return password.equals(givenPassword);
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", identifier=" + identifier + "]";
	}

}
