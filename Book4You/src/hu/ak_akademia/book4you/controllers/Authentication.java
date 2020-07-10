package hu.ak_akademia.book4you.controllers;

import hu.ak_akademia.book4you.entities.user.User;

public interface Authentication {
	public User getUser(String ID);
	public boolean isAccessGranted(User user, String password);
	public String getUserType(User user);
}
