package hu.ak_akademia.book4you.entities.client;

import java.util.List;
import hu.ak_akademia.book4you.databases.DataLoader;
import hu.ak_akademia.book4you.databases.DataSaver;
import hu.ak_akademia.book4you.databases.FileHandler;

public class Clients implements ClientsHandler {
	private List<Client> clientsList;
	private final String url;

	public Clients(String url) {
		this.url = url;
		this.clientsList = load();
	}

	@Override
	public List<Client> load() {
		DataLoader clientsFileLoader = new FileHandler(url);

		return (List<Client>) clientsFileLoader.load();
	}

	@Override
	public void add(Client client) {
		clientsList.add(client);
	}

	@Override
	public void modify(Client from, Client to) {
		clientsList.set(getClientIndex(from), to);
	}

	@Override
	public void save() {
		DataSaver clientsFileSaver = new FileHandler(url);
		clientsFileSaver.save(clientsList);
	}

	@Override
	public Client getClient(String ID) {
		Client result = null;
		for (Client client : clientsList) {
			if (client.getIdentifier().equals(ID)) {
				result = client;
			}
		}
		return result;
	}
	
	private int getClientIndex(Client client) {
		return clientsList.indexOf(client);
	}
}
