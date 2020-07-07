package hu.ak_akademia.book4you.entities.client;

import hu.ak_akademia.book4you.entities.Address;
import hu.ak_akademia.book4you.entities.PublicSpaceType;

public class ClientsTester {
	// Ez a file csak tesztelési célokból/a működés szemléltetése céljából született
	// meg.
	// A végső változtban nem lesz bent.

	public static void main(String[] args) {

		// Új ügyfél hozzáadása
		ClientsHandler clients = new Clients("src/hu/ak_akademia/book4you/databases/clients.bin");

		Address address1 = new Address("Magyarország", 1037, "Budapest", "Hunor", PublicSpaceType.STREET, 15);
		clients.add(new NaturalClient("Vásárló Viktor", "vv01", address1));
		clients.save();

		// Ügyfél módosítása
		Client client = clients.getClient("vv01");

		Client modified = new NaturalClient("Vásárló Vivien", "vv01", address1);
		modified.setVIP(true);
		clients.modify(client, modified);
		clients.save();
	}
}
