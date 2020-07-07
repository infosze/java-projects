package hu.ak_akademia.book4you.entities.user;

import java.util.ArrayList;
import java.util.List;

public class UsersTester {

	public static void main(String[] args) {
		// Új pénztáros hozzáadása
		UsersHandler users = new Users("src/hu/ak_akademia/book4you/databases/users.bin");

		users.add(new Cashier("Pénztáros Gizi", "pg01", "E1234567"));
		users.save();

		// Pénztáros módosítása
		User cashier = users.getUser("pg01");

		User modified = new Cashier("Pénztáros Gizi", "pg01", "E1234567", false);
		users.modify(cashier, modified);
		users.save();
		
		// Felhasználók listája
		List<User> usersList = users.load();
		
		List<Cashier> cashiers = new ArrayList<>();
		for (User user : usersList) {
			if (user instanceof Cashier) {
				//ha a felhasználó pénztáros akkor belekerül a pénztáros listába
				cashiers.add((Cashier) user);
			}
		}
		
		for (Cashier cashier2 : cashiers) {
			System.out.println(cashier2);
		}
		
		
		
	}
}
