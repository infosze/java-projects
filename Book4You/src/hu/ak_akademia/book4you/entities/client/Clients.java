package hu.ak_akademia.book4you.entities.client;

import java.util.List;


public class Clients implements ClientsHandler{
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
		clientsList.add(newClient);
		
	}

	@Override
	public void modify(String clientWhoseName) {
		// TODO Auto-generated method stub
		
	}
}
