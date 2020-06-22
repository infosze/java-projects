package hu.ak_akademia.book4you.entities.client;

import java.util.List;

public interface ClientsHandle {
	
	List<Client> loadToList();
	void add(Client newClient);
	void modify(String clientWhoseName); 
	
}
