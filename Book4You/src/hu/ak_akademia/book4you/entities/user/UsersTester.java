package hu.ak_akademia.book4you.entities.user;

public class UsersTester {

	public static void main(String[] args) {
		// Új pénztáros hozzáadása
		UsersHandler users = new Users("src/hu/ak_akademia/book4you/databases/users.bin");

		users.add(new Cashier("Pénztáros Gizi", "pg01", "E1234567"));
		users.save();

		// Pénztáros módosítása
		User cashier = users.getUser("pg01");

		User modified = new Cashier("Pénztáros Gizi", "pg01", "E1234560");
		users.modify(cashier, modified);
		users.save();
	}
}
