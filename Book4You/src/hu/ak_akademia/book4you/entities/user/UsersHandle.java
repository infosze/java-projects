package hu.ak_akademia.book4you.entities.user;

import java.util.List;

public interface UsersHandle {
	
	List<User> generate();
	void add();
	void modify();
	
}
