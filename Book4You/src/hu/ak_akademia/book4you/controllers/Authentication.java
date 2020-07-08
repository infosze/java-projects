package hu.ak_akademia.book4you.controllers;

import hu.ak_akademia.book4you.entities.user.User;

public interface Authentication {
	public boolean isAccessGranted(String ID, String password);
	public String getUserType(User user);
}
