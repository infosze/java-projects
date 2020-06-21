package hu.ak_akademia.book4you.entities.client;

import java.util.List;

public interface ClientsHandle {
	
	List<Client> generate();
	void add();
	void modify();
	
}
