package hu.ak_akademia.book4you.entities.user;

import java.util.List;

public interface UsersHandle {
	
	List<User> loadToList();
	void add(User newUser);
	void modify(String identifier);
	
}
