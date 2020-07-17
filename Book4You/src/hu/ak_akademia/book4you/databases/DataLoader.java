package hu.ak_akademia.book4you.databases;

import java.util.List;

public interface DataLoader <E> {
	List<E> load();
}
