package hu.ak_akademia.book4you.login;

public interface Login {
	void authenticate(String ID, String password) throws Exception;
	void storeSession();
}
