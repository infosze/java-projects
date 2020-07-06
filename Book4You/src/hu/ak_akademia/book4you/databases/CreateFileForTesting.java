package hu.ak_akademia.book4you.databases;

import java.util.ArrayList;
import java.util.List;

import hu.ak_akademia.book4you.entities.Address;
import hu.ak_akademia.book4you.entities.PublicSpaceType;
import hu.ak_akademia.book4you.entities.owncompany.OwnCompany;
import hu.ak_akademia.book4you.entities.user.Admin;
import hu.ak_akademia.book4you.entities.user.Cashier;
import hu.ak_akademia.book4you.entities.user.User;

public class CreateFileForTesting {

	public static void main(String[] args) {
//		String url = "src/hu/ak_akademia/book4you/databases/owncompany.bin";
//		OwnCompany data = new OwnCompany("Book4You",
//				new Address("Magyarország", 1037, "Budapest", "Hunor", PublicSpaceType.STREET, 62));
//
//		DataSaver ownCompanyFileSaver = new OwnCompanyFileHandler("src/hu/ak_akademia/book4you/databases/owncompany.bin");
//		ownCompanyFileSaver.save(data);
//
//		DataLoader ownCompanyFileLoader = new OwnCompanyFileHandler(url);
//		OwnCompany companyData = (OwnCompany) ownCompanyFileLoader.load();
//
//		System.out.println(companyData.toString());
		
		
		String url = "src/hu/ak_akademia/book4you/databases/users.bin";
		List<User> users =  new ArrayList<>();
		users.add(new Admin("Teszt Elek", "admin", "E1234567"));
		users.add(new Cashier("Pénztáros Géza", "pg01", "E1234567"));
		users.add(new Admin("Pénztáros Jolán", "pj01", "E1234567"));
		
		
		DataSaver usersFileSaver = new UsersFileHandler(url);
		usersFileSaver.save(users);

	}

}
