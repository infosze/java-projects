package hu.ak_akademia.book4you.entities.client;

import java.util.List;


public class Clients implements ClientsHandle{
	private List<Client> clientsList;
	
	public Clients(List<Client> clientsList) {
		super();
		this.clientsList = loadToList();
	}

	@Override
	public List<Client> loadToList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(Client newClient) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modify() {
		// TODO Auto-generated method stub
		
	}
}
