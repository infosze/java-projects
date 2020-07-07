package hu.ak_akademia.book4you.databases;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import hu.ak_akademia.book4you.entities.Address;
import hu.ak_akademia.book4you.entities.PublicSpaceType;
import hu.ak_akademia.book4you.entities.owncompany.OwnCompany;

public class FileHandler implements DataSaver, DataLoader {
	private String url;

	public FileHandler(String url) {
		this.url = url;
	}

	@Override
	public Object load() {
		ObjectInputStream in;
		Object obj = null;
		
		try {
			in = new ObjectInputStream(new FileInputStream(url));
			obj = in.readObject();
			in.close();
		} catch (FileNotFoundException e) {
			System.out.println(url + " nem található!");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return obj;
	}

	@Override
	public void save(Object obj) {
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(url));
			out.writeObject(obj);
			out.close();
			System.out.println(url + "-be adatok kiírva.");
		} catch (FileNotFoundException e) {
			System.out.println(url + " nem található!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
