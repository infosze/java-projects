package hu.ak_akademia.book4you.databases;

import java.util.ArrayList;
import java.util.List;

import hu.ak_akademia.book4you.entities.Address;
import hu.ak_akademia.book4you.entities.PublicSpaceType;
import hu.ak_akademia.book4you.entities.owncompany.OwnCompany;
import hu.ak_akademia.book4you.entities.user.Admin;
import hu.ak_akademia.book4you.entities.user.Cashier;
import hu.ak_akademia.book4you.entities.user.User;

public class CreateFilesForTesting {

	public static void main(String[] args) {
		//owncompany.bin feltöltése teszt adatokkal
		String url1 = "src/hu/ak_akademia/book4you/databases/owncompany.bin";
		OwnCompany data = new OwnCompany("Book4You",
				new Address("Magyarország", 1037, "Budapest", "Hunor", PublicSpaceType.STREET, 62));

		DataSaver ownCompanyFileSaver = new FileHandler("src/hu/ak_akademia/book4you/databases/owncompany.bin");
		ownCompanyFileSaver.save(data);

		
		//owncompany.bin-ből OwnCompany objektumba töltés
		DataLoader ownCompanyFileLoader = new FileHandler(url1);
		OwnCompany companyData = (OwnCompany) ownCompanyFileLoader.load();

		System.out.println(companyData.toString());
		
		
		//users.bin feltöltése teszt adatokkal
		String url2 = "src/hu/ak_akademia/book4you/databases/users.bin";
		List<User> usersToSave =  new ArrayList<>();
		usersToSave.add(new Admin("Teszt Elek", "admin", "E1234567"));
		usersToSave.add(new Cashier("Pénztáros Géza", "pg01", "E1234567"));
		usersToSave.add(new Cashier("Pénztáros Jolán", "pj01", "E1234567"));
		
		DataSaver usersFileSaver = new FileHandler(url2);
		usersFileSaver.save(usersToSave);

		
		
		//users.bin-ből ArrayList-be töltés
		String url3 = "src/hu/ak_akademia/book4you/databases/users.bin";
		DataLoader usersFileLoader = new FileHandler(url3);
		List<User> usersToLoad = (List<User>) usersFileLoader.load();
		
		System.out.println(usersToLoad.toString());

	}

}
