package hu.ak_akademia.book4you.entities;

import java.io.Serializable;

public class Address implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String country;
	private int postalCode;
	private String city;
	private String publicSpaceName;
	private PublicSpaceType publicSpaceType;
	private int number;
	
	public Address(String country, int postalCode, String city, String publicSpaceName, PublicSpaceType publicSpaceType, int number) {
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

	@Override
	public String toString() {
		return "Address [country=" + country + ", postalCode=" + postalCode + ", city=" + city + ", publicSpaceName="
				+ publicSpaceName + ", publicSpaceType=" + publicSpaceType + ", number=" + number + "]";
	}
}
