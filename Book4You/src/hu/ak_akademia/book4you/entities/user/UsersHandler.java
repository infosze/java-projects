package hu.ak_akademia.book4you.entities.user;

import java.util.List;

public interface UsersHandler {
	
	List<User> load();
	User getUser(String ID);
	void add(User user);
	void modify(User from, User to);
	void save();
}
