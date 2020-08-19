package hu.ak_akademia.book4you.entities;

import java.io.Serializable;

public class Address implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String country;
	private String postalCode;
	private String city;
	private String publicSpaceName;
	private PublicSpaceType publicSpaceType;
	private String number;
	
	public Address(String country, String postalCode, String city, String publicSpaceName, PublicSpaceType publicSpaceType, String number) {
		this.country = country;
		this.postalCode = postalCode;
		this.city = city;
		this.publicSpaceName = publicSpaceName;
		this.publicSpaceType = publicSpaceType;
		this.number = number;
	}
	
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
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

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return country + " " + postalCode + " " + city + " "
				+ publicSpaceName + " " + publicSpaceType.getLongName() + " " + number + ".";
	}
}
