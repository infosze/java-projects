package hu.ak_akademia.book4you.login;

import hu.ak_akademia.book4you.validation.MyException;

public interface Login {
	void authenticate(String ID, String password) throws MyException;
	void storeSession();
}
