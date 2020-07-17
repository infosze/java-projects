package hu.ak_akademia.book4you.databases;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.List;

public class FileHandler <E> implements DataSaver <E>, DataLoader <E> {
	private String url;

	public FileHandler(String url) {
		this.url = url;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<E> load(){
		ObjectInputStream in;
		List<E> result = Collections.emptyList();
		
		try {
			FileInputStream file = new FileInputStream(url);
			in = new ObjectInputStream(file);
			result = (List<E>) in.readObject();
			in.close();
		} catch (FileNotFoundException e) {
			System.out.println(url + " nem található! A visszatérési érték egy üres lista!");
		} catch (IOException e) {
			System.out.println("Hiba történt a file tartalmának olvasásakor (Talán üres). A visszatérési érték egy üres lista.");
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		
		return result;
	}

	@Override
	public void save(List<E> data) {
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(url));
			out.writeObject(data);
			out.close();
		} catch (IOException e) {
			System.out.println("Hiba: " + e.getMessage());
		}
	}
}
