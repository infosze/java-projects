package hu.ak_akademia.book4you.entities.user;

import java.util.List;
import hu.ak_akademia.book4you.databases.DataLoader;
import hu.ak_akademia.book4you.databases.DataSaver;
import hu.ak_akademia.book4you.databases.FileHandler;

public class Users implements UsersHandler {
	private final String url;
	private List<User> usersList;

	public Users(String url) {
		this.url = url;
		this.usersList = load();
	}

	@Override
	public List<User> load() {
		DataLoader usersFileLoader = new FileHandler(url);

		return (List<User>) usersFileLoader.load();
	}

	@Override
	public User getUser(String ID) {
		User result = null;
		for (User user : usersList) {
			if (user.getIdentifier().equals(ID)) {
				result = user;
			}
		}
		return result;
	}

	@Override
	public void add(User user) {
		usersList.add(user);
	}

	@Override
	public void modify(User from, User to) {
		int userIndex = getUserIndex(from);
		
		if (userIndex >= 0) {
			usersList.set(userIndex, to);
		} else {
			System.out.println("A módosítandó objektum nem található!");
		}
	}

	@Override
	public void save() {
		DataSaver usersFileSaver = new FileHandler(url);
		usersFileSaver.save(usersList);
	}

	private int getUserIndex(User user) {
		return usersList.indexOf(user);
	}
}
