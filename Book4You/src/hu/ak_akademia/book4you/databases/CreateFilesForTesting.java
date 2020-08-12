package hu.ak_akademia.book4you.databases;

import java.time.LocalDate;
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
		String url1 = "src/hu/ak_akademia/book4you/databases/owncompany.bin";
		String url2 = "src/hu/ak_akademia/book4you/databases/users.bin";
		String url3 = "src/hu/ak_akademia/book4you/databases/clients.bin";
		String url4 = "src/hu/ak_akademia/book4you/databases/certificates.bin";

		Address address1 = new Address("Magyarország", "1037", "Budapest", "Hunor", PublicSpaceType.STREET, "65");
		Address address2 = new Address("Magyarország", "1037", "Budapest", "Hunor", PublicSpaceType.STREET, "100");
		Address address3 = new Address("Magyarország", "1037", "Budapest", "Hunor", PublicSpaceType.STREET, "8");

//		fillOwnCompany(url1);
//		fillUsers(url2);
//		fillClients(url3, address1, address2, address3);
		fillCertificates(url4, address1, address2, address3);

//		loadOwnCompany(url1);
//		loadUsers(url2);
//		loadClients(url3);
//		loadCertificates(url4);
	}

	private static void loadCertificates(String url) {
		DataLoader<Certificate> certificatesFileLoader = new FileHandler<Certificate>(url);

		List<Certificate> certificates = certificatesFileLoader.load();

		for (Certificate certificate : certificates) {
			System.out.println(certificate.getDate() + " " + certificate.getCashier().getName());
		}
	}

	private static void fillCertificates(String url, Address address1, Address address2, Address address3) {
		List<Certificate> certificatesToSave = new ArrayList<>();

		Cashier actualCashier1 = new Cashier("Pénztáros Géza", "pg01", "E1234567");
		Cashier actualCashier2 = new Cashier("Pénztáros Jolán", "pj01", "E1234567");
		Client actualClient1 = new NaturalClient("Vásárló Tamás", "vt01", address1);
		Client actualClient2 = new NaturalClient("Vásárló Balázs", "vb01", address2);
		Client actualClient3 = new EconomicClient("Takarítószolgálat Kft.", "tk01", address3);

		Certificate certificate1 = new Certificate(1, LocalDate.now(), actualCashier1, Direction.INCOME, actualClient1,
				4_000, Title.SELL_BOOK, "Robert C. Martin - Tiszta kód könyv" , 4_000L);
		Certificate certificate2 = new Certificate(2, LocalDate.of(2020, 6, 1), actualCashier1, Direction.INCOME,
				actualClient2, 5_000, Title.SELL_BOOK, "Robert C. Martin - Túlélőkönyv programozóknak",  0L + 4_000L + 5_000L);
		Certificate certificate3 = new Certificate(3, LocalDate.of(2020, 6, 10), actualCashier2, Direction.OUTCOME,
				actualClient3, 3_000, Title.CLEANING, "Üzlet napi takarítása", 0L + 4_000L + 5_000L - 3_000L);

//		System.out.println(certificate1);
//		System.out.println(certificate2);
//		System.out.println(certificate3);
		
		certificatesToSave.add(certificate1);
		certificatesToSave.add(certificate2);
		certificatesToSave.add(certificate3);

		DataSaver<Certificate> certificateFileSaver = new FileHandler<Certificate>(url);
		certificateFileSaver.save(certificatesToSave);
	}

	private static void loadClients(String url) {
		DataLoader<Client> clientsFileLoader = new FileHandler<Client>(url);

		List<Client> clients = clientsFileLoader.load();
		System.out.println(clients.toString());
	}

	private static void fillClients(String url, Address address1, Address address2, Address address3) {
		List<Client> clientsToSave = new ArrayList<>();

		clientsToSave.add(new NaturalClient("Vásárló Tamás", "vt01", address1));
		clientsToSave.add(new NaturalClient("Vásárló Balázs", "vb01", address2));
		clientsToSave.add(new EconomicClient("Takarítószolgálat Kft.", "tk01", address3));

		DataSaver<Client> clientsFileSaver = new FileHandler<Client>(url);
		clientsFileSaver.save(clientsToSave);
	}

	private static void loadUsers(String url) {
		DataLoader<User> usersFileLoader = new FileHandler<User>(url);
		List<User> users = usersFileLoader.load();

		for (User user : users) {
			System.out.println(user.getName() + " " + user.getID());
		}
	}

	private static void fillUsers(String url) {
		List<User> usersToSave = new ArrayList<>();
		usersToSave.add(new Admin("Teszt Elek", "admin", "E1234567"));
		usersToSave.add(new Cashier("Pénztáros Géza", "pg01", "E1234567"));
		usersToSave.add(new Cashier("Pénztáros Jolán", "pj01", "E1234567"));

		DataSaver<User> usersFileSaver = new FileHandler<User>(url);
		usersFileSaver.save(usersToSave);
	}

	private static void loadOwnCompany(String url) {
		DataLoader<Store> bookStoreFileLoader = new FileHandler<Store>(url);
		List<Store> companyData = bookStoreFileLoader.load();

		System.out.println(companyData.get(0).toString());
	}

	private static void fillOwnCompany(String url) {
		Store data = new Store("Book4You",
				new Address("Magyarország", "1037", "Budapest", "Hunor", PublicSpaceType.STREET, "62"));
		List<Store> bookstore = List.of(data);
		DataSaver<Store> bookStoreFileSaver = new FileHandler<Store>(url);
		bookStoreFileSaver.save(bookstore);
	}

}
