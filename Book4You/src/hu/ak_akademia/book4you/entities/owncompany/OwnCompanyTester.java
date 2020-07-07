package hu.ak_akademia.book4you.entities.owncompany;

import hu.ak_akademia.book4you.entities.Address;
import hu.ak_akademia.book4you.entities.PublicSpaceType;

public class OwnCompanyTester {

	public static void main(String[] args) {
		// adatok beolvasása
		OwnCompanyHandler store = new OwnCompany();

		OwnCompany storeData = store.load("src/hu/ak_akademia/book4you/databases/owncompany.bin");

		System.out.println(storeData.getName());
		System.out.println(storeData.getAddress().getCity());

		// adatok módosítása
		OwnCompany modifiedData = new OwnCompany("NewBook4You",
				new Address("Magyarország", 1037, "Budapest", "Hunor", PublicSpaceType.STREET, 62));
		store.modify(modifiedData);
		store.save("src/hu/ak_akademia/book4you/databases/owncompany.bin");
	}
}
