package dao;

import entity.User;

public interface UserDAO {
	
	
	public User findUserByName(String userName);
	
	public int addUser(User user);
	
	public int editUser(User user);
	
	public int deleteUser(int id);
	
	public int updateUserPassword();

}
