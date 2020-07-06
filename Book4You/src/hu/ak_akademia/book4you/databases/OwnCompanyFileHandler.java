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

public class OwnCompanyFileHandler implements DataSaver, DataLoader {
	private String url;
	private OwnCompany data;

	public OwnCompanyFileHandler(String url, OwnCompany data) {
		this.url = url;
		this.data = data;
	}

	@Override
	public Object load() throws ClassNotFoundException {
		ObjectInputStream in;
		OwnCompany obj = null;
		try {
			in = new ObjectInputStream(new FileInputStream(url));
			obj = (OwnCompany) in.readObject();
		} catch (FileNotFoundException e) {
			System.out.println(url + " nem található!");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	public void save() {
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(url));
			out.writeObject(data);
			out.close();
			System.out.println(url + "-be adatok kiírva.");
		} catch (FileNotFoundException e) {
			System.out.println(url + " nem található!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
