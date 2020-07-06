package hu.ak_akademia.book4you.databases;

import hu.ak_akademia.book4you.entities.Address;
import hu.ak_akademia.book4you.entities.PublicSpaceType;
import hu.ak_akademia.book4you.entities.owncompany.OwnCompany;

public class CreateFileForTesting {

	public static void main(String[] args) {
		OwnCompany data = new OwnCompany("Book4You", new Address("Magyarország", 1037, "Budapest", "Hunor", PublicSpaceType.STREET, 62));
		DataSaver fileHandler = new OwnCompanyFileHandler("src/hu/ak_akademia/book4you/databases/owncompany.bin", data);
		fileHandler.save();

	}

}
