package hu.ak_akademia.book4you.databases;

import java.util.ArrayList;
import java.util.List;

import hu.ak_akademia.book4you.entities.Address;
import hu.ak_akademia.book4you.entities.PublicSpaceType;
import hu.ak_akademia.book4you.entities.bookstore.Store;
import hu.ak_akademia.book4you.entities.certificate.Certificate;
import hu.ak_akademia.book4you.entities.certificate.Direction;
import hu.ak_akademia.book4you.entities.certificate.Title;
import hu.ak_akademia.book4you.entities.client.Client;
import hu.ak_akademia.book4you.entities.client.EconomicClient;
import hu.ak_akademia.book4you.entities.client.NaturalClient;
import hu.ak_akademia.book4you.entities.user.Admin;
import hu.ak_akademia.book4you.entities.user.Cashier;
import hu.ak_akademia.book4you.entities.user.User;

public class CreateFilesForTesting {

	public static void main(String[] args) {
		// owncompany.bin feltöltése teszt adatokkal
		String url1 = "src/hu/ak_akademia/book4you/databases/owncompany.bin";
		Store data = new Store("Book4You", new Address("Magyarország", 1037, "Budapest", "Hunor", PublicSpaceType.STREET, 62));
		List<Store> bookstore = List.of(data);
		DataSaver<Store> bookStoreFileSaver = new FileHandler<Store>(url1);
		bookStoreFileSaver.save(bookstore);

		// owncompany.bin-ből OwnCompany objektumba töltés
		DataLoader<Store> bookStoreFileLoader = new FileHandler<Store>(url1);
		List<Store> companyData = bookStoreFileLoader.load();

		System.out.println(companyData.get(0).toString());

		// users.bin feltöltése teszt adatokkal
		String url2 = "src/hu/ak_akademia/book4you/databases/users.bin";
		List<User> usersToSave = new ArrayList<>();
		usersToSave.add(new Admin("Teszt Elek", "admin", "E1234567"));
		usersToSave.add(new Cashier("Pénztáros Géza", "pg01", "E1234567"));
		usersToSave.add(new Cashier("Pénztáros Jolán", "pj01", "E1234567"));

		DataSaver<User> usersFileSaver = new FileHandler<User>(url2);
		usersFileSaver.save(usersToSave);

		// users.bin-ből ArrayList-be töltés
		DataLoader<User> usersFileLoader = new FileHandler<User>(url2);
		List<User> users = usersFileLoader.load();
		
		for (User user : users) {
			System.out.println(user.getName() + " " + user.getID());
		}
		
		// clients.bin feltöltése teszt adatokkal
		String url3 = "src/hu/ak_akademia/book4you/databases/clients.bin";
		List<Client> clientsToSave = new ArrayList<>();
		
		Address address1 = new Address("Magyarország", 1037, "Budapest", "Hunor", PublicSpaceType.STREET, 65);
		Address address2 = new Address("Magyarország", 1037, "Budapest", "Hunor", PublicSpaceType.STREET, 100);
		Address address3 = new Address("Magyarország", 1037, "Budapest", "Hunor", PublicSpaceType.STREET, 8);
		
		clientsToSave.add(new NaturalClient("Vásárló Tamás","vt01", address1));
		clientsToSave.add(new NaturalClient("Vásárló Balázs","vb01", address2));
		clientsToSave.add(new EconomicClient("Takarítószolgálat Kft.","tk01", address3));
		
		DataSaver<Client> clientsFileSaver = new FileHandler<Client>(url3);
		clientsFileSaver.save(clientsToSave);
		
		// clients.bin-ből ArrayList-be töltés
		DataLoader<Client> clientsFileLoader = new FileHandler<Client>(url3);
		
		List<Client> clients = clientsFileLoader.load();
		System.out.println(clients.toString());
		
		// certificates.bin feltöltése teszt adatokkal
		String url4 = "src/hu/ak_akademia/book4you/databases/certificates.bin";
		List<Certificate> certificatesToSave = new ArrayList<>();
		
		Cashier actualCashier1 = new Cashier("Pénztáros Géza", "pg01", "E1234567");
		Cashier actualCashier2 = new Cashier("Pénztáros Jolán", "pj01", "E1234567");
		Client actualClient1 = new NaturalClient("Vásárló Tamás","vt01", address1);
		Client actualClient2 = new NaturalClient("Vásárló Balázs","vb01", address2);
		Client actualClient3 = new EconomicClient("Takarítószolgálat Kft.","tk01", address3);
		
		Certificate certificate1 = new Certificate(1, actualCashier1, Direction.INCOME, actualClient1, 4_000, Title.BUY_BOOK, "Robert C. Martin - Tiszta kód könyv");
		Certificate certificate2 = new Certificate(2, actualCashier1, Direction.INCOME, actualClient2, 5_000, Title.BUY_BOOK, "Robert C. Martin - Túlélőkönyv programozóknak");
		Certificate certificate3 = new Certificate(3, actualCashier2, Direction.OUTCOME, actualClient3, 3_000, Title.CLEANING, "Üzlet napi takarítása");
		
		certificatesToSave.add(certificate1);
		certificatesToSave.add(certificate2);
		certificatesToSave.add(certificate3);
		
		DataSaver<Certificate> certificateFileSaver = new FileHandler<Certificate>(url4);
		certificateFileSaver.save(certificatesToSave);
		
		// certificates.bin-ből ArrayList-be töltés
		DataLoader<Certificate> certificatesFileLoader = new FileHandler<Certificate>(url4);
		
		List<Certificate> certificates = certificatesFileLoader.load();
		
		for (Certificate certificate : certificates) {
			System.out.println(certificate.getDate() + " " + certificate.getCashier().getName());
		}
	}
}
