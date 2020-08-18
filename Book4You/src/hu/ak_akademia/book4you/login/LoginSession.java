package hu.ak_akademia.book4you.login;

import hu.ak_akademia.book4you.entities.user.Admin;
import hu.ak_akademia.book4you.entities.user.Cashier;
import hu.ak_akademia.book4you.entities.user.User;
import hu.ak_akademia.book4you.entities.user.Users;
import hu.ak_akademia.book4you.validation.MyException;

public class LoginSession implements Login {
	private Users users;
	private User user;

	public LoginSession(Users usersDB) {
		this.users = usersDB;
	}

	@Override
	public void authenticate(String ID, String password) throws MyException {
		checkValues(ID, password);
		findAndSetUser(ID);
		checkUserStatus();
		checkAccess(password);
	}

	private void checkValues(String ID, String password) throws MyException {
		if (ID.isEmpty() || password.isEmpty())
			throw new MyException("Hiányzó mező(k)!");
	}

	private void findAndSetUser(String ID) throws MyException {
		this.user = users.getUser(ID);

		if (user == null)
			throw new MyException("Nincs ilyen felhasználó!");
	}

	private void checkUserStatus() throws MyException {
		if (user instanceof Admin)
			return;

		if (user instanceof Cashier) {
			if (((Cashier) user).isActive())
				return;
		}

		throw new MyException("Inaktív felhasználó!");
	}

	private void checkAccess(String password) throws MyException {
		if (!user.isPasswordMatch(password))
			throw new MyException("Helytelen jelszó!");
	}

	@Override
	public void storeSession() {
		Session.setUser(user);
	}
}
