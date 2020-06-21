package hu.ak_akademia.book4you.entities.client;

import hu.ak_akademia.book4you.entities.Address;

public class EconomicClient extends Client{

	private String CompanyName;

	public EconomicClient(String companyName, Address address) {
		super(address);
		CompanyName = companyName;
	}

	public String getCompanyName() {
		return CompanyName;
	}
}
