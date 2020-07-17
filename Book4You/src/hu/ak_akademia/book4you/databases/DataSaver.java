package hu.ak_akademia.book4you.databases;

import java.util.List;

public interface DataSaver <E> {
	void save(List<E> data);
}
