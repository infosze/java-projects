package hu.ak_akademia.book4you.entities.user;

import java.util.List;

public class Users implements UsersHandler{
	private List<User> usersList;

	public Users() {
		this.usersList = loadToList();
	}
	

	@Override
	public List<User> loadToList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(User newUser) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modify(String identifier) {
		// TODO Auto-generated method stub
		
	}
}
