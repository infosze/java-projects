package hu.ak_akademia.book4you.entities.bookstore;

import java.util.List;

public interface StoreHandler {
	List<Store> load();
	void modify(Store newData);
	void save();
}
