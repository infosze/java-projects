package hu.ak_akademia.book4you.entities.bookstore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import hu.ak_akademia.book4you.databases.DataLoader;
import hu.ak_akademia.book4you.databases.DataSaver;
import hu.ak_akademia.book4you.databases.FileHandler;

public class BookStore implements StoreHandler, Serializable {

	private static final long serialVersionUID = 1L;
	private List<Store> storeList;
	private final String url;

	public BookStore(String url) {
		this.url = url;
		DataLoader<Store> bookStoreFileLoader = new FileHandler<Store>(url);
		this.storeList = bookStoreFileLoader.load();
	}

	@Override
	public List<Store> load() {
		return storeList;
	}

	@Override
	public void modify(Store newData) throws IllegalStateException{
		if (storeList.isEmpty()) throw new IllegalStateException();
		
		this.storeList = new ArrayList<>();
		this.storeList.add(newData);
	}

	@Override
	public void save() {
		DataSaver<Store> bookStoreFileSaver = new FileHandler<Store>(url);
		bookStoreFileSaver.save(storeList);
	}
}
