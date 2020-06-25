package hu.ak_akademia.book4you.entities.client;

import java.util.List;

public interface ClientsHandler {
	
	List<Client> loadToList();
	void add(Client newClient);
	void modify(String clientWhoseName); 
	
}
