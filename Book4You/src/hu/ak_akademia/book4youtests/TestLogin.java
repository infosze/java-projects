package hu.ak_akademia.book4youtests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import hu.ak_akademia.book4you.entities.user.Cashier;
import hu.ak_akademia.book4you.entities.user.User;
import hu.ak_akademia.book4you.entities.user.Users;
import hu.ak_akademia.book4you.login.LoginSession;
import hu.ak_akademia.book4you.validation.MyException;

class TestLogin {
	
	LoginSession loginSession;
	
	@BeforeEach
	public void init() {
		List<User> testUsers = new ArrayList<>();
		testUsers.add(new Cashier("cashier1", "c01", "password", true));
		testUsers.add(new Cashier("cashier2", "c02", "password", false));
		
		Users users = new Users(testUsers);
		loginSession = new LoginSession(users);
	}
	
	@Test
	void TestEmptyID() {
	    MyException exception = assertThrows(MyException.class, () ->
	        loginSession.authenticate("", "password"));
	    assertEquals("ID mező üres!", exception.getMessage());
	}
	
	@Test
	void TestEmptyPassword() {
		MyException exception = assertThrows(MyException.class, () ->
		loginSession.authenticate("c01", ""));
		assertEquals("Jelszó mező üres!", exception.getMessage());
	}
	
	@Test
	void TestNoSuchUser() {
		MyException exception = assertThrows(MyException.class, () ->
		loginSession.authenticate("nobody", "password"));
		assertEquals("Nincs ilyen felhasználó!", exception.getMessage());
	}
	
	@Test
	void TestInaktivUser() {
		MyException exception = assertThrows(MyException.class, () ->
		loginSession.authenticate("c02", "password"));
		assertEquals("Inaktív felhasználó!", exception.getMessage());
	}
	
	@Test
	void TestWrongPassword() {
		MyException exception = assertThrows(MyException.class, () ->
		loginSession.authenticate("c01", "wrongpassword"));
		assertEquals("Helytelen jelszó!", exception.getMessage());
	}
	
	

}
