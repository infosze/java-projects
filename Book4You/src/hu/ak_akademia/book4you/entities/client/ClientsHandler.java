package hu.ak_akademia.book4you.entities.client;

import java.util.List;

public interface ClientsHandler {
	
	List<Client> load();
	Client getClient(String ID);
	void add(Client client);
	void modify(Client from, Client to);
	void save();
}
