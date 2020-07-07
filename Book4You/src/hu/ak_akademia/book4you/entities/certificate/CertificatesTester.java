package hu.ak_akademia.book4you.entities.certificate;

import java.util.List;
import hu.ak_akademia.book4you.entities.Address;
import hu.ak_akademia.book4you.entities.PublicSpaceType;
import hu.ak_akademia.book4you.entities.client.Client;
import hu.ak_akademia.book4you.entities.client.NaturalClient;
import hu.ak_akademia.book4you.entities.user.Cashier;

public class CertificatesTester {

	public static void main(String[] args) {
		CertificatesHandler certificates = new Certificates("src/hu/ak_akademia/book4you/databases/certificates.bin");

		// Bizonylatok listája
		List<Certificate> certificatesList = certificates.load();
		for (Certificate certificate : certificatesList) {
			System.out.println(certificate.getDate() + " " + certificate.getAmount() + " Ft");
		}

		// Bizonylat hozzáadása
		Address address1 = new Address("Magyarország", 1037, "Budapest", "Hunor", PublicSpaceType.STREET, 65);
		Cashier actualCashier = new Cashier("Pénztáros Géza", "pg01", "E1234567");
		Client actualClient = new NaturalClient("Vásárló Tamás", "vt01", address1);

		certificates.add(new Certificate(10, actualCashier, Direction.INCOME, actualClient, 2_000, Title.SELL_BOOK,
				"CSS zsebkönyv"));
		certificates.save();

	}

}
