package hu.ak_akademia.book4you.entities;

public class Address {
	private int postalCode;
	private String city;
	private String publicSpaceName;
	private PublicSpaceType publicSpaceType;
	private int number;
	
	public Address(int postalCode, String city, String publicSpaceName, PublicSpaceType publicSpaceType, int number) {
		this.postalCode = postalCode;
		this.city = city;
		this.publicSpaceName = publicSpaceName;
		this.publicSpaceType = publicSpaceType;
		this.number = number;
	}

	public int getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPublicSpaceName() {
		return publicSpaceName;
	}

	public void setPublicSpaceName(String publicSpaceName) {
		this.publicSpaceName = publicSpaceName;
	}

	public PublicSpaceType getPublicSpaceType() {
		return publicSpaceType;
	}

	public void setPublicSpaceType(PublicSpaceType publicSpaceType) {
		this.publicSpaceType = publicSpaceType;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
}
