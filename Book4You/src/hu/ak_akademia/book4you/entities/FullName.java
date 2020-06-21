package hu.ak_akademia.book4you.entities;

public class FullName {
	private String lastName;
	private String midleName;
	private String firstName;
	
	public FullName(String lastName, String midleName, String firstName) {
		super();
		this.lastName = lastName;
		this.midleName = midleName;
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getMidleName() {
		return midleName;
	}

	public String getFirstName() {
		return firstName;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append(lastName);
		
		if (!midleName.equals("")) {
			result.append(" ");
			result.append(midleName);
			result.append(" ");
		} else {
			result.append(" ");
		}
		
		result.append(firstName);
		
		return result.toString();
	}
	
	
	
	
	
	
	
}
