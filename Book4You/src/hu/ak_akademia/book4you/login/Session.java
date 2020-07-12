package hu.ak_akademia.book4you.login;

import hu.ak_akademia.book4you.entities.user.User;

public class Session {
	private static User user;

	public static User getUser() {
		return user;
	}

	public static void setUser(User user) {
		Session.user = user;
	}
}
