package hu.ak_akademia.book4you.entities.owncompany;

import java.io.Serializable;

import hu.ak_akademia.book4you.databases.DataLoader;
import hu.ak_akademia.book4you.databases.DataSaver;
import hu.ak_akademia.book4you.databases.FileHandler;
import hu.ak_akademia.book4you.entities.Address;
import hu.ak_akademia.book4you.entities.PublicSpaceType;

public class OwnCompany implements Serializable, OwnCompanyHandler{

	private static final long serialVersionUID = 1L;
	
	private String name;
	private Address address;
	
	public OwnCompany() {
	}

	public OwnCompany(String name, Address address) {
		this.name = name;
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public Address getAddress() {
		return address;
	}

	@Override
	public OwnCompany load(String from) {
		DataLoader ownCompanyFileLoader = new FileHandler(from);
		OwnCompany companyData = (OwnCompany) ownCompanyFileLoader.load();

		return companyData;
	}

	@Override
	public void modify(OwnCompany newData) {
		this.name = newData.getName();
		this.address = newData.getAddress();
	}

	@Override
	public void save(String to) {
		DataSaver ownCompanyFileSaver = new FileHandler("src/hu/ak_akademia/book4you/databases/owncompany.bin");
		ownCompanyFileSaver.save(this);
	}
	
	@Override
	public String toString() {
		return "OwnCompany: " + name + " " + address.toString();
	}
}
