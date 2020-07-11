package hu.ak_akademia.book4you.login;

import hu.ak_akademia.book4you.controllers.Session;
import hu.ak_akademia.book4you.entities.user.Admin;
import hu.ak_akademia.book4you.entities.user.Cashier;
import hu.ak_akademia.book4you.entities.user.User;
import hu.ak_akademia.book4you.entities.user.Users;

public class Login {
	private String ID;
	private String password;
	private Users users;
	private User user;

	public Login(String ID, String password) {
		this.ID = ID;
		this.password = password;
		this.users = new Users("src/hu/ak_akademia/book4you/databases/users.bin");
		this.user = users.getUser(ID);
	}

	public boolean isAuthorized() throws MyException {

		if (!isRequiredFieldsSpecified())
			throw new MyException("Üres mező(k)!");
		else if (user == null)
			throw new MyException("Nincs ilyen felhasználó!");
		else if (!isAktiv())
			throw new MyException("Inaktív felhasználó");
		else if (!isAccessGranted())
			throw new MyException("Belépés megtagadva!");

		return true;
	}

	public void setupSession(User user) {
		Session.setUser(user);
	}

	private boolean isAktiv() {
		if (user instanceof Admin) {
			return true;
		} else if (user instanceof Cashier) {
			return ((Cashier) user).isActive();
		}
		return false;
	}

	private boolean isRequiredFieldsSpecified() {
		return (!ID.isEmpty() || ID != null) && (password != null || !password.isEmpty());
	}

	private boolean isAccessGranted() {
		return user.isPasswordMatch(password);
	}

}
